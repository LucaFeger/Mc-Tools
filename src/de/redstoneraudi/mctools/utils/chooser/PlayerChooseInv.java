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
import org.bukkit.inventory.meta.SkullMeta;

import de.redstoneraudi.mctools.McTools;
import de.redstoneraudi.mctools.events.PlayerChooseEvent;
import de.redstoneraudi.mctools.utils.LongMap;

public class PlayerChooseInv implements Listener{

	private static String InvTitle = "§3PlayerChoose";
	
	private McTools plugin;
	
	public PlayerChooseInv(McTools plugin) {
		this.plugin = plugin;
	}
	
	private static LongMap map = new LongMap();
	
	public static void openChooseInv(Player p, ItemStack item, Inventory inv){
		Inventory playerInv = Bukkit.createInventory(null, getPlayerInventorysRows(Bukkit.getOnlinePlayers().size()) * 9, InvTitle);
		
		map.setValues(p.getName(), item, inv);
		
		for(Player target : Bukkit.getOnlinePlayers()){
			ItemStack head = new ItemStack(Material.SKULL_ITEM,1,(short)3);
			SkullMeta meta = (SkullMeta) head.getItemMeta();
			meta.setOwner(target.getName());
			meta.setDisplayName("§5" + target.getName());
			head.setItemMeta(meta);
			playerInv.addItem(head);
		}
		p.openInventory(playerInv);
		
	}

	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getTitle().equals(InvTitle)){
			e.setCancelled(true);
			if(e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()){
				ItemStack is = e.getCurrentItem();
				ItemMeta im = is.getItemMeta();
				String playername = ChatColor.stripColor(im.getDisplayName());
				if(Bukkit.getOnlinePlayers().size() >0){
					p.closeInventory();
					if(Bukkit.getPlayer(playername) != null){
						Bukkit.getPluginManager().callEvent(new PlayerChooseEvent(p, Bukkit.getPlayer(playername), (Inventory) map.getValue(p.getName(), 1), (ItemStack) map.getValue(p.getName(), 0)));
						map.removeValue(p.getName());
					}else{
						p.sendMessage(plugin.getPrefix() + "§cThe player is not online! §2Inventory opens new!");
						openChooseInv(p, (ItemStack) map.getValue(p.getName(), 0), (Inventory) map.getValue(p.getName(), 1));
					}
				}
			}
		}
	}
	
	public static int getPlayerInventorysRows(int size){
		return (int) Math.ceil(size/9.0D);
	}
	
}
