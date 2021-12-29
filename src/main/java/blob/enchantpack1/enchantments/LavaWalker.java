package blob.enchantpack1.enchantments;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import blob.enchantpack1.EnchantPack1;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentSlotType;
import net.minecraft.world.item.enchantment.Enchantments;

public class LavaWalker extends Enchantment implements Listener{

	public LavaWalker(Rarity rarity, EnumItemSlot... aenumitemslot) {
		super(rarity, EnchantmentSlotType.b, aenumitemslot);
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
		return true;
	}
	
	@Override
	public int a() {
		return 2;
	}
	
	@Override
	public boolean a(Enchantment enchantment) {
		return (enchantment != Enchantments.I && enchantment != Enchantments.j);
	}
	
	@EventHandler
    public void onMove(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		if(p.getInventory().getBoots() != null) {
			int level = p.getInventory().getBoots().getEnchantmentLevel(EnchantPack1.API.getBukkitEnchant(this));
			if (level > 0 && !p.getLocation().getBlock().getRelative(0, -1, 0).isPassable()) {
				int radius = (int) Math.min(16, 2 + level);
				final Block block = event.getTo().getBlock(); //placed block
				for (int x = -(radius); x <= radius; x ++) {
					for (int z = -(radius); z <= radius; z ++) {
						if (block.getRelative(x,-1,z).getType() == Material.LAVA && block.getRelative(0, -1, 0).getLocation().distance(block.getRelative(x,-1,z).getLocation()) <= radius) {
							block.getRelative(x,-1,z).setType(Material.MAGMA_BLOCK);
							Block b = block.getRelative(x,-1,z);
							BukkitRunnable br = new BukkitRunnable() {
								
								private int state = 0;
								
								@Override
								public void run() {
									if (Math.random() > 0.66) state++;
									int count = 0;
									for (int x = -(1); x <= 1; x ++) {
										for (int z = -(1); z <= 1; z ++) {
											if (b.getRelative(x, 0, z).getType() == Material.LAVA) count++;
										}
									}
									if (count > 4) state++;
									if (state >= 4) {
										b.setType(Material.LAVA);
										this.cancel();
									}
								}
							};
							br.runTaskTimer(EnchantPack1.Instance, 0, 20);
						}
					}
				}
			}
		}
	}
}
