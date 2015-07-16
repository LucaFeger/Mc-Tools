package de.redstoneraudi.mctools.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TrueOrFalseChooseEvent extends PlayerEvent{

	public static HandlerList handlers = new HandlerList();
	
	private Inventory inventory;
	private ItemStack is;
	private boolean yes;
	private String question ;
	
	public TrueOrFalseChooseEvent(Player who, boolean yes, Inventory inv,String question,  ItemStack item) {
		super(who);
		this.is = item;
		this.inventory = inv;
		this.yes = yes;
		this.question = question;
	}
	
	/**
	 * Return the inventory that is before that.  
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	public boolean isYes() {
		return yes;
	}
	
	public String getQuestion() {
		return question;
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
