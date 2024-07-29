package com.zyneonstudios.projects.base.modules.timewatch;

import com.zyneonstudios.projects.base.ProjectsBase;
import com.zyneonstudios.projects.base.modules.module.Module;
import com.zyneonstudios.projects.base.modules.timewatch.commands.TimewatchCommand;
import com.zyneonstudios.projects.base.modules.timewatch.listeners.*;
import com.zyneonstudios.projects.base.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.HashMap;

public final class TimeWatch extends Module {

    public static HashMap<Player, LocalDateTime> logins = new HashMap<>();

    public TimeWatch() {
        super("TimeWatch","2024.7.3","nerotvlive");
    }

    @Override
    public void load() {

    }

    @Override
    public void enable() {
        ProjectsBase.registerCommand("timewatch", new TimewatchCommand(), false);
        ProjectsBase.registerEvents(new PlayerJoinListener());
        ProjectsBase.registerEvents(new PlayerQuitListener());
    }

    @Override
    public void disable() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            logout(player,true);
        }
    }

    public static void login(Player player) {
        logins.remove(player);
        logins.put(player, LocalDateTime.now());
    }

    public static void logout(Player player, boolean deleteFromMap) {
        LocalDateTime login = logins.get(player);
        if(deleteFromMap) {
            logins.remove(player);
        }
        LocalDateTime now = LocalDateTime.now();
        int difference = (int)ChronoUnit.SECONDS.between(login, now);
        Config config = new Config("plugins/TimeWatch/players/"+player.getUniqueId()+".yml");
        String path = "Ontime.year"+now.getYear()+".week"+Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)+"."+now.getDayOfWeek();
        int ontime = 0;
        if(config.get(path)!=null) {
            int i = (int)config.get(path);
            ontime = i+difference;
        }
        config.set(path,ontime);
    }

    public static int getOntimeHours(String uuid, int year, int week) {
        Config config = new Config("plugins/TimeWatch/players/"+uuid+".yml");
        String pathBase = "Ontime.year"+year+".week"+week+".";
        int seconds = 0;
        if(config.get(pathBase+"MONDAY")!=null) {
            seconds = seconds + (int)config.get(pathBase+"MONDAY");
        }
        if(config.get(pathBase+"TUESDAY")!=null) {
            seconds = seconds + (int)config.get(pathBase+"TUESDAY");
        }
        if(config.get(pathBase+"WEDNESDAY")!=null) {
            seconds = seconds + (int)config.get(pathBase+"WEDNESDAY");
        }
        if(config.get(pathBase+"THURSDAY")!=null) {
            seconds = seconds + (int)config.get(pathBase+"THURSDAY");
        }
        if(config.get(pathBase+"FRIDAY")!=null) {
            seconds = seconds + (int)config.get(pathBase+"FRIDAY");
        }
        if(config.get(pathBase+"SATURDAY")!=null) {
            seconds = seconds + (int)config.get(pathBase+"SATURDAY");
        }
        if(config.get(pathBase+"SUNDAY")!=null) {
            seconds = seconds + (int)config.get(pathBase+"SUNDAY");
        }
        return (seconds/60)/60;
    }
}