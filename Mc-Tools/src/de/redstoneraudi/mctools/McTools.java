package de.redstoneraudi.mctools;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class McTools extends JavaPlugin {
	
	public String prefix = "§7[§6McTools§7] §r";
	public String noPerm = prefix + "§4You don't have Permission to do that!";
	
	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(prefix + "§9Plugin §4" + getDescription().getName() + " §9by §4" 
		+ getDescription().getAuthors() + " §9enabled!");
	}

}
