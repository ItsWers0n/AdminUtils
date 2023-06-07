package me.itswers0n;

import me.itswers0n.commands.*;
import me.itswers0n.listeners.*;
import me.itswers0n.tabcomplete.*;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class AdminUtils extends JavaPlugin {


    public static AdminUtils plugin;

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("Enabling AdminUtils.");
        plugin = this;
        this.getCommand("gm").setExecutor(new GamemodeCommand());
        this.getCommand("gm").setTabCompleter(new GamemodeTabComplete());
        this.getCommand("fly").setExecutor(new FlyCommand());
        this.getCommand("weatherlock").setExecutor(new WeatherLockCommand());
        this.getCommand("weatherlock").setTabCompleter(new WeatherLockTabComplete());
        this.getCommand("heal").setExecutor(new HealCommand());
        this.getCommand("feed").setExecutor(new FeedCommand());
        this.getCommand("day").setExecutor(new DayCommand());
        this.getCommand("night").setExecutor(new NightCommand());
        this.getCommand("repair").setExecutor(new RepairCommand());
        this.getCommand("repair").setTabCompleter(new RepairTabComplete());
        this.getCommand("mw").setExecutor(new MultiWorldCommand());
        this.getCommand("mw").setTabCompleter(new MultiWorldTabComplete());
        this.getServer().getPluginManager().registerEvents(new WeatherLockListeners(), this);
        getConfig().options().copyDefaults();
        this.saveDefaultConfig();
        loadWorlds();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[AdminUtils] Saving worlds.");
        for (World world : Bukkit.getWorlds()) {
            world.save();
        }
    }

    public void loadWorlds() {
        Bukkit.getConsoleSender().sendMessage("[AdminUtils] Loading worlds.");
        List<String> worlds = config.getStringList("worlds");
        for (String world : worlds) {
            new WorldCreator(world).createWorld();
        }
    }

}
