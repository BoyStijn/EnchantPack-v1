package blob.enchantpack1.enchantments;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import blob.enchantlib.CustomEnchantment;
import blob.enchantlib.EnchantRarity;
import blob.enchantlib.EnchantSlot;
import blob.enchantlib.EnchantTarget;
import blob.enchantpack1.EnchantPack1;

public class LavaWalker extends CustomEnchantment implements Listener{

	public LavaWalker(double rarity, EnchantSlot... aenumitemslot) {
		super(rarity, EnchantTarget.ARMOR_FEET, aenumitemslot);
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
		return true;
	}
	
	@Override
	public int MaxLvl() {
		return 2;
	}
	
	@Override
	public boolean isCompatible(Enchantment enchantment) {
		return (enchantment != Enchantment.FROST_WALKER && enchantment != Enchantment.DEPTH_STRIDER);
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
