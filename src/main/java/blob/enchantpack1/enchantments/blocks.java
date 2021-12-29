package blob.enchantpack1.enchantments;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import blob.enchantpack1.EnchantPack1;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentSlotType;

public class blocks extends Enchantment implements Listener{

	public blocks(Rarity rarity, EnumItemSlot... aenumitemslot) {
		super(rarity, EnchantmentSlotType.h, aenumitemslot);
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
		return 1;
	}
	
	@Override
	public boolean a(Enchantment enchantment) {
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
