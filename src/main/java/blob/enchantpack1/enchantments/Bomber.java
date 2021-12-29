package blob.enchantpack1.enchantments;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.scheduler.BukkitRunnable;

import blob.enchantpack1.EnchantPack1;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentSlotType;

public class Bomber extends Enchantment implements Listener {

	public Bomber(Rarity rarity, EnumItemSlot... aenumitemslot) {
		super(rarity, EnchantmentSlotType.k, aenumitemslot);
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
		return true;
	}
	
	
	@Override
	public int a() {
		return 5;
	}
	
	@Override
	public boolean a(Enchantment enchantment) {
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
