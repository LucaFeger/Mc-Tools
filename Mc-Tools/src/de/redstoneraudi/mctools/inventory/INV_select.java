package de.redstoneraudi.mctools.inventory;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class INV_select {
	
	public Inventory inv_select = Bukkit.createInventory(null, InventoryType.HOPPER, "§3Category");

}
