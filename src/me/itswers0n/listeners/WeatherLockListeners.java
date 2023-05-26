package me.itswers0n.listeners;




import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import me.itswers0n.AdminUtils;

public class WeatherLockListeners implements Listener{

    FileConfiguration config = AdminUtils.plugin.getConfig();


    @EventHandler
    public void weatherChange(final WeatherChangeEvent e) {
        if (e.isCancelled()) {
            return;
        }
        String worldName = e.getWorld().getName();
        for(String key : config.getConfigurationSection("worldoptions").getKeys(false)) {
            if(config.getBoolean("worldoptions." + key + ".lockweather") == true) {
                if(worldName.equals(key)) {
                    e.setCancelled(e.toWeatherState());
                } else {
                }

                return;
            } else if(config.getBoolean("worldoptions." + key + ".lockweather") == false){
                return;
            }
            return;
        }
    }


    @EventHandler
    public void thunderChange(final ThunderChangeEvent e) {
        if (e.isCancelled()) {
            return;
        }
        String worldName = e.getWorld().getName();
        for(String key : config.getConfigurationSection("worldoptions").getKeys(false)) {
            if(config.getBoolean("worldoptions." + key + ".lockweather") == true) {

                if(worldName.equals(key)) {
                    e.setCancelled(e.toThunderState());
                } else {
                }

                return;
            } else if(config.getBoolean("worldoptions." + key + ".lockweather") == false){
                return;
            }
            return;
        }
    }

}
