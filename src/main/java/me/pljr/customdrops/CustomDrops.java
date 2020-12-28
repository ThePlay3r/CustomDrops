package me.pljr.customdrops;

import me.pljr.customdrops.config.CfgBlocks;
import me.pljr.customdrops.config.CfgMobs;
import me.pljr.customdrops.listeners.BlockDestroy;
import me.pljr.customdrops.listeners.EntityDeathListener;
import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomDrops extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        setupConfig();
        setupListeners();
    }

    private void setupConfig(){
        saveDefaultConfig();
        ConfigManager configManager = new ConfigManager(this, "config.yml");
        CfgMobs.load(configManager);
        CfgBlocks.load(configManager);
    }

    private void setupListeners(){
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new BlockDestroy(), this);
        pluginManager.registerEvents(new EntityDeathListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
