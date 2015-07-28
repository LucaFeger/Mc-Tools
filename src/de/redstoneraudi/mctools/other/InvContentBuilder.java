package de.redstoneraudi.mctools.other;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InvContentBuilder {

	private Inventory inv;
	
	private InvContentBuilder(Inventory inv) {
		this.inv = inv;
	}
	
	public static InvContentBuilder setInventory(int slotValue, String name) {
		return new InvContentBuilder(Bukkit.createInventory(null, slotValue, name));
	}
	
	public static InvContentBuilder setInventory(int slotValue) {
		return new InvContentBuilder(Bukkit.createInventory(null, slotValue, "Inventory"));
	}
	
	public static InvContentBuilder setInventory(String name) {
		return new InvContentBuilder(Bukkit.createInventory(null, 27, name));
	}
	
	public static InvContentBuilder setInventory(Inventory inv) {
		return new InvContentBuilder(inv);
	}
	
	public InvContentBuilder setBackground(ItemStack stack){
		for(int i = 0; i<inv.getSize(); i++){
			if(inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR){
				inv.setItem(i, stack);
			}
		}
		return this;
	}
	
	public InvContentBuilder setTitle(String name){
		Inventory inventory = Bukkit.createInventory(null, inv.getSize(), name);
		inventory.setContents(inv.getContents());
		this.inv = inventory;
		return this;
	}
	
	public InvContentBuilder setItem(int slot, ItemStack is){
		inv.setItem(slot, is);
		return this;
	}
	
	public InvContentBuilder addItem(ItemStack is){
		inv.addItem(is);
		return this;
	}
	
	public InvContentBuilder addItems(ItemStack[] items){
		inv.setContents(items);
		return this;
	}
	
	public InvContentBuilder addItem(ItemStack... items){
		inv.setContents(items);
		return this;
	}
	
	public Inventory getInventory(){
		return inv;
	}
	
	public String getTitle(){
		return inv.getTitle();
	}
	
	public int getSize(){
		return inv.getSize();
	}
	
}
