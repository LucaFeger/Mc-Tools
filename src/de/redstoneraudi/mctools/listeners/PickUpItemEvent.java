package de.redstoneraudi.mctools.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import de.redstoneraudi.mctools.utils.DropFakeDiamonds;

public class PickUpItemEvent implements Listener{

	@EventHandler
	public void onPickup(PlayerPickupItemEvent e){
		Player p = e.getPlayer();
		if(p.getGameMode() == GameMode.CREATIVE) return;
		if(DropFakeDiamonds.isExplosive(e.getItem().getItemStack())){
			e.setCancelled(true);
			p.getWorld().createExplosion(p.getLocation(), 5F);
			for(Entity entity :p.getNearbyEntities(16, 16, 16)){
				if(entity instanceof Item){
					ItemStack is = ((Item) entity).getItemStack();
					if(is.getType() == Material.DIAMOND){
						if(DropFakeDiamonds.isExplosive(is)){
							entity.remove();
						}
					}
				}
			}
		}
	}
	
}
