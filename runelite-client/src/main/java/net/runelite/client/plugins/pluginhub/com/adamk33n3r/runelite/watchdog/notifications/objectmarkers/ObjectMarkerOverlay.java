package net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.notifications.objectmarkers;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import net.runelite.api.*;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.ui.overlay.outline.ModelOutlineRenderer;
import net.runelite.client.util.ColorUtil;

import javax.inject.Inject;
import java.awt.*;

import static com.adamk33n3r.runelite.watchdog.notifications.objectmarkers.ObjectMarker.*;

public class ObjectMarkerOverlay extends Overlay
{
    private final Client client;
    private final ObjectMarkerManager objectMarkerManager;
    private final ModelOutlineRenderer modelOutlineRenderer;

    @Inject
    private ObjectMarkerOverlay(
        Client client,
        ObjectMarkerManager objectMarkerManager,
        ModelOutlineRenderer modelOutlineRenderer
    ) {
        this.client = client;
        this.objectMarkerManager = objectMarkerManager;
        this.modelOutlineRenderer = modelOutlineRenderer;
        setPosition(OverlayPosition.DYNAMIC);
        setPriority(PRIORITY_LOW);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if (this.objectMarkerManager.isInObjectMarkerMode() && this.objectMarkerManager.getEditingObjectMarker() != null) {
            renderObject(graphics, new ObjectMarkerData(this.objectMarkerManager.getEditingObjectMarker(), true));
        }

        var objects = this.objectMarkerManager.getObjects();
        if (objects.isEmpty()) {
            return null;
        }

        for (ObjectMarkerData objData : objects) {
            if (!objData.getMarker().isSticky() && objData.isExpired()) {
                this.objectMarkerManager.hideObjectMarker(objData.getMarker());
                continue;
            }
            renderObject(graphics, objData);
        }
        return null;
    }

    private void renderObject(Graphics2D graphics, ObjectMarkerData objData) {
        ObjectMarker obj = objData.getMarker();
        Stroke stroke = new BasicStroke((float) obj.getBorderWidth());
        TileObject object = obj.getTileObject();
        if (object == null || object.getPlane() != client.getTopLevelWorldView().getPlane())
        {
            return;
        }

        ObjectComposition composition = obj.getComposition();
        if (composition == null) {
            return;
        }
        if (composition.getImpostorIds() != null)
        {
            // This is a multiloc
            composition = composition.getImpostor();
            // Only mark the object if the name still matches
            if (composition == null
                || Strings.isNullOrEmpty(composition.getName())
                || "null".equals(composition.getName())
                || !composition.getName().equals(obj.getObjectPoint().getName()))
            {
                return;
            }
        }

        Color borderColor = obj.getBorderColor();

        final var flags = obj.getFlags();
        if ((flags & HF_HULL) != 0)
        {
            // default hull fill color is a=50 while the clickbox and tiles are a/12
            Color fillColor = MoreObjects.firstNonNull(obj.getFillColor(), new Color(0, 0, 0, 50));
            renderConvexHull(graphics, object, borderColor, fillColor, stroke);
        }

        if ((flags & HF_OUTLINE) != 0)
        {
            modelOutlineRenderer.drawOutline(object, (int)obj.getBorderWidth(), borderColor, obj.getOutlineFeather());
        }

        if ((flags & HF_CLICKBOX) != 0)
        {
            Shape clickbox = object.getClickbox();
            if (clickbox != null)
            {
                Color fillColor = MoreObjects.firstNonNull(obj.getFillColor(), ColorUtil.colorWithAlpha(borderColor, borderColor.getAlpha() / 12));
                OverlayUtil.renderPolygon(graphics, clickbox, borderColor, fillColor, stroke);
            }
        }

        if ((flags & HF_TILE) != 0)
        {
            Polygon tilePoly = object.getCanvasTilePoly();
            if (tilePoly != null)
            {
                Color fillColor = MoreObjects.firstNonNull(obj.getFillColor(), ColorUtil.colorWithAlpha(borderColor, borderColor.getAlpha() / 12));
                OverlayUtil.renderPolygon(graphics, tilePoly, borderColor, fillColor, stroke);
            }
        }
    }

    private void renderConvexHull(Graphics2D graphics, TileObject object, Color color, Color fillColor, Stroke stroke)
    {
        final Shape polygon;
        Shape polygon2 = null;

        if (object instanceof GameObject)
        {
            polygon = ((GameObject) object).getConvexHull();
        }
        else if (object instanceof WallObject)
        {
            polygon = ((WallObject) object).getConvexHull();
            polygon2 = ((WallObject) object).getConvexHull2();
        }
        else if (object instanceof DecorativeObject)
        {
            polygon = ((DecorativeObject) object).getConvexHull();
            polygon2 = ((DecorativeObject) object).getConvexHull2();
        }
        else if (object instanceof GroundObject)
        {
            polygon = ((GroundObject) object).getConvexHull();
        }
        else
        {
            polygon = object.getCanvasTilePoly();
        }

        if (polygon != null)
        {
            OverlayUtil.renderPolygon(graphics, polygon, color, fillColor, stroke);
        }

        if (polygon2 != null)
        {
            OverlayUtil.renderPolygon(graphics, polygon2, color, fillColor, stroke);
        }
    }
}