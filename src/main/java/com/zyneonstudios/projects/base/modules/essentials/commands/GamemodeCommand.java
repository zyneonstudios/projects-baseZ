package com.zyneonstudios.projects.base.modules.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GamemodeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s.hasPermission("zyneon.commands.gamemode")) {
            if(args.length == 1) {
                if(s instanceof Player) {
                    Player p = (Player) s;
                    String gamemode = args[0];
                    if (gamemode.equalsIgnoreCase("0") || gamemode.equalsIgnoreCase("s") || gamemode.equalsIgnoreCase("survival")) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage("§7Du bist nun im §fSurvival-Mode§8.");
                    } else if (gamemode.equalsIgnoreCase("1") || gamemode.equalsIgnoreCase("c") || gamemode.equalsIgnoreCase("creative")) {
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage("§7Du bist nun im §fCreative-Mode§8.");
                    } else if (gamemode.equalsIgnoreCase("2") || gamemode.equalsIgnoreCase("a") || gamemode.equalsIgnoreCase("adventure")) {
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage("§7Du bist nun im §fAdventure-Mode§8.");
                    } else if (gamemode.equalsIgnoreCase("3") || gamemode.equalsIgnoreCase("z") || gamemode.equalsIgnoreCase("spectator")) {
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage("§7Du bist nun im §fSpectator-Mode§8.");
                    } else {
                        Bukkit.dispatchCommand(s, "/gamemode");
                    }
                } else {
                    s.sendMessage("§cDazu musst du ein Spieler sein§8!");
                }
            } else if(args.length == 2) {
                if(Bukkit.getPlayer(args[1]) != null) {
                    Player p = Bukkit.getPlayer(args[1]);
                    String gamemode = args[0];
                    if (gamemode.equalsIgnoreCase("0") || gamemode.equalsIgnoreCase("s") || gamemode.equalsIgnoreCase("survival")) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage("§7Du bist nun im §fSurvival-Mode§8.");
                        s.sendMessage("§7Du hast §f"+p.getName()+"§7 in den §fSurvival-Mode §7gesetzt§8.");
                    } else if (gamemode.equalsIgnoreCase("1") || gamemode.equalsIgnoreCase("c") || gamemode.equalsIgnoreCase("creative")) {
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage("§7Du bist nun im §fCreative-Mode§8.");
                        s.sendMessage("§7Du hast §f"+p.getName()+"§7 in den §fCreative-Mode §7gesetzt§8.");
                    } else if (gamemode.equalsIgnoreCase("2") || gamemode.equalsIgnoreCase("a") || gamemode.equalsIgnoreCase("adventure")) {
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage("§7Du bist nun im §fAdventure-Mode§8.");
                        s.sendMessage("§7Du hast §f"+p.getName()+"§7 in den §fAdventure-Mode §7gesetzt§8.");
                    } else if (gamemode.equalsIgnoreCase("3") || gamemode.equalsIgnoreCase("z") || gamemode.equalsIgnoreCase("spectator")) {
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage("§7Du bist nun im §fSpectator-Mode§8.");
                        s.sendMessage("§7Du hast §f"+p.getName()+"§7 in den §fSpectator-Mode §7gesetzt§8.");
                    } else {
                        Bukkit.dispatchCommand(s, "/gamemode");
                    }
                } else {
                    s.sendMessage("§cDer Spieler §4"+args[1]+"§c konnte nicht gefunden werden§8!");
                }
            } else {
                s.sendMessage("§4Fehler§8: §c/gamemode [0,1,2 o. 3] §7(Spieler)");
            }
        } else {
            s.sendMessage("§cDas darfst du nicht§8!");
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender s, Command cmd, String label, String[] args) {
        ArrayList<String> completer = new ArrayList<>();
        if(args.length == 1) {
            completer.add("0");
            completer.add("s");
            completer.add("survival");
            completer.add("1");
            completer.add("c");
            completer.add("creative");
            completer.add("2");
            completer.add("a");
            completer.add("adventure");
            completer.add("3");
            completer.add("z");
            completer.add("spectator");
        } else if(args.length == 2) {
            for(Player all:Bukkit.getOnlinePlayers()) {
                completer.add(all.getName());
            }
        }
        return completer;
    }
}
