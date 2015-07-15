package de.redstoneraudi.mctools.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerChooseEvent extends PlayerEvent{

	public static HandlerList handlers = new HandlerList();
	
	private Inventory inventory;
	private ItemStack is;
	private Player target;
	
	public PlayerChooseEvent(Player who,Player target, Inventory inv, ItemStack item) {
		super(who);
		this.is = item;
		this.inventory = inv;
		this.target = target;
	}
	
	/**
	 * Return the inventory that is before that.  
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	public Player getTarget() {
		return target;
	}
	
	/**
	 * Return the item of that the Player have clicked in the inventory that is before that.  
	 */
	public ItemStack getItem(){
		return is;
	}
	
	@Override
	public HandlerList getHandlers() {
		return PlayerChooseEvent.handlers;
	}

	public static HandlerList getHandlerList(){
		return PlayerChooseEvent.handlers;
	}
	
}
