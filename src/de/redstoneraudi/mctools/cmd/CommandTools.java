package de.redstoneraudi.mctools.cmd;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.redstoneraudi.mctools.McTools;
import de.redstoneraudi.mctools.utils.OpenInvUtils;

public class CommandTools implements CommandExecutor {
	
	private McTools plugin;
	
	public CommandTools(McTools plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				OpenInvUtils.openInvSelect(p);
			} else {
				Bukkit.getConsoleSender().sendMessage(plugin.getPrefix() + "§4You must be a Player!");
			}
			
		} else {
			sender.sendMessage(plugin.getFailCommandMessage());
		}
		return false;
	}

}
