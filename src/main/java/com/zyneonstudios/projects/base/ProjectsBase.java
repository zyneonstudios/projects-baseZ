package com.zyneonstudios.projects.base;

import com.zyneonstudios.projects.Preloader;
import com.zyneonstudios.projects.base.modules.module.Module;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import java.util.HashMap;
import java.util.Objects;

@SuppressWarnings("all")
public class ProjectsBase {

    private static final PluginManager pluginManager = Bukkit.getPluginManager();
    private static final HashMap<String, Module> modules = new HashMap<>();
    private static Preloader plugin = null;

    public static PluginManager getPluginManager() {
        return pluginManager;
    }

    public static Preloader getPlugin() {
        return plugin;
    }

    public static boolean registerCommand(String mainCommand, CommandExecutor commandExecuter, boolean tabCompleter) {
        try {
            Objects.requireNonNull(plugin.getCommand(mainCommand)).setExecutor(commandExecuter);
            if(tabCompleter) {
                Objects.requireNonNull(plugin.getCommand(mainCommand)).setTabCompleter((TabCompleter)commandExecuter);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean registerEvents(Listener eventListener) {
        try {
            pluginManager.registerEvents(eventListener,plugin);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //

    public void load(Preloader preloader, Module... modules) {
        plugin = preloader;
        for (Module module : modules) {
            try {
                if(!ProjectsBase.modules.containsKey(module.getName())) {
                    ProjectsBase.modules.put(module.getName(), module);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(Module module : ProjectsBase.modules.values()) {
            module.load();
        }
        System.gc();
    }

    public void enable() {
        for(Module module : modules.values()) {
            module.enable();
        }
        System.gc();
    }

    public void disable() {
        for(Module module : modules.values()) {
            module.disable();
            modules.remove(module.getName());
        }
        System.gc();
    }
}