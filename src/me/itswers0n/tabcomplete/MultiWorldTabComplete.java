package me.itswers0n.tabcomplete;

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
            return mwArgs;
        }
        return null;
    }
}
