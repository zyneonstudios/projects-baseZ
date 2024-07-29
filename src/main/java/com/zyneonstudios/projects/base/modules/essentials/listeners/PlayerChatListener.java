package com.zyneonstudios.projects.base.modules.essentials.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        String message = "§f"+event.getPlayer().getDisplayName()+"§8» §7"+event.getMessage().replace("&&","%and%").replace("&","§").replace("%and%","&");
        Bukkit.broadcastMessage(message);
    }
}