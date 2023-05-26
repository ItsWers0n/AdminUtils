package me.itswers0n.commands;

import me.itswers0n.AdminUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RepairCommand implements CommandExecutor {

    FileConfiguration config = AdminUtils.plugin.getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if(!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.onlyplayercommand")));
        }
        Player p = (Player) sender;
        switch(args.length) {
            case 0:
                RepairOne(p);
                break;
            case 1:
                switch(args[0].toLowerCase()) {
                    case "hand":
                        RepairOne(p);
                        break;
                    case "all":
                        RepairAll(p);
                        break;
                    default:
                        p.sendMessage(ChatColor.RED + "Invalid argument, usage: /repair [hand|all]");
                        break;
                }
                break;
            default:
                p.sendMessage(ChatColor.RED + "Too many arguments, usage: /repair [hand|all]");
                break;
        }

        return true;
    }

    void RepairOne(Player p) {
        p.getItemInHand().setDurability((short)0);
    }

    void RepairAll(Player p) {
        final ItemStack[] content = p.getInventory().getContents();

        for(int i = 0; i < content.length; i++) {
            final ItemStack item = content[i];
            if(item == null) return;
            item.setDurability((short)0);
            content[i] = item;
        }
        p.getInventory().setContents(content);
        p.updateInventory();
    }

}
