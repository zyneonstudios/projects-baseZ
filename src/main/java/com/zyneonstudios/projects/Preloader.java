package com.zyneonstudios.projects;

import com.zyneonstudios.projects.base.ProjectsBase;
import com.zyneonstudios.projects.base.modules.essentials.Essentials;
import com.zyneonstudios.projects.base.modules.timewatch.TimeWatch;
import org.bukkit.plugin.java.JavaPlugin;

public class Preloader extends JavaPlugin {

    private final ProjectsBase plugin = new ProjectsBase();

    @Override
    public void onLoad() {
        plugin.load(this, new Essentials(), new TimeWatch());
    }

    @Override
    public void onDisable() {
        plugin.disable();
    }

    @Override
    public void onEnable() {
        plugin.enable();
    }
}