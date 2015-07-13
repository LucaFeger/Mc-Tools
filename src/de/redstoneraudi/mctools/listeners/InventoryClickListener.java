package de.redstoneraudi.mctools.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		if(e.getInventory().getName().equals("§3§lCategory")) {
		e.setCancelled(true);
		}
	}

}
