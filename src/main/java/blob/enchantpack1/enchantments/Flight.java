package blob.enchantpack1.enchantments;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import blob.enchantlib.CustomEnchantment;
import blob.enchantlib.EnchantRarity;
import blob.enchantlib.EnchantSlot;
import blob.enchantlib.EnchantTarget;
import blob.enchantpack1.EnchantPack1;

public class Flight extends CustomEnchantment implements Listener {

	public Flight(double rarity, EnchantSlot... aenumitemslot) {
		super(rarity, EnchantTarget.ARMOR_CHEST, aenumitemslot);
		EnchantPack1.Instance.getServer().getPluginManager().registerEvents(this, EnchantPack1.Instance);
	}
	
	@Override
	public int MinCost(int i) {
	    return i * 20;
	}
	
	@Override
	public int MaxCost(int i) {
	    return MinCost(i) + 15;
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
	
	@Override
	public boolean canEnchant(ItemStack var0) {
		return var0.getType() == Material.ELYTRA;
	}
	
	@EventHandler
    public void onBoost(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        ItemStack item = event.getItem();

        if (!p.isGliding()) return;
        if (event.getAction() != Action.RIGHT_CLICK_AIR) return;
        if (item == null) return;
        if (item.getType() != Material.FIREWORK_ROCKET) return;
        if (p.getInventory().getChestplate() == null) return;
        if (p.getInventory().getChestplate().getType() != Material.ELYTRA) return;
        int level = p.getInventory().getChestplate().getEnchantmentLevel(EnchantPack1.API.getBukkitEnchant(this));
        if (level <= 0) return;

        FireworkMeta meta = (FireworkMeta) item.getItemMeta();
        int defpow = meta.getPower();
        meta.setPower(meta.getPower() + (4 * level));
        item.setItemMeta(meta);
        
        Bukkit.getScheduler().runTask(EnchantPack1.Instance, new Runnable() {

			@Override
			public void run() {
				meta.setPower(defpow);
				item.setItemMeta(meta);
			}
        	
        });
        
    }

}
