package blob.enchantpack1.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import blob.enchantlib.CustomEnchantment;
import blob.enchantlib.EnchantSlot;
import blob.enchantlib.EnchantTarget;
import blob.enchantpack1.EnchantPack1;
public class Lagging extends CustomEnchantment implements Listener {

	public Lagging(double rarity, EnchantSlot[] aenumitemslot) {
		super(rarity, EnchantTarget.ARMOR_HEAD, aenumitemslot);
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
	public boolean isCursed() {
		return true;
	}
	
	@Override
	public int MaxLvl() {
		return 3;
	}
	
	@Override
	public boolean canTrade() {
		return false;
	}
	
	
	@Override
	public boolean isCompatible(Enchantment enchantment) {
		return true;
	}
	
	@Override
	public boolean canEnchant(ItemStack var0) {
		return true;
	}
	
	@EventHandler
    public void onShoot(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		if(p.getInventory().getHelmet() != null) {
			int level = p.getInventory().getHelmet().getEnchantmentLevel(EnchantPack1.API.getBukkitEnchant(this));
			if (level > 0) {
				double rng = Math.random() * 100;
				if (rng > (100 -(10*level))) {
					event.getPlayer().teleport(event.getFrom());
				}
			}
		}	
	}
}
