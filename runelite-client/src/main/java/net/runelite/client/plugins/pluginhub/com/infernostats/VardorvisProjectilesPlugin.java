package net.runelite.client.plugins.pluginhub.com.infernostats;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Projectile;
import net.runelite.api.events.ProjectileMoved;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Vardorvis Projectiles",
	description = "Alter the Projectiles for Vardorvis' Prayer-Disable Attack",
	tags = {"dt2", "boss", "pvm"}
)
public class VardorvisProjectilesPlugin extends Plugin
{
	// Vardorvis' Head Projectile IDs
	private static final int MAGIC_PROJECTILE = 2520;
	private static final int RANGE_PROJECTILE = 2521;

	@Inject
	private Client client;

	@Inject
	private VardorvisProjectilesConfig config;

	@Provides
	VardorvisProjectilesConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(VardorvisProjectilesConfig.class);
	}

	@Subscribe
	public void onProjectileMoved(ProjectileMoved projectileMoved)
	{
		Projectile projectile = projectileMoved.getProjectile();
		if (projectile.getId() == RANGE_PROJECTILE)
		{
			replaceProjectile(projectile, config.style().getRange());
		}
		else if (projectile.getId() == MAGIC_PROJECTILE)
		{
			replaceProjectile(projectile, config.style().getMagic());
		}
	}

	private void replaceProjectile(Projectile projectile, int projectileId)
	{
		Projectile p = client.createProjectile(projectileId,
			projectile.getFloor(),
			projectile.getX1(), projectile.getY1(),
			projectile.getHeight(),
			projectile.getStartCycle(), projectile.getEndCycle(),
			projectile.getSlope(),
			projectile.getStartHeight(), projectile.getEndHeight(),
			projectile.getInteracting(),
			projectile.getTarget().getX(), projectile.getTarget().getY());

		client.getProjectiles().addLast(p);
		projectile.setEndCycle(0);
	}
}

