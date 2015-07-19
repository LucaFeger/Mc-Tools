package de.redstoneraudi.mctools;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.redstoneraudi.mctools.cmd.CommandTools;
import de.redstoneraudi.mctools.listeners.EatListener;
import de.redstoneraudi.mctools.listeners.InventoryClickListener;
import de.redstoneraudi.mctools.listeners.MoveEvent;
import de.redstoneraudi.mctools.listeners.PickUpItemEvent;
import de.redstoneraudi.mctools.utils.chooser.PlayerChooseInv;
import de.redstoneraudi.mctools.utils.chooser.TrueOrFalseChooseInv;

public class McTools extends JavaPlugin {
	
	private final String prefix = "§7[§6McTools§7] §r";
	private final String noPerm = prefix + "§4You don't have Permission to do that!";
	private final String failCommand = prefix + "§4Wrong Arguments: /mctools";
	
	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(prefix + "§9Plugin §4" + getDescription().getName() + " §9by §4" 
		+ getDescription().getAuthors() + " §9enabled!");
		registerEvents();
		registerCommands();
	}

	public String getNoPermMessage() {
		return noPerm;
	}

	public String getPrefix() {
		return prefix;
	}
	
	public String getFailCommandMessage() {
		return failCommand;
	}
	
	public void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new InventoryClickListener(this), this);
		pm.registerEvents(new PlayerChooseInv(this), this);
		pm.registerEvents(new TrueOrFalseChooseInv(), this);
		pm.registerEvents(new MoveEvent(this), this);
		pm.registerEvents(new EatListener(this), this);
		pm.registerEvents(new PickUpItemEvent(), this);
	}
	
	public void registerCommands() {
		getCommand("mctools").setExecutor(new CommandTools(this));
	}
}
