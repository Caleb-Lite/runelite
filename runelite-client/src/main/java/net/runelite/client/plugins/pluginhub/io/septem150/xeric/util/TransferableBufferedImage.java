package net.runelite.client.plugins.pluginhub.io.septem150.xeric.util;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * Taken from: <a
 * href="https://github.com/runelite/runelite/blob/master/runelite-client/src/main/java/net/runelite/client/util/TransferableBufferedImage.java">TransferableBufferedImage</a>
 *
 * <p>Changes: - made class public, since it was package protected. - added @NonNull annotation to
 * {@link #getTransferData(DataFlavor)}
 */
@AllArgsConstructor
public class TransferableBufferedImage implements Transferable {
  @NonNull private final BufferedImage image;

  @Override
  public @NonNull Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
    if (flavor.equals(DataFlavor.imageFlavor)) {
      return image;
    } else {
      throw new UnsupportedFlavorException(flavor);
    }
  }

  @Override
  public DataFlavor[] getTransferDataFlavors() {
    return new DataFlavor[] {DataFlavor.imageFlavor};
  }

  @Override
  public boolean isDataFlavorSupported(DataFlavor flavor) {
    return flavor.equals(DataFlavor.imageFlavor);
  }
}
