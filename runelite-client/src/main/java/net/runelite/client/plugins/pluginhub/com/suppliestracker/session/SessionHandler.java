package net.runelite.client.plugins.pluginhub.com.suppliestracker.session;

import com.google.inject.Inject;
import net.runelite.api.Client;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static net.runelite.client.RuneLite.RUNELITE_DIR;

public class SessionHandler
{
	private static final File SESSION_DIR = new File(RUNELITE_DIR, "supplies-tracker");

	private final Client client;

	private final Map<Integer, Integer> supplies = new HashMap<>();
	private final Map<Integer, Integer> charges = new HashMap<>();

	@Inject
	public SessionHandler(Client client)
	{
		this.client = client;
		SESSION_DIR.mkdir();
	}

	public void setupMaps(int itemId, int quantity, boolean isCharges)
	{
		Map<Integer, Integer> map = isCharges ? charges : supplies;
		map.put(itemId, map.getOrDefault(itemId, 0) + quantity);
	}

	public void clearItem(int itemId)
	{
		this.supplies.remove(itemId);
		this.charges.remove(itemId);
		buildSessionFile(this.charges, this.supplies);
	}

	public void clearSupplies()
	{
		this.supplies.clear();
		this.charges.clear();
		buildSessionFile(this.charges, this.supplies);
	}

	public void addToSession(int itemId, int quantity, boolean isCharges)
	{
		Map<Integer, Integer> map = isCharges ? charges : supplies;
		map.put(itemId, map.getOrDefault(itemId, 0) + quantity);
		buildSessionFile(this.charges, this.supplies);
	}

	private void buildSessionFile(Map<Integer, Integer> c, Map<Integer, Integer> s)
	{
		try
		{
			File sessionFile = new File(RUNELITE_DIR + "/supplies-tracker/" + client.getAccountHash() + ".txt");

			if (!sessionFile.createNewFile())
			{
				sessionFile.delete();
				sessionFile.createNewFile();
			}

			try (FileWriter f = new FileWriter(sessionFile, true); BufferedWriter b = new BufferedWriter(f); PrintWriter p = new PrintWriter(b))
			{
				for (int id : c.keySet())
				{
					p.println("c" + id + ":" + c.get(id));
				}
				for (int id : s.keySet())
				{
					p.println(id + ":" + s.get(id));
				}
			}
			catch (IOException i)
			{
				i.printStackTrace();
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void clearSession()
	{
		supplies.clear();
		charges.clear();
	}
}

/*
 * Copyright (c) 2018, Daddy Dozer <https://github.com/Dyldozer>
 * Copyright (c) 2018, Davis Cook <daviscook447@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *	list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *	this list of conditions and the following disclaimer in the documentation
 *	and/or other materials provided with the distribution.
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