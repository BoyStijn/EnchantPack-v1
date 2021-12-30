package blob.enchantpack1.enchantments;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Cow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import blob.enchantpack1.EnchantPack1;
import blob.enchantpack1.PathfinderGoalAttackEntity;
import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.entity.ai.attributes.GenericAttributes;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.ai.goal.PathfinderGoalFloat;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLeapAtTarget;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomLookaround;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomStrollLand;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentSlotType;

public class BullRider extends Enchantment implements Listener{

	private static HashMap<UUID, UUID> bulls = new HashMap<UUID, UUID>();
	
	public BullRider(Rarity rarity, EnumItemSlot... aenumitemslot) {
		super(rarity, EnchantmentSlotType.d, aenumitemslot);
		EnchantPack1.Instance.getServer().getPluginManager().registerEvents(this, EnchantPack1.Instance);
	}
	
	@Override
	public int a(int i) {
	    return i * 10;
	}
	
	@Override
	public int b(int i) {
	    return a(i) + 15;
	}
	
	@Override
	public boolean b() {
		return true;
	}
	
	@Override
	public int a() {
		return 3;
	}
	
	@Override
	public boolean a(Enchantment enchantment) {
		return true;
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof LivingEntity)) return;
		if (event.getEntity() instanceof Player) {
			Player p = (Player) event.getEntity();
			if (event.getDamager().isDead()) return;
			if (p.getInventory().getChestplate() != null) {
				int level = p.getInventory().getChestplate().getEnchantmentLevel(EnchantPack1.API.getBukkitEnchant(this));
				if (level > 0 && !bulls.containsValue(p.getUniqueId())) {
					for (int i = 0; i < level; i++) {
						Cow cw = (Cow) p.getWorld().spawnEntity(p.getLocation(), EntityType.COW);
						EnchantPack1.API.addAttributes(cw, GenericAttributes.f, 2.0D);
						EnchantPack1.API.addAttributes(cw, GenericAttributes.d, 0.4D);
						EntityCreature cwnsm = EnchantPack1.API.getNSMEntity(cw);
						HashMap<PathfinderGoal, Integer> goal = new HashMap<PathfinderGoal, Integer>();
						HashMap<PathfinderGoal, Integer> target = new HashMap<PathfinderGoal, Integer>();
						goal.put(new PathfinderGoalFloat(cwnsm), 0);
						goal.put(new PathfinderGoalMeleeAttack(cwnsm, 1.0, true), 2);
						goal.put(new PathfinderGoalLeapAtTarget(cwnsm, 0.4f), 1);
					    goal.put(new PathfinderGoalRandomStrollLand(cwnsm, 1.0D), 5);
					    goal.put(new PathfinderGoalLookAtPlayer(cwnsm, EntityHuman.class, 6.0F), 6);
					    goal.put(new PathfinderGoalRandomLookaround(cwnsm), 7);
					    target.put(new PathfinderGoalAttackEntity(cwnsm, EnchantPack1.API.getNSMEntity((LivingEntity)event.getDamager())), 0);
						EnchantPack1.API.overrideGoals(cw, goal, target);
						bulls.put(cw.getUniqueId(), p.getUniqueId());
						cw.setInvulnerable(true);
						BukkitRunnable br = new BukkitRunnable() {

							@Override
							public void run() {
								bulls.remove(cw.getUniqueId());
								cw.remove();
							}
							
						};
						br.runTaskLater(EnchantPack1.Instance, 100);
					}
				}
			}
		}
	}
	
}
