package blob.enchantpack1.enchantments;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import blob.enchantpack1.EnchantPack1;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.item.ItemPickaxe;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentSlotType;

public class Digger extends Enchantment implements Listener {

	public Digger(Rarity rarity, EnumItemSlot... aenumitemslot) {
		super(rarity, EnchantmentSlotType.g, aenumitemslot);
		EnchantPack1.Instance.getServer().getPluginManager().registerEvents(this, EnchantPack1.Instance);
	}
	
	@Override
	public int a(int i) {
	    return i * 10;
	}
	
	@Override
	public int b(int i) {
	    return a(i) + 15;
	}
	
	@Override
	public boolean b() {
		return false;
	}
	
	
	@Override
	public int a() {
		return 10;
	}
	
	@Override
	public boolean a(Enchantment enchantment) {
		return true;
	}
	
	@Override
	public boolean a(net.minecraft.world.item.ItemStack var0) {
		return (super.a(var0) && (var0.c() instanceof ItemPickaxe));
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
