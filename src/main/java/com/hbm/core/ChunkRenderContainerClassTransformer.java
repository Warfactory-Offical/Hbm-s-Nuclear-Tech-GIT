package com.hbm.core;

import com.hbm.main.ModEventHandlerClient;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import java.util.Arrays;

import static org.objectweb.asm.Opcodes.*;

public class ChunkRenderContainerClassTransformer implements IClassTransformer {

	private static final String[] classesBeingTransformed = { "net.minecraft.client.renderer.ChunkRenderContainer" };

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
				transformProfiler(classNode, isObfuscated);
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

	private static void transformProfiler(final ClassNode profilerClass, final boolean isObfuscated) {

		for (final MethodNode method : profilerClass.methods) {
			if (method.name.equals("preRenderChunk") || method.name.equals("func_178003_a ")) {
				final InsnList code = method.instructions;
				final AbstractInsnNode[] instructions = code.toArray();
				for(final AbstractInsnNode a : instructions){
					if(a.getOpcode() == RETURN){
						code.insertBefore(a, new VarInsnNode(ALOAD, 1));
						code.insertBefore(a, new MethodInsnNode(INVOKESTATIC, Type.getInternalName(ModEventHandlerClient.class), "preRenderChunk", "(Lnet/minecraft/client/renderer/chunk/RenderChunk;)V", false));
						break;
					}
				}
			}
		}
	}
}
