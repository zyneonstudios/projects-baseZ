package com.zyneonstudios.projects.base.modules.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpeedCommand implements CommandExecutor {

    private void sendSyntax(CommandSender s) {
        if(s instanceof Player) {
            Player p = (Player)s;
            p.sendMessage("§4Fehler: §c/speed [0-9/d/default] §7[Spieler]");
        } else {
            s.sendMessage("§4Fehler: §c/speed [0-9/d/default] [Spieler]");
        }
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (s instanceof Player) {
            Player p = (Player) s;
            if (p.hasPermission("zyneon.commands.speed")) {
                if (args.length == 0) {
                    sendSyntax(p);
                } else if (args.length == 1) {
                    if (isSpeedCompatible(args[0])) {
                        if (p.isFlying()) {
                            String Speed = "0." + args[0];
                            p.setFlySpeed(Float.parseFloat(Speed));
                            s.sendMessage("Du hast deine §eFluggeschwindigkeit§7 auf §e" + args[0] + "§7 gesetzt!");
                        } else {
                            String Speed = "0." + args[0];
                            p.setWalkSpeed(Float.parseFloat(Speed));
                            s.sendMessage("Du hast deine §eLaufgeschwindigkeit§7 auf §e" + args[0] + "§7 gesetzt!");
                        }
                    } else if (args[0].equalsIgnoreCase("d") || args[0].equalsIgnoreCase("default")) {
                        if (p.isFlying()) {
                            String Speed = "0.1";
                            p.setFlySpeed(Float.parseFloat(Speed));
                            s.sendMessage("Du hast deine §eFluggeschwindigkeit§7 auf §eStandard§7 gesetzt!");
                        } else {
                            String Speed = "0.2";
                            p.setWalkSpeed(Float.parseFloat(Speed));
                            s.sendMessage("Du hast deine §eLaufgeschwindigkeit§7 auf §eStandard§7 gesetzt!");
                        }
                    } else {
                        s.sendMessage("§cDas ist keine gültige Zahl!");
                    }
                } else {
                    if (p.hasPermission("zyneon.commands.speed.other")) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            if (isSpeedCompatible(args[0])) {
                                Player t = Bukkit.getPlayer(args[1]);
                                if (t.isFlying()) {
                                    String Speed = "0." + args[0];
                                    t.setFlySpeed(Float.parseFloat(Speed));
                                    s.sendMessage("Du hast die §eFluggeschwindigkeit§7 von §a" + t.getName() + "§7auf §e" + args[0] + "§7 gesetzt!");
                                } else {
                                    String Speed = "0." + args[0];
                                    t.setWalkSpeed(Float.parseFloat(Speed));
                                    s.sendMessage("Du hast die §eLaufgeschwindigkeit§7 von §a" + t.getName() + "§7auf §e" + args[0] + "§7 gesetzt!");
                                }
                            } else if (args[0].equalsIgnoreCase("d") || args[0].equalsIgnoreCase("default")) {
                                Player t = Bukkit.getPlayer(args[1]);
                                if (t.isFlying()) {
                                    String Speed = "0.1";
                                    t.setFlySpeed(Float.parseFloat(Speed));
                                    s.sendMessage("Du hast die §eFluggeschwindigkeit§7 von §a" + t.getName() + "§7auf §eStandard§7 gesetzt!");
                                } else {
                                    String Speed = "0.2";
                                    t.setWalkSpeed(Float.parseFloat(Speed));
                                    s.sendMessage("Du hast die §eLaufgeschwindigkeit§7 von §a" + t.getName() + "§7auf §eStandard§7 gesetzt!");
                                }
                            } else {
                                s.sendMessage("§cDas ist keine gültige Zahl!");
                            }
                        } else {
                            s.sendMessage("§cSpieler*In nicht gefunden!");
                        }
                    } else {
                        s.sendMessage("§cNicht erlaubt!");
                    }
                }
            } else {
                s.sendMessage("§cNicht erlaubt!");
            }
        } else {
            if (args.length == 0) {
                sendSyntax(s);
            } else if (args.length == 1) {
                sendSyntax(s);
            } else {
                if (Bukkit.getPlayer(args[1]) != null) {
                    if (isSpeedCompatible(args[0])) {
                        Player t = Bukkit.getPlayer(args[1]);
                        if (t.isFlying()) {
                            String Speed = "0." + args[0];
                            t.setFlySpeed(Float.parseFloat(Speed));
                            s.sendMessage("Du hast die §eFluggeschwindigkeit§7 von §a" + t.getName() + "§7auf §e" + args[0] + "§7 gesetzt!");
                        } else {
                            String Speed = "0." + args[0];
                            t.setWalkSpeed(Float.parseFloat(Speed));
                            s.sendMessage("Du hast die §eLaufgeschwindigkeit§7 von §a" + t.getName() + "§7auf §e" + args[0] + "§7 gesetzt!");
                        }
                    } else if (args[0].equalsIgnoreCase("d") || args[0].equalsIgnoreCase("default")) {
                        Player t = Bukkit.getPlayer(args[1]);
                        if (t.isFlying()) {
                            String Speed = "0.1";
                            t.setFlySpeed(Float.parseFloat(Speed));
                            s.sendMessage("Du hast die §eFluggeschwindigkeit§7 von §a" + t.getName() + "§7auf §eStandard§7 gesetzt!");
                        } else {
                            String Speed = "0.2";
                            t.setWalkSpeed(Float.parseFloat(Speed));
                            s.sendMessage("Du hast die §eLaufgeschwindigkeit§7 von §a" + t.getName() + "§7auf §eStandard§7 gesetzt!");
                        }
                    } else {
                        s.sendMessage("§cDas ist keine gültige Zahl!");
                    }
                } else {
                    s.sendMessage("§cSpieler*In nicht gefunden!");
                }
            }
        }
        return false;
    }

    public boolean isSpeedCompatible(String Check) {
        if(isNumeric(Check)) {
            int i = Integer.parseInt(Check);
            return i >= 0 && i <= 9;
        } else {
            return false;
        }
    }

    public boolean isNumericPart(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean isNumeric(String Check) {
        if(isNumericPart(Check)) {
            return !(Double.parseDouble(Check) > 999999998);
        } else {
            return false;
        }
    }
}