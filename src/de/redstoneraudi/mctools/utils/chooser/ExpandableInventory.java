package de.redstoneraudi.mctools.utils.chooser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.redstoneraudi.mctools.McTools;
import de.redstoneraudi.mctools.events.PlayerChooseEvent;
import de.redstoneraudi.mctools.listeners.InventoryClickListener;
import de.redstoneraudi.mctools.other.ItemBuilder;
import de.redstoneraudi.mctools.other.LongMap;

/**
 * ExpandableInventory - Utils
 *
 * --- class-info ---
 * description: Useful Inventory-Wrapper which dynamically / adds new 'Pages' represented as inventories when a 'Page' has no space left
 *-------------------
 * @author See
 */
public class ExpandableInventory
{
	// ExpandableInventory - Super
	
	private LinkedList<Page> pages;
	private String title;
	private int size;
	
	private ItemStack next_page_item = new ItemStack(Material.ANVIL);
	private ItemStack last_page_item = new ItemStack(Material.ANVIL);
	
	private Map<String, ExpandableInventory> playersThatOpenedInventories;
	private Listener listener;
	private McTools plugin;
	
	private static LongMap map = new LongMap();
	/**
	 * Creates an ExpandableInventory
	 * @param title - The Inventory-Title // - a ' - Seite #pageCount'-String is added here
	 * @param size - Inventory-Size
	 * @param next_page_item - the item that's generated for the next page
	 * @param last_page_item -  the item that's generated for the last page
	 * @param plugin
	 */
	public ExpandableInventory(String title, int size, ItemStack next_page_item, ItemStack last_page_item, McTools plugin)
	{
		this.next_page_item = next_page_item;
		this.last_page_item = last_page_item;
		this.title = title;
		this.size = size;
		this.plugin = plugin;
		pages = new LinkedList<Page>();
		
		listener = new Listener(){
			@EventHandler
			public void on(InventoryClickEvent e)
			{	
				if(playersThatOpenedInventories.containsKey(e.getWhoClicked().getName()))
				{
					ExpandableInventory inventory = playersThatOpenedInventories.get(e.getWhoClicked().getName());
					
					if(e.getCurrentItem().equals(next_page_item))
					{
						e.setCancelled(true);
						inventory.openPage((Player)e.getWhoClicked(), Integer.valueOf(e.getClickedInventory().getTitle().replace(title+" - Seite #", "")).intValue()+1);
						return;
					}
					
					if(e.getCurrentItem().equals(last_page_item))
					{
						e.setCancelled(true);
						inventory.openPage((Player)e.getWhoClicked(), Integer.valueOf(e.getClickedInventory().getTitle().replace(title+" - Seite #", "")).intValue()-1);
						return;
					}
					checkPlayerClicked(e.getCurrentItem(), (Player) e.getWhoClicked());
					Bukkit.getPluginManager().callEvent(new ExpandableInventoryClickEvent((Player)e.getWhoClicked(), playersThatOpenedInventories.get(e.getWhoClicked().getName()), e));
				}
			}
			
			@EventHandler
			public void on(InventoryCloseEvent e)
			{
				if(playersThatOpenedInventories.containsKey(e.getPlayer().getName()))
				{
					playersThatOpenedInventories.remove(e.getPlayer().getName());
				}
			}
		};

		register();
		
		this.playersThatOpenedInventories = new HashMap<String, ExpandableInventory>();
		
		pages.add(new Page(title, size, pages.size()));
	}
	
	/**
	 * registers the listeners that's used for switching the pages
	 */
	public void register()
	{
		Bukkit.getPluginManager().registerEvents(listener, plugin);
	}
	
	/**
	 * unregisters the listeners that's used for switching the pages
	 */
	public void unregister()
	{
		HandlerList.unregisterAll(listener);
	}
	
	/**
	 * opens the first page of the ExpandableInventory
	 * @param player - the player
	 */
	public void open(Player player){
		openPage(player, 0);
	}
	
	/**
	 * opens the page defined in @param pageCount of the ExpandableInventory
	 * @param player - the player 
	 * @param pageCount - the pageCount
	 */
	public void openPage(Player player, int pageCount)
	{
		player.openInventory(pages.get(pageCount).getInventory());
		playersThatOpenedInventories.put(player.getName(), this);
	}
	
	/**
	 * adds an item to the ExpandableInventory, adds new pages dynamically
	 * @param item
	 */
	public void addItem(ItemStack item)
	{
		pages.getLast().addItem(item);
		
		if(pages.getLast().isAboutToBeFull())
		{
			pages.getLast().getInventory().setItem(size-1, next_page_item);
			pages.add(new Page(title, size, pages.size()));
		}
		
		if(pages.getLast().getCount() != 0)
		{
			pages.getLast().getInventory().setItem(size-2, last_page_item);
		}
	}
	
	public void registerClicked(Player p, ItemStack item, Inventory inv){
		map.setValues(p.getName(), item, inv);
	}
	
	public void unregisterClicked(Player p){
	map.removeValue(p.getName());
	}
	
	public void checkPlayerClicked(ItemStack is, Player p){
		if(is != null && is.hasItemMeta() && is.getItemMeta().hasDisplayName()){
			ItemMeta im = is.getItemMeta();
			String playername = ChatColor.stripColor(im.getDisplayName());
			if(Bukkit.getOnlinePlayers().size() >0){
				p.closeInventory();
				if(Bukkit.getPlayer(playername) != null){
					Bukkit.getPluginManager().callEvent(new PlayerChooseEvent(p, Bukkit.getPlayer(playername), (Inventory) map.getValue(p.getName(), 1), (ItemStack) map.getValue(p.getName(), 0)));
					map.removeValue(p.getName());
				}else{
					p.sendMessage(plugin.getPrefix() + "§cThe player is not online! §2Inventory opens new!");
					InventoryClickListener.openChooser(p, (ItemStack)map.getValue(p.getName(), 0), (Inventory) map.getValue(p.getName(), 1));
				}
			}
		}
	}
	
	public void setItems(ExpandableInventory expandableInventory){
		for(Player p: Bukkit.getOnlinePlayers()){expandableInventory.addItem(ItemBuilder.material(Material.SKULL_ITEM, 1, (short)3).setSkullOwner(p.getName()).setDisplayName("§5"+p.getName()).build());};
	}
	
	public void removeItems(){
		for(Page page : pages){
			page.getInventory().clear();
			page.setCount(0);
		}
	}
	
	public static class ExpandableInventoryClickEvent extends Event implements Cancellable
	{
		private static final HandlerList HANDLERS = new HandlerList();
		
		private Player player;
		private ExpandableInventory inventory;
		private InventoryClickEvent event;
		
		public ExpandableInventoryClickEvent(Player player, ExpandableInventory inventory, InventoryClickEvent event)
		{
			this.player = player;
			this.inventory = inventory;
			this.event = event;
		}
		
		public Player getPlayer()
		{
			return player;
		}
		
		public ExpandableInventory getInventory()
		{
			return inventory;
		}
		
		@Override
		public HandlerList getHandlers()
		{
			return HANDLERS;
		}
		
		public static HandlerList getHandlerList()
		{
			return HANDLERS;
		}

		@Override
		public boolean isCancelled()
		{
			return event.isCancelled();
		}

		@Override
		public void setCancelled(boolean arg0)
		{
			event.setCancelled(arg0);
		}
		
	}
	public static class Page
	{
		private Inventory inventory;
		private int count, itemCount;
		
		public Page(String title, int size, int count)
		{
			this.count = count;
			inventory = Bukkit.createInventory(null, size, title+" - Seite #"+count);
		}
		
		public void addItem(ItemStack item)
		{
			inventory.setItem(itemCount, item);
			itemCount++;
		}
		
		public boolean isAboutToBeFull()
		{
			return (inventory.getSize()-3)==(itemCount-1);
		}
		
		public int getItemCount()
		{
			return itemCount;
		}
		
		public Inventory getInventory()
		{
			return inventory;
		}
		
		public int getCount()
		{
			return count;
		}
		public void setCount(int count) {
			this.itemCount = count;
		}
	}
}
