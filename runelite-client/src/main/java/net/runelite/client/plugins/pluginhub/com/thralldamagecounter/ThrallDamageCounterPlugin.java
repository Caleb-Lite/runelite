package net.runelite.client.plugins.pluginhub.com.thralldamagecounter;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.http.api.item.ItemEquipmentStats;
import net.runelite.http.api.item.ItemStats;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@PluginDescriptor(
	name = "Thrall Damage Counter"
)
public class ThrallDamageCounterPlugin extends Plugin
{
	public DamageMember activeThrallDamage;

	private ArrayList<Hitsplat> hitsplatsInTick = new ArrayList<>();

	private boolean isAttackTick;
	int oldMagicXP;
	int oldRangedXP;
	int oldAttackXP;
	int oldStrengthXP;
	int oldDefenceXP;
	int latestXPDiff;

	public void resetTracking() {
		if(activeThrallDamage != null)
			activeThrallDamage.reset();
	}


	public enum AttackState {
		NOT_ATTACKING,
		DELAYED
	}

	final int ATTACK_DELAY_NONE = 0;
	public int attackDelayHoldoffTicks = ATTACK_DELAY_NONE;

	public AttackState attackState = AttackState.NOT_ATTACKING;
	String walkHereMenuOption = "Walk here";


	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	ThrallDamageCounterOverlay overlay;

	@Inject
	private ItemManager itemManager;

	@Subscribe
	public void onHitsplatApplied(HitsplatApplied hitsplatApplied)
	{
		Actor actor = hitsplatApplied.getActor();
		if (!(actor instanceof NPC))
		{
			return;
		}

		Hitsplat hitsplat = hitsplatApplied.getHitsplat();

		if(actor != client.getLocalPlayer() && hitsplat.isMine()) {
			hitsplatsInTick.add(hitsplat);
		}
	}

	private boolean isPlayerAttacking()
	{
		return AnimationData.fromId(client.getLocalPlayer().getAnimation()) != null;
	}

	private ItemStats getItemStatsFromContainer(ItemContainer container, int slotID)
	{
		final Item item = container.getItem(slotID);
		return item != null ? itemManager.getItemStats(item.getId(), false) : null;
	}

	private ItemStats getWeaponStats()
	{
		return getItemStatsFromContainer(client.getItemContainer(InventoryID.EQUIPMENT),
				EquipmentInventorySlot.WEAPON.getSlotIdx());
	}

	private AttackStyle getAttackStyle()
	{
		final int currentAttackStyleVarbit = client.getVarpValue(VarPlayer.ATTACK_STYLE);
		final int currentEquippedWeaponTypeVarbit = client.getVarbitValue(Varbits.EQUIPPED_WEAPON_TYPE);
		AttackStyle[] attackStyles = WeaponType.getWeaponType(currentEquippedWeaponTypeVarbit).getAttackStyles();

		if (currentAttackStyleVarbit < attackStyles.length) {
			return attackStyles[currentAttackStyleVarbit];
		}

		return AttackStyle.ACCURATE;
	}

	private int getWeaponSpeed()
	{
		ItemStats weaponStats = getWeaponStats();
		if (weaponStats == null) {
			return 4; // Assume barehanded == 4t
		}

		ItemEquipmentStats e = weaponStats.getEquipment();

		int speed = e.getAspeed();
		if (getAttackStyle() == AttackStyle.RANGING &&
				client.getVarpValue(VarPlayer.ATTACK_STYLE) == 1) { // Hack for index 1 => rapid
			speed -= 1; // Assume ranging == rapid.
		}

		return speed; // Deadline for next available attack.
	}

	private void performAttack()
	{
		attackState = AttackState.DELAYED;
		attackDelayHoldoffTicks = getWeaponSpeed();
	}

	@Subscribe
	public void onInteractingChanged(InteractingChanged interactingChanged)
	{
		Actor source = interactingChanged.getSource();
		Actor target = interactingChanged.getTarget();

		Player p = client.getLocalPlayer();

		if (source.equals(p) && (target instanceof NPC)) {

			switch (attackState) {
				case NOT_ATTACKING:
					// If not previously attacking, this action can result in a queued attack or
					// an instant attack. If its queued, don't trigger the cooldown yet.
					if (isPlayerAttacking()) {
						performAttack();
					}
					break;

				//case PENDING:
				case DELAYED:
					// Don't reset tick counter or tick period.
					break;
			}
		}
	}

	@Subscribe
	public void onGameTick(GameTick tick)
	{
		String name = client.getLocalPlayer().getName();
		if(name != null && activeThrallDamage == null) {
			activeThrallDamage = new DamageMember(name + "'s Thrall");
		}

		boolean isAttacking = isPlayerAttacking(); // Heuristic for attacking based on animation.

		switch (attackState) {
			case NOT_ATTACKING:
				if (isAttacking) {
					performAttack(); // Sets state to DELAYED.
				}
				break;
			case DELAYED:
				if (attackDelayHoldoffTicks <= 0) { // Eligible for a new attack
					if (isAttacking) {
						// Found an attack animation. Assume auto attack triggered.
						performAttack();
					} else {
						// No attack animation; assume no attack.
						attackState = AttackState.NOT_ATTACKING;
					}
				}
		}

		isAttackTick = attackState != AttackState.NOT_ATTACKING && attackDelayHoldoffTicks == (getWeaponSpeed() - 1);
		attackDelayHoldoffTicks--;

		int magicXP = client.getSkillExperience(Skill.MAGIC);
		int rangedXP = client.getSkillExperience(Skill.RANGED);
		int attackXP = client.getSkillExperience(Skill.ATTACK);
		int strengthXP = client.getSkillExperience(Skill.STRENGTH);
		int defenceXP = client.getSkillExperience(Skill.DEFENCE);

		int xpDiff = magicXP != oldMagicXP ? magicXP - oldMagicXP :
				rangedXP != oldRangedXP ? rangedXP - oldRangedXP :
						(attackXP - oldAttackXP) + (defenceXP - oldDefenceXP) + (strengthXP - oldStrengthXP);
		if(xpDiff != 0)
			latestXPDiff = xpDiff;

		if(hitsplatsInTick.size() > 0) {
			// Identify xp drop this tick and based off that identify which hitsplat was the player's
			Optional<Hitsplat> firstSplat = hitsplatsInTick.stream().min((h1, h2) -> {
//				System.out.println(h1.getAmount() + " : " + h2.getAmount() + " : " + latestXPDiff);

				int distanceFromXPDrop = Math.abs(h1.getAmount() - (latestXPDiff/4));
				int distanceFromXPDrop2 = Math.abs(h2.getAmount() - (latestXPDiff/4));
				return Integer.compare(distanceFromXPDrop2, distanceFromXPDrop);
			});
			latestXPDiff = 0;

			if(!isAttackTick || hitsplatsInTick.size() > 1) {
				firstSplat.ifPresent(hitsplat ->  {
					int amount = hitsplat.getAmount();
					System.out.println("Adding thrall damage: " + amount);
					activeThrallDamage.addDamage(amount);
				});
			}
		}
		hitsplatsInTick.clear();

		if(magicXP != oldMagicXP)
			oldMagicXP = magicXP;

		if(rangedXP != oldRangedXP)
			oldRangedXP = rangedXP;

		if(attackXP != oldAttackXP)
			oldAttackXP = attackXP;

		if(strengthXP != oldStrengthXP)
			oldStrengthXP = strengthXP;

		if(defenceXP != oldDefenceXP)
			oldDefenceXP = defenceXP;
	}

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
		log.info("Thrall Damage Counter started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		log.info("Thrall Damage Counter stopped!");
	}

	@Provides
	ThrallDamageCounterConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ThrallDamageCounterConfig.class);
	}

	@Subscribe
	private void onMenuOptionClicked(MenuOptionClicked event) {
		String menuOption = event.getMenuOption();

		if (Objects.equals(menuOption, walkHereMenuOption)) {
			attackDelayHoldoffTicks = ATTACK_DELAY_NONE;
			attackState = AttackState.NOT_ATTACKING;
		}
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event) {
		ItemContainer itemContainer = event.getItemContainer();
		if (itemContainer != client.getItemContainer(InventoryID.EQUIPMENT)) {
			return;
		}

		attackDelayHoldoffTicks = ATTACK_DELAY_NONE;
		attackState = AttackState.NOT_ATTACKING;
	}
}

