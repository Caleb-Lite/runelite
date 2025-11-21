package net.runelite.client.plugins.pluginhub.com.esotericsoftware.kryo.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/** An OutputStream whose target is a {@link ByteBuffer}. If bytes would be written that would overflow the buffer,
 * {@link #flush()} is called. Subclasses can override flush to empty the buffer.
 * @author Nathan Sweet <misc@n4te.com> */
public class ByteBufferOutputStream extends OutputStream {
	private ByteBuffer byteBuffer;

	/** Creates an uninitialized stream that cannot be used until {@link #setByteBuffer(ByteBuffer)} is called. */
	public ByteBufferOutputStream () {
	}

	/** Creates a stream with a new non-direct buffer of the specified size. */
	public ByteBufferOutputStream (int bufferSize) {
		this(ByteBuffer.allocate(bufferSize));
	}

	public ByteBufferOutputStream (ByteBuffer byteBuffer) {
		this.byteBuffer = byteBuffer;
	}

	public ByteBuffer getByteBuffer () {
		return byteBuffer;
	}

	public void setByteBuffer (ByteBuffer byteBuffer) {
		this.byteBuffer = byteBuffer;
	}

	public void write (int b) throws IOException {
		if (!byteBuffer.hasRemaining()) flush();
		byteBuffer.put((byte)b);
	}

	public void write (byte[] bytes, int offset, int length) throws IOException {
		if (byteBuffer.remaining() < length) flush();
		byteBuffer.put(bytes, offset, length);
	}
}

/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
