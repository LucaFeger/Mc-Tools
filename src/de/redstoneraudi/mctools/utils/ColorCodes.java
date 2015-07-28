package de.redstoneraudi.mctools.utils;

import java.util.ArrayList;
import java.util.List;

public enum ColorCodes{
		BLACK(0,0,0, "§0Black"),
		DARKBLUE(0,0,170, "§1Dark Blue"),
		DARKGREEN(0,170,0, "§2Dark Green"),
		DARKAQUA(0,170,170, "§3Dark Aqua"),
		DARKRED(170, 0, 0, "§4Dark Red"),
		DARKPURPLE(170, 0, 170, "§5Dark Purple"),
		GOLD(255, 170, 0, "§6Gold"),
		GRAY(170,170,170, "§7Gray"),
		DARKGRAY(85, 85, 85, "§8Dark Gray"),
		BLUE(85, 85, 255, "§9Blue"),
		GREEN(85, 255, 85, "§aGreen"),
		AQUA(85, 255, 255, "§bAqua"),
		RED(255, 85, 85, "§cRed"),
		LIGHTPURPLE(255, 85, 255, "§dLight Purple"),
		YELLOW(255, 255, 85, "§eYellow"),
		WHITE(255, 255, 255, "§fWhite");
		
		private int r, g, b;
		private String name;
		
		private ColorCodes(int r, int g, int b, String name) {
			this.r = r;
			this.g = g;
			this.b = b;
			this.name = name;
		}
		
		public int getR() {
			return r;
		}
		
		public int getG() {
			return g;
		}
		
		public int getB() {
			return b;
		}
		
		public String getName() {
			return name;
		}
		
		public static List<ColorCodes> getColors(){
			List<ColorCodes> units = new ArrayList<>();
			for(ColorCodes unit: ColorCodes.values()){
				units.add(unit);
			}
			return units;
		}
		
		public static ColorCodes findColor(String displayname){
			for(ColorCodes codes: getColors()){
				if(codes.getName().equals(displayname)){
					return codes;
				}
			}
			return null;
		}
		
	}