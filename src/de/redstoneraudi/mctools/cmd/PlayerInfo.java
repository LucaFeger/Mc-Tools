package de.redstoneraudi.mctools.cmd;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import de.redstoneraudi.mctools.McTools;
import de.redstoneraudi.mctools.other.ItemBuilder;
import de.redstoneraudi.mctools.other.NBTHelper;

public class PlayerInfo implements CommandExecutor, Listener {

	private McTools plugin;
	
	public PlayerInfo(McTools plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {

		if(!cs.hasPermission("mctools.command.playerinfo")){
			cs.sendMessage(plugin.getNoPermMessage());
			return true;
		}
		
		if(args.length != 1){
			cs.sendMessage(plugin.getPrefix() + "§c/playerinfo (give) <Player>");
			return true;
		}
		
		if(args[0].equalsIgnoreCase("give")){
			
			if(!(cs instanceof Player)){
				cs.sendMessage(plugin.getPrefix() + "§cYou must be a player!");
				return true;
			}
			
			Player p = (Player) cs;
			
			ItemBuilder builder = ItemBuilder.material(Material.SIGN);
			builder.setDisplayName("§3PlayerInfo");
			
			NBTHelper nbtHelper = new NBTHelper(builder.build());
			nbtHelper.setBoolean("isPlayerInfo", true);
			
			p.getInventory().addItem(nbtHelper.modify());
			return true;
		}
		
		Player target = Bukkit.getPlayer(args[0]);
		if(target == null){
			cs.sendMessage(plugin.getPrefix() + "§cThe player is not online!");
			return true;
		}
		
		String uuid = target.getUniqueId().toString();
		int ping = getPing(target);
		String ip = target.getAddress().toString();
		
		double x = target.getLocation().getBlockX();
		double y = target.getLocation().getBlockY();
		double z = target.getLocation().getBlockZ();
		
		cs.sendMessage("§3-------§8[§5Player§7-§3Info§8]§3-------");
		cs.sendMessage("§3Name: §a" + target.getName());
		cs.sendMessage("§3UUID: §a" + uuid);
		cs.sendMessage("§3Ping: §a" + ping);
		cs.sendMessage("§3Health: §a" + target.getHealth());
		cs.sendMessage("§3FoodLevel: §a" + target.getFoodLevel());
		cs.sendMessage("§3IP: §a" + ip);
		cs.sendMessage("§3X§7/§3Y§7/§3Z: §a" + x + "§7/§a" + y + "§7/§a" + z);
		
		return true;
	}

	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e){
		Player p = e.getPlayer();
		ItemStack is = p.getItemInHand();
		if(is == null || is.getType() == Material.AIR) return;
		if(!isPlayerInfo(is)) return;
		Entity targetEntity = e.getRightClicked();
		if(!(targetEntity instanceof Player)) return;
		Player tp = (Player) targetEntity;
		Bukkit.dispatchCommand(p, "playerinfo " + tp.getName());
	}
	
	@EventHandler
	public void onInteract(BlockPlaceEvent e){
		Player p = e.getPlayer();
		ItemStack is = p.getItemInHand();
		if(is == null || is.getType() == Material.AIR) return;
		if(!isPlayerInfo(is)) return;
		e.setCancelled(true);
		p.closeInventory();
	}
	
	public boolean isPlayerInfo(ItemStack is) {
		NBTHelper helper = new NBTHelper(is);
		Boolean isPlayerInfo = helper.getBoolean("isPlayerInfo");
		if(isPlayerInfo == null){
			return false;
		}else{
			return isPlayerInfo;
		}
	}
	
	public int getPing(Player player) {
		Object nms_player;
		try {
			nms_player = player.getClass().getMethod("getHandle").invoke(player);
			Field fieldPing = nms_player.getClass().getDeclaredField("ping");
			fieldPing.setAccessible(true);
			return fieldPing.getInt(nms_player);
		} catch (IllegalAccessException | IllegalArgumentException| InvocationTargetException | NoSuchMethodException| SecurityException | NoSuchFieldException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}
