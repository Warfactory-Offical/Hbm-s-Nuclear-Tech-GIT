package com.hbm.animloader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.Level;
import org.lwjgl.opengl.GL11;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.hbm.main.MainRegistry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ColladaLoader {
	//Drillgon200: This code is only slightly less terrible than the first time. I hate XML.
	
	/*
	 * My attempt at making a collada loader.
	 * Some things to note: You can't use child of constraints with it with complete accuracy, 
	 * as this will break the linear interpolation and I don't know how to fix it
	 * To get around this, you can put multiple objects with different parents or origins and toggle their visibility.
	 * It's hacky, but it works, at least if you don't need an object affected by multiple bones at the same time.
	 */
	
	public static AnimatedModel load(final ResourceLocation file) {
		return load(file, false);
	}
	
	public static AnimatedModel load(final ResourceLocation file, final boolean flipV) {
		final IResource res;
		try {
			res = Minecraft.getMinecraft().getResourceManager().getResource(file);
			final Document doc;
			try {
				doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(res.getInputStream());
				return parse(doc.getDocumentElement(), flipV);
			} catch(final SAXException e) {
				e.printStackTrace();
			} catch(final IOException e) {
				e.printStackTrace();
			} catch(final ParserConfigurationException e) {
				e.printStackTrace();
			}
		} catch(final IOException e) {
			e.printStackTrace();
		}
		MainRegistry.logger.log(Level.ERROR, "FAILED TO LOAD MODEL: " + file);
		return null;
	}
	
	//Model loading section
	
	private static AnimatedModel parse(final Element root, final boolean flipV){
		//Should get the first bone
		final Element scene = getFirstElement(root.getElementsByTagName("library_visual_scenes").item(0));
		final AnimatedModel structure = new AnimatedModel(){
			@Override
			protected void renderWithIndex(final float inter, final int firstIndex, final int nextIndex, final float diffN, final IAnimatedModelCallback c) {
				for(final AnimatedModel m : children){
					m.renderWithIndex(inter, firstIndex, nextIndex, diffN, c);
				}
			}
			@Override
			public void render() {
				for(final AnimatedModel m : children){
					m.render();
				}
			}
		};
		for(final Element node : getChildElements(scene)){
			if(node.getElementsByTagName("instance_geometry").getLength() > 0){
				structure.children.add(parseStructure(node));
			}
		}
		final Map<String, Integer> geometry = parseGeometry((Element)root.getElementsByTagName("library_geometries").item(0), flipV);
		addGeometry(structure, geometry);
		setAnimationController(structure, new AnimationController());
		
		return structure;
	}
	
	private static void setAnimationController(final AnimatedModel model, final AnimationController control){
		model.controller = control;
		for(final AnimatedModel m : model.children)
			setAnimationController(m, control);
	}
	
	private static Element getFirstElement(final Node root){
		final NodeList nodes = root.getChildNodes();
		for(int i = 0; i < nodes.getLength(); i ++){
			final Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				return (Element)node;
			}
		}
		return null;
	}
	
	private static List<Element> getElementsByName(final Element e, final String name){
		final List<Element> elements = new ArrayList<Element>();
		final NodeList n = e.getChildNodes();
		for(int i = 0; i < n.getLength(); i ++){
			final Node node = n.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(name)){
				elements.add((Element) node);
			}
		}
		return elements;
	}
	
	private static List<Element> getChildElements(final Element e){
		final List<Element> elements = new ArrayList<Element>();
		if(e == null)
			return elements;
		final NodeList n = e.getChildNodes();
		for(int i = 0; i < n.getLength(); i ++){
			final Node node = n.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				elements.add((Element) node);
			}
		}
		return elements;
	}
	
	private static AnimatedModel parseStructure(final Element root){
		final AnimatedModel model = new AnimatedModel();
		model.name = root.getAttribute("name");
		
		final NodeList children = root.getChildNodes();
		for(int i = 0; i < children.getLength(); i ++){
			final Node node = children.item(i);
			if(node.getNodeType() != Node.ELEMENT_NODE)
				continue;
			final Element ele = (Element) node;
			if("transform".equals(ele.getAttribute("sid"))){
				//Do I even need to flip the matrix here? No idea!
				model.transform = flipMatrix(parseFloatArray(ele.getTextContent()));
				model.hasTransform = true;
			} else if("instance_geometry".equals(ele.getTagName())){
				model.geo_name = ele.getAttribute("url").substring(1);
			} else if(ele.getElementsByTagName("instance_geometry").getLength() > 0){
				final AnimatedModel childModel = parseStructure(ele);
				childModel.parent = model;
				model.children.add(childModel);
			}
		}
		return model;
	}
	
	/*private static void addStructureChildren(Element root, AnimatedModel model){
		NodeList children = root.getChildNodes();
		for(int i = 0; i < children.getLength(); i ++){
			Node node = children.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element element = (Element) node;if(getElementsByName(element, "instance_geometry").size() > 0){
					addGeoNamesToModel(element, model);
				} else if(getElementsByName(element, "node").size() > 0 && "JOINT".equals(((Element)getElementsByName(element, "node").get(0)).getAttribute("type"))){
					AnimatedModel m = parseStructure(element);
					model.children.add(m);
					m.parent = model;
				}
			}
		}
	}
	
	private static void addGeoNamesToModel(Element root, AnimatedModel model){
		List<Element> geo_names = getElementsByName(root, "instance_geometry");
		for(Element e : geo_names){
			String name = e.getAttribute("url").substring(1);
			model.geo_names.add(name);
		}
	}*/
	
	
	//Geometry loading section
	
	//Map of geometry name to display list id
	private static Map<String, Integer> parseGeometry(final Element root, final boolean flipV){
		final Map<String, Integer> allGeometry = new HashMap<String, Integer>();
		for(final Element e : getElementsByName(root, "geometry")){
			final String name = e.getAttribute("id");
			final Element mesh = getElementsByName(e, "mesh").get(0);
			
			float[] positions = new float[0];
			float[] normals = new float[0];
			float[] texCoords = new float[0];
			int[] indices = new int[0];
			
			for(final Element section : getChildElements(mesh)){
				final String id = section.getAttribute("id");
				if(id.endsWith("mesh-positions")){
					positions = parsePositions(section);
				} else if(id.endsWith("mesh-normals")){
					normals = parseNormals(section);
				} else if(id.endsWith("mesh-map-0")){
					texCoords = parseTexCoords(section);
				} else if(section.getNodeName().equals("triangles")){
					indices = ArrayUtils.addAll(indices, parseIndices(section));
				}
			}
			if(positions.length == 0)
				continue;
			
			final int displayList = GL11.glGenLists(1);
			GL11.glNewList(displayList, GL11.GL_COMPILE);
			final BufferBuilder buf = Tessellator.getInstance().getBuffer();
			buf.begin(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION_TEX_NORMAL);
			if(indices.length > 0){
				for(int i = 0; i < indices.length; i += 3){
					float v = texCoords[indices[i+2]*2+1];
					if(flipV){
						v = 1-v;
					}
					buf.pos(positions[indices[i]*3], positions[indices[i]*3+1], positions[indices[i]*3+2])
					.tex(texCoords[indices[i+2]*2], v)
					.normal(normals[indices[i+1]*3], normals[indices[i+1]*3+1], normals[indices[i+1]*3+2])
					.endVertex();
				}
			} else {
				
			}
			
			Tessellator.getInstance().draw();
			GL11.glEndList();
			
			allGeometry.put(name, displayList);
		}
		return allGeometry;
	}
	
	private static float[] parsePositions(final Element root){
		final String content = root.getElementsByTagName("float_array").item(0).getTextContent();
		return parseFloatArray(content);
	}
	
	private static float[] parseNormals(final Element root){
		final String content = root.getElementsByTagName("float_array").item(0).getTextContent();
		return parseFloatArray(content);
	}
	
	private static float[] parseTexCoords(final Element root){
		final String content = root.getElementsByTagName("float_array").item(0).getTextContent();
		return parseFloatArray(content);
	}
	
	private static int[] parseIndices(final Element root){
		final String content = root.getElementsByTagName("p").item(0).getTextContent();
		return parseIntegerArray(content);
	}
	
	private static float[] parseFloatArray(final String s){
		if(s.isEmpty()){
			return new float[0];
		}
		final String[] numbers = s.split(" ");
		final float[] arr = new float[numbers.length];
		for(int i = 0; i < numbers.length; i ++){
			arr[i] = Float.parseFloat(numbers[i]);
		}
		return arr;
	}
	private static int[] parseIntegerArray(final String s){
		final String[] numbers = s.split(" ");
		final int[] arr = new int[numbers.length];
		for(int i = 0; i < numbers.length; i ++){
			arr[i] = Integer.parseInt(numbers[i]);
		}
		return arr;
	}
	
	private static void addGeometry(final AnimatedModel m, final Map<String, Integer> geometry){
		if(!"".equals(m.geo_name) && geometry.containsKey(m.geo_name))
			m.callList = geometry.get(m.geo_name);
		else {
			m.hasGeometry = false;
			m.callList = -1;
		}
		for(final AnimatedModel child : m.children){
			addGeometry(child, geometry);
		}
	}
	
	
	
	
	//Animation loading section
	public static Animation loadAnim(final int length, final ResourceLocation file){
		final IResource res;
		try {
			res = Minecraft.getMinecraft().getResourceManager().getResource(file);
			final Document doc;
			try {
				doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(res.getInputStream());
				return parseAnim(doc.getDocumentElement(), length);
			} catch(final SAXException e) {
				e.printStackTrace();
			} catch(final IOException e) {
				e.printStackTrace();
			} catch(final ParserConfigurationException e) {
				e.printStackTrace();
			}
		} catch(final IOException e) {
			e.printStackTrace();
		}
		MainRegistry.logger.log(Level.ERROR, "FAILED TO LOAD MODEL: " + file);
		return null;
	}
	
	private static Animation parseAnim(final Element root, final int length){
		final Element anim_section = (Element)root.getElementsByTagName("library_animations").item(0);
		final Animation anim = new Animation();
		anim.length = length;
		for(final Element e : getChildElements(anim_section)){
			if("animation".equals(e.getNodeName())){
				final String name = e.getAttribute("name");
				Transform[] t = null;
				final List<Element> elements2 = getChildElements(e);
				if(elements2.isEmpty()){
					continue;
				}
				for(final Element e2 : elements2){
					if(e2.getAttribute("id").endsWith("transform")){
						t = parseTransforms(e2);
					} else if(e2.getAttribute("id").endsWith("hide_viewport")){
						setViewportHiddenKeyframes(t, e2);
					}
				}
				anim.objectTransforms.put(name, t);
				anim.numKeyFrames = t.length;
			}
		}
		return anim;
	}
	
	private static Transform[] parseTransforms(final Element root){
		final String output = getOutputLocation(root);
		for(final Element e : getChildElements(root)){
			if(e.getAttribute("id").equals(output)){
				return parseTransformsFromText(e.getElementsByTagName("float_array").item(0).getTextContent());
			}
		}
		System.out.println("Failed to parse transforms! This will not work!");
		System.out.println("Node name: " + root.getTagName());
		return null;
	}
	
	private static void setViewportHiddenKeyframes(final Transform[] t, final Element root){
		final String output = getOutputLocation(root);
		for(final Element e : getChildElements(root)){
			if(e.getAttribute("id").equals(output)){
				final int[] hiddenFrames = parseIntegerArray(e.getElementsByTagName("float_array").item(0).getTextContent());
				for(int i = 0; i < hiddenFrames.length; i ++){
					t[i].hidden = hiddenFrames[i] > 0;
				}
			}
		}
	}
	
	private static String getOutputLocation(final Element root){
		final Element sampler = (Element) root.getElementsByTagName("sampler").item(0);
		for(final Element e : getChildElements(sampler)){
			if("OUTPUT".equals(e.getAttribute("semantic"))){
				return e.getAttribute("source").substring(1);
			}
		}
		return null;
	}
	
	private static Transform[] parseTransformsFromText(final String data){
		final float[] floats = parseFloatArray(data);
		final Transform[] transforms = new Transform[floats.length/16];
		for(int i = 0; i < floats.length/16; i++){
			final float[] rawTransform = new float[16];
            System.arraycopy(floats, i * 16 + 0, rawTransform, 0, 16);
			transforms[i] = new Transform(rawTransform);
		}
		return transforms;
	}
	
	private static float[] flipMatrix(final float[] f){
		if(f.length != 16){
			System.out.println("Error flipping matrix: array length not 16. This will not work!");
			System.out.println("Matrix: " + f);
		}
		return new float[]{
			f[0], f[4], f[8], f[12],
			f[1], f[5], f[9], f[13],
			f[2], f[6], f[10], f[14],
			f[3], f[7], f[11], f[15]
		};
	}
	
}
