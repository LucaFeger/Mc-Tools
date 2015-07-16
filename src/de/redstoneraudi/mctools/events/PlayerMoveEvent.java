package de.redstoneraudi.mctools.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.redstoneraudi.mctools.listeners.InventoryClickListener;



public class PlayerMoveEvent implements Listener {
	
	@EventHandler
	public void onMove(org.bukkit.event.player.PlayerMoveEvent e) {
		if(InventoryClickListener.freezedPlayers.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		} else {
			e.setCancelled(false);
		}
	}

}
