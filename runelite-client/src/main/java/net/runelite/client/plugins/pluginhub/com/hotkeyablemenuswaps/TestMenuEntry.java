package net.runelite.client.plugins.pluginhub.com.hotkeyablemenuswaps;

import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import lombok.EqualsAndHashCode;
import net.runelite.api.Actor;
import net.runelite.api.Menu;
import net.runelite.api.MenuAction;
import net.runelite.api.MenuEntry;
import net.runelite.api.NPC;
import net.runelite.api.Player;
import net.runelite.api.widgets.Widget;

@EqualsAndHashCode
public class TestMenuEntry implements MenuEntry
{
	private String option;
	private String target;
	private int identifier;
	private int type;
	private int param0;
	private int param1;
	private boolean forceLeftClick;
	private int itemOp = -1;
	private int itemId = -1;
	private Widget widget;
	private Actor actor;

	@Override
	public String getOption()
	{
		return option;
	}

	@Override
	public TestMenuEntry setOption(String option)
	{
		this.option = option;
		return this;
	}

	@Override
	public String getTarget()
	{
		return target;
	}

	@Override
	public TestMenuEntry setTarget(String target)
	{
		this.target = target;
		return this;
	}

	@Override
	public int getIdentifier()
	{
		return this.identifier;
	}

	@Override
	public TestMenuEntry setIdentifier(int identifier)
	{
		this.identifier = identifier;
		return this;
	}

	@Override
	public MenuAction getType()
	{
		return MenuAction.of(this.type);
	}

	@Override
	public TestMenuEntry setType(MenuAction type)
	{
		this.type = type.getId();
		return this;
	}

	@Override
	public int getParam0()
	{
		return this.param0;
	}

	@Override
	public TestMenuEntry setParam0(int param0)
	{
		this.param0 = param0;
		return this;
	}

	@Override
	public int getParam1()
	{
		return this.param1;
	}

	@Override
	public TestMenuEntry setParam1(int param1)
	{
		this.param1 = param1;
		return this;
	}

	@Override
	public boolean isForceLeftClick()
	{
		return this.forceLeftClick;
	}

	@Override
	public TestMenuEntry setForceLeftClick(boolean forceLeftClick)
	{
		this.forceLeftClick = forceLeftClick;
		return this;
	}

	@Override
	public int getWorldViewId()
	{
		return 0;
	}

	@Override
	public MenuEntry setWorldViewId(int worldViewId)
	{
		return null;
	}

	@Override
	public boolean isDeprioritized()
	{
		return type >= MenuAction.MENU_ACTION_DEPRIORITIZE_OFFSET;
	}

	@Override
	public TestMenuEntry setDeprioritized(boolean deprioritized)
	{
		if (deprioritized)
		{
			if (type < MenuAction.MENU_ACTION_DEPRIORITIZE_OFFSET)
			{
				type += MenuAction.MENU_ACTION_DEPRIORITIZE_OFFSET;
			}
		}
		else
		{
			if (type >= MenuAction.MENU_ACTION_DEPRIORITIZE_OFFSET)
			{
				type -= MenuAction.MENU_ACTION_DEPRIORITIZE_OFFSET;
			}
		}

		return this;
	}

	@Override
	public MenuEntry onClick(Consumer<MenuEntry> callback)
	{
		return this;
	}

	@Override
	public Consumer<MenuEntry> onClick()
	{
		return null;
	}

	@Override
	public boolean isItemOp()
	{
		return itemOp != -1;
	}

	@Override
	public int getItemOp()
	{
		return itemOp;
	}

	public TestMenuEntry setItemOp(int itemOp)
	{
		this.itemOp = itemOp;
		return this;
	}

	@Override
	public int getItemId()
	{
		return itemId;
	}

	public TestMenuEntry setItemId(int itemId)
	{
		this.itemId = itemId;
		return this;
	}

	@Nullable
	@Override
	public Widget getWidget()
	{
		return widget;
	}

	public TestMenuEntry setWidget(Widget widget)
	{
		this.widget = widget;
		return this;
	}

	public TestMenuEntry setActor(Actor actor)
	{
		this.actor = actor;
		return this;
	}

	@Nullable
	@Override
	public NPC getNpc()
	{
		return actor instanceof NPC ? (NPC) actor : null;
	}

	@Nullable
	@Override
	public Player getPlayer()
	{
		return actor instanceof Player ? (Player) actor : null;
	}

	@Nullable
	@Override
	public Actor getActor()
	{
		return actor;
	}

	@Nullable
	@Override
	public Menu getSubMenu()
	{
		return null;
	}

	@Nonnull
	@Override
	public Menu createSubMenu()
	{
		return null;
	}

	@Override
	public void deleteSubMenu()
	{

	}
}
