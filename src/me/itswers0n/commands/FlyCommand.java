package me.itswers0n.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if(args.length == 0) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if(p.getAllowFlight() == false)
                {
                    p.setAllowFlight(true);
                    p.sendMessage(ChatColor.AQUA + "Flying turned on.");
                } else {
                    p.setAllowFlight(false);
                    p.sendMessage(ChatColor.AQUA + "Flying turned off.");
                }
            } else {
                sender.sendMessage("Usage: /fly [player]");
            }
        }

        if(args.length == 1) {
            CommandSender mesSend = null;
            if(Bukkit.getServer().getPlayerExact(args[0]) != null) {
                if(sender instanceof Player) {
                    mesSend = (Player) sender;
                    Player p = Bukkit.getPlayer(args[0]);
                    if(mesSend == p) {
                        mesSend = null;
                        makeFly(p, mesSend);
                    }
                    makeFly(p, mesSend);
                }else {
                    Player p = Bukkit.getPlayer(args[0]);
                    mesSend = sender;
                    makeFly(p, mesSend);
                }
            } else {
                sender.sendMessage(ChatColor.RED + "The player could not be found");return true;
            }

        } else {
            sender.sendMessage(ChatColor.RED + "Too many arguments, usage: /fly [player]"); return true;
        }

        return true;
    }

    public void makeFly(Player p, CommandSender mesSend) {
        if(p.getAllowFlight() == false)
        {
            p.setAllowFlight(true);
            if(mesSend == null) {
                p.sendMessage(ChatColor.AQUA + "Flying turned on.");
            } else {
                mesSend.sendMessage(ChatColor.AQUA + p.getName() +" flying turned on.");
                p.sendMessage(ChatColor.AQUA + "Flying turned on.");
            }
        } else {
            p.setAllowFlight(false);
            if(mesSend == null) {
                p.sendMessage(ChatColor.AQUA + "Flying turned off.");
            } else {
                mesSend.sendMessage(ChatColor.AQUA + p.getName() +" flying turned off.");
                p.sendMessage(ChatColor.AQUA + "Flying turned off.");
            }
        }
    }
}
