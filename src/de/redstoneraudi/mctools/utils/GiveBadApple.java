package de.redstoneraudi.mctools.utils;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveBadApple {

	public static void giveBadApple(Player p){
		
		net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(new ItemStack(Material.GOLDEN_APPLE, 1,(short) 1));
		NBTTagCompound tag = item.hasTag() ? item.getTag() : new NBTTagCompound();
		tag.setBoolean("isBad", true);
		item.setTag(tag);
		p.getInventory().addItem(CraftItemStack.asBukkitCopy(item));
	}
	
	public static boolean isBad(ItemStack is){
		net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(is);
		if(item.hasTag()){
			NBTTagCompound tag = item.getTag();
			if(tag.hasKey("isBad")){
				return tag.getBoolean("isBad");
			}
			return false;
		}
		return false;
	}
}
