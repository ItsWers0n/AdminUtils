package me.itswers0n.tabcomplete;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class MultiWorldTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if(args.length == 1) {
            List<String> mwArgs = new ArrayList<>();
            mwArgs.add("load");
            mwArgs.add("goto");
            mwArgs.add("delete");
            mwArgs.add("clone");
            return mwArgs;
        } else if (args.length == 2 && args[0] != "load") {
            List<String> worlds = new ArrayList<>();
            for (World world : Bukkit.getWorlds()) {
                String worldName = world.getName();
                worlds.add(worldName);
            }
            return worlds;
        }
        return null;
    }
}
