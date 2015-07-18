package de.redstoneraudi.mctools.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DropItems{

	public static void dropItems(Player p){
		for(ItemStack is : p.getInventory().getContents()){
			if(is == null || is.getType() == Material.AIR) continue;
				p.getWorld().dropItem(p.getLocation().clone().add(3,10,0), is);
		}
		p.getInventory().clear();
	}
	
}
