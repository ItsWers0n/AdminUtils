package me.itswers0n;

import me.itswers0n.commands.*;
import me.itswers0n.listeners.*;
import me.itswers0n.tabcomplete.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AdminUtils extends JavaPlugin {


    public static AdminUtils plugin;

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
    }

    @Override
    public void onDisable() {

    }
}
