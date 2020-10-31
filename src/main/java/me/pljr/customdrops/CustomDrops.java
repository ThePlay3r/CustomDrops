package me.pljr.customdrops;

import me.pljr.customdrops.config.CfgBlocks;
import me.pljr.customdrops.config.CfgMobs;
import me.pljr.customdrops.listeners.BlockDestroy;
import me.pljr.customdrops.listeners.EntityDeathListener;
import me.pljr.pljrapi.PLJRApi;
import me.pljr.pljrapi.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomDrops extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!setupPLJRApi()) return;
        setupConfig();
        setupListeners();
    }

    private boolean setupPLJRApi(){
        PLJRApi api = (PLJRApi) Bukkit.getServer().getPluginManager().getPlugin("PLJRApi");
        if (api == null){
            Bukkit.getConsoleSender().sendMessage("§cCustomDrops: PLJRApi not found, disabling plugin!");
            getServer().getPluginManager().disablePlugin(this);
            return false;
        }else{
            Bukkit.getConsoleSender().sendMessage("§aCustomDrops: Hooked into PLJRApi!");
            return true;
        }
    }

    private void setupConfig(){
        saveDefaultConfig();
        ConfigManager configManager = new ConfigManager(getConfig(), "§cCustomDrops:", "config.yml");
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
