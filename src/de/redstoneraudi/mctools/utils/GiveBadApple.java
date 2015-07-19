package de.redstoneraudi.mctools.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.redstoneraudi.mctools.other.NBTHelper;

public class GiveBadApple {

	public static void giveBadApple(Player p){
		
		NBTHelper helper = new NBTHelper(new ItemStack(Material.GOLDEN_APPLE, 1,(short) 1));
		helper.setBoolean("isBad", true);
		p.getInventory().addItem(helper.modify());
	}
	
	public static boolean isBad(ItemStack is){
		NBTHelper helper = new NBTHelper(is);
		Boolean isBad = helper.getBoolean("isBad");
		if(isBad == null){
			return false;
		}else{
			return isBad;
		}
	}
}
