package blob.enchantpack1.enchantments;

import java.util.List;

import org.bukkit.Tag;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import blob.enchantlib.CustomEnchantment;
import blob.enchantlib.EnchantRarity;
import blob.enchantlib.EnchantSlot;
import blob.enchantlib.EnchantTarget;
import blob.enchantpack1.EnchantPack1;

public class Slam extends CustomEnchantment implements Listener {

	public Slam(EnchantRarity rarity, EnchantSlot... aenumitemslot) {
		super(rarity, EnchantTarget.TOOLS, aenumitemslot);
		EnchantPack1.Instance.getServer().getPluginManager().registerEvents(this, EnchantPack1.Instance);
	}
	
	@Override
	public int MinCost(int i) {
	    return i * 30;
	}
	
	@Override
	public int MaxCost(int i) {
	    return MinCost(i) + 30;
	}
	
	@Override
	public boolean OnlyTreasure() {
		return false;
	}
	
	
	@Override
	public int MaxLvl() {
		return 3;
	}
	
	@Override
	public boolean isCompatible(Enchantment enchantment) {
		return true;
	}
	
	@Override
	public boolean canEnchant(ItemStack var0) {
		return Tag.MINEABLE_SHOVEL.isTagged(var0.getType());
	}
	
	@EventHandler
	public void onUse(PlayerInteractEvent event) {
        Player p = event.getPlayer();

        if (event.getAction() != Action.RIGHT_CLICK_AIR) return;
        if (p.getInventory().getItemInMainHand() == null) return;
        int level = p.getInventory().getItemInMainHand().getEnchantmentLevel(EnchantPack1.API.getBukkitEnchant(this));
        if (level <= 0) return;
        if (p.getLocation().clone().subtract(0, 1, 0).getBlock().isPassable()) return;
        
        p.setVelocity(new Vector(0, level, 0));
    }
	
	@EventHandler
    public void onMove(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		if (p.getInventory().getItemInMainHand() != null) {
			int level = p.getInventory().getItemInMainHand().getEnchantmentLevel(EnchantPack1.API.getBukkitEnchant(this));
			if (level > 0 && p.getFallDistance() > 3 + level) {
				Vector v = event.getTo().clone().subtract(event.getFrom().clone()).toVector().normalize();
				if (!event.getTo().clone().add(v.clone().multiply(0.5)).getBlock().isPassable()) {
					
					List<Entity> el = p.getNearbyEntities(level*3, level*3, level*3);
					for (Entity e : el) {
						if (e instanceof LivingEntity) {
							LivingEntity tel = (LivingEntity) e;
							Vector k = tel.getEyeLocation().clone().subtract(p.getLocation().clone()).toVector().normalize();
							k.multiply(level);
							k.setY(level);
							tel.setVelocity(k);
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if (event.getCause() != DamageCause.FALL) return;
		if (event.getEntity() instanceof Player) {
			Player p = (Player) event.getEntity();
			if (p.getInventory().getItemInMainHand() != null) {
				int level = p.getInventory().getItemInMainHand().getEnchantmentLevel(EnchantPack1.API.getBukkitEnchant(this));
				if (level > 0) {
					event.setDamage(0);
					event.setCancelled(true);
				}
			}
		}
	}
}
