package com.hbm.core;

import com.hbm.main.ModEventHandlerClient;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import java.util.Arrays;

public class EntityRendererTransformer implements IClassTransformer {

	private static final String[] classesBeingTransformed = { "net.minecraft.client.renderer.EntityRenderer" };

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
				transformEntityRenderer(classNode, isObfuscated);
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

	private static void transformEntityRenderer(final ClassNode dispatcher, final boolean isObfuscated) {
		System.out.println("AAAAAAA");
		for (final MethodNode method : dispatcher.methods) {
			if (method.name.equals("updateLightmap") || method.name.equals("func_78472_g")) {
				for(int i = 1; i < method.instructions.size(); i++){
					final AbstractInsnNode insn = method.instructions.get(i);
					final AbstractInsnNode insn2 = method.instructions.get(i-1);
					if(insn instanceof VarInsnNode && insn.getOpcode() == Opcodes.ISTORE && ((VarInsnNode)insn).var == 20
							&& insn2 instanceof IntInsnNode && insn2.getOpcode() == Opcodes.SIPUSH && ((IntInsnNode)insn2).operand == 255){
						final InsnList inject = new InsnList();
						System.out.println(method.desc);
						System.out.println(method.instructions.get(i-1));
						
						inject.add(new VarInsnNode(Opcodes.FLOAD, 12));
						inject.add(new VarInsnNode(Opcodes.FLOAD, 12));
						inject.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(ModEventHandlerClient.class), "getRLightmapColor", "(F)F", false));
						inject.add(new InsnNode(Opcodes.FADD));
						inject.add(new VarInsnNode(Opcodes.FSTORE, 12));
						
						inject.add(new VarInsnNode(Opcodes.FLOAD, 13));
						inject.add(new VarInsnNode(Opcodes.FLOAD, 13));
						inject.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(ModEventHandlerClient.class), "getGLightmapColor", "(F)F", false));
						inject.add(new InsnNode(Opcodes.FADD));
						inject.add(new VarInsnNode(Opcodes.FSTORE, 13));
						
						inject.add(new VarInsnNode(Opcodes.FLOAD, 14));
						inject.add(new VarInsnNode(Opcodes.FLOAD, 14));
						inject.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(ModEventHandlerClient.class), "getBLightmapColor", "(F)F", false));
						inject.add(new InsnNode(Opcodes.FADD));
						inject.add(new VarInsnNode(Opcodes.FSTORE, 14));
						
						method.instructions.insert(insn, inject);
						break;
					}
				}
			}
		}
	}
}
