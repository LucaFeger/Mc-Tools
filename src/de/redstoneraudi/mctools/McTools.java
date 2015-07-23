package de.redstoneraudi.mctools;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.redstoneraudi.mctools.cmd.CommandTools;
import de.redstoneraudi.mctools.cmd.PlayerInfo;
import de.redstoneraudi.mctools.listeners.EatListener;
import de.redstoneraudi.mctools.listeners.InventoryClickListener;
import de.redstoneraudi.mctools.listeners.MoveEvent;
import de.redstoneraudi.mctools.listeners.PickUpItemEvent;
import de.redstoneraudi.mctools.listeners.PlayerInteractListener;
import de.redstoneraudi.mctools.listeners.PlayerOptionsListener;
import de.redstoneraudi.mctools.listeners.WorldOptionsListener;
import de.redstoneraudi.mctools.utils.chooser.PlayerChooseInv;
import de.redstoneraudi.mctools.utils.chooser.TrueOrFalseChooseInv;

public class McTools extends JavaPlugin {
	
	private final String prefix = "§7[§6McTools§7] §r";
	private final String noPerm = prefix + "§4You don't have Permission to do that!";
	private final String failCommand = prefix + "§4Wrong Arguments: /mctools";
	
	public List<String> allowedInv = new ArrayList<String>();
	
	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(prefix + "§9Plugin §4" + getDescription().getName() + " §9by §4" 
		+ getDescription().getAuthors() + " §9enabled!");
		registerEvents();
		registerCommands();
		
		allowedInv.add("§3§lCategory");
		allowedInv.add("§3§lTroll-Items");
		allowedInv.add("§3§lAdmin-Tools");
		allowedInv.add("§3§lServer-Tools");
		allowedInv.add("§3§lPlayer-Options");
		allowedInv.add("§3§lFun-Tools");
		allowedInv.add("§3§lWorld-Options");
		allowedInv.add("§3§lPractical");
		
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
		pm.registerEvents(new PlayerInteractListener(), this);
		pm.registerEvents(new WorldOptionsListener(this), this);
		pm.registerEvents(new PlayerOptionsListener(this), this);
		
		pm.registerEvents(new PlayerInfo(this), this);
		
	}
	
	public void registerCommands() {
		getCommand("mctools").setExecutor(new CommandTools(this));
		getCommand("playerinfo").setExecutor(new PlayerInfo(this));
	}
}
