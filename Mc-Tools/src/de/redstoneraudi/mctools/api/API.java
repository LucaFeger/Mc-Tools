package de.redstoneraudi.mctools.api;

import java.util.Arrays;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class API {
	
	public void addItemToInv(Player p, Inventory inv, int slot, ItemStack item, String name, String lore) {
		ItemStack is = new ItemStack(item);
		ItemMeta isMeta = is.getItemMeta();
		isMeta.setDisplayName(name);
		isMeta.setLore(Arrays.asList(lore));
		
		inv.setItem(slot, is);
	}

}
