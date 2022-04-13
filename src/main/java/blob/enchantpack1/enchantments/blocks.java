package blob.enchantpack1.enchantments;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import blob.enchantlib.CustomEnchantment;
import blob.enchantlib.EnchantRarity;
import blob.enchantlib.EnchantSlot;
import blob.enchantlib.EnchantTarget;
import blob.enchantpack1.EnchantPack1;

public class blocks extends CustomEnchantment implements Listener {

	public blocks(EnchantRarity rarity, EnchantSlot... aenumitemslot) {
		super(rarity, EnchantTarget.FISHING_ROD, aenumitemslot);
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
		return 1;
	}
	
	@Override
	public boolean isCompatible(Enchantment enchantment) {
		return true;
	}
	
	 @EventHandler
	    public void FishOnTheGround(ProjectileHitEvent e) {
	        if(e.getEntityType().equals(EntityType.FISHING_HOOK) && e.getEntity().getShooter() instanceof Player) {
	        	Player p = (Player) e.getEntity().getShooter();
	    		if (p.getInventory().getItemInMainHand() != null) {
	    			int level = p.getInventory().getItemInMainHand().getEnchantmentLevel(EnchantPack1.API.getBukkitEnchant(this));
	    			if (level > 0) {
	    				Block b = e.getHitBlock();
	    				if (b != null) {
		    				if (!b.isPassable() && !Tag.DRAGON_IMMUNE.isTagged(b.getType())) {
		    					String bd = b.getBlockData().getAsString();
		    					b.setType(Material.AIR);
		    					FallingBlock bl = p.getWorld().spawnFallingBlock(b.getLocation().clone().add(0.5,0 , 0.5), Bukkit.getServer().createBlockData(bd));
		    					bl.setGravity(false);
		    					bl.setInvulnerable(true);
		    					((FishHook) e.getEntity()).setHookedEntity(bl);
		    					FishHook hk = (FishHook) e.getEntity();
		    					
		    					BukkitRunnable br = new BukkitRunnable() {
									
									@Override
									public void run() {
										if(hk.getHookedEntity() == null) {
											bl.setGravity(true);
											this.cancel();
										}
										if (hk.isDead()) {
											bl.setGravity(true);
											this.cancel();
										}
									}
								};
								br.runTaskTimer(EnchantPack1.Instance, 0, 1);
		    				}
	    				}
	    			}
	    		}
	        }
	    }
}
