package blob.enchantpack1;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import blob.enchantlib.Api;
import blob.enchantlib.EnchantLib;
import blob.enchantpack1.enchantments.*;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.item.enchantment.Enchantment.Rarity;

public class EnchantPack1 extends JavaPlugin {

	public static JavaPlugin Instance;
	public static Api API;
	
	@Override
	public void onEnable() {
		Instance = this;
		
		if (Bukkit.getServer().getPluginManager().isPluginEnabled("EnchantLib")) {
			API = EnchantLib.API;
			
			API.registerEnchants(new NamespacedKey(Instance, "lava_walker"), new LavaWalker(Rarity.c, new EnumItemSlot[] { EnumItemSlot.c }), "Lava Walker");
			API.registerEnchants(new NamespacedKey(Instance, "flight"), new Flight(Rarity.d, new EnumItemSlot[] { EnumItemSlot.e }), "Extended Flight");
			API.registerEnchants(new NamespacedKey(Instance, "reaper"), new Reaper(Rarity.d, new EnumItemSlot[] { EnumItemSlot.a }), "Reaper");
			API.registerEnchants(new NamespacedKey(Instance, "bomber"), new Bomber(Rarity.d, new EnumItemSlot[] { EnumItemSlot.a }), "Bomber");
			API.registerEnchants(new NamespacedKey(Instance, "lumberjack"), new LumberJack(Rarity.c, new EnumItemSlot[] { EnumItemSlot.a }), "LumberJack");
			API.registerEnchants(new NamespacedKey(Instance, "spiked"), new Spiked(Rarity.b, new EnumItemSlot[] { EnumItemSlot.a }), "Spiked");
			API.registerEnchants(new NamespacedKey(Instance, "king"), new King(Rarity.b, new EnumItemSlot[] { EnumItemSlot.f }), "Mighty King");
			API.registerEnchants(new NamespacedKey(Instance, "digger"), new Digger(Rarity.b, new EnumItemSlot[] { EnumItemSlot.a }), "Digger");
			API.registerEnchants(new NamespacedKey(Instance, "jelly"), new JellyLegs(Rarity.c, new EnumItemSlot[] { EnumItemSlot.c }), "Jelly Legs");
			API.registerEnchants(new NamespacedKey(Instance, "slam"), new Slam(Rarity.c, new EnumItemSlot[] { EnumItemSlot.a }), "Ground Slam");
			API.registerEnchants(new NamespacedKey(Instance, "block"), new blocks(Rarity.d, new EnumItemSlot[] { EnumItemSlot.a }), "Fishy Blocks");
			API.registerEnchants(new NamespacedKey(Instance, "bull"), new BullRider(Rarity.d, new EnumItemSlot[] { EnumItemSlot.e }), "Bull Rider");
		}
	}
	
}
