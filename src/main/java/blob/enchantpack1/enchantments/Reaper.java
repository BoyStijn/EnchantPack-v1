package blob.enchantpack1.enchantments;


import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import blob.enchantlib.CustomEnchantment;
import blob.enchantlib.EnchantRarity;
import blob.enchantlib.EnchantSlot;
import blob.enchantlib.EnchantTarget;
import blob.enchantpack1.EnchantPack1;

public class Reaper extends CustomEnchantment implements Listener {

	public Reaper(EnchantRarity rarity, EnchantSlot... aenumitemslot) {
		super(rarity, EnchantTarget.SWORD, aenumitemslot);
		EnchantPack1.Instance.getServer().getPluginManager().registerEvents(this, EnchantPack1.Instance);
	}
	
	@Override
	public int MinCost(int i) {
	    return i * 50;
	}
	
	@Override
	public int MaxCost(int i) {
	    return MinCost(i) + 30;
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
    public void onDamage(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player p = (Player)event.getDamager();
			if(p.getInventory().getItemInMainHand() != null) {
				int level = p.getInventory().getItemInMainHand().getEnchantmentLevel(EnchantPack1.API.getBukkitEnchant(this));
				if (level > 0) {
					double rng = Math.random() * 1001;
					if (rng > (1000 -(2*level)) && event.getEntity() instanceof LivingEntity) {
						((LivingEntity)event.getEntity()).setHealth(0);
					}
				}
			}
		}		
	}
}
