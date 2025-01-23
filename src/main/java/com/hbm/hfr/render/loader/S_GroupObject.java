package com.hbm.hfr.render.loader;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

import java.util.ArrayList;

public class S_GroupObject {
	public String name;
	public ArrayList<S_Face> faces = new ArrayList<S_Face>();
	public int glDrawingMode;

	public S_GroupObject() {
		this("");
	}

	public S_GroupObject(final String name) {
		this(name, -1);
	}

	public S_GroupObject(final String name, final int glDrawingMode) {
		this.name = name;
		this.glDrawingMode = glDrawingMode;
	}

	public void render() {
		if (this.faces.size() > 0) {
			final Tessellator tessellator = Tessellator.getInstance();
			tessellator.getBuffer().begin(glDrawingMode, DefaultVertexFormats.POSITION_TEX_NORMAL);
			render(tessellator);
			tessellator.draw();
		}
	}

	public void render(final Tessellator tessellator) {
		if (this.faces.size() > 0) {
			for (final S_Face face : this.faces) {
				face.addFaceForRender(tessellator.getBuffer());
			}
		}
	}
}
