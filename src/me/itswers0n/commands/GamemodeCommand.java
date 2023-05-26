package me.itswers0n.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player)
        {
            if(args.length == 0) {
                sender.sendMessage("Usage: /gm <mode> [player]");
            }
            if(args.length <= 2) {
                Player p = (Player) sender;
                Player mesSend = null;
                if(args.length <= 1)
                {
                } else if(Bukkit.getServer().getPlayerExact(args[1]) != null) {
                    mesSend = (Player) sender;
                    p = Bukkit.getPlayer(args[1]);
                    if(mesSend == p) {
                        mesSend = null;
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "The player could not be found");
                    return true;
                }
                switch(args[0].toLowerCase()) {
                    case "0":
                    case "s":
                    case "survival":
                        changeGamemode(p, "survival", GameMode.SURVIVAL, mesSend);
                        break;
                    case "1":
                    case "c":
                    case "creative":
                        changeGamemode(p, "creative", GameMode.CREATIVE, mesSend);
                        break;
                    case "2":
                    case "a":
                    case "adventure":
                        changeGamemode(p, "adventure", GameMode.ADVENTURE, mesSend);
                        break;
                    case "3":
                    case "sp":
                    case "spectator":
                        changeGamemode(p, "spectator", GameMode.SPECTATOR, mesSend);
                        break;
                    default:
                        if(mesSend == null) {
                            p.sendMessage(ChatColor.RED + "Invalid argument.");
                        } else {
                            mesSend.sendMessage(ChatColor.RED + "Invalid argument.");
                        }
                        break;
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Too many arguments, usage: /gm <mode> [player]");
            }
        }else {
            sender.sendMessage("This command can be only executed as player.");
        }
        return true;
    }
    public void changeGamemode(Player p, String gmString, GameMode gm, Player mesSend) {
        p.setGameMode(gm);
        if(mesSend == null) {
            p.sendMessage(ChatColor.AQUA + "Your game mode has been changed to " + gmString + ".");
        } else {
            mesSend.sendMessage(ChatColor.AQUA + p.getName() +" game mode has been changed to " + gmString + ".");
            p.sendMessage(ChatColor.AQUA + "Your game mode has been changed to " + gmString + ".");
        }
    }
}
