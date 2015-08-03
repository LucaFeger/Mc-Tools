package de.redstoneraudi.mctools.other;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class RegisterUtil<P extends Plugin> {

    
    private static String VERSION;
    
    static {
        String path = Bukkit.getServer().getClass().getPackage().getName();
        VERSION = path.substring(path.lastIndexOf(".") + 1, path.length());
    }
    
    private P plugin;

    public RegisterUtil(P plugin) {
        this.plugin = plugin;
    }
    
    public void registerEvents(Class<?>... listeners) {
        for (Class<?> clazz : listeners) {
            boolean isConstructor = false;
            try {
                clazz.getConstructor(new Class[] { plugin.getClass() });
                isConstructor = true;
            }catch(NoSuchMethodException ex1) {
                isConstructor = false;
            }
            
            try {
                Listener listener = null;
                if(isConstructor) {
                Constructor<?> cww = clazz.getConstructor(new Class[] { plugin.getClass() });
                listener = (Listener) cww.newInstance(new Object[] { plugin });
                }else{
                    listener = (Listener) clazz.newInstance();
                }
                Bukkit.getPluginManager().registerEvents(listener, plugin);
            }catch(Exception ex) {
                Logger.getLogger(RegisterUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void registerCommand(String name, String description, CommandExecutor commandClas, String... aliases) {
        try {
            DynCommand dynCmd = new DynCommand(name, description, commandClas, aliases);
            Class<?> craftServerClass = Class.forName("org.bukkit.craftbukkit." + VERSION + ".CraftServer");
            Field field = craftServerClass.getDeclaredField("commandMap");
            field.setAccessible(true);
            CommandMap commandMap = (CommandMap) field.get(Bukkit.getServer());
            commandMap.register(plugin.getName(), dynCmd);
        }catch(Exception ex) {
            Logger.getLogger(RegisterUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private class DynCommand extends Command {
        
        private CommandExecutor exec;
        
        protected DynCommand(String name, String description, CommandExecutor exec, String... aliases) {
            super(name);
            this.exec = exec;
            super.setDescription(description);
            super.setAliases(Arrays.asList(aliases));
        }
        
        @Override
        public boolean execute(CommandSender cs, String label, String[] args) {
            exec.onCommand(cs, this, label, args);
            return false;
        }
        
        
        
    }
    
}	