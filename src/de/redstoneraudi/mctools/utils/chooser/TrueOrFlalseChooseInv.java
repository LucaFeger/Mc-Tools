package de.redstoneraudi.mctools.utils.chooser;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.redstoneraudi.mctools.events.TrueOrFalseChooseEvent;
import de.redstoneraudi.mctools.utils.ItemBuilder;
import de.redstoneraudi.mctools.utils.LongMap;

public class TrueOrFlalseChooseInv implements Listener{

private static String InvTitle = "§eConfirmChoose";
	
	private static LongMap map = new LongMap();
	
	public static void openChooseInv(Player p, ItemStack item, Inventory inv, String messages){
		Inventory chooseInv = Bukkit.createInventory(null, 9, InvTitle);
		
		map.setValues(p.getName(), item, inv, messages);
		
		ItemBuilder builderInfo = ItemBuilder.material(Material.SIGN);
		builderInfo.setDisplayName("§3" + messages);
		
		ItemBuilder builderYes = ItemBuilder.material(Material.STAINED_GLASS_PANE, 1, (short) 5);
		builderYes.setDisplayName("§aYes!");
		
		ItemBuilder builderNo = ItemBuilder.material(Material.STAINED_GLASS_PANE, 1, (short) 14);
		builderNo.setDisplayName("§cNo!");
		
		chooseInv.setItem(0, builderYes.build());
		chooseInv.setItem(8, builderNo.build());
		chooseInv.setItem(4, builderInfo.build());
		
		p.openInventory(chooseInv);
		
	}

	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getTitle().equals(InvTitle)){
			e.setCancelled(true);
			if(e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()){
				ItemStack is = e.getCurrentItem();
				ItemMeta im = is.getItemMeta();
				String choose = ChatColor.stripColor(im.getDisplayName());
				if(choose.equals("Yes!")){
					p.closeInventory();
					Bukkit.getPluginManager().callEvent(new TrueOrFalseChooseEvent(p, true, (Inventory) map.getValue(p.getName(), 1), (ItemStack) map.getValue(p.getName(), 0)));
					map.removeValue(p.getName());
				}else if(choose.equals("No!")){
					p.closeInventory();
					Bukkit.getPluginManager().callEvent(new TrueOrFalseChooseEvent(p, false, (Inventory) map.getValue(p.getName(), 1), (ItemStack) map.getValue(p.getName(), 0)));
					map.removeValue(p.getName());
				}
			}
		}
	}
	
}
