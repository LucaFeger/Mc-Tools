package de.redstoneraudi.mctools.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.redstoneraudi.mctools.listeners.MoveEvent;

public class PlayerLoginEvent implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		MoveEvent.freezedPlayers.remove(p.getName());
	}
	
}
