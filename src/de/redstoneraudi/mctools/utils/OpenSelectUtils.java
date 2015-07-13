package de.redstoneraudi.mctools.utils;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class OpenSelectUtils {
	
	public Inventory invSelect = Bukkit.createInventory(null, InventoryType.HOPPER, "§3Category");
	public Inventory invTrollTool =Bukkit.createInventory(null, InventoryType.WORKBENCH, "§3Category");	
	public Inventory invFunTool = Bukkit.createInventory(null, InventoryType.WORKBENCH, "§3Category");
	public Inventory invAdminTool = Bukkit.createInventory(null, InventoryType.WORKBENCH, "§3Category");
	
	public void openInvSelect() {
		
	}

}
