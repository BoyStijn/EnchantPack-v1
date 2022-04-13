package blob.enchantpack1;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import blob.enchantlib.Api;
import blob.enchantlib.EnchantLib;
import blob.enchantlib.EnchantRarity;
import blob.enchantlib.EnchantSlot;
import blob.enchantpack1.enchantments.Bomber;
import blob.enchantpack1.enchantments.BullRider;
import blob.enchantpack1.enchantments.Digger;
import blob.enchantpack1.enchantments.Flight;
import blob.enchantpack1.enchantments.JellyLegs;
import blob.enchantpack1.enchantments.King;
import blob.enchantpack1.enchantments.LavaWalker;
import blob.enchantpack1.enchantments.LumberJack;
import blob.enchantpack1.enchantments.Reaper;
import blob.enchantpack1.enchantments.Slam;
import blob.enchantpack1.enchantments.Spiked;
import blob.enchantpack1.enchantments.blocks;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.item.enchantment.Enchantment.Rarity;
import net.minecraft.world.level.block.state.properties.BlockStateInteger;



public class EnchantPack1 extends JavaPlugin implements Listener {

	public static JavaPlugin Instance;
	public static Api API;
	 public static final BlockStateInteger ORE = BlockStateInteger.a("ore", 0, 255);
	
	@Override
	public void onEnable() {
		Instance = this;
		
		if (Bukkit.getServer().getPluginManager().isPluginEnabled("EnchantLib")) {
			API = EnchantLib.API;
			
			API.registerEnchants(new NamespacedKey(Instance, "lava_walker"), new LavaWalker(EnchantRarity.EPIC, new EnchantSlot[] { EnchantSlot.FEET }), "Lava Walker");
			API.registerEnchants(new NamespacedKey(Instance, "flight"), new Flight(EnchantRarity.LEGENDARY, new EnchantSlot[] { EnchantSlot.CHEST }), "Extended Flight");
			API.registerEnchants(new NamespacedKey(Instance, "reaper"), new Reaper(EnchantRarity.LEGENDARY, new EnchantSlot[] { EnchantSlot.MAIN_HAND, EnchantSlot.OFF_HAND }), "Reaper");
			API.registerEnchants(new NamespacedKey(Instance, "bomber"), new Bomber(EnchantRarity.LEGENDARY, new EnchantSlot[] { EnchantSlot.MAIN_HAND, EnchantSlot.OFF_HAND }), "Bomber");
			API.registerEnchants(new NamespacedKey(Instance, "lumberjack"), new LumberJack(EnchantRarity.EPIC, new EnchantSlot[] { EnchantSlot.MAIN_HAND, EnchantSlot.OFF_HAND }), "LumberJack");
			API.registerEnchants(new NamespacedKey(Instance, "spiked"), new Spiked(EnchantRarity.RARE, new EnchantSlot[] { EnchantSlot.MAIN_HAND, EnchantSlot.OFF_HAND }), "Spiked");
			API.registerEnchants(new NamespacedKey(Instance, "king"), new King(EnchantRarity.RARE, new EnchantSlot[] { EnchantSlot.HEAD }), "Mighty King");
			API.registerEnchants(new NamespacedKey(Instance, "digger"), new Digger(EnchantRarity.RARE, new EnchantSlot[] { EnchantSlot.MAIN_HAND }), "Digger");
			API.registerEnchants(new NamespacedKey(Instance, "jelly"), new JellyLegs(EnchantRarity.EPIC, new EnchantSlot[] { EnchantSlot.FEET }), "Jelly Legs");
			API.registerEnchants(new NamespacedKey(Instance, "slam"), new Slam(EnchantRarity.EPIC, new EnchantSlot[] { EnchantSlot.MAIN_HAND }), "Ground Slam");
			API.registerEnchants(new NamespacedKey(Instance, "block"), new blocks(EnchantRarity.LEGENDARY, new EnchantSlot[] { EnchantSlot.MAIN_HAND, EnchantSlot.OFF_HAND }), "Fishy Blocks");
			API.registerEnchants(new NamespacedKey(Instance, "bull"), new BullRider(EnchantRarity.LEGENDARY, new EnchantSlot[] { EnchantSlot.HEAD }), "Bull Rider");
		}
		this.getServer().getPluginManager().registerEvents(this, this);
	
	}
	
}
