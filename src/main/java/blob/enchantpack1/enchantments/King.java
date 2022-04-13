package blob.enchantpack1.enchantments;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.PiglinAbstract;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.inventory.ItemStack;

import blob.enchantlib.CustomEnchantment;
import blob.enchantlib.EnchantRarity;
import blob.enchantlib.EnchantSlot;
import blob.enchantlib.EnchantTarget;
import blob.enchantpack1.EnchantPack1;

public class King extends CustomEnchantment implements Listener{

	public King(EnchantRarity rarity, EnchantSlot... aenumitemslot) {
		super(rarity, EnchantTarget.ARMOR_HEAD, aenumitemslot);
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
		return false;
	}
	
	@Override
	public int MaxLvl() {
		return 5;
	}
	
	@Override
	public boolean isCompatible(Enchantment enchantment) {
		return true;
	}
	
	@Override
	public boolean canEnchant(ItemStack var0) {
		return var0.getType() == Material.GOLDEN_HELMET;
	}
	
	@EventHandler
	public void onTarget(EntityTargetEvent event) {
		ItemStack i = null;
		if (!(event.getTarget() instanceof Player)) return;
		Player p = (Player) event.getTarget();
		if (p.getInventory().getHelmet() != null) {
			if (p.getInventory().getHelmet().getType() == Material.GOLDEN_HELMET) {
				i = p.getInventory().getHelmet();
			}
		}
		
		if(i!= null) {
			int level = i.getEnchantmentLevel(EnchantPack1.API.getBukkitEnchant(this));
			if (level > 0) {
				if (event.getEntity() instanceof PiglinAbstract) {
					double rng = Math.random() * 100;
					if (rng > (100 -(20*level))) {
						event.setTarget(null);
					}
				}
			}
		}
	}
}
