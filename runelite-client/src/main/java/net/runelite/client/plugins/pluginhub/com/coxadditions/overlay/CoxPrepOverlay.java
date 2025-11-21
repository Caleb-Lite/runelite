package net.runelite.client.plugins.pluginhub.com.coxadditions.overlay;

import net.runelite.client.plugins.pluginhub.com.coxadditions.CoxAdditionsConfig;
import net.runelite.client.plugins.pluginhub.com.coxadditions.CoxAdditionsPlugin;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import java.util.List;
import net.runelite.api.InstanceTemplates;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.LayoutableRenderableEntity;
import net.runelite.client.ui.overlay.components.LineComponent;

public class CoxPrepOverlay extends OverlayPanel
{
	private final CoxAdditionsPlugin plugin;
	private final CoxAdditionsConfig config;

	@Inject
	private CoxPrepOverlay(CoxAdditionsPlugin plugin, CoxAdditionsConfig config)
	{
		this.plugin = plugin;
		this.config = config;
		setPosition(OverlayPosition.TOP_LEFT);
		setPriority(OverlayPriority.HIGH);
		setLayer(OverlayLayer.ABOVE_SCENE);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (plugin.getPanelFont() == null)
		{
			plugin.loadFont(false);
		}

		graphics.setFont(plugin.getPanelFont());

		List<LayoutableRenderableEntity> elems = panelComponent.getChildren();
		if (plugin.isInPrep())
		{
			if ((config.brews() + config.revites() + config.enhances()) != 0)
			{
				elems.add(LineComponent.builder()
					.leftColor(Color.WHITE)
					.left("Buchu: ")
					.rightColor(plugin.getTotalBuchus() >= (config.brews() + config.revites() + config.enhances())
						? Color.GREEN : Color.WHITE)
					.right(plugin.getTotalBuchus() + "/" + (config.brews() + config.revites() + config.enhances()))
					.build());

				if (config.showPots())
				{
					if (config.brews() > 0)
					{
						elems.add(LineComponent.builder()
							.leftColor(Color.WHITE)
							.left("    Brews: ")
							.rightColor(plugin.getTotalBrews() >= config.brews() ? Color.GREEN : Color.WHITE)
							.right(plugin.getTotalBrews() + "/" + config.brews())
							.build());
					}

					if (config.revites() > 0)
					{
						elems.add(LineComponent.builder()
							.leftColor(Color.WHITE)
							.left("    Revites: ")
							.rightColor(plugin.getTotalRevites() >= config.revites() ? Color.GREEN : Color.WHITE)
							.right(plugin.getTotalRevites() + "/" + config.revites())
							.build());
					}

					if (config.enhances() > 0)
					{
						elems.add(LineComponent.builder()
							.leftColor(Color.WHITE)
							.left("    Enhances: ")
							.rightColor(plugin.getTotalEnhances() >= config.enhances() ? Color.GREEN : Color.WHITE)
							.right(plugin.getTotalEnhances() + "/" + config.enhances())
							.build());
					}
				}
			}

			if (config.overloads() != 0)
			{
				elems.add(LineComponent.builder()
					.leftColor(Color.WHITE)
					.left("Golpar: ")
					.rightColor(plugin.getTotalGolpar() >= (config.overloads() * 3) ? Color.GREEN : Color.WHITE)
					.right(plugin.getTotalGolpar() + "/" + (config.overloads() * 3))
					.build());

				if (config.showPots())
				{
					elems.add(LineComponent.builder()
						.leftColor(Color.WHITE)
						.left("    Elders: ")
						.rightColor(plugin.getTotalElders() >= config.overloads() ? Color.GREEN : Color.WHITE)
						.right(plugin.getTotalElders() + "/" + config.overloads())
						.build());

					elems.add(LineComponent.builder()
						.leftColor(Color.WHITE)
						.left("    Twisteds: ")
						.rightColor(plugin.getTotalTwisteds() >= config.overloads() ? Color.GREEN : Color.WHITE)
						.right(plugin.getTotalTwisteds() + "/" + config.overloads())
						.build());

					elems.add(LineComponent.builder()
						.leftColor(Color.WHITE)
						.left("    Kodais: ")
						.rightColor(plugin.getTotalKodais() >= config.overloads() ? Color.GREEN : Color.WHITE)
						.right(plugin.getTotalKodais() + "/" + config.overloads())
						.build());

				}

				elems.add(LineComponent.builder()
					.leftColor(Color.WHITE)
					.left("Noxifer: ")
					.rightColor(plugin.getTotalNox() >= (config.overloads() + config.extraNox()) ? Color.GREEN : Color.WHITE)
					.right(plugin.getTotalNox() + "/" + (config.overloads() + config.extraNox()))
					.build());

				if (config.showPots())
				{
					elems.add(LineComponent.builder()
						.leftColor(Color.WHITE)
						.left("    Overloads: ")
						.rightColor(plugin.getTotalOverloads() >= config.overloads() ? Color.GREEN : Color.WHITE)
						.right(plugin.getTotalOverloads() + "/" + config.overloads())
						.build());
				}
			}
		}

		if ((plugin.room() == InstanceTemplates.RAIDS_SCAVENGERS || plugin.room() == InstanceTemplates.RAIDS_SCAVENGERS2)
			&& config.showSecondaries())
		{
			if (config.brews() != 0 || config.overloads() != 0)
			{
				elems.add(LineComponent.builder()
					.leftColor(Color.WHITE)
					.left("Juice: ")
					.rightColor(plugin.getPickedJuice() >= (config.brews() + config.overloads()) ? Color.GREEN : Color.WHITE)
					.right(plugin.getPickedJuice() + "/" + (config.brews() + config.overloads()))
					.build());
			}

			if (config.revites() != 0 || config.overloads() != 0)
			{
				elems.add(LineComponent.builder()
					.leftColor(Color.WHITE)
					.left("Shrooms: ")
					.rightColor(plugin.getPickedShrooms() >= (config.revites() + config.overloads()) ? Color.GREEN : Color.WHITE)
					.right(plugin.getPickedShrooms() + "/" + (config.revites() + config.overloads()))
					.build());
			}

			if (config.enhances() != 0 || config.overloads() != 0)
			{
				elems.add(LineComponent.builder()
					.leftColor(Color.WHITE)
					.left("Cicely: ")
					.rightColor(plugin.getPickedCicely() >= (config.enhances() + config.overloads()) ? Color.GREEN : Color.WHITE)
					.right(plugin.getPickedCicely() + "/" + (config.enhances() + config.overloads()))
					.build());
			}
		}
		return super.render(graphics);
	}
}
/*
 * Copyright (c) 2022, Buchus <http://github.com/MoreBuchus>
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