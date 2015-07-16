package de.redstoneraudi.mctools.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.redstoneraudi.mctools.McTools;
import de.redstoneraudi.mctools.events.PlayerChooseEvent;

public class MoveEvent implements Listener {

	public static List<String> freezedPlayers = new ArrayList<String>();
	
	private McTools plugin;
		
		public MoveEvent(McTools plugin) {
			this.plugin = plugin;
		}
	
		@EventHandler
		public void onMove(PlayerMoveEvent e) {
			Player p = e.getPlayer();
			
			if(!freezedPlayers.contains(p.getName())) {
				e.setCancelled(false);
			} else {
				e.setCancelled(true);
				Location from = e.getFrom();
				Location to = e.getTo();
				double x = Math.floor(from.getX());
				double z = Math.floor(from.getZ());
				if(Math.floor(to.getX())!=x||Math.floor(to.getZ())!=z){
				    x+=.5;
				    z+=.5;
				    e.getPlayer().teleport(new Location(from.getWorld(),x,from.getY(),z,from.getYaw(),from.getPitch()));
			}
		}
	}
	
	@EventHandler
	public void onChoose1(PlayerChooseEvent e) {
		if(e.getItem().getType() == Material.ICE && e.getInventory().getName().equals("§3§lTroll-Items")) {
			if(MoveEvent.freezedPlayers.contains(e.getPlayer().getName())) {
				MoveEvent.freezedPlayers.remove(e.getTarget().getName());
			} else {
				MoveEvent.freezedPlayers.add(e.getTarget().getName());
				e.getTarget().sendMessage(plugin.getPrefix() + "§3<< You are now freezed! >>");
			}
		}
	}
	
}
