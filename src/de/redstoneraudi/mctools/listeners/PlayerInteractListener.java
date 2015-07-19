package de.redstoneraudi.mctools.listeners;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import de.redstoneraudi.mctools.utils.PigCannon;

public class PlayerInteractListener implements Listener{

	@EventHandler
	public void onInteractPigCannon(PlayerInteractEvent e){
		Player p = e.getPlayer();
		ItemStack is = p.getItemInHand();
		if(is == null || is.getType() == Material.AIR) return;
		if(!(is.getType() == Material.GOLD_HOE)) return;
		if(!PigCannon.isPigCannon(is)) return;
		Pig pig = (Pig) p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.PIG);
		pig.setVelocity(p.getLocation().getDirection().multiply(5D));
	}
	
}
