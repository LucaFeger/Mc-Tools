package de.redstoneraudi.mctools.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import de.redstoneraudi.mctools.Global;
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
					}else
						if(is.getType() == Material.ENDER_PEARL){
							boolean use = Practical.toggleCreativeEnderPearl(p);
							if(use){
								p.sendMessage(plugin.getPrefix() + "§aYou can use the EnderPearl in Creative now");
							}else{
								p.sendMessage(plugin.getPrefix() + "§cYou can not use the EnderPearl in Creative now");
							}
							p.closeInventory();
					}
				}
			}
		}catch(NullPointerException ex){}
	}
	
	@EventHandler
	public void onEnderPearlLaunch(PlayerInteractEvent e){
		Player  p = e.getPlayer();
		if(p.getGameMode() == GameMode.CREATIVE){
			if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
				if(e.getItem().getType() == Material.ENDER_PEARL){	
					if(Global.enderpearlTogglePlayer.contains(p.getName())){
						p.launchProjectile(EnderPearl.class);
					}
				}
			}
		}
	}
	
}
