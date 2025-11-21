package net.runelite.client.plugins.pluginhub.com.esotericsoftware.kryo.factories;

import net.runelite.client.plugins.pluginhub.com.esotericsoftware.kryo.Kryo;
import net.runelite.client.plugins.pluginhub.com.esotericsoftware.kryo.Serializer;

public class NullSerializerFactory implements SerializerFactory {
	@Override
	public Serializer makeSerializer (Kryo kryo, Class<?> type) {
		return makeSerializer();
	}

	public static Serializer makeSerializer() {
		return null;
	}
}
