package de.redstoneraudi.mctools.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.redstoneraudi.mctools.McTools;
import de.redstoneraudi.mctools.events.PlayerChooseEvent;

public class PlayerOptionsListener implements Listener{

	private McTools plugin;
	
	public PlayerOptionsListener(McTools plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		try{
			if(plugin.allowedInv.contains(e.getInventory().getName())) {
				if(e.getInventory().getName().equals("§3§lPlayer-Options")){
						if(e.getCurrentItem().getType() == Material.PISTON_BASE){
							p.sendMessage(plugin.getPrefix() + "§3Which Player is your target?");
							InventoryClickListener.openChooser(p, e.getCurrentItem(), e.getInventory());
					}else 
						if(e.getCurrentItem().getType() == Material.GOLDEN_APPLE){
							p.sendMessage(plugin.getPrefix() + "§3Which Player is your target?");
							InventoryClickListener.openChooser(p, e.getCurrentItem(), e.getInventory());
					}else
						if(e.getCurrentItem().getType() == Material.CHEST){
							p.sendMessage(plugin.getPrefix() + "§3Which Player is your target?");
							InventoryClickListener.openChooser(p, e.getCurrentItem(), e.getInventory());
					}else 
						if(e.getCurrentItem().getType() == Material.SIGN){
							p.sendMessage(plugin.getPrefix() + "§3Which Player is your target?");
							InventoryClickListener.openChooser(p, e.getCurrentItem(), e.getInventory());
					}else
						if(e.getCurrentItem().getType() == Material.NETHER_STAR){
							p.sendMessage(plugin.getPrefix() + "§3Which Player is your target?");
							InventoryClickListener.openChooser(p, e.getCurrentItem(), e.getInventory());
						}
				}
				e.setCancelled(true);
			}
		}catch(NullPointerException ex){}
	}
	
	@EventHandler
	public void onChoosePlayerOptions(PlayerChooseEvent e){
			if(e.getItem().getType() == Material.PISTON_BASE && e.getInventory().getName().equals("§3§lPlayer-Options")){
			e.getTarget().kickPlayer(plugin.getPrefix() + "§cYou was kickt from the server! \n\n by §5" + e.getPlayer().getName());
		}else 
			if(e.getItem().getType() == Material.GOLDEN_APPLE && e.getInventory().getName().equals("§3§lPlayer-Options")){
				e.getTarget().setOp(!e.getTarget().isOp());
		}else
			if(e.getItem().getType() == Material.CHEST && e.getInventory().getName().equals("§3§lPlayer-Options")){
				if(e.getTarget() == e.getPlayer()) {
					e.getPlayer().sendMessage(plugin.getPrefix() + "§cYou can not look into your own inventory!");
					return;
				}
				e.getPlayer().openInventory(e.getTarget().getInventory());
		}else
			if(e.getItem().getType() == Material.SIGN && e.getInventory().getName().equals("§3§lPlayer-Options")){
				Bukkit.dispatchCommand(e.getPlayer(), "playerinfo " + e.getTarget().getName());
		}else
			if(e.getItem().getType() == Material.NETHER_STAR && e.getInventory().getName().equals("§3§lPlayer-Options")){
				e.getTarget().spigot().respawn();
				e.getPlayer().sendMessage(plugin.getPrefix() + "§aPlayer §5"+e.getTarget().getName()+ " §awas respawn!");
				e.getTarget().sendMessage(plugin.getPrefix() + "§aYou was respanwn by §5" + e.getPlayer().getName() + "§a!");
		}
	}
	
}
