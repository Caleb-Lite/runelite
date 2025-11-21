package net.runelite.client.plugins.pluginhub.com.godwarsprot;

import com.godwarsprot.items.*;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.Item;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

@Slf4j
@PluginDescriptor(
	name = "God Wars Protection Overlay"
)
public class GodWarsProtPlugin extends Plugin
{
	//Zamorak scimitar Zamorak-painted heraldic kiteshields Zamorak-painted heraldic helmets not included
	//Saradomin scimitar Saradomin-painted heraldic kiteshields Saradomin-painted heraldic helmets not included
	// Item Source https://oldschool.runescape.wiki/w/God_Wars_Dungeon#God_equipment

	public static boolean hasBandos = false;
	public static boolean hasArma = false;
	public static boolean hasZam = false;
	public static boolean hasAnc = false;
	public static boolean hasSara = false;

	@Inject
	private Client client;
	@Inject
	private OverlayManager overlayManager;
	@Inject
	private GodWarsProtConfig config;
	@Inject
	private GodWarsProtOverlay overlay;
	@Inject
	private GodWarsProtItemOverlay itemOverlay;
	@Inject
	private Armadyl armadyl;
	@Inject
	private Bandos bandos;
	@Inject
	private Zamorak zamorak;
	@Inject
	private Saradomin saradomin;
	@Inject
	private Ancient ancient;
	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
		overlayManager.add(itemOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		overlayManager.remove(itemOverlay);
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		if (event.getItemContainer() != client.getItemContainer(InventoryID.EQUIPMENT)) {
			return;
		}
		checkItems(event.getItemContainer().getItems());

	}

	private void checkItems(Item[] items){

		boolean sara2 = false;
		boolean bandos2 =  false;
		boolean arma2 = false;
		boolean zam2 = false;
		boolean anc2 =  false;

		for (Item item : items){

			if (!arma2){
				if (armadyl.checkList(item.getId())){
					arma2 = true;
				}
			}
			if (!bandos2){
				if (bandos.checkList(item.getId())){
					bandos2 = true;
				}
			}
			if (!zam2){
				if (zamorak.checkList(item.getId())){
					zam2 = true;
				}

			}
			if (!sara2){
				if (saradomin.checkList(item.getId())){
					sara2 = true;
				}
			}
			if (!anc2){
				if (ancient.checkList(item.getId())){
					anc2 = true;
				}
			}

		}
		hasArma = arma2;
		hasZam = zam2;
		hasBandos = bandos2;
		hasAnc = anc2;
		hasSara = sara2;


	}



	@Provides
	GodWarsProtConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(GodWarsProtConfig.class);
	}
}
