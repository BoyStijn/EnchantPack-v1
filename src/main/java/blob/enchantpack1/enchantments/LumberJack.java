package blob.enchantpack1.enchantments;

import java.util.ArrayList;
import java.util.HashSet;

import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import blob.enchantpack1.EnchantPack1;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.item.ItemAxe;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentSlotType;

public class LumberJack extends Enchantment implements Listener {

	public LumberJack(Rarity rarity, EnumItemSlot... aenumitemslot) {
		super(rarity, EnchantmentSlotType.g, aenumitemslot);
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
		return (super.a(var0) && (var0.c() instanceof ItemAxe));
	}
	
	@EventHandler
    public void onShoot(BlockBreakEvent event) {
		Player p = event.getPlayer();
		if(p.getInventory().getItemInMainHand() != null) {
			int level = p.getInventory().getItemInMainHand().getEnchantmentLevel(EnchantPack1.API.getBukkitEnchant(this));
			if (level > 0 && Tag.LOGS.isTagged(event.getBlock().getType())) {
				double rng = Math.random() * 100;
				if (rng > (100 -(33*level))) {
					ArrayList<Block> blocks = new ArrayList<Block>();
					ArrayList<Block> torem = new ArrayList<Block>();
					ArrayList<Block> toadd = new ArrayList<Block>();
					blocks.add(event.getBlock());
					int count = 0;
					while (!blocks.isEmpty()) {
						for (Block b : blocks) {
							for (int x = -1; x <= 1; x ++) {
								for (int z = -1; z <= 1; z ++) {
									for (int y = -1; y <= 1; y ++) {
										if (Tag.LOGS.isTagged(b.getRelative(x, y, z).getType())) {
											toadd.add(b.getRelative(x, y, z));
										}
									}
								}
							}
							b.breakNaturally(p.getInventory().getItemInMainHand());
							torem.add(b);
							count++;
						}
						blocks.removeAll(torem);
						blocks.addAll(toadd);
						torem.clear();
						toadd.clear();
						blocks = new ArrayList<>(new HashSet<>(blocks));
					}
					EnchantPack1.API.damageItem(count, p.getInventory().getItemInMainHand());
				}
			}
		}	
	}
}
