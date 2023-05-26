package me.itswers0n.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NightCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length >= 1) {
                sender.sendMessage(ChatColor.RED + "You can't add arguments to this command");
            } else {
                p.getLocation().getWorld().setTime(14000);
                p.sendMessage(ChatColor.GREEN + "Time set to night.");
            }
        }
        return true;
    }
}
