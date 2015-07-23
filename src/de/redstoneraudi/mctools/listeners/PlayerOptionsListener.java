package de.redstoneraudi.mctools.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

import de.galaxyhd.banplugin.api.BanPlayer;
import de.redstoneraudi.mctools.McTools;
import de.redstoneraudi.mctools.events.PlayerChooseEvent;
import de.redstoneraudi.mctools.utils.chooser.PlayerChooseInv;

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
					if(e.getCurrentItem().getType() == Material.BARRIER){
						p.sendMessage(plugin.getPrefix() + "§3Which Player is your target?");
						PlayerChooseInv.openChooseInv(p, e.getCurrentItem(), e.getInventory());	
					}
				}
				e.setCancelled(true);
			}
		}catch(NullPointerException ex){}
	}
	
	@SuppressWarnings("deprecation")
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
			if(e.getItem().getType() == Material.BARRIER && e.getInventory().getName().equals("§3§lPlayer-Options")){ //Nur mit meinem BanPlugin :)
				if(isBanpluginLoaded()){
					BanPlayer banPlayer = new BanPlayer(e.getTarget());
					banPlayer.setSender(e.getPlayer());
					banPlayer.setReason("§cBanned from the Server by §5" + plugin.getDescription().getName());
					banPlayer.ban();
				}else{
					e.getTarget().setBanned(true);
					e.getTarget().kickPlayer("§cBanned from the Server by §5" + plugin.getDescription().getName());
				}
		}
	}
	
	private boolean isBanpluginLoaded(){
		boolean returnstatement = false;
		Plugin banPlugin = Bukkit.getPluginManager().getPlugin("BanPlugin");
		if(banPlugin != null){
			returnstatement = true;
		}
		return returnstatement;
	}
	
}
