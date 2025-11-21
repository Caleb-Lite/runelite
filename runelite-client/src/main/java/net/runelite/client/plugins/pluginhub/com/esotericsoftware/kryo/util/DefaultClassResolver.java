package net.runelite.client.plugins.pluginhub.com.esotericsoftware.kryo.util;

import static com.esotericsoftware.kryo.util.Util.*;
import static com.esotericsoftware.minlog.Log.*;

import net.runelite.client.plugins.pluginhub.com.esotericsoftware.kryo.ClassResolver;
import net.runelite.client.plugins.pluginhub.com.esotericsoftware.kryo.Kryo;
import net.runelite.client.plugins.pluginhub.com.esotericsoftware.kryo.KryoException;
import net.runelite.client.plugins.pluginhub.com.esotericsoftware.kryo.Registration;
import net.runelite.client.plugins.pluginhub.com.esotericsoftware.kryo.io.Input;
import net.runelite.client.plugins.pluginhub.com.esotericsoftware.kryo.io.Output;

/** Resolves classes by ID or by fully qualified class name.
 * @author Nathan Sweet <misc@n4te.com> */
public class DefaultClassResolver implements ClassResolver {

	protected Kryo kryo;

	protected final IntMap<Registration> idToRegistration = new IntMap();
	protected final ObjectMap<Class, Registration> classToRegistration = new ObjectMap();

	protected IdentityObjectIntMap<Class> classToNameId;
	protected IntMap<Class> nameIdToClass;
	protected int nextNameId;

	private int memoizedClassId = -1;
	private Registration memoizedClassIdValue;
	private Class memoizedClass;
	private Registration memoizedClassValue;

	public void setKryo (Kryo kryo) {
		this.kryo = kryo;
	}

	public Registration register (Registration registration) {
		if (registration == null) throw new IllegalArgumentException("registration cannot be null.");
		if (TRACE) {
			trace("kryo", "Register class ID " + registration.getId() + ": " + className(registration.getType()) + " ("
				+ registration.getSerializer().getClass().getName() + ")");
		}
		idToRegistration.put(registration.getId(), registration);
		classToRegistration.put(registration.getType(), registration);
		if (registration.getType().isPrimitive()) classToRegistration.put(getWrapperClass(registration.getType()), registration);
		return registration;
	}

	public Registration getRegistration (Class type) {
		if (type == memoizedClass) return memoizedClassValue;
		Registration registration = classToRegistration.get(type);
		if (registration != null) {
			memoizedClass = type;
			memoizedClassValue = registration;
		}
		return registration;
	}

	public Registration getRegistration (int classID) {
		return idToRegistration.get(classID);
	}

	public Registration writeClass (Output output, Class type) {
		if (type == null) {
			if (TRACE || (DEBUG && kryo.getDepth() == 1)) log("Write", null);
			output.writeVarInt(Kryo.NULL, true);
			return null;
		}
		Registration registration = kryo.getRegistration(type);
		if (TRACE) trace("kryo", "Write class " + registration.getId() + ": " + className(type));
		output.writeVarInt(registration.getId() + 2, true);
		return registration;
	}

	public Registration readClass (Input input) {
		int classID = input.readVarInt(true);
		if (classID == Kryo.NULL)
		{
			if (TRACE || (DEBUG && kryo.getDepth() == 1)) log("Read", null);
			return null;
		}
		if (classID == memoizedClassId) return memoizedClassIdValue;
		Registration registration = idToRegistration.get(classID - 2);
		if (registration == null) throw new KryoException("Encountered unregistered class ID: " + (classID - 2));
		if (TRACE) trace("kryo", "Read class " + (classID - 2) + ": " + className(registration.getType()));
		memoizedClassId = classID;
		memoizedClassIdValue = registration;
		return registration;
	}

	public void reset () {
		if (!kryo.isRegistrationRequired()) {
			if (classToNameId != null) classToNameId.clear();
			if (nameIdToClass != null) nameIdToClass.clear();
			nextNameId = 0;
		}
	}
}

