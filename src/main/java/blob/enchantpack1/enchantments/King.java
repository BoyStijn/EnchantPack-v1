package blob.enchantpack1.enchantments;

import org.bukkit.Material;
import org.bukkit.entity.PiglinAbstract;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.inventory.ItemStack;

import blob.enchantpack1.EnchantPack1;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.item.EnumArmorMaterial;
import net.minecraft.world.item.ItemArmor;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentSlotType;

public class King extends Enchantment implements Listener{

	public King(Rarity rarity, EnumItemSlot... aenumitemslot) {
		super(rarity, EnchantmentSlotType.e, aenumitemslot);
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
		return 5;
	}
	
	@Override
	public boolean a(Enchantment enchantment) {
		return true;
	}
	
	@Override
	public boolean a(net.minecraft.world.item.ItemStack var0) {
		return (super.a(var0) && ((ItemArmor)var0.c()).d() == EnumArmorMaterial.d);
	}
	
	@EventHandler
	public void onTarget(EntityTargetEvent event) {
		ItemStack i = null;
		if (!(event.getTarget() instanceof Player)) return;
		Player p = (Player) event.getTarget();
		if (p.getInventory().getHelmet() != null) {
			if (p.getInventory().getHelmet().getType() == Material.GOLDEN_HELMET) {
				i = p.getInventory().getHelmet();
			}
		}
		
		if(i!= null) {
			int level = i.getEnchantmentLevel(EnchantPack1.API.getBukkitEnchant(this));
			if (level > 0) {
				if (event.getEntity() instanceof PiglinAbstract) {
					double rng = Math.random() * 100;
					if (rng > (100 -(20*level))) {
						event.setTarget(null);
					}
				}
			}
		}
	}
}
