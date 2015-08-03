package de.redstoneraudi.mctools;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.redstoneraudi.mctools.cmd.CommandTools;
import de.redstoneraudi.mctools.cmd.PlayerInfo;
import de.redstoneraudi.mctools.listeners.EatListener;
import de.redstoneraudi.mctools.listeners.InventoryClickListener;
import de.redstoneraudi.mctools.listeners.MoveEvent;
import de.redstoneraudi.mctools.listeners.PickUpItemEvent;
import de.redstoneraudi.mctools.listeners.PlayerInteractListener;
import de.redstoneraudi.mctools.listeners.PlayerOptionsListener;
import de.redstoneraudi.mctools.listeners.PracticalListener;
import de.redstoneraudi.mctools.listeners.WorldOptionsListener;
import de.redstoneraudi.mctools.other.RegisterUtil;
import de.redstoneraudi.mctools.utils.ArmorInventory;
import de.redstoneraudi.mctools.utils.Practical;
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
		
		setAllowedInvs();
		
	}
	
	@Override
	public void onDisable() {
		Practical.disable();
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
		RegisterUtil<McTools> register = new RegisterUtil<>(this);
		register.registerEvents(InventoryClickListener.class);
		register.registerEvents(PlayerChooseInv.class);
		register.registerEvents(TrueOrFalseChooseInv.class);
		register.registerEvents(MoveEvent.class);
		register.registerEvents(EatListener.class);
		register.registerEvents(PickUpItemEvent.class);
		register.registerEvents(PlayerInteractListener.class);
		register.registerEvents(WorldOptionsListener.class);
		register.registerEvents(PlayerOptionsListener.class);
		register.registerEvents(PracticalListener.class);
		register.registerEvents(ArmorInventory.class);
		
		register.registerEvents(PlayerInfo.class);
		
	}
	
	public void registerCommands() {
		RegisterUtil<McTools> register = new RegisterUtil<>(this);
		
		register.registerCommand("mctools", "", new CommandTools(this));
		register.registerCommand("playerinfo", "", new PlayerInfo(this), "pi");
	}
	
	private void setAllowedInvs(){
		allowedInv.add("§3§lCategory");
		allowedInv.add("§3§lTroll-Items");
		allowedInv.add("§3§lAdmin-Tools");
		allowedInv.add("§3§lServer-Tools");
		allowedInv.add("§3§lPlayer-Options");
		allowedInv.add("§3§lFun-Tools");
		allowedInv.add("§3§lWorld-Options");
		allowedInv.add("§3§lPractical");
	}
}
