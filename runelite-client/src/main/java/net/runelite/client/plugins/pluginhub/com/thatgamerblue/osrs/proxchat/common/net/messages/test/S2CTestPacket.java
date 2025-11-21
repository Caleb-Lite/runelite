package net.runelite.client.plugins.pluginhub.com.thatgamerblue.osrs.proxchat.common.net.messages.test;

import net.runelite.client.plugins.pluginhub.com.esotericsoftware.kryo.Kryo;
import net.runelite.client.plugins.pluginhub.com.esotericsoftware.kryo.io.Input;
import net.runelite.client.plugins.pluginhub.com.esotericsoftware.kryo.io.Output;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Packet sent from test server to test client containing a string
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class S2CTestPacket
{
	/**
	 * Will be printed out on the other side
	 */
	public String s;

	/**
	 * Serializes a S2CTestPacket packet to binary
	 */
	public static class Serializer extends com.esotericsoftware.kryo.Serializer<S2CTestPacket>
	{
		@Override
		public void write(Kryo kryo, Output output, S2CTestPacket packet)
		{
			output.writeString(packet.s);
		}

		@Override
		public S2CTestPacket read(Kryo kryo, Input input, Class<S2CTestPacket> aClass)
		{
			return new S2CTestPacket(input.readString());
		}
	}
}
