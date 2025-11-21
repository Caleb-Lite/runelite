package net.runelite.client.plugins.pluginhub.rs117.hd.opengl.shader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL33C.*;
import static rs117.hd.opengl.shader.ShaderIncludes.SHADER_DUMP_PATH;

@Slf4j
public class ShaderTemplate
{
	private final Map<Integer, String> shaderTypePaths = new HashMap<>();

	public ShaderTemplate add(int type, String name) {
		shaderTypePaths.put(type, name);
		return this;
	}

	public ShaderTemplate remove(int type) {
		shaderTypePaths.remove(type);
		return this;
	}

	public int compile(ShaderIncludes includes) throws ShaderException, IOException {
		int program = glCreateProgram();
		int[] shaders = new int[shaderTypePaths.size()];
		int i = 0;
		boolean ok = false;

		try
		{
			for (var entry : shaderTypePaths.entrySet()) {
				int shader = glCreateShader(entry.getKey());
				if (shader == 0)
					throw new ShaderException("Unable to create shader of type " + entry.getKey());

				String source = includes.loadFile(entry.getValue());
				glShaderSource(shader, source);
				glCompileShader(shader);

				if (glGetShaderi(shader, GL_COMPILE_STATUS) != GL_TRUE) {
					String error = glGetShaderInfoLog(shader);
					glDeleteShader(shader);
					throw ShaderException.compileError(includes, source, error, entry.getValue());
				}

				glAttachShader(program, shader);
				shaders[i++] = shader;
			}

			glLinkProgram(program);

			String[] paths = shaderTypePaths.values().toArray(String[]::new);
			String combinedName = String.join(" + ", paths);
			if (glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE) {
				throw ShaderException.compileError(
					includes,
					"// Linking " + combinedName,
					glGetProgramInfoLog(program),
					paths
				);
			}

			ok = true;

			if (SHADER_DUMP_PATH != null) {
				int[] numFormats = { 0 };
				glGetIntegerv(GL41C.GL_NUM_PROGRAM_BINARY_FORMATS, numFormats);
				if (numFormats[0] < 1) {
					log.error("OpenGL driver does not support any binary formats");
				} else {
					int[] size = { 0 };
					glGetProgramiv(program, GL41C.GL_PROGRAM_BINARY_LENGTH, size);

					int[] format = { 0 };
					ByteBuffer binary = BufferUtils.createByteBuffer(size[0]);
					GL41C.glGetProgramBinary(program, size, format, binary);

					SHADER_DUMP_PATH.resolve("binaries", combinedName + ".bin").mkdirs().writeByteBuffer(binary);
				}
			}
		} finally {
			while (i > 0) {
				int shader = shaders[--i];
				glDetachShader(program, shader);
				glDeleteShader(shader);
			}

			if (!ok)
				glDeleteProgram(program);
		}

		return program;
	}
}
