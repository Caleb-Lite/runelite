package net.runelite.client.plugins.pluginhub.com.questhelper.runeliteobjects.extendedruneliteobjects;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

// A group of RuneliteNpcs, which are used as a group in RuneliteObjectManager
public class ExtendedRuneliteObjects
{
	@Getter
	private final String groupName;

	@Getter
	List<ExtendedRuneliteObject> extendedRuneliteObjects = new ArrayList<>();

	List<ExtendedRuneliteObjects> subGroups = new ArrayList<>();

	public ExtendedRuneliteObjects(String groupName)
	{
		this.groupName = groupName;
	}

	public ExtendedRuneliteObjects(String groupName, ExtendedRuneliteObject npc)
	{
		this.groupName = groupName;
		this.extendedRuneliteObjects.add(npc);
	}

	public ExtendedRuneliteObjects(String groupName, List<ExtendedRuneliteObject> extendedRuneliteObjects)
	{
		this.groupName = groupName;
		this.extendedRuneliteObjects.addAll(extendedRuneliteObjects);
	}

	public void addExtendedRuneliteObject(ExtendedRuneliteObject npc)
	{
		extendedRuneliteObjects.add(npc);
	}

	public void addSubGroup(ExtendedRuneliteObjects subgroup)
	{
		subGroups.add(subgroup);
	}

	public void remove(ExtendedRuneliteObject npc)
	{
		extendedRuneliteObjects.remove(npc);
	}

	public void removeAll(RuneliteObjectManager runeliteObjectManager)
	{
		disableAll(runeliteObjectManager);
		extendedRuneliteObjects.clear();
	}

	public void disableAll(RuneliteObjectManager runeliteObjectManager)
	{
		for (ExtendedRuneliteObject npc : extendedRuneliteObjects)
		{
			npc.disable();
		}
	}

	public void disableAllIncludingSubgroups(RuneliteObjectManager runeliteObjectManager)
	{
		disableAll(runeliteObjectManager);
		// Remove all associated groups
		for (ExtendedRuneliteObjects subGroup : subGroups)
		{
			runeliteObjectManager.removeGroup(subGroup.getGroupName());
		}
	}

	public void removeAllIncludingSubgroups(RuneliteObjectManager runeliteObjectManager)
	{
		disableAllIncludingSubgroups(runeliteObjectManager);
		// Remove all associated groups
		for (ExtendedRuneliteObjects subGroup : subGroups)
		{
			runeliteObjectManager.removeGroupAndSubgroups(subGroup.getGroupName());
		}
		extendedRuneliteObjects.clear();
		subGroups.clear();
	}
}

/*
 * Copyright (c) 2023, Zoinkwiz <https://github.com/Zoinkwiz>
 * Copyright (c) 2021, Trevor <https://github.com/Trevor159>
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