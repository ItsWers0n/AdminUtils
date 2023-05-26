package me.itswers0n.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class WeatherLockTabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if(args.length == 1) {
            List<String> arguments = new ArrayList<>();
            arguments.add("true");
            arguments.add("false");
            return arguments;
        }
        return null;
    }

}