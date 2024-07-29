package com.zyneonstudios.projects.base.modules.timewatch.listeners;

import com.zyneonstudios.projects.base.modules.timewatch.TimeWatch;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        TimeWatch.login(event.getPlayer());
        Bukkit.dispatchCommand(event.getPlayer(),"ot");
    }
}