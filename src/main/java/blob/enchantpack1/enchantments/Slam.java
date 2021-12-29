package blob.enchantpack1.enchantments;

import java.util.List;

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
import org.bukkit.util.Vector;

import blob.enchantpack1.EnchantPack1;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.item.ItemSpade;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentSlotType;

public class Slam extends Enchantment implements Listener {

	public Slam(Rarity rarity, EnumItemSlot... aenumitemslot) {
		super(rarity, EnchantmentSlotType.g, aenumitemslot);
		EnchantPack1.Instance.getServer().getPluginManager().registerEvents(this, EnchantPack1.Instance);
	}
	
	@Override
	public int a(int i) {
	    return i * 30;
	}
	
	@Override
	public int b(int i) {
	    return a(i) + 30;
	}
	
	@Override
	public boolean b() {
		return false;
	}
	
	
	@Override
	public int a() {
		return 3;
	}
	
	@Override
	public boolean a(Enchantment enchantment) {
		return true;
	}
	
	@Override
	public boolean a(net.minecraft.world.item.ItemStack var0) {
		return (super.a(var0) && (var0.c() instanceof ItemSpade));
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
