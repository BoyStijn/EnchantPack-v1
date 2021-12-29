package blob.enchantpack1.enchantments;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import blob.enchantpack1.EnchantPack1;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.item.ItemElytra;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentSlotType;

public class Flight extends Enchantment implements Listener {

	public Flight(Rarity rarity, EnumItemSlot... aenumitemslot) {
		super(rarity, EnchantmentSlotType.l, aenumitemslot);
		EnchantPack1.Instance.getServer().getPluginManager().registerEvents(this, EnchantPack1.Instance);
	}
	
	@Override
	public int a(int i) {
	    return i * 20;
	}
	
	@Override
	public int b(int i) {
	    return a(i) + 15;
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
		return false;
	}
	
	@Override
	public boolean a(net.minecraft.world.item.ItemStack var0) {
		return (super.a(var0) && (var0.c() instanceof ItemElytra));
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
