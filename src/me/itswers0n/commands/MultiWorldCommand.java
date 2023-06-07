package me.itswers0n.commands;

import me.itswers0n.AdminUtils;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.World;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;



public class MultiWorldCommand implements CommandExecutor {

    static FileConfiguration config = AdminUtils.plugin.getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length == 0) {
                p.sendMessage("You need to add more arguments.");
            } else if(args.length == 1) {
                if(args[0].equalsIgnoreCase("goto")){
                    p.sendMessage("Usage: /multiworld goto <worldname>");
                } else if(args[0].equalsIgnoreCase("load")) {
                    p.sendMessage("Usage: /multiworld load <worldname>");
                } else if(args[0].equalsIgnoreCase("delete")) {
                    p.sendMessage("Usage: /multiworld delete <worldname>");
                } else if(args[0].equalsIgnoreCase("clone")) {
                    p.sendMessage("Usage: /multiworld clone <world-to-clone> <cloned-world-name>");
                } else {
                    p.sendMessage(ChatColor.RED + "Invalid argument.");
                }
            } else if(args.length == 2) {
                if(args[0].equalsIgnoreCase("goto")){
                    try {
                        p.teleport(Bukkit.getWorld(args[1]).getSpawnLocation());
                    } catch (Exception e){
                        p.sendMessage("Could not find a world with name " + args[1]);
                    }
                } else if(args[0].equalsIgnoreCase("load")) {
                    loadWorld(args[1]);
                    p.sendMessage(ChatColor.GREEN + "World " + args[1] + " has been created successfully.");
                } else if(args[0].equalsIgnoreCase("delete")) {
                    deleteWorld(args[1]);
                    p.sendMessage(ChatColor.GREEN + "World " + args[1] + " has been deleted successfully.");
                } else if(args[0].equalsIgnoreCase("clone")) {
                    p.sendMessage("Usage: /multiworld clone <world-to-clone> <cloned-world-name>");
                } else {
                    p.sendMessage(ChatColor.RED + "Invalid arguments.");
                }
            } else {
                if(args.length == 3 && args[0].equalsIgnoreCase("clone")) {
                    cloneWorld(p, args[1], args[2]);
                } else {
                    p.sendMessage(ChatColor.RED + "Too many arguments.");
                }
            }
        } else {
            System.out.println("This command cannot be used in console.");
        }
        return true;
    }

    public void cloneWorld(Player p, String sourceWorldName, String destinationWorldName){
        World sourceWorld = Bukkit.getWorld(sourceWorldName);

        if (sourceWorld == null) {
            p.sendMessage("World " + sourceWorldName + " does not exist.");
            return;
        }

        // Pobranie folderu z danymi świata
        File sourceWorldFolder = sourceWorld.getWorldFolder();

        // Nowa ścieżka docelowa dla skopiowanego świata
        File targetWorldFolder = new File(Bukkit.getWorldContainer(), destinationWorldName);

        if (targetWorldFolder.exists()) {
            p.sendMessage("World " + destinationWorldName + " already exist.");
            return;
        }

        try {
            // Skopiowanie folderu z danymi świata
            copyWorldFolder(sourceWorldFolder.toPath(), targetWorldFolder.toPath());

            // Załadowanie skopiowanego świata
            Bukkit.getServer().createWorld(new WorldCreator(destinationWorldName));
            File uidFile = new File(targetWorldFolder, "uid.dat");

            // Usunięcie pliku uid.dat
            if (uidFile.exists()) {
                uidFile.delete();
            }
            loadWorld(destinationWorldName);
            p.sendMessage("World " + sourceWorldName + " has been cloned as " + destinationWorldName + ".");
        } catch (IOException e) {
            p.sendMessage("There was an error while cloning world.");
            e.printStackTrace();
        }
    }
    private void copyWorldFolder(Path sourcePath, Path targetPath) throws IOException {
        Files.walk(sourcePath).forEach(source -> {
            Path target = targetPath.resolve(sourcePath.relativize(source));
            try {
                Files.copy(source, target, StandardCopyOption.COPY_ATTRIBUTES);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void deleteWorld(String worldName) {
        World world = Bukkit.getWorld(worldName);

        if (world != null) {
            List<String> list = config.getStringList("worlds");
            try{
                list.remove(worldName);
            } catch (Exception e) {

            }
            Bukkit.unloadWorld(world, true); // Wyładowanie świata z serwera
            boolean deleted = Bukkit.getWorlds().remove(world); // Usunięcie świata z listy światów


            List<String> worldsList = config.getStringList("worlds");
            worldsList.removeIf(w -> w.equals(worldName));
            config.set("worlds", worldsList);
            AdminUtils.plugin.saveConfig();
            if (deleted) {
                boolean success = Bukkit.getServer().getWorldContainer().delete(); // Usunięcie folderu świata

                if (success) {

                    System.out.println("World " + worldName + " has been deleted successfully.");
                } else {
                    System.err.println("Could not delete world " + worldName + ".");
                }
            } else {
                System.err.println("Could not delete world " + worldName + " from world list.");
            }
            config.set("worlds", list);
            AdminUtils.plugin.saveConfig();
        } else {
            System.err.println("World " + worldName + " has not been found.");
        }

    }


    public void loadWorld(String worldName){
        boolean until = true;
        while (until) {
            try{
                new WorldCreator(worldName).createWorld();
                List<String> list = config.getStringList("worlds");
                list.add(worldName);
                config.set("worlds", list);
                AdminUtils.plugin.saveConfig();
                until = false;
            } catch (Exception e){

            }
        }
    }
}