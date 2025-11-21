package net.runelite.client.plugins.pluginhub.com.micro.petinfo.dataretrieval;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.RuneLite;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class PetInfo
{
	protected static final String localDir = RuneLite.RUNELITE_DIR + "/pet-info";
	protected static final String localJson = localDir + "/pets.json";

	private final Map<Integer, Pet> PETS;

	public PetInfo(PetDataFetcher petDataFetcher, boolean useRemote) throws IOException {
		this.PETS = petDataFetcher.getMap(useRemote);
	}

	/**
	 * Returns the Pet enum if the passed NPCid is a pet, null if not
	 */
	public Pet findPet(int npcId)
	{
		return PETS.get(npcId);
	}

	/**
	 * Returns the info string for the passed NPCid
	 */
	public String getInfo(int npcId)
	{
		return PETS.get(npcId).info;
	}

	/**
	 * Returns the examine string for the passed NPCid
	 */
	public String getExamine(int npcId)
	{
		return PETS.get(npcId).examine;
	}
}

/* Copyright (c) 2020 by micro
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
 *
 * Portions of the code are based off of the "Implings" RuneLite plugin.
 * The "Implings" is:
 * Copyright (c) 2017, Robin <robin.weymans@gmail.com>
 * All rights reserved.
 */
