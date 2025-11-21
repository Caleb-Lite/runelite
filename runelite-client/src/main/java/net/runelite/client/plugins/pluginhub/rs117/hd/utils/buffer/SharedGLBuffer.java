package net.runelite.client.plugins.pluginhub.rs117.hd.utils.buffer;

import java.nio.IntBuffer;
import net.runelite.client.plugins.pluginhub.rs117.hd.opengl.compute.OpenCLManager;

import static org.lwjgl.opencl.CL10.*;
import static org.lwjgl.opencl.CL10GL.clCreateFromGLBuffer;

public class SharedGLBuffer extends GLBuffer {
	public final int clUsage;

	public long clId;

	public SharedGLBuffer(String name, int target, int glUsage, int clUsage) {
		super(name, target, glUsage);
		this.clUsage = clUsage;
	}

	private void releaseCLBuffer() {
		if (clId != 0 && OpenCLManager.context != 0)
			clReleaseMemObject(clId);
		clId = 0;
	}

	@Override
	public void destroy() {
		releaseCLBuffer();
		super.destroy();
	}

	@Override
	public void ensureCapacity(long byteOffset, long numBytes) {
		super.ensureCapacity(byteOffset, numBytes);
		if (OpenCLManager.context == 0)
			return;

		releaseCLBuffer();

		// OpenCL does not allow 0-size GL buffers, it will segfault on macOS
		if (size != 0)
			clId = clCreateFromGLBuffer(OpenCLManager.context, clUsage, id, (IntBuffer) null);
	}
}

/*
 * Copyright (c) 2021, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */