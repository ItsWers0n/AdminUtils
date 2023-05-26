package me.itswers0n.commands;


import me.itswers0n.AdminUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {


    FileConfiguration config = AdminUtils.plugin.getConfig();


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if(args.length > 1) {
            sender.sendMessage(ChatColor.RED + "Too many arguments.");
        } else if (args.length == 1) {
            String target = args[0];
            if(target.equals(sender.getName())) {
                healPlayer(Bukkit.getPlayer(target), sender, true);
            } else {
                if(!Bukkit.getPlayer(target).isOnline()) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.playernotexist")));
                }
                healPlayer(Bukkit.getPlayer(target), sender, false);
            }
        } else {
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.onlyplayercommand")));
            }
            healPlayer((Player)sender, sender, true);
        }
        return true;
    }


    void healPlayer(Player target, CommandSender sender, Boolean notarget) {
        target.setHealth(target.getMaxHealth());
        target.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.healtarget")));
        if(notarget == false) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.healsender").replace("%player%", target.getName())));
        }
    }
}
