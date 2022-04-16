package blob.enchantpack1.enchantments;

import org.bukkit.Tag;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import blob.enchantlib.CustomEnchantment;
import blob.enchantlib.EnchantRarity;
import blob.enchantlib.EnchantSlot;
import blob.enchantlib.EnchantTarget;
import blob.enchantpack1.EnchantPack1;

public class Digger extends CustomEnchantment implements Listener {

	public Digger(double rarity, EnchantSlot... aenumitemslot) {
		super(rarity, EnchantTarget.TOOLS, aenumitemslot);
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
		return 10;
	}
	
	@Override
	public boolean isCompatible(Enchantment enchantment) {
		return true;
	}
	
	@Override
	public boolean canEnchant(ItemStack var0) {
		return (Tag.MINEABLE_PICKAXE.isTagged(var0.getType()));
	}
	
	@EventHandler
    public void onDamage(BlockBreakEvent event) {
		ItemStack i = null;
		Player p = (Player) event.getPlayer();
		if (p.getInventory().getItemInMainHand() != null) {
				i = p.getInventory().getItemInMainHand();
		}
	
		
		if(i!= null) {
			int level = i.getEnchantmentLevel(EnchantPack1.API.getBukkitEnchant(this));
			if (level > 0) {
				double rng = Math.random() * 100;
				if (rng > (100 -(10*level))) {
					int count = 0;
					for (int x = -1; x <= 1; x ++) {
						for (int z = -1; z <= 1; z ++) {
							for (int y = -1; y <= 1; y ++) {
								if (event.getBlock().getRelative(x, y, z).isPreferredTool(i) && !event.getBlock().getRelative(x, y, z).isPassable()) {
									event.getBlock().getRelative(x, y, z).breakNaturally(i);
									count++;
								}
							}
						}
					}
					EnchantPack1.API.damageItem(count/(1 + level/5), i);
				}
			}
		}	
	}
}
