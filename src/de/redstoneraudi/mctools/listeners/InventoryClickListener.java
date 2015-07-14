package de.redstoneraudi.mctools.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.redstoneraudi.mctools.McTools;
import de.redstoneraudi.mctools.utils.OpenInvUtils;

public class InventoryClickListener implements Listener {
	
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
				if(e.getCurrentItem().getType() == Material.ICE) {
					p.sendMessage(plugin.getPrefix() + "§3Which Player is your target?");
//					------------Message: Prefix + The Player + The Player in your Message + was freezt--------------
//					------Cancel PlayerMoveEvent for this Player!-------------				
				}
			} catch(NullPointerException ex) {
				System.out.println(ex);
			}
			
		e.setCancelled(true);
		}
	}

}
