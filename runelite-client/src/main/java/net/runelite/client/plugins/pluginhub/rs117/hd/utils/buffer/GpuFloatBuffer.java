package net.runelite.client.plugins.pluginhub.rs117.hd.utils.buffer;

import java.nio.FloatBuffer;
import org.lwjgl.system.MemoryUtil;
import net.runelite.client.plugins.pluginhub.rs117.hd.HdPlugin;

public class GpuFloatBuffer
{
	private FloatBuffer buffer;

	public GpuFloatBuffer()
	{
		this(65536);
	}

	public GpuFloatBuffer(int initialCapacity) {
		try {
			buffer = MemoryUtil.memAllocFloat(initialCapacity);
		} catch (OutOfMemoryError oom) {
			// Force garbage collection and try again
			System.gc();
			buffer = MemoryUtil.memAllocFloat(initialCapacity);
		}
	}

	public void destroy() {
		if (buffer != null)
			MemoryUtil.memFree(buffer);
		buffer = null;
	}

	@Override
	@SuppressWarnings("deprecation")
	protected void finalize() {
		destroy();
	}

	public void put(float x, float y, float z, float w) {
		buffer.put(x).put(y).put(z).put(w);
	}

	public void put(float x, float y, float z, int w) {
		buffer.put(x).put(y).put(z).put(Float.intBitsToFloat(w));
	}

	public void put(float[] floats) {
		buffer.put(floats);
	}

	public void put(FloatBuffer buffer) {
		this.buffer.put(buffer);
	}

	public int position()
	{
		return buffer.position();
	}

	public void flip() {
		buffer.flip();
	}

	public GpuFloatBuffer clear() {
		buffer.clear();
		return this;
	}

	public int capacity() {
		return buffer.capacity();
	}

	public void ensureCapacity(int size) {
		int capacity = buffer.capacity();
		final int position = buffer.position();
		if ((capacity - position) < size) {
			do {
				capacity = (int) (capacity * HdPlugin.BUFFER_GROWTH_MULTIPLIER);
			}
			while ((capacity - position) < size);

			FloatBuffer newB = MemoryUtil.memAllocFloat(capacity);
			buffer.flip();
			newB.put(buffer);
			MemoryUtil.memFree(buffer);
			buffer = newB;
		}
	}

	public FloatBuffer getBuffer()
	{
		return buffer;
	}
}
