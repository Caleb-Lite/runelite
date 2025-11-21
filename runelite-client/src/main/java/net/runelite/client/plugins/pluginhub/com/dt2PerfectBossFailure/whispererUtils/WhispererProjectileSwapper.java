package net.runelite.client.plugins.pluginhub.com.dt2PerfectBossFailure.whispererUtils;

import net.runelite.client.plugins.pluginhub.com.dt2PerfectBossFailure.dt2pbfConfig;
import net.runelite.client.plugins.pluginhub.com.dt2PerfectBossFailure.dt2pbfPlugin;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Projectile;
import net.runelite.api.events.ProjectileMoved;
import net.runelite.client.eventbus.Subscribe;

@Slf4j
public class WhispererProjectileSwapper
{
	// Vardorvis' Head Projectile IDs
	private static final int MAGIC_PROJECTILE = 2445;
	private static final int RANGE_PROJECTILE = 2444;

	private final Client client;
	private final dt2pbfConfig config;
	private final dt2pbfPlugin plugin;

	@Inject
	private WhispererProjectileSwapper(Client client, dt2pbfPlugin plugin, dt2pbfConfig config)
	{
		this.client = client;
		this.plugin = plugin;
		this.config = config;
	}

	@Subscribe
	public void onProjectileMoved(ProjectileMoved projectileMoved)
	{
		if(config.whispererProjectileStyle()== dt2pbfConfig.WhispererStyle.Default)
			return;
		Projectile projectile = projectileMoved.getProjectile();
		if (projectile.getId() == RANGE_PROJECTILE)
		{
			replaceProjectile(projectile, config.whispererProjectileStyle().getRange());
		}
		else if (projectile.getId() == MAGIC_PROJECTILE)
		{
			replaceProjectile(projectile, config.whispererProjectileStyle().getMagic());
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
