package de.redstoneraudi.mctools.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.redstoneraudi.mctools.McTools;
import de.redstoneraudi.mctools.events.PlayerChooseEvent;

public class WorldOptionsListener implements Listener{

	private McTools plugin;

	public WorldOptionsListener(McTools plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		try{
			if(plugin.allowedInv.contains(e.getInventory().getName())) {
				if(e.getInventory().getName().equals("§3§lWorld-Options")){
					if(e.getCurrentItem().getType() == Material.DOUBLE_PLANT){
						p.getWorld().setStorm(false);
						p.getWorld().setThundering(false);
						p.sendMessage(plugin.getPrefix() + "§aThe weather was set to sun!");
					}
				}
				e.setCancelled(true);
			}
		}catch(NullPointerException ex){}
	}
	
	@EventHandler
	public void onChoose(PlayerChooseEvent e){
		
	}
	
}
