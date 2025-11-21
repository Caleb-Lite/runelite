package net.runelite.client.plugins.pluginhub.com.recoilplugin.overlays;

import net.runelite.client.plugins.pluginhub.com.recoilplugin.RecoilConfig;
import net.runelite.client.plugins.pluginhub.com.recoilplugin.RecoilPlugin;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.plugins.PluginInstantiationException;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.ImageComponent;
import net.runelite.client.util.ImageUtil;

@Slf4j
public class RecoilOverlay extends Overlay {

  private static final ScaledImage previouslyScaledImage = new ScaledImage();
  private static BufferedImage recoilImage;
  private final RecoilPlugin plugin;
  private final RecoilConfig recoilConfig;

  @Inject
  RecoilOverlay(RecoilPlugin plugin, RecoilConfig config) throws PluginInstantiationException {
    super(plugin);
    setPriority(OverlayPriority.MED);
    setPosition(OverlayPosition.BOTTOM_LEFT);
    setLayer(OverlayLayer.ALWAYS_ON_TOP);
    this.plugin = plugin;
    this.recoilConfig = config;
    loadRecoilImage();
    previouslyScaledImage.scale = 1;
    previouslyScaledImage.scaledBufferedImage = recoilImage;
  }

  private static void loadRecoilImage() {
    recoilImage = ImageUtil.getResourceStreamFromClass(RecoilPlugin.class, "/ring_of_recoil.png");
  }

  @Override
  public Dimension render(Graphics2D graphics) {
    if (!plugin.isRecoilPresent()) {
      return null;
    }

    BufferedImage scaledRecoilImage = scaleImage(recoilImage);
    ImageComponent imagePanelComponent = new ImageComponent(scaledRecoilImage);
    return imagePanelComponent.render(graphics);
  }

  private BufferedImage scaleImage(BufferedImage recoilImage) {
    if (previouslyScaledImage.scale == recoilConfig.scale() || recoilConfig.scale() <= 0) {
      return previouslyScaledImage.scaledBufferedImage;
    }
    log.debug("Rescaling image!");
    int w = recoilImage.getWidth();
    int h = recoilImage.getHeight();
    BufferedImage scaledRecoilImage =
        new BufferedImage(
            recoilConfig.scale() * w, recoilConfig.scale() * h, BufferedImage.TYPE_INT_ARGB);
    AffineTransform at = new AffineTransform();
    at.scale(recoilConfig.scale(), recoilConfig.scale());
    AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
    scaledRecoilImage = scaleOp.filter(recoilImage, scaledRecoilImage);
    previouslyScaledImage.scaledBufferedImage = scaledRecoilImage;
    previouslyScaledImage.scale = recoilConfig.scale();
    return scaledRecoilImage;
  }

  private static class ScaledImage {
    private int scale;
    private BufferedImage scaledBufferedImage;
  }
}

