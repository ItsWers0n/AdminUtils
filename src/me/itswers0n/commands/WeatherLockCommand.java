package me.itswers0n.commands;

import me.itswers0n.AdminUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class WeatherLockCommand implements CommandExecutor {

    FileConfiguration config = AdminUtils.plugin.getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if(sender instanceof Player) {
            if(args.length == 1) {
                Player p = (Player) sender;
                String worldName = p.getWorld().getName();
                if(args[0].equalsIgnoreCase("false")) {config.set("worldoptions." + worldName + ".lockweather", false); AdminUtils.plugin.saveConfig(); p.sendMessage(ChatColor.AQUA + "Weatherlock for this world changed to false.");}
                else if(args[0].equalsIgnoreCase("true")) {config.set("worldoptions." + worldName + ".lockweather", true); AdminUtils.plugin.saveConfig(); p.sendMessage(ChatColor.AQUA + "Weatherlock for this world changed to true.");}
                else {p.sendMessage(ChatColor.RED + "Invalid argument, usage: /weatherlock <true/false>");}

            } else {
                sender.sendMessage("Usage: /weatherlock <true/false>");
            }
        } else {
            sender.sendMessage("This command can be only executed by player.");
        }
        return true;
    }

}