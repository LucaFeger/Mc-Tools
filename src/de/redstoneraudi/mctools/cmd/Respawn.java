package de.redstoneraudi.mctools.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.redstoneraudi.mctools.McTools;

public class Respawn implements CommandExecutor{

	private McTools plugin;
	
	public Respawn(McTools plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(!cs.hasPermission("mctools.command.respawn")){
			cs.sendMessage(plugin.getNoPermMessage());
			return true;
		}
		
		if(args.length != 1){
			cs.sendMessage(plugin.getPrefix() + "§c/respawn <Player>");
			return true;
		}
		
		Player target = Bukkit.getPlayer(args[0]);
		if(target == null){
			cs.sendMessage(plugin.getPrefix() + "§cThe player is not online!");
			return true;
		}
		
		target.spigot().respawn();
		cs.sendMessage(plugin.getPrefix() + "§aPlayer §5"+target.getName()+ " §awas respawn!");
		target.sendMessage(plugin.getPrefix() + "§aYou was respanwn by §5" + cs.getName() + "§a!");
		
		return true;
	}
	
}
