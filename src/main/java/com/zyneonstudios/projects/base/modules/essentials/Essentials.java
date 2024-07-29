package com.zyneonstudios.projects.base.modules.essentials;

import com.zyneonstudios.projects.base.ProjectsBase;
import com.zyneonstudios.projects.base.modules.essentials.commands.*;
import com.zyneonstudios.projects.base.modules.essentials.listeners.*;
import com.zyneonstudios.projects.base.modules.module.Module;

public class Essentials extends Module {

    public Essentials() {
        super("Essentials", "2024.8-alpha.1", "nerotvlive");
    }

    @Override
    public void load() {

    }

    @Override
    public void enable() {
        ProjectsBase.registerCommand("speed", new SpeedCommand(), false);
        ProjectsBase.registerCommand("gamemode", new GamemodeCommand(), true);
        ProjectsBase.registerEvents(new PlayerJoinListener());
        ProjectsBase.registerEvents(new PlayerQuitListener());
        ProjectsBase.registerEvents(new PlayerChatListener());
    }

    @Override
    public void disable() {

    }
}
