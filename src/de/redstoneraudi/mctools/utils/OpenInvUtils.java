package de.redstoneraudi.mctools.utils;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OpenInvUtils {

	
	public static Inventory invSelect = Bukkit.createInventory(null, InventoryType.HOPPER, "§3§lCategory");
	public static Inventory invTrollTool =Bukkit.createInventory(null, InventoryType.WORKBENCH, "§3§lTroll-Items");	
	public static Inventory invFunTool = Bukkit.createInventory(null, InventoryType.WORKBENCH, "§3§lCategory");
	public static Inventory invAdminTool = Bukkit.createInventory(null, InventoryType.WORKBENCH, "§3§lCategory");
	
	public static void openInvSelect(Player p) {
		
		ItemStack is = new ItemStack(Material.BLAZE_ROD);
		ItemMeta isMeta = is.getItemMeta();
		isMeta.setDisplayName("§6Fun-Tools");
		isMeta.setLore(Arrays.asList("§eYou can have Fun with our Fun-Tools!"));
		is.setItemMeta(isMeta);
		
		ItemStack is1 = new ItemStack(Material.LAVA_BUCKET);
		ItemMeta is1Meta = is1.getItemMeta();
		is1Meta.setDisplayName("§6Troll-Tools");
		is1Meta.setLore(Arrays.asList("§eYou can troll other Players!"));
		is1.setItemMeta(is1Meta);
		
		ItemStack is2 = new ItemStack(Material.BARRIER);
		ItemMeta is2Meta = is2.getItemMeta();
		is2Meta.setDisplayName("§6Admin-Tools");
		is2Meta.setLore(Arrays.asList("§4ONLY FOR ADMINS!"));
		is2.setItemMeta(is2Meta);
		
		invSelect.setItem(0, is);
		invSelect.setItem(2, is1);
		invSelect.setItem(4, is2);
		
		p.openInventory(invSelect);
		
	}

}
