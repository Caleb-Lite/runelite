package net.runelite.client.plugins.pluginhub.com.questhelper.tools;

import net.runelite.api.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class VisibilityHelper
{
	private final Map<Integer, Boolean> visibilityCache = new HashMap<>();

	public boolean isObjectVisible(TileObject tileObject)
	{
		if (!visibilityCache.containsKey(tileObject.getId()))
		{
			visibilityCache.put(tileObject.getId(), isObjectVisibleChecker(tileObject));
		}
		return visibilityCache.get(tileObject.getId());
	}


	public Shape getObjectHull(TileObject tileObject)
	{
		if (tileObject instanceof GameObject)
		{
			return ((GameObject) tileObject).getConvexHull();
		}
		else if (tileObject instanceof GroundObject)
		{
			return ((GroundObject) tileObject).getConvexHull();
		}
		else if (tileObject instanceof DecorativeObject)
		{
			return ((DecorativeObject) tileObject).getConvexHull();
		}
		else if (tileObject instanceof WallObject)
		{
			return ((WallObject) tileObject).getConvexHull();
		}
		return null;
	}

	private boolean isObjectVisibleChecker(TileObject tileObject)
	{
		if (tileObject instanceof GameObject)
		{
			Model model = extractModel(((GameObject) tileObject).getRenderable());
			return modelHasVisibleTriangles(model);
		}
		else if (tileObject instanceof GroundObject)
		{
			Model model = extractModel(((GroundObject) tileObject).getRenderable());
			return modelHasVisibleTriangles(model);
		}
		else if (tileObject instanceof DecorativeObject)
		{
			DecorativeObject decoObj = ((DecorativeObject) tileObject);
			Model model1 = extractModel(decoObj.getRenderable());
			Model model2 = extractModel(decoObj.getRenderable2());
			return modelHasVisibleTriangles(model1) || modelHasVisibleTriangles(model2);
		}
		else if (tileObject instanceof WallObject)
		{
			WallObject wallObj = ((WallObject) tileObject);
			Model model1 = extractModel(wallObj.getRenderable1());
			Model model2 = extractModel(wallObj.getRenderable2());
			return modelHasVisibleTriangles(model1) || modelHasVisibleTriangles(model2);
		}
		return false;
	}

	private Model extractModel(Renderable renderable)
	{
		if (renderable == null)
		{
			return null;
		}
		return renderable instanceof Model ? (Model) renderable : renderable.getModel();
	}

	private boolean modelHasVisibleTriangles(Model model)
	{
		if (model == null)
		{
			return false;
		}
		byte[] triangleTransparencies = model.getFaceTransparencies();
		int triangleCount = model.getFaceCount();
		if (triangleTransparencies == null)
		{
			return true;
		}
		for (int i = 0; i < triangleCount; i++)
		{
			if ((triangleTransparencies[i] & 255) < 254)
			{
				return true;
			}
		}
		return false;
	}
}

/*
 * Copyright (c) 2021, Zoinkwiz <https://github.com/Zoinkwiz>
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