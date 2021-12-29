package blob.enchantpack1.enchantments;


import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import blob.enchantpack1.EnchantPack1;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentSlotType;

public class Reaper extends Enchantment implements Listener {

	public Reaper(Rarity rarity, EnumItemSlot... aenumitemslot) {
		super(rarity, EnchantmentSlotType.f, aenumitemslot);
		EnchantPack1.Instance.getServer().getPluginManager().registerEvents(this, EnchantPack1.Instance);
	}
	
	@Override
	public int a(int i) {
	    return i * 50;
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
		return 1;
	}
	
	@Override
	public boolean a(Enchantment enchantment) {
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
