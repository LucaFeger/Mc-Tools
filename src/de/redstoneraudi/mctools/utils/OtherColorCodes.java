package de.redstoneraudi.mctools.utils;

import java.util.ArrayList;
import java.util.List;

public enum OtherColorCodes {

	
	OTHERCOLOR1(42, 203, 169, "§5Other Color", getPos(6, 1)),
	OTHERCOLOR2(127, 95, 208, "§5Other Color", getPos(7, 1)),
	OTHERCOLOR3(37, 141, 180, "§5Other Color", getPos(8, 1)),
	OTHERCOLOR4(54, 213, 103, "§5Other Color", getPos(5, 2)),
	OTHERCOLOR5(59, 179, 200,"§5Other Color", getPos(6, 2)),
	OTHERCOLOR6(35, 102, 190 ,"§5Other Color", getPos(7, 2)),
	OTHERCOLOR7(82, 35, 190,"§5Other Color", getPos(8, 2)),
	OTHERCOLOR8(29, 149, 59, "§5Other Color", getPos(5, 3)),
	OTHERCOLOR9(119, 54, 177, "§5Other Color", getPos(6, 3)),
	OTHERCOLOR10(236,48,200,"§5Other Color", getPos(7, 3)),
	OTHERCOLOR11(54,203,189,"§5Other Color", getPos(8, 3));

	private int r, g, b, pos;
	private String name;
	
	private OtherColorCodes(int r, int g, int b, String name, int pos) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.name = name;
		this.pos = pos;
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
	
	public int getPos() {
		return pos;
	}
	
	public static List<OtherColorCodes> getColors(){
		List<OtherColorCodes> units = new ArrayList<>();
		for(OtherColorCodes unit: OtherColorCodes.values()){
			units.add(unit);
		}
		return units;
	}
	
	public static OtherColorCodes findColor(String displayname){
		for(OtherColorCodes codes: getColors()){
			if(codes.getName().equals(displayname)){
				return codes;
			}
		}
		return null;
	}
	
	private static int getPos(int x, int y){
		return x+y*9;
	}
	
}
