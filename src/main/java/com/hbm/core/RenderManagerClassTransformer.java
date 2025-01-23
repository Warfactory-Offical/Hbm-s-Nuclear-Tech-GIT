package com.hbm.core;

import com.hbm.main.ModEventHandlerClient;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import java.util.Arrays;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class RenderManagerClassTransformer implements IClassTransformer {

	private static final String[] classesBeingTransformed = { "net.minecraft.client.renderer.entity.RenderManager" };

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
			//I must have my mappings wrong or something, I see this as doRenderEntity
			if (method.name.equals("renderEntity") || method.name.equals("func_188391_a")) {
				
				method.instructions.insert(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(ModEventHandlerClient.class), "onEntityRender", "(Lnet/minecraft/entity/Entity;)V", false));
				method.instructions.insert(new VarInsnNode(ALOAD, 1));
			}
		}
	}
}
