package de.redstoneraudi.mctools.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import de.redstoneraudi.mctools.McTools;
import de.redstoneraudi.mctools.other.ItemBuilder;
import de.redstoneraudi.mctools.utils.Practical;

public class PracticalListener implements Listener {

	private McTools plugin;
	
	public PracticalListener(McTools plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		try{
			if(plugin.allowedInv.contains(e.getInventory().getName())){
				if(e.getInventory().getName().equals("§3§lPractical")){
					ItemStack is = e.getCurrentItem();
					if(is.getType() == Material.GHAST_TEAR){
						Practical.toggleVanish(p);
						p.closeInventory();
					}else
						if(is.getType() == Material.FEATHER){
							Practical.toggleFly(p);
							p.closeInventory();
					}else 
						if(is.getType() == Material.WOOD_AXE){
							ItemBuilder builder = ItemBuilder.material(Material.WOOD_AXE);
							builder.setDisplayName("§3WorldEdit-Axe");
							p.getInventory().addItem(builder.build());
							p.closeInventory();
					}
				}
			}
		}catch(NullPointerException ex){}
	}
	
}
