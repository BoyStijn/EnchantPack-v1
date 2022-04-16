package blob.enchantpack1.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import blob.enchantlib.CustomEnchantment;
import blob.enchantlib.EnchantRarity;
import blob.enchantlib.EnchantSlot;
import blob.enchantlib.EnchantTarget;
import blob.enchantpack1.EnchantPack1;
public class JellyLegs extends CustomEnchantment implements Listener{

	public JellyLegs(double rarity, EnchantSlot... aenumitemslot) {
		super(rarity, EnchantTarget.ARMOR_FEET, aenumitemslot);
		EnchantPack1.Instance.getServer().getPluginManager().registerEvents(this, EnchantPack1.Instance);
	}
	
	@Override
	public int MinCost(int i) {
	    return i * 10;
	}
	
	@Override
	public int MaxCost(int i) {
	    return MinCost(i) + 15;
	}
	
	@Override
	public boolean OnlyTreasure() {
		return true;
	}
	
	@Override
	public int MaxLvl() {
		return 1;
	}
	
	@Override
	public boolean isCompatible(Enchantment enchantment) {
		return true;
	}
	
	@EventHandler
    public void onMove(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		if (p.getInventory().getBoots() != null) {
			int level = p.getInventory().getBoots().getEnchantmentLevel(EnchantPack1.API.getBukkitEnchant(this));
			if (level > 0 && p.getFallDistance() > 3) {
				Vector v = event.getTo().clone().subtract(event.getFrom().clone()).toVector().normalize();
				if (!event.getTo().clone().add(v.clone().multiply(0.5)).getBlock().isPassable()) {
					v.setY(v.getY() * -0.1 * p.getFallDistance());
					v.setX(v.getX() * 0.1 * p.getFallDistance());
					v.setZ(v.getZ() * 0.1 * p.getFallDistance());
					p.setVelocity(v);
				}
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if (event.getCause() != DamageCause.FALL) return;
		if (event.getEntity() instanceof Player) {
			Player p = (Player) event.getEntity();
			if (p.getInventory().getBoots() != null) {
				int level = p.getInventory().getBoots().getEnchantmentLevel(EnchantPack1.API.getBukkitEnchant(this));
				if (level > 0) {
					event.setDamage(0);
					event.setCancelled(true);
				}
			}
		}
	}
}
