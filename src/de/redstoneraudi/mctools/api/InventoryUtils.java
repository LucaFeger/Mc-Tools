package de.redstoneraudi.mctools.api;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class InventoryUtils {
	
	public void setItem(Inventory inv, int slot, ItemStack item, String name, String... lore) {
		ItemStack is = new ItemStack(item);
		ItemMeta isMeta = is.getItemMeta();
		isMeta.setDisplayName(name);
		isMeta.setLore(Arrays.asList(lore));
		
		inv.setItem(slot, is);
	}

}
