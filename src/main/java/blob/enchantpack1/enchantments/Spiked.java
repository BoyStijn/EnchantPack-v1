package blob.enchantpack1.enchantments;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import blob.enchantpack1.EnchantPack1;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.item.ItemShield;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentSlotType;

public class Spiked extends Enchantment implements Listener {

	public Spiked(Rarity rarity, EnumItemSlot... aenumitemslot) {
		super(rarity, EnchantmentSlotType.j, aenumitemslot);
		EnchantPack1.Instance.getServer().getPluginManager().registerEvents(this, EnchantPack1.Instance);
	}
	
	@Override
	public int a(int i) {
	    return i * 20;
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
		return (super.a(var0) && (var0.c() instanceof ItemShield));
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
