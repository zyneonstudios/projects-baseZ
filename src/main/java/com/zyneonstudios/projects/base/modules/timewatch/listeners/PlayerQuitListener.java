package com.zyneonstudios.projects.base.modules.timewatch.listeners;

import com.zyneonstudios.projects.base.modules.timewatch.TimeWatch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        TimeWatch.logout(event.getPlayer(),true);
    }
}