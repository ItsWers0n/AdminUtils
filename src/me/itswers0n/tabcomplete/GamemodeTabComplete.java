package me.itswers0n.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class GamemodeTabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if(args.length == 1) {
            List<String> GmArgs1 = new ArrayList<>();
            GmArgs1.add("survival");
            GmArgs1.add("creative");
            GmArgs1.add("adventure");
            GmArgs1.add("spectator");
            return GmArgs1;
        }
        return null;
    }

}
