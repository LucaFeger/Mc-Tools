package de.redstoneraudi.mctools.inventory;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InventorySelect {
	
	public Inventory invSelect = Bukkit.createInventory(null, InventoryType.HOPPER, "§3Category");

}
