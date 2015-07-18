package de.redstoneraudi.mctools.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.redstoneraudi.mctools.McTools;
import de.redstoneraudi.mctools.utils.GiveBadApple;

public class EatListener implements Listener{

	private McTools plugin;

	public EatListener(McTools plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEat(PlayerItemConsumeEvent e){
		if(GiveBadApple.isBad(e.getItem())){
			e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 5000, 60), true);
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
				
				@Override
				public void run() {
					e.getPlayer().setHealth(0.0);
				}
			}, 20L);
		}
	}
	
}
