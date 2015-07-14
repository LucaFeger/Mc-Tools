package de.redstoneraudi.mctools.utils;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class InventoryUtils {
	
	public static void setItem(Inventory inv, int slot, Material item, String name, String lore) {
		ItemStack is = new ItemStack(item);
		ItemMeta isMeta = is.getItemMeta();
		isMeta.setDisplayName(name);
		isMeta.setLore(Arrays.asList(lore));
		is.setItemMeta(isMeta);
		
		inv.setItem(slot, is);
	}

}
