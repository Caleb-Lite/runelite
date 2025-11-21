package net.runelite.client.plugins.pluginhub.com.questhelper.requirements.zone;

import net.runelite.api.coords.WorldPoint;

import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class PolyZone extends Zone
{
	List<Point2D.Double> perimeter = new ArrayList<>();

	private int minPlane = 8;
	private int maxPlane = 0;

	int minX = Integer.MAX_VALUE;
	int minY = Integer.MAX_VALUE;

	//The first plane of the "Overworld"
	public PolyZone(List<WorldPoint> permiter)
	{
		// Check minimum z, check for max z
		for (WorldPoint p : permiter)
		{
			Point2D.Double pos = new Point2D.Double();
			pos.x = p.getX();
			pos.y = p.getY();
			this.perimeter.add(pos);

			if (p.getPlane() > maxPlane)
			{
				maxPlane = p.getPlane();
			}
			else if (p.getPlane() < minPlane)
			{
				minPlane = p.getPlane();
			}

			if (p.getX() < minX)
			{
				minX = p.getX();
			}
			if (p.getY() < minY)
			{
				minY = p.getY();
			}
		}
	}

	public boolean contains(WorldPoint worldPoint)
	{
		Path2D.Double path = new Path2D.Double();
		Point2D.Double firstVertex = perimeter.get(0);
		path.moveTo(firstVertex.x, firstVertex.y);

		for (int i = 1; i < perimeter.size(); i++)
		{
			Point2D.Double vertex = perimeter.get(i);
			path.lineTo(vertex.x, vertex.y);
		}

		path.closePath();
		return path.contains(worldPoint.getX(), worldPoint.getY());
	}

	public WorldPoint getMinWorldPoint()
	{

		return new WorldPoint(minX, minY, minPlane);
	}
}

/*
 *
 *  * Copyright (c) 2021
 *  * All rights reserved.
 *  *
 *  * Redistribution and use in source and binary forms, with or without
 *  * modification, are permitted provided that the following conditions are met:
 *  *
 *  * 1. Redistributions of source code must retain the above copyright notice, this
 *  *    list of conditions and the following disclaimer.
 *  * 2. Redistributions in binary form must reproduce the above copyright notice,
 *  *    this list of conditions and the following disclaimer in the documentation
 *  *    and/or other materials provided with the distribution.
 *  *
 *  * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 *  * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */