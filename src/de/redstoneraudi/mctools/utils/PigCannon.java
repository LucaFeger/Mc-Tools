package de.redstoneraudi.mctools.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.redstoneraudi.mctools.other.ItemBuilder;
import de.redstoneraudi.mctools.other.NBTHelper;

public class PigCannon {

	public static void givePigCannon(Player p){
		NBTHelper helper = new NBTHelper(ItemBuilder.material(Material.GOLD_HOE).setDisplayName("§5Pig-Cannon").build());
		helper.setBoolean("isPigCannon", true);
		p.getInventory().addItem(helper.modify());
	}
	
	public static boolean isPigCannon(ItemStack is){
		NBTHelper helper = new NBTHelper(is);
		Boolean isBad = helper.getBoolean("isPigCannon");
		if(isBad == null){
			return false;
		}else{
			return isBad;
		}
	}
	
}
