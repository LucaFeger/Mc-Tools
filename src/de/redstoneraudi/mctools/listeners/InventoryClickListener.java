package de.redstoneraudi.mctools.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.redstoneraudi.mctools.McTools;
import de.redstoneraudi.mctools.events.PlayerChooseEvent;
import de.redstoneraudi.mctools.events.TrueOrFalseChooseEvent;
import de.redstoneraudi.mctools.utils.OpenInvUtils;
import de.redstoneraudi.mctools.utils.TrollRocket;
import de.redstoneraudi.mctools.utils.chooser.PlayerChooseInv;
import de.redstoneraudi.mctools.utils.chooser.TrueOrFalseChooseInv;

public class InventoryClickListener implements Listener {
	
	public static List<String> allowedInv = new ArrayList<String>();
	public static List<String> freezedPlayers = new ArrayList<String>();
	
	private McTools plugin;
	public InventoryClickListener(McTools plugin) {
		this.plugin = plugin;
		allowedInv.add("�3�lCategory");
		allowedInv.add("�3�lTroll-Items");
		allowedInv.add("�3�lAdmin-Tools");
		allowedInv.add("�3�lServer-Tools");
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(allowedInv.contains(e.getInventory().getName())) {
//------------------Opening the TrollTool Menu---------------------------			
			try {
				if(e.getCurrentItem().getType() == Material.LAVA_BUCKET && e.getClickedInventory().getName().equals("�3�lCategory")) {
					OpenInvUtils.openTrollInv(p);
				}else if(e.getCurrentItem().getType() == Material.BARRIER && e.getClickedInventory().getName().equals("�3�lCategory")){
					if(p.hasPermission("mctools.inv.admin")){
						OpenInvUtils.openAdminInv(p);
					}else{
						p.sendMessage(plugin.getNoPermMessage());
					}
				}else if(e.getCurrentItem().getType() == Material.BEACON && e.getClickedInventory().getName().equals("�3�lAdmin-Tools")){ /*Opens the Server-Tool Inv */
					OpenInvUtils.openServerTools(p);
				}
			} catch(NullPointerException ex) {
				System.out.println(ex);
			}
//------------------Activate Freezer---------------------------	
			try {
				if(e.getCurrentItem().getType() == Material.ICE && e.getInventory().getName().equals("�3�lTroll-Items")) {
					p.sendMessage(plugin.getPrefix() + "�3Which Player is your target?");
					PlayerChooseInv.openChooseInv(p, e.getCurrentItem(), e.getInventory());
				}else if(e.getCurrentItem().getType() == Material.FIREWORK && e.getInventory().getName().equals("�3�lTroll-Items")){
					p.sendMessage(plugin.getPrefix() + "�3Which Player is your target?");
					PlayerChooseInv.openChooseInv(p, e.getCurrentItem(), e.getInventory());
				}else 
					if(e.getCurrentItem().getType() == Material.PISTON_BASE && e.getInventory().getName().equals("�3�lAdmin-Tools")){
						p.sendMessage(plugin.getPrefix() + "�3Which Player is your target?");
						PlayerChooseInv.openChooseInv(p, e.getCurrentItem(), e.getInventory());
				}else 
					if(e.getCurrentItem().getType() == Material.GOLDEN_APPLE && e.getInventory().getName().equals("�3�lAdmin-Tools")){
						p.sendMessage(plugin.getPrefix() + "�3Which Player is your target?");
						PlayerChooseInv.openChooseInv(p, e.getCurrentItem(), e.getInventory());
				}else 
					if(e.getCurrentItem().getType() == Material.BARRIER && e.getInventory().getName().equals("�3�lServer-Tools")){
						TrueOrFalseChooseInv.openChooseInv(p, e.getCurrentItem(),e.getInventory(), "Would you like to stop the server?");
				}else
					if(e.getCurrentItem().getType() == Material.NAME_TAG && e.getInventory().getName().equals("�3�lServer-Tools")){
						TrueOrFalseChooseInv.openChooseInv(p, e.getCurrentItem(), e.getInventory(), "Would you like to reload the server?");
				}
				
			} catch(NullPointerException ex) {
				System.out.println(ex);
			}
			
		e.setCancelled(true);
		}
	}

	//Troll-Actions
	@EventHandler
	public void onChoose(PlayerChooseEvent e){
		if(e.getItem().getType() == Material.FIREWORK && e.getInventory().getName().equals("�3�lTroll-Items")){
			TrollRocket.RocketStart(plugin, e.getTarget());
		}else
			if(e.getItem().getType() == Material.ICE && e.getInventory().getName().equals("�3�lTroll-Items")) {
				if(freezedPlayers.contains(e.getTarget().getName())) {
					freezedPlayers.remove(e.getTarget().getName());
				} else {
					freezedPlayers.add(e.getTarget().getName());
				}
			}
	}
	
	//Admin-Actions
	@EventHandler
	public void onChooseAdmin(PlayerChooseEvent e){
			if(e.getItem().getType() == Material.PISTON_BASE && e.getInventory().getName().equals("�3�lAdmin-Tools")){
			e.getTarget().kickPlayer(plugin.getPrefix() + "�cYou was kickt from the server! \n\n by �5" + e.getPlayer().getName());
		}else 
			if(e.getItem().getType() == Material.GOLDEN_APPLE && e.getInventory().getName().equals("�3�lAdmin-Tools")){
				e.getTarget().setOp(!e.getTarget().isOp());
		}
	}
	
	
	@EventHandler
	public void onTrueChoose(TrueOrFalseChooseEvent e){
		if(e.getItem().getType() == Material.BARRIER && e.getInventory().getName().equals("�3�lServer-Tools")){
			if(e.isYes()){
				for(Player tages : Bukkit.getOnlinePlayers()){
					tages.kickPlayer(plugin.getPrefix() + "�cThe server was stopped by \n �5" + e.getPlayer().getName() + "�c!");
				}
				Bukkit.shutdown();
			}
		}else
			if(e.getItem().getType() == Material.NAME_TAG && e.getInventory().getName().equals("�3�lServer-Tools")){
				if(e.isYes()){
					Bukkit.reload();
					e.getPlayer().sendMessage(plugin.getPrefix() + "�aReload complete.");				}
			}
	}
	
}
