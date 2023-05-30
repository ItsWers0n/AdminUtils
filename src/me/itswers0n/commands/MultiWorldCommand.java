package me.itswers0n.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MultiWorldCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length == 0) {
                p.sendMessage("You need to add more arguments.");
            } else if(args.length == 1) {
                if(args[0].equalsIgnoreCase("goto")){
                    p.sendMessage("Usage: /multiworld goto <worldname>");
                } else if(args[0].equalsIgnoreCase("load")){
                    p.sendMessage("Usage: /multiworld load <worldname>");
                } else {
                    p.sendMessage(ChatColor.RED + "Invalid argument.");
                }
            } else if(args.length == 2) {
                if(args[0].equalsIgnoreCase("goto")){
                    p.teleport(new Location(Bukkit.getServer().getWorld(args[1]), 0,150,0));
                } else if(args[0].equalsIgnoreCase("load")) {
                    new WorldCreator(args[1]).createWorld();
                } else {
                    p.sendMessage(ChatColor.RED + "Invalid arguments.");
                }
            }
        } else {
            System.out.println("This command cannot be used in console.");
        }
        return true;
    }

}