package de.redstoneraudi.mctools.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.redstoneraudi.mctools.Global;

public class Practical {
	
	public static void toggleVanish(Player p){
		if(Global.vanishPlayers.contains(p.getName())){
			Global.vanishPlayers.remove(p.getName());
			for(Player tp : Bukkit.getOnlinePlayers()){
				tp.showPlayer(p);
			}
		}else{
			Global.vanishPlayers.add(p.getName());
			for(Player tp : Bukkit.getOnlinePlayers()){
				tp.hidePlayer(p);
			}
		}
	}
	
	public static void disable(){
		for(String name : Global.vanishPlayers){
			Player tp = Bukkit.getPlayer(name);
			if(tp == null) continue;
			
			for(Player target : Bukkit.getOnlinePlayers()){
				target.showPlayer(tp);
			}
			
		}
	}
	
	public static void toggleFly(Player p){
		if(p.getAllowFlight()){
			p.setAllowFlight(false);
			p.setFlying(false);
		}else{
			p.setAllowFlight(true);
		}
	}
	
}
