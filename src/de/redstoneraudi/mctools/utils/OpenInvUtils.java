package de.redstoneraudi.mctools.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;


public class OpenInvUtils {

	public static Inventory invSelect = Bukkit.createInventory(null, InventoryType.HOPPER, "�3�lCategory");
	public static Inventory invTrollTool =Bukkit.createInventory(null, InventoryType.DISPENSER, "�3�lTroll-Items");	
	public static Inventory invFunTool = Bukkit.createInventory(null, InventoryType.DISPENSER, "�3�lFun-Tools");
	public static Inventory invAdminTool = Bukkit.createInventory(null, InventoryType.DISPENSER, "�3�lAdmin-Tools");
	public static Inventory invServerAdminTool = Bukkit.createInventory(null, InventoryType.DISPENSER, "�3�lServer-Tools");
	
	public static void openInvSelect(Player p) {
		InventoryUtils.setItem(invSelect, 0, Material.BLAZE_ROD, "�6Fun-Tools", "�eYou can have Fun with our Fun-Tools!");
		InventoryUtils.setItem(invSelect, 2, Material.LAVA_BUCKET, "�6Troll-Tools", "�eYou can troll other Players!");
		InventoryUtils.setItem(invSelect, 4, Material.BARRIER, "�6Admin-Tools", "�4ONLY FOR ADMINS!");
		p.openInventory(invSelect);
	}
	
	public static void openTrollInv(Player p) {
		InventoryUtils.setItem(invTrollTool, 0, Material.ICE, "�5Dance", "�7The Player is 'Dancing' :D!");
		InventoryUtils.setItem(invTrollTool, 1, Material.FIREWORK, "�5Rocket", "�7Hurls a Player in the air!");
		p.openInventory(invTrollTool);
	}

	public static void openAdminInv(Player p){
		InventoryUtils.setItem(invAdminTool, 0, Material.GOLDEN_APPLE, "�5Toggle OP", "�7Toggle Op by a Player");
		InventoryUtils.setItem(invAdminTool, 1, Material.PISTON_BASE, "�cKick", "�7Kicks a player from the server!");
		InventoryUtils.setItem(invAdminTool, 8, Material.BEACON, "�5Server-Tools!", "�7Server-Tools!");
		p.openInventory(invAdminTool);
	}
	
	public static void openServerTools(Player p){
		InventoryUtils.setItem(invServerAdminTool, 8, Material.BARRIER, "�4Close Server!", "�7Stop the server!");
		InventoryUtils.setItem(invServerAdminTool, 0, Material.NAME_TAG, "�3Reload", "�7Reload the server!");
		
		p.openInventory(invServerAdminTool);
	}
	
}
