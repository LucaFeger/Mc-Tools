package de.redstoneraudi.mctools.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;


public class OpenInvUtils {

	public static Inventory invSelect = Bukkit.createInventory(null, InventoryType.DISPENSER, "§3§lCategory");
	public static Inventory invTrollTool =Bukkit.createInventory(null, InventoryType.DISPENSER, "§3§lTroll-Items");	
	public static Inventory invFunTool = Bukkit.createInventory(null, InventoryType.DISPENSER, "§3§lFun-Tools");
	public static Inventory invAdminTool = Bukkit.createInventory(null, InventoryType.HOPPER, "§3§lAdmin-Tools");
	public static Inventory invServerAdminTool = Bukkit.createInventory(null, InventoryType.DISPENSER, "§3§lServer-Tools");
	public static Inventory invPlayerAdminTool = Bukkit.createInventory(null, InventoryType.DISPENSER, "§3§lPlayer-Options");
	public static Inventory invWorldAdminTool = Bukkit.createInventory(null, InventoryType.DISPENSER, "§3§lWorld-Options");
	public static Inventory invPracticalTool = Bukkit.createInventory(null, InventoryType.DISPENSER, "§3§lPractical");
	
	public static void openInvSelect(Player p) {
		InventoryUtils.setItem(invSelect, 0, Material.BLAZE_ROD, "§6Fun-Tools", "§eYou can have Fun with our Fun-Tools!");
		InventoryUtils.setItem(invSelect, 2, Material.LAVA_BUCKET, "§6Troll-Tools", "§eYou can troll other Players!");
		InventoryUtils.setItem(invSelect, 4, Material.BARRIER, "§6Admin-Tools", "§4ONLY FOR ADMINS!");
		InventoryUtils.setItem(invSelect, 6, Material.LEATHER_BOOTS, "§6Practical", "§ePractical matters");
		p.openInventory(invSelect);
	}
	
	public static void openFunInv(Player p){
		InventoryUtils.setItem(invFunTool, 0, Material.GOLD_HOE, "§5PigCannon", "§7Give the Player a cannon!");
		
		p.openInventory(invFunTool);
	}
	
	public static void openTrollInv(Player p) {
		InventoryUtils.setItem(invTrollTool, 0, Material.ICE, "§5Dance", "§7The Player is 'Dancing' :D!");
		InventoryUtils.setItem(invTrollTool, 1, Material.FIREWORK, "§5Rocket", "§7Hurls a Player in the air!");
		InventoryUtils.setItem(invTrollTool, 2, Material.GOLDEN_APPLE, "§5BadApple ", "§7Add an apple to someones inventory,", "§7which will kill him if he eats it");
		InventoryUtils.setItem(invTrollTool, 3, Material.DIAMOND, "§5FakeDiamond", "§7Drops explosive diamonds around the player!");
		InventoryUtils.setItem(invTrollTool, 4, Material.DISPENSER, "§5DropInv ", "§7Drops every item in the players inventory");
		InventoryUtils.setItem(invTrollTool, 5, Material.BARRIER, "§5Crash ", "§cCrashes the Target Player");
		p.openInventory(invTrollTool);
	}

	public static void openAdminInv(Player p){
		InventoryUtils.setItem(invAdminTool, 0, Material.SKULL_ITEM, "§3Player-Tools!", "§7Player-Options");
		InventoryUtils.setItem(invAdminTool, 2, Material.BEACON, "§5Server-Tools!", "§7Server-Tools!");
		InventoryUtils.setItem(invAdminTool, 4, Material.GRASS, "§5World-Options", "§7World-Options!");
		
		p.openInventory(invAdminTool);
	}
	
	public static void openPlayerOptions(Player p){
		InventoryUtils.setItem(invPlayerAdminTool, 0, Material.GOLDEN_APPLE, "§5Toggle OP", "§7Toggle Op from a Player");
		InventoryUtils.setItem(invPlayerAdminTool, 1, Material.PISTON_BASE, "§cKick", "§7Kicks a player from the server!");
		InventoryUtils.setItem(invPlayerAdminTool, 2, Material.CHEST, "§5InvSee", "§7See the Inventory of the Player!");
		InventoryUtils.setItem(invPlayerAdminTool, 3, Material.SIGN, "§3PlayerInfo", "§7Player-Info");
		InventoryUtils.setItem(invPlayerAdminTool, 4, Material.NETHER_STAR, "§3Respawn!", "§7Respawnt the Player!");
		
		p.openInventory(invPlayerAdminTool);
	}
	
	public static void openServerTools(Player p){
		InventoryUtils.setItem(invServerAdminTool, 8, Material.BARRIER, "§4Close Server!", "§7Stop the server!");
		InventoryUtils.setItem(invServerAdminTool, 0, Material.NAME_TAG, "§3Reload", "§7Reload the server!");
		
		p.openInventory(invServerAdminTool);
	}
	
	public static void openWorldOptions(Player p){
		InventoryUtils.setItem(invWorldAdminTool, 0, Material.DOUBLE_PLANT, "§eSun!", "§6Set the weather to sun!");
		
		p.openInventory(invWorldAdminTool);
	}
	
	public static void openPracticalTool(Player p){
		InventoryUtils.setItem(invPracticalTool, 0, Material.GHAST_TEAR, "§3Vanish", "§7Toggle Vanish from you");
		InventoryUtils.setItem(invPracticalTool, 1, Material.FEATHER, "§3Fly", "§7Toggle Fly from you");
		InventoryUtils.setItem(invPracticalTool, 2, Material.WOOD_AXE, "§3WorldEdit-Axe", "§7Give you the WordEdit Axe!");
		InventoryUtils.setItem(invPracticalTool, 3, Material.ENDER_PEARL, "§3Creative EnderPearl", "§7You can use the Ender Pearl in Creative Mode!");
		InventoryUtils.setItem(invPracticalTool, 4, Material.LEATHER_BOOTS, "§3Armor", "§7Opens an inventory with armor");
		
		p.openInventory(invPracticalTool);
	}
	
}
