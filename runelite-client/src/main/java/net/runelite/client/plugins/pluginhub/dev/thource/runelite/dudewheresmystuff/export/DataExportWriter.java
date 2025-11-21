package net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.export;

import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.ItemStack;
import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.Storage;
import net.runelite.client.plugins.pluginhub.dev.thource.runelite.dudewheresmystuff.StorageManager;
import java.io.IOException;
import javax.annotation.Nullable;

public interface DataExportWriter {
  void writeItemStack(
      ItemStack itemStack,
      @Nullable StorageManager<?, ?> storageManager,
      @Nullable Storage<?> storage,
      boolean mergeItems);

  void writeHeader(boolean mergeItems, boolean shouldSplitUp) throws IOException;

  String getFileLocation();

  void close();
}
