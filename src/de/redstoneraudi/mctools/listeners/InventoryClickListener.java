package de.redstoneraudi.mctools.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import de.redstoneraudi.mctools.McTools;
import de.redstoneraudi.mctools.events.PlayerChooseEvent;
import de.redstoneraudi.mctools.utils.OpenInvUtils;
import de.redstoneraudi.mctools.utils.PlayerChooseInv;
import de.redstoneraudi.mctools.utils.TrollRocket;

public class InventoryClickListener implements Listener {
	
	public static List<String> freezedPlayers = new ArrayList<String>();
	
	private McTools plugin;
	public InventoryClickListener(McTools plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getName().equals("§3§lCategory") || e.getInventory().getName().equals("§3§lTroll-Items")) {
//------------------Opening the TrollTool Menu---------------------------			
			try {
				if(e.getCurrentItem().getType() == Material.LAVA_BUCKET) {
					OpenInvUtils.openTrollInv(p);
				}
			} catch(NullPointerException ex) {
				System.out.println(ex);
			}
//------------------Activate Freezer---------------------------	
			try {
				if(e.getCurrentItem().getType() == Material.ICE && e.getInventory().getName().equals("§3§lTroll-Items")) {
					p.sendMessage(plugin.getPrefix() + "§3Which Player is your target?");
					PlayerChooseInv.openChooseInv(p, e.getCurrentItem(), e.getInventory());
				}else if(e.getCurrentItem().getType() == Material.FIREWORK && e.getInventory().getName().equals("§3§lTroll-Items")){
					p.sendMessage(plugin.getPrefix() + "§3Which Player is your target?");
					PlayerChooseInv.openChooseInv(p, e.getCurrentItem(), e.getInventory());
				}
			} catch(NullPointerException ex) {
				System.out.println(ex);
			}
			
		e.setCancelled(true);
		}
	}

	
	@EventHandler
	public void onChoose(PlayerChooseEvent e){
		if(e.getItem().getType() == Material.FIREWORK && e.getInventory().getName().equals("§3§lTroll-Items")){
			TrollRocket.RocketStart(plugin, e.getTarget());
		}
		
	}
	
	@EventHandler
	public void onChoose1(PlayerChooseEvent e) {
		if(e.getItem().getType() == Material.ICE && e.getInventory().getName().equals("§3§lTroll-Items")) {
			if(freezedPlayers.contains(e.getPlayer().getName())) {
				freezedPlayers.remove(e.getTarget().getName());
			} else {
				freezedPlayers.add(e.getTarget().getName());
				e.getTarget().sendMessage(plugin.getPrefix() + "§3<< You are now freezed! >>");
			}
		}
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
}
