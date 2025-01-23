package com.hbm.render;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.opengl.GL11;

import com.hbm.hfr.render.loader.HFRWavefrontObject;
import com.hbm.hfr.render.loader.S_GroupObject;
import com.hbm.render.amlfrom1710.GroupObject;
import com.hbm.render.amlfrom1710.IModelCustom;
import com.hbm.render.amlfrom1710.Tessellator;
import com.hbm.render.amlfrom1710.WavefrontObject;

public class WavefrontObjDisplayList implements IModelCustom {

	public List<Pair<String, Integer>> nameToCallList = new ArrayList<>();
	
	public WavefrontObjDisplayList(final WavefrontObject obj) {
		final Tessellator tes = Tessellator.instance;
		for(final GroupObject g : obj.groupObjects){
			final int list = GL11.glGenLists(1);
			GL11.glNewList(list, GL11.GL_COMPILE);
			tes.startDrawing(g.glDrawingMode);
			g.render(tes);
			tes.draw();
			GL11.glEndList();
			nameToCallList.add(Pair.of(g.name, list));
		}
		
	}
	
	public WavefrontObjDisplayList(final HFRWavefrontObject obj) {
		for(final S_GroupObject g : obj.groupObjects){
			final int list = GL11.glGenLists(1);
			GL11.glNewList(list, GL11.GL_COMPILE);
			g.render();
			GL11.glEndList();
			nameToCallList.add(Pair.of(g.name, list));
		}
	}

	public int getListForName(final String name){
		for(final Pair<String, Integer> p : nameToCallList){
			if(p.getLeft().equalsIgnoreCase(name)){
				return p.getRight();
			}
		}
		return 0;
	}

	@Override
	public String getType() {
		return "obj_list";
	}

	@Override
	public void renderAll() {
		for(final Pair<String, Integer> p : nameToCallList)
			GL11.glCallList(p.getRight());
	}

	@Override
	public void renderOnly(final String... groupNames) {
		for(final Pair<String, Integer> p : nameToCallList){
			for(final String name : groupNames){
				if(p.getLeft().equalsIgnoreCase(name)){
					GL11.glCallList(p.getRight());
					break;
				}
			}
		}
	}

	@Override
	public void renderPart(final String partName) {
		for(final Pair<String, Integer> p : nameToCallList){
			if(p.getLeft().equalsIgnoreCase(partName)){
				GL11.glCallList(p.getRight());
			}
		}
	}

	@Override
	public void renderAllExcept(final String... excludedGroupNames) {
		for(final Pair<String, Integer> p : nameToCallList){
			boolean skip = false;
			for(final String name : excludedGroupNames){
				if(p.getLeft().equalsIgnoreCase(name)){
					skip = true;
					break;
				}
			}
			if(!skip){
				GL11.glCallList(p.getRight());
			}
		}
	}

	@Override
	public void tessellateAll(final Tessellator tes){
		throw new RuntimeException("Tessellate operation not supported on display list object");
	}

	@Override
	public void tessellatePart(final Tessellator tes, final String name){
		throw new RuntimeException("Tessellate operation not supported on display list object");
	}

	@Override
	public void tessellateOnly(final Tessellator tes, final String... names){
		throw new RuntimeException("Tessellate operation not supported on display list object");
	}

	@Override
	public void tessellateAllExcept(final Tessellator tes, final String... excluded){
		throw new RuntimeException("Tessellate operation not supported on display list object");
	}
}
