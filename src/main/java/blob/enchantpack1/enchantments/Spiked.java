package blob.enchantpack1.enchantments;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import blob.enchantlib.CustomEnchantment;
import blob.enchantlib.EnchantRarity;
import blob.enchantlib.EnchantSlot;
import blob.enchantlib.EnchantTarget;
import blob.enchantpack1.EnchantPack1;
public class Spiked extends CustomEnchantment implements Listener {

	public Spiked(EnchantRarity rarity, EnchantSlot... aenumitemslot) {
		super(rarity, EnchantTarget.BREAKABLE, aenumitemslot);
		EnchantPack1.Instance.getServer().getPluginManager().registerEvents(this, EnchantPack1.Instance);
	}
	
	@Override
	public int MinCost(int i) {
	    return i * 20;
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
		return var0.getType() == Material.SHIELD;
	}
	
	@EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
		ItemStack i = null;
		if (!(event.getEntity() instanceof Player)) return;
		Player p = (Player) event.getEntity();
		if (p.getInventory().getItemInMainHand() != null) {
			if (p.getInventory().getItemInMainHand().getType() == Material.SHIELD) {
				i = p.getInventory().getItemInMainHand();
			}
		}
		
		if (p.getInventory().getItemInOffHand() != null) {
			if (p.getInventory().getItemInOffHand().getType() == Material.SHIELD && i == null) {
				i = p.getInventory().getItemInOffHand();
			}
		}
		
		if(i!= null) {
			int level = i.getEnchantmentLevel(EnchantPack1.API.getBukkitEnchant(this));
			if (level > 0 && p.isBlocking()) {
				double rng = Math.random() * 100;
				double rng2 = Math.random() * level;
				if (rng > (80 -(10*level))) {
					if (event.getDamager() instanceof LivingEntity) {
						((LivingEntity)event.getDamager()).damage(level * rng2 , p);
					}
				}
			}
		}	
	}
}
