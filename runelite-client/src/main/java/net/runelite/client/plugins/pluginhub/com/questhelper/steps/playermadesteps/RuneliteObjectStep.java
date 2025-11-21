package net.runelite.client.plugins.pluginhub.com.questhelper.steps.playermadesteps;

import net.runelite.client.plugins.pluginhub.com.questhelper.QuestHelperPlugin;
import net.runelite.client.plugins.pluginhub.com.questhelper.questhelpers.QuestHelper;
import net.runelite.client.plugins.pluginhub.com.questhelper.requirements.Requirement;
import net.runelite.client.plugins.pluginhub.com.questhelper.runeliteobjects.extendedruneliteobjects.ExtendedRuneliteObject;
import net.runelite.client.plugins.pluginhub.com.questhelper.runeliteobjects.extendedruneliteobjects.RuneliteObjectManager;
import net.runelite.client.plugins.pluginhub.com.questhelper.steps.DetailedQuestStep;
import net.runelite.client.plugins.pluginhub.com.questhelper.steps.overlay.DirectionArrow;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.inject.Inject;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// TODO: Separate out NPC logic from Step logic
public class RuneliteObjectStep extends DetailedQuestStep
{
	@Inject
	private RuneliteObjectManager runeliteObjectManager;

	private final ExtendedRuneliteObject extendedRuneliteObject;

	// TODO: Maybe a list of npcs to 'delete' with this?
	List<String> npcsGroupsToDelete = new ArrayList<>();
	HashMap<String, ExtendedRuneliteObject> npcsToDelete = new HashMap<>();

	public RuneliteObjectStep(QuestHelper questHelper, ExtendedRuneliteObject extendedRuneliteObject, String text, Requirement... requirements)
	{
		super(questHelper, extendedRuneliteObject.getWorldPoint(), text, requirements);
		this.extendedRuneliteObject = extendedRuneliteObject;
	}

	@Override
	public void startUp()
	{
		super.startUp();
	}

	@Override
	public void shutDown()
	{
		super.shutDown();
		// Delete all fake npcs associated
		clientThread.invokeLater(this::removeRuneliteNpcs);
	}

	private void removeRuneliteNpcs()
	{
		runeliteObjectManager.removeGroupAndSubgroups(this.toString());
	}

	@Override
	public void renderArrow(Graphics2D graphics)
	{
		if (!extendedRuneliteObject.getWorldPoint().isInScene(client)) return;
		if (questHelper.getConfig().showMiniMapArrow())
		{
			if (!extendedRuneliteObject.isRuneliteObjectActive())
			{
				super.renderArrow(graphics);
			}
			else if (!hideWorldArrow)
			{
				Point p = Perspective.localToCanvas(client, extendedRuneliteObject.getRuneliteObject().getLocation(), client.getPlane(),
					extendedRuneliteObject.getRuneliteObject().getModel().getModelHeight());
				if (p != null)
				{
					DirectionArrow.drawWorldArrow(graphics, getQuestHelper().getConfig().targetOverlayColor(), p.getX(), p.getY() - ARROW_SHIFT_Y);
				}
			}
		}
	}

	@Override
	public void makeWorldOverlayHint(Graphics2D graphics, QuestHelperPlugin plugin)
	{
		super.makeWorldOverlayHint(graphics, plugin);

		Color configColor = getQuestHelper().getConfig().targetOverlayColor();
		highlightNpc(configColor, graphics);
	}

	private void highlightNpc(Color color, Graphics2D graphics)
	{
		if (!extendedRuneliteObject.getWorldPoint().isInScene(client)) return;
		switch (questHelper.getConfig().highlightStyleNpcs())
		{
			case CONVEX_HULL:
			case OUTLINE:
				if (!extendedRuneliteObject.getRuneliteObject().isActive()) break;
				modelOutlineRenderer.drawOutline(
					extendedRuneliteObject.getRuneliteObject(),
					questHelper.getConfig().outlineThickness(),
					color,
					questHelper.getConfig().outlineFeathering()
				);
				break;
			case TILE:
				Polygon poly = Perspective.getCanvasTilePoly(client, extendedRuneliteObject.getRuneliteObject().getLocation());
				if (poly != null)
				{
					OverlayUtil.renderPolygon(graphics, poly, color);
				}
				break;
			default:
		}
	}
}
