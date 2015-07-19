package de.redstoneraudi.mctools.utils;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.redstoneraudi.mctools.other.ItemBuilder;
import de.redstoneraudi.mctools.other.NBTHelper;

public class DropFakeDiamonds {

	private static ItemStack drop;
	
	public static void dropDiamonds(Location center, int radius, int anzahl){
		ItemBuilder builder = ItemBuilder.material(Material.DIAMOND);
		builder.setUnbreakable(true);
		builder.setDisplayName("§cExplosive!!!");
		NBTHelper helper = new NBTHelper(builder.build());
		helper.setBoolean("isExplosive", true);
		
		drop = helper.modify();
		
		for(Location loc :radius(center, radius, radius) ){
			loc.getWorld().dropItem(loc.clone().add(0,2,0), drop);
		}
	}
	
	private static ArrayList<Location> radius(Location center, int r, int n){
		ArrayList<Location> locs = new ArrayList<>();
			
		float alpha = 360 / n;
			for(int i = 0; i<n;i++){
				float p = 1;
				float alpha2 = alpha*i;
				float x = (float) Math.cos(Math.toRadians(alpha2) *p)*r;
				float z = (float) Math.sin(Math.toRadians(alpha2) *p)*r;
				locs.add(new Location(center.getWorld(), center.getX() + x, center.getY(),center.getZ() + z));
			}
			
		return locs;
	}
	
	public static boolean isExplosive(ItemStack is){
		NBTHelper helper = new NBTHelper(is);
		Boolean isExplosive = helper.getBoolean("isExplosive");
		if(isExplosive == null){
			return false;
		}else{
			return isExplosive;
		}
	}
	
}
