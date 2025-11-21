package net.runelite.client.plugins.pluginhub.com.hotkeyablemenuswaps;

import java.util.Arrays;
import java.util.stream.Collectors;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Menu;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.CommandExecuted;
import net.runelite.api.events.MenuOpened;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetUtil;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;

@PluginDescriptor(
	name = "[Tools] Hotkeyable menu swaps",
	description = "",
	tags = {"entry", "swapper"}
)
@PluginDependency(HotkeyableMenuSwapsPlugin.class)
public class HotkeyableMenuSwapsToolsPlugin extends Plugin
{

	@Inject
	private HotkeyableMenuSwapsPlugin plugin;

	@Inject
	private ItemManager itemManager;

	int demoanim = -1;
	int demogfx = -1;

	@Subscribe
	public void onCommandExecuted(CommandExecuted commandExecuted) {
		String[] arguments = commandExecuted.getArguments();
		String command = commandExecuted.getCommand();
		System.out.println(arguments.length);

		if (command.equals("record")) {
			recordMenuEntries = true;
		}
	}

	private boolean recordMenuEntries = false;

	@Inject
	private Client client;

	@Subscribe(priority = -1000f) public void onMenuOpened(MenuOpened e) {
		System.out.println("===menu opened===");
		HotkeyableMenuSwapsPlugin.ForwardsMenuIterator menuIterator = new HotkeyableMenuSwapsPlugin.ForwardsMenuIterator(client.getMenuEntries());
		while (menuIterator.hasNext())
		{
			MenuEntry menuEntry = menuIterator.next();
			Menu subMenu = menuEntry.getSubMenu();
			Widget widget = menuEntry.getWidget();
			int interfaceId = widget != null ? WidgetUtil.componentToInterface(widget.getId()) : -1;
//			System.out.println(menuEntry.getOption() + " " + menuEntry.getTarget() + " " + menuEntry.getType() + " " + menuEntry.getIdentifier() + " " + interfaceId);
			if (menuIterator.inSubmenu()) System.out.print("\t");
			System.out.println(menuEntry.getOption() + " " + menuEntry.getTarget() + " " + menuIterator.submenuIndex);
			if (menuIterator.inSubmenu()) System.out.println("\t" + " parent: " + menuIterator.index + " " + menuIterator.getMenuEntries()[menuIterator.index].getOption() + "," + menuIterator.getMenuEntries()[menuIterator.index].getTarget());
		}
	}

	@Subscribe(priority = -2) // This will run after our swaps.
	public void onClientTick(ClientTick clientTick) {
		if (recordMenuEntries)
		{
			recordMenuEntries = false;
			if (client.isMenuOpen()) {
				System.out.println("do not do this with the menu open.");
				return;
			}
			System.out.println("printing menu:");

			for (MenuEntry menuEntry : client.getMenuEntries())
			{
//				System.out.println(
//					"\"" + menuEntry.getOption() + "\", \"" + menuEntry.getTarget() + "\", "
//					+ menuEntry.getType() + ", "
//					+ menuEntry.getIdentifier() + ", "
//					+ menuEntry.isDeprioritized() + ", "
//				);
				System.out.println("new TestMenuEntry().setOption(\"" + menuEntry.getOption() + "\").setTarget(\"" + menuEntry.getTarget() + "\").setIdentifier(" + menuEntry.getIdentifier() + ").setType(" + menuEntry.getType() + ").setParam0(" + menuEntry.getParam0() + ").setParam1(" + menuEntry.getParam1() + ").setForceLeftClick(" + menuEntry.isForceLeftClick() + ").setDeprioritized(" + menuEntry.isDeprioritized() + ").setItemOp(" + menuEntry.getItemOp() + ").setItemId(" + menuEntry.getItemId() + ").setWidget(" + menuEntry.getWidget() + ").setActor(" + menuEntry.getActor() + ")");
				if (menuEntry.getNpc() != null) {
					String[] actions = menuEntry.getNpc().getTransformedComposition().getActions();
					System.out.println("actions is " + Arrays.asList(actions).stream().map(s -> "\"" + s + "\"").collect(Collectors.toList()));
				}
			}
		}

//		for (int i = 0; i < Math.min(100, client.getLocalPlayer().getModel().getFaceColors1().length); i++)
//		for (int i = 0; i < client.getLocalPlayer().getModel().getFaceColors1().length; i++)
//		{
//			client.getLocalPlayer().getModel().getFaceColors1()[i] = 0;
//		}
//		if (demoanim != -1) {
//			client.getLocalPlayer().setAnimation(demoanim);
//			client.getLocalPlayer().setAnimationFrame(0);
//		}
		if (demogfx != -1) {
			client.getLocalPlayer().setGraphic(demogfx);
			client.getLocalPlayer().setSpotAnimFrame(0);
		}
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked menuOptionClicked)
	{
//		if (menuOptionClicked.getMenuOption().equals("Use") && menuOptionClicked.getId() == 563) {
//			if (demoanim != -1) {
//				demoanim--;
//				for (Constants.ActorAnimation value : values())
//				{
//					value.setAnimation(client.getLocalPlayer(), demoanim);
//				}
//				System.out.println("demo anim " + demoanim);
//			}
//			if (demogfx != -1) {
//				demogfx--;
//				System.out.println("demo gfx " + demogfx);
//			}
//		} else if (menuOptionClicked.getMenuOption().equals("Use") && menuOptionClicked.getId() == 995){
//			if (demoanim != -1) {
//				demoanim++;
//				for (Constants.ActorAnimation value : values())
//				{
//					value.setAnimation(client.getLocalPlayer(), demoanim);
//				}
//				System.out.println("demo anim " + demoanim);
//			}
//			if (demogfx != -1) {
//				demogfx++;
//				System.out.println("demo gfx " + demogfx);
//			}
//		}
//		System.out.println(menuOptionClicked.getMenuOption() + " " + Text.removeTags(menuOptionClicked.getMenuTarget()));
	}

}

/*
 * Copyright (c) 2021, Adam <Adam@sigterm.info>
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