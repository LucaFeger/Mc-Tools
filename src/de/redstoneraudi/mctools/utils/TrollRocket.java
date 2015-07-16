package de.redstoneraudi.mctools.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import de.redstoneraudi.mctools.McTools;

public class TrollRocket {
	
	private static HashMap<String, Integer> count = new HashMap<>();
	private static HashMap<String, GameMode> gm = new HashMap<>();
	private static int id;
	
	public static void RocketStart(McTools plugin, Player target){
		gm.put(target.getName(), target.getGameMode());
		
		target.setGameMode(GameMode.CREATIVE);
		
		count.put(target.getName(), 0);
		id = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				if(count.get(target.getName()) == 50){
					
					target.getLocation().getWorld().strikeLightningEffect(target.getLocation());
					for (Location loc : getCircle(target.getLocation(), 12)){
						 spawnParticle(loc);
					 }
					target.setGameMode(gm.get(target.getName()));
					Bukkit.getScheduler().cancelTask(id);
				}
				count.put(target.getName(), count.get(target.getName()) + 1);
				target.teleport(target.getLocation().clone().add(0, 1, 0));
				spawnParticle(target.getLocation());
			}
		}, 1L, 1L);
	}
	
	 public static void spawnParticle(Location loc){
		 PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.LAVA,
				 true,
				 (float) loc.getX(), 
				 (float) loc.getY(), 
				 (float) loc.getZ(), 
				 0.1F, 
				 0.1F,
				 0.1F,
				 0.0F,
				 1,
				 0,
				 0);
		 for(Player p : Bukkit.getOnlinePlayers()){
			 ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
		 }
	 }
	
	 private static List<Location> getCircle(Location center, int radius){
		 
		 List<Location> locs =  new ArrayList<Location>();
			 
		 for(double x = -radius; x<=radius; x+=1.5D){
				 for(double z = -radius; z<=radius; z+=1.5D){
					 if(x*x + z*z > radius*radius) continue;
					 
					 locs.add(center.clone().add(x,0,z));
				 }
			 }
		 return locs;
	 }
	
}
