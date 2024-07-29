package com.zyneonstudios.projects.base.modules.timewatch.commands;

import com.zyneonstudios.projects.base.utils.Config;
import com.zyneonstudios.projects.base.modules.timewatch.TimeWatch;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Calendar;

public class TimewatchCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(args.length == 2) {
            if(s.hasPermission("zyneon.commands.timewatch.get")) {
                if (args[0].equalsIgnoreCase("get")) {
                    try {
                        OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                        if (new File("plugins/TimeWatch/players/" + player.getUniqueId() + ".yml").exists()) {
                            Calendar c = Calendar.getInstance();
                            s.sendMessage("§9Time§bWatch§8 >> §a" + player.getName() + "§7 war §fdiese Woche§7 bereits §e" + TimeWatch.getOntimeHours(player.getUniqueId().toString(), c.get(Calendar.YEAR), c.get(Calendar.WEEK_OF_YEAR)) + " Stunden§7 online§8!");
                            s.sendMessage("§9Time§bWatch§8 >> §a" + player.getName() + "§7 war §fletzte Woche §e" + TimeWatch.getOntimeHours(player.getUniqueId().toString(), c.get(Calendar.YEAR), c.get(Calendar.WEEK_OF_YEAR) - 1) + " Stunden§7 online§8!");
                        } else {
                            s.sendMessage("§4Fehler§8: §cFür §4" + player.getName() + "§c gibt es keine Ontime-Aufzeichnungen§8.");
                        }
                        return true;
                    } catch (Exception e) {
                        s.sendMessage("§4Fehler§8: §cFür §4" + args[1] + "§c gibt es keine Ontime-Aufzeichnungen§8.");
                        return false;
                    }
                }
            } else {
                Bukkit.dispatchCommand(s,"timewatch");
            }
        } else if(args.length == 0) {
            if(s instanceof Player) {
                Player player = (Player)s;
                TimeWatch.logout(player,true);
                LocalDateTime now = LocalDateTime.now();
                Calendar c = Calendar.getInstance();

                Config config = new Config("plugins/TimeWatch/players/" + player.getUniqueId() + ".yml");
                int difference = (int)config.get("Ontime.year"+now.getYear()+".week"+Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)+"."+now.getDayOfWeek());

                int hours = difference / 3600;
                int minutes = (difference % 3600) / 60;
                int seconds = difference % 60;

                s.sendMessage("§9Time§bWatch §8>> §7Heutige Onlinezeit: §e"+hours+" Stunden§8, §e"+minutes+" Minuten §7und §e"+seconds+" Sekunden");
                s.sendMessage("§9Time§bWatch §8>> §7Onlinestunden diese Woche§8: §e"+ TimeWatch.getOntimeHours(player.getUniqueId().toString(), c.get(Calendar.YEAR), c.get(Calendar.WEEK_OF_YEAR)));
                s.sendMessage("§9Time§bWatch §8>> §7Onlinestunden letzte Woche§8: §e"+ TimeWatch.getOntimeHours(player.getUniqueId().toString(), c.get(Calendar.YEAR), c.get(Calendar.WEEK_OF_YEAR)-1));
                TimeWatch.login(player);
                return true;
            }
        }

        if(s instanceof Player) {
            s.sendMessage("§4Fehler§8: §c/timewatch §7get [Spieler*In]");
        } else {
            s.sendMessage("§4Fehler§8: §c/timewatch get [Spieler*In]");
        }
        return false;
    }
}