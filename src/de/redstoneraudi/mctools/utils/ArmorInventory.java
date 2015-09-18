package de.redstoneraudi.mctools.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import de.redstoneraudi.mctools.other.InvContentBuilder;
import de.redstoneraudi.mctools.other.ItemBuilder;

public class ArmorInventory implements Listener{

	public static HashMap<String, Inventory> invs = new HashMap<>();
	public static ArrayList<String> use = new ArrayList<>();
	
	public static void OpenArmorInventory(Player p) {
		Inventory inv = Bukkit.createInventory(null, 5*9, "§3Armor");
		
		InvContentBuilder contentBuilder = InvContentBuilder.setInventory(inv);
		contentBuilder.setBackground(ItemBuilder.material(Material.STAINED_GLASS_PANE, 1, (short)15).setDisplayName(" ").build());
		contentBuilder.setItem(40, ItemBuilder.material(Material.SULPHUR).setDisplayName("§8Reset!").build());
		inv = contentBuilder.getInventory();
		
		setDefault(inv);
		
		invs.put(p.getName(), inv);
		p.openInventory(inv);
	}
	
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		try{
			if(e.getInventory().getName().equals("§3Armor")){
				ItemStack is = e.getCurrentItem();
				if(ItemBuilder.hasThisName(e.getCurrentItem(), " ")){
					e.setCancelled(true);
				}
				if(is.getType() == Material.SULPHUR){
					resetInv(p);
					e.setCancelled(true);
				}
					if(is.getType() == Material.LEATHER_CHESTPLATE){
						if(!use.contains(p.getName())){
							e.setCancelled(true);
							if(is.getItemMeta().getDisplayName().equals("§3Leather") || is.getItemMeta().getDisplayName().equals("§5Other Color")){
								setArmorIntheCenterOther(p, e.getCurrentItem());
								return;
							}
							setArmorInTheCenterColor(is.getItemMeta().getDisplayName(), p);
					}
					if(!e.getClickedInventory().getName().equals("§3Armor")){
						e.setCancelled(true);
					}
				}
				if(is.getType() == Material.GOLD_CHESTPLATE || is.getType() == Material.CHAINMAIL_CHESTPLATE || is.getType() == Material.IRON_CHESTPLATE || is.getType() == Material.DIAMOND_CHESTPLATE){
					if(!use.contains(p.getName())){
						e.setCancelled(true);
						setArmorIntheCenterOther(p, e.getCurrentItem());
					}
				}
			}
		}catch(NullPointerException ex){}
	}
	
	public void setArmorInTheCenterColor(String displayname, Player p) {
		Inventory inv = invs.get(p.getName());
				
		ColorCodes colorCodes = ColorCodes.findColor(displayname);
		
		inv.setItem(getPos(4, 0), ItemBuilder.material(Material.LEATHER_HELMET).setColor(colorCodes.getR(), colorCodes.getG(), colorCodes.getB()).setDisplayName(colorCodes.getName()).build());
		inv.setItem(getPos(4, 1), ItemBuilder.material(Material.LEATHER_CHESTPLATE).setColor(colorCodes.getR(), colorCodes.getG(), colorCodes.getB()).setDisplayName(colorCodes.getName()).build());
		inv.setItem(getPos(4, 2), ItemBuilder.material(Material.LEATHER_LEGGINGS).setColor(colorCodes.getR(), colorCodes.getG(), colorCodes.getB()).setDisplayName(colorCodes.getName()).build() );
		inv.setItem(getPos(4, 3), ItemBuilder.material(Material.LEATHER_BOOTS).setColor(colorCodes.getR(), colorCodes.getG(), colorCodes.getB()).setDisplayName(colorCodes.getName()).build());
		
		for(int x = 0; x<4 ; x++){
			for(int y = 0; y<4; y++){
				inv.setItem(getPos2(x, y), ItemBuilder.material(Material.STAINED_GLASS, 1, (short)14).setDisplayName(" ").build());
			}
		}
		
		for(int x = 5; x<9 ; x++){
			for(int y = 0; y<4; y++){
				inv.setItem(getPos(x, y), ItemBuilder.material(Material.STAINED_GLASS, 1, (short)14).setDisplayName(" ").build());
			}
		}
		
		use.add(p.getName());
	}
	
	public void setArmorIntheCenterOther(Player p, ItemStack is){
		Inventory inv = invs.get(p.getName());
		
		Material head = null;
		Material chestplate = null;
		Material leggings = null;
		Material boots = null;
		String displayname = " ";
		Color color = null;
		
		switch (is.getType()) {
		case GOLD_CHESTPLATE:
			head = Material.GOLD_HELMET;
			chestplate = Material.GOLD_CHESTPLATE;
			leggings = Material.GOLD_LEGGINGS;
			boots = Material.GOLD_BOOTS;
			displayname = "§6Gold";
			break;
		case CHAINMAIL_CHESTPLATE:
			head = Material.CHAINMAIL_HELMET;
			chestplate = Material.CHAINMAIL_CHESTPLATE;
			leggings = Material.CHAINMAIL_LEGGINGS;
			boots = Material.CHAINMAIL_BOOTS;
			displayname = "§8Chaimail";
			break;
		case IRON_CHESTPLATE:
			head = Material.IRON_HELMET;
			chestplate = Material.IRON_CHESTPLATE;
			leggings = Material.IRON_LEGGINGS;
			boots = Material.IRON_BOOTS;
			displayname = "§7Iron";
			break;
		case DIAMOND_CHESTPLATE:
			head = Material.DIAMOND_HELMET;
			chestplate = Material.DIAMOND_CHESTPLATE;
			leggings = Material.DIAMOND_LEGGINGS;
			boots = Material.DIAMOND_BOOTS;
			displayname = "§bDiamond";
			break;
		case LEATHER_CHESTPLATE:
			LeatherArmorMeta armorMeta = (LeatherArmorMeta) is.getItemMeta();
			head = Material.LEATHER_HELMET;
			chestplate = Material.LEATHER_CHESTPLATE;
			leggings = Material.LEATHER_LEGGINGS;
			boots = Material.LEATHER_BOOTS;
			if(armorMeta.getDisplayName().equals("§5Other Color")){
				displayname = "§5Other Color";
				color = armorMeta.getColor();
			}else if(armorMeta.getDisplayName().equals("§3Leather")){
				displayname = "§3Leather";
				color = armorMeta.getColor();
			}
			break;
		default:
			break;
		}
		
		if(!(is.getType() == Material.LEATHER_CHESTPLATE)){
			inv.setItem(getPos(4, 0), ItemBuilder.material(head).setDisplayName(displayname).build());
			inv.setItem(getPos(4, 1), ItemBuilder.material(chestplate).setDisplayName(displayname).build());
			inv.setItem(getPos(4, 2), ItemBuilder.material(leggings).setDisplayName(displayname).build());
			inv.setItem(getPos(4, 3), ItemBuilder.material(boots).setDisplayName(displayname).build());
		}else{
			inv.setItem(getPos(4, 0), ItemBuilder.material(head).setColor(color).setDisplayName(displayname).build());
			inv.setItem(getPos(4, 1), ItemBuilder.material(chestplate).setColor(color).setDisplayName(displayname).build());
			inv.setItem(getPos(4, 2), ItemBuilder.material(leggings).setColor(color).setDisplayName(displayname).build());
			inv.setItem(getPos(4, 3), ItemBuilder.material(boots).setColor(color).setDisplayName(displayname).build());
		}
		
		for(int x = 0; x<4 ; x++){
			for(int y = 0; y<4; y++){
				inv.setItem(getPos2(x, y), ItemBuilder.material(Material.STAINED_GLASS, 1, (short)14).setDisplayName(" ").build());
			}
		}
		
		for(int x = 5; x<9 ; x++){
			for(int y = 0; y<4; y++){
				inv.setItem(getPos(x, y), ItemBuilder.material(Material.STAINED_GLASS, 1, (short)14).setDisplayName(" ").build());
			}
		}
		
		use.add(p.getName());
	}
	
	public void resetInv(Player p){
		Inventory inv = invs.get(p.getName());
		setDefault(inv);
		use.remove(p.getName());
	}
	
	public static void setDefault(Inventory inv){
		int count = 0;
		for(int x = 0; x<4 ; x++){
			for(int y = 0; y<4; y++){
				inv.setItem(getPos2(x, y), ItemBuilder.material(Material.LEATHER_CHESTPLATE).setColor(ColorCodes.getColors().get(count).getR(), ColorCodes.getColors().get(count).getG(), ColorCodes.getColors().get(count).getB()).setDisplayName(ColorCodes.getColors().get(count).getName()).build());
				count ++;
			}
		}
		
		inv.setItem(getPos(5, 0), ItemBuilder.material(Material.LEATHER_CHESTPLATE).setDisplayName("§3Leather").build());
		inv.setItem(getPos(6, 0), ItemBuilder.material(Material.GOLD_CHESTPLATE).setDisplayName("§6Gold").build());
		inv.setItem(getPos(7, 0), ItemBuilder.material(Material.CHAINMAIL_CHESTPLATE).setDisplayName("§8Chaimail").build());
		inv.setItem(getPos(8, 0), ItemBuilder.material(Material.IRON_CHESTPLATE).setDisplayName("§7Iron").build());
		inv.setItem(getPos(5, 1), ItemBuilder.material(Material.DIAMOND_CHESTPLATE).setDisplayName("§bDiamond").build());
		
		for(OtherColorCodes colorCodes : OtherColorCodes.getColors()){
				int r = colorCodes.getR();
				int g = colorCodes.getG();
				int b = colorCodes.getB();
				
				inv.setItem(colorCodes.getPos(), ItemBuilder.material(Material.LEATHER_CHESTPLATE).setColor(r,g,b).setDisplayName(colorCodes.getName()).build());
		}
		
		inv.setItem(4, new ItemStack(Material.AIR));
		inv.setItem(13, new ItemStack(Material.AIR));
		inv.setItem(22, new ItemStack(Material.AIR));
		inv.setItem(31, new ItemStack(Material.AIR));
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e){
		Player p = (Player) e.getPlayer();
		if(use.contains(p.getName())){
			use.remove(p.getName());
		}
	}
	
	public static int getPos(int x, int y){
		return x+y*9;
	}
	
	public static int getPos2(int x, int y){
		return y+x*9;
	}
	
}
