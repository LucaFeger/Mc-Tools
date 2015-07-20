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
import de.redstoneraudi.mctools.utils.DropFakeDiamonds;
import de.redstoneraudi.mctools.utils.DropItems;
import de.redstoneraudi.mctools.utils.GiveBadApple;
import de.redstoneraudi.mctools.utils.OpenInvUtils;
import de.redstoneraudi.mctools.utils.PigCannon;
import de.redstoneraudi.mctools.utils.TrollRocket;
import de.redstoneraudi.mctools.utils.chooser.PlayerChooseInv;
import de.redstoneraudi.mctools.utils.chooser.TrueOrFalseChooseInv;

public class InventoryClickListener implements Listener {
	
	public static List<String> allowedInv = new ArrayList<String>();
	public static List<String> freezedPlayers = new ArrayList<String>();
	
	private McTools plugin;
	public InventoryClickListener(McTools plugin) {
		this.plugin = plugin;
		allowedInv.add("§3§lCategory");
		allowedInv.add("§3§lTroll-Items");
		allowedInv.add("§3§lAdmin-Tools");
		allowedInv.add("§3§lServer-Tools");
		allowedInv.add("§3§lPlayer-Options");
		allowedInv.add("§3§lFun-Tools");
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(allowedInv.contains(e.getInventory().getName())) {
//------------------Opening the TrollTool Menu---------------------------			
			try {
				if(e.getCurrentItem().getType() == Material.LAVA_BUCKET && e.getClickedInventory().getName().equals("§3§lCategory")) {
					OpenInvUtils.openTrollInv(p);
				}else if(e.getCurrentItem().getType() == Material.BARRIER && e.getClickedInventory().getName().equals("§3§lCategory")){
					if(p.hasPermission("mctools.inv.admin")){
						OpenInvUtils.openAdminInv(p);
					}else{
						p.sendMessage(plugin.getNoPermMessage());
					}
				}else if(e.getCurrentItem().getType() == Material.BLAZE_ROD && e.getClickedInventory().getName().equals("§3§lCategory")){
					OpenInvUtils.openFunInv(p);
				}else if(e.getCurrentItem().getType() == Material.BEACON && e.getClickedInventory().getName().equals("§3§lAdmin-Tools")){ /*Opens the Server-Tool Inv */
					OpenInvUtils.openServerTools(p);
				}else if(e.getCurrentItem().getType() == Material.SKULL_ITEM && e.getClickedInventory().getName().equals("§3§lAdmin-Tools")){
					OpenInvUtils.openPlayerOptions(p);
				}
			} catch(NullPointerException ex) {
//				System.out.println(ex);
			}
//------------------Activate Freezer---------------------------	
			try {
				if(e.getCurrentItem().getType() == Material.ICE && e.getInventory().getName().equals("§3§lTroll-Items")) {
					p.sendMessage(plugin.getPrefix() + "§3Which Player is your target?");
					PlayerChooseInv.openChooseInv(p, e.getCurrentItem(), e.getInventory());
				}else if(e.getCurrentItem().getType() == Material.FIREWORK && e.getInventory().getName().equals("§3§lTroll-Items")){
					p.sendMessage(plugin.getPrefix() + "§3Which Player is your target?");
					PlayerChooseInv.openChooseInv(p, e.getCurrentItem(), e.getInventory());
				}else 
					if(e.getCurrentItem().getType() == Material.PISTON_BASE && e.getInventory().getName().equals("§3§lPlayer-Options")){
						p.sendMessage(plugin.getPrefix() + "§3Which Player is your target?");
						PlayerChooseInv.openChooseInv(p, e.getCurrentItem(), e.getInventory());
				}else 
					if(e.getCurrentItem().getType() == Material.GOLDEN_APPLE && e.getInventory().getName().equals("§3§lPlayer-Options")){
						p.sendMessage(plugin.getPrefix() + "§3Which Player is your target?");
						PlayerChooseInv.openChooseInv(p, e.getCurrentItem(), e.getInventory());
				}else
					if(e.getCurrentItem().getType() == Material.CHEST && e.getInventory().getName().equals("§3§lPlayer-Options")){
						p.sendMessage(plugin.getPrefix() + "§3Which Player is your target?");
						PlayerChooseInv.openChooseInv(p, e.getCurrentItem(), e.getInventory());	
				}else 
					if(e.getCurrentItem().getType() == Material.BARRIER && e.getInventory().getName().equals("§3§lServer-Tools")){
						TrueOrFalseChooseInv.openChooseInv(p, e.getCurrentItem(),e.getInventory(), "Would you like to stop the server?");
				}else
					if(e.getCurrentItem().getType() == Material.NAME_TAG && e.getInventory().getName().equals("§3§lServer-Tools")){
						TrueOrFalseChooseInv.openChooseInv(p, e.getCurrentItem(), e.getInventory(), "Would you like to reload the server?");
				}else
					if(e.getCurrentItem().getType() == Material.GOLDEN_APPLE && e.getInventory().getName().equals("§3§lTroll-Items")){
						p.sendMessage(plugin.getPrefix() + "§3Which Player is your target?");
						PlayerChooseInv.openChooseInv(p, e.getCurrentItem(), e.getInventory());	
				}else
					if(e.getCurrentItem().getType() == Material.DISPENSER && e.getInventory().getName().equals("§3§lTroll-Items")){
						p.sendMessage(plugin.getPrefix() + "§3Which Player is your target?");
						PlayerChooseInv.openChooseInv(p, e.getCurrentItem(), e.getInventory());	
				}else
					if(e.getCurrentItem().getType() == Material.DIAMOND && e.getInventory().getName().equals("§3§lTroll-Items")){
						p.sendMessage(plugin.getPrefix() + "§3Which Player is your target?");
						PlayerChooseInv.openChooseInv(p, e.getCurrentItem(), e.getInventory());		
				}else
					if(e.getCurrentItem().getType() == Material.GOLD_HOE && e.getInventory().getName().equals("§3§lFun-Tools")){
						p.sendMessage(plugin.getPrefix() + "§3Which Player is your target?");
						PlayerChooseInv.openChooseInv(p, e.getCurrentItem(), e.getInventory());	
				}
				
			} catch(NullPointerException ex) {
//				System.out.println(ex);
			}
			
		e.setCancelled(true);
		}
	}

	//Troll-Actions
	@EventHandler
	public void onChoose(PlayerChooseEvent e){
		if(e.getItem().getType() == Material.FIREWORK && e.getInventory().getName().equals("§3§lTroll-Items")){
			TrollRocket.RocketStart(plugin, e.getTarget());
		}else
			if(e.getItem().getType() == Material.ICE && e.getInventory().getName().equals("§3§lTroll-Items")) {
				if(freezedPlayers.contains(e.getTarget().getName())) {
					freezedPlayers.remove(e.getTarget().getName());
				} else {
					freezedPlayers.add(e.getTarget().getName());
				}
		}else 
			if(e.getItem().getType() == Material.GOLDEN_APPLE && e.getInventory().getName().equals("§3§lTroll-Items")){
				GiveBadApple.giveBadApple(e.getTarget());
		}else
			if(e.getItem().getType() == Material.DISPENSER && e.getInventory().getName().equals("§3§lTroll-Items")){
				DropItems.dropItems(e.getTarget());
		}else
			if(e.getItem().getType() == Material.DIAMOND && e.getInventory().getName().equals("§3§lTroll-Items")){
				DropFakeDiamonds.dropDiamonds(e.getTarget().getLocation(), 8, 8);
		}
	}
	
	//Fun-Actions
	@EventHandler
	public void onChooseFunItems(PlayerChooseEvent e){
		if(e.getItem().getType() == Material.GOLD_HOE && e.getInventory().getName().equals("§3§lFun-Tools")){
			PigCannon.givePigCannon(e.getTarget());
		}
	}
	
	//Admin-Actions
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
		}
	}
	
	
	@EventHandler
	public void onTrueChoose(TrueOrFalseChooseEvent e){
		if(e.getItem().getType() == Material.BARRIER && e.getInventory().getName().equals("§3§lServer-Tools")){
			if(e.isYes()){
				for(Player tages : Bukkit.getOnlinePlayers()){
					tages.kickPlayer(plugin.getPrefix() + "§cThe server was stopped by \n §5" + e.getPlayer().getName() + "§c!");
				}
				Bukkit.shutdown();
			}
		}else
			if(e.getItem().getType() == Material.NAME_TAG && e.getInventory().getName().equals("§3§lServer-Tools")){
				if(e.isYes()){
					Bukkit.reload();
					e.getPlayer().sendMessage(plugin.getPrefix() + "§aReload complete.");				
				}
			}
	}
	
}
