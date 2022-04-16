package blob.enchantpack1.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.scheduler.BukkitRunnable;

import blob.enchantlib.CustomEnchantment;
import blob.enchantlib.EnchantRarity;
import blob.enchantlib.EnchantSlot;
import blob.enchantlib.EnchantTarget;
import blob.enchantpack1.EnchantPack1;

public class Bomber extends CustomEnchantment implements Listener {

	public Bomber(double rarity, EnchantSlot... aenumitemslot) {
		super(rarity, EnchantTarget.BOW, aenumitemslot);
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
		return true;
	}
	
	
	@Override
	public int MaxLvl() {
		return 5;
	}
	
	@Override
	public boolean isCompatible(Enchantment enchantment) {
		return true;
	}
	
	@EventHandler
    public void onShoot(EntityShootBowEvent event) {
		if(event.getBow() != null) {
			int level = event.getBow().getEnchantmentLevel(EnchantPack1.API.getBukkitEnchant(this));
			if (level > 0) {
				double rng = Math.random() * 100;
				if (rng > (70 -(10*level))) {
					Entity e = event.getProjectile();
					if (e instanceof Arrow) {
						Arrow a = (Arrow)e;
						BukkitRunnable br = new BukkitRunnable() {

							@Override
							public void run() {
								if (a.isInBlock()) {
									a.getWorld().createExplosion(a.getLocation(), level, false);
									a.remove();
									this.cancel();
								}
							}
							
						};
						br.runTaskTimer(EnchantPack1.Instance, 0, 5);
					}
				}
			}
		}	
	}
}
