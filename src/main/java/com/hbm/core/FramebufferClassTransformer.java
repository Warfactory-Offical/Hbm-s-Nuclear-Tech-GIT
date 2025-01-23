package com.hbm.core;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;

import java.util.Arrays;

public class FramebufferClassTransformer implements IClassTransformer {

	private static final String[] classesBeingTransformed = { "net.minecraft.client.shader.Framebuffer" };

	@Override
	public byte[] transform(final String name, final String transformedName, final byte[] classBeingTransformed) {
		final boolean isObfuscated = !name.equals(transformedName);
		final int index = Arrays.asList(classesBeingTransformed).indexOf(transformedName);
		return index != -1 ? transform(index, classBeingTransformed, isObfuscated) : classBeingTransformed;
	}

	private static byte[] transform(final int index, final byte[] classBeingTransformed, final boolean isObfuscated) {
		System.out.println("Transforming: " + classesBeingTransformed[index]);
		try {
			final ClassNode classNode = new ClassNode();
			final ClassReader classReader = new ClassReader(classBeingTransformed);
			classReader.accept(classNode, 0);

			switch (index) {
			case 0:
				transformFramebuffer(classNode, isObfuscated);
				break;
			}

			final ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			classNode.accept(classWriter);
			return classWriter.toByteArray();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return classBeingTransformed;
	}

	private static void transformFramebuffer(final ClassNode clazz, final boolean isObfuscated) {

		for (final MethodNode method : clazz.methods) {
			if(method.name.equals("createFramebuffer") || method.name.equals("func_147605_b")){
				for(int i = 0; i < method.instructions.toArray().length; i++){
					final AbstractInsnNode m = method.instructions.get(i);
					if(m instanceof MethodInsnNode m2){
                        if(m2.name.equals("glTexImage2D")){
							final AbstractInsnNode node = method.instructions.get(i - 10);
							if(node instanceof LdcInsnNode rgba_8){
                                if(rgba_8.cst instanceof Integer && ((Integer)rgba_8.cst).intValue() == 32856){
									//Change the frame buffer color storage from rgba8 to rgba16 to support hdr.
									rgba_8.cst = 32859;
								}
								//System.out.println(rgba_8.cst);
							}
							//System.out.println(m2.desc);
							//method.instructions.set(m, new MethodInsnNode(i.getOpcode(), i.owner, i.name, i.desc, i.itf));
						}
					}
				}
			}
			
		}
	}
}
