package me.pljr.customdrops;

import me.pljr.customdrops.config.Blocks;
import me.pljr.customdrops.config.Mobs;
import me.pljr.customdrops.listeners.BlockDestroy;
import me.pljr.customdrops.listeners.EntityDeathListener;
import me.pljr.pljrapispigot.PLJRApiSpigot;
import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomDrops extends JavaPlugin {

    private PLJRApiSpigot pljrApiSpigot;

    private Mobs mobs;
    private Blocks blocks;

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!setupPLJRApi()) return;
        setupConfig();
        setupListeners();
    }

    public boolean setupPLJRApi(){
        if (PLJRApiSpigot.get() == null){
            getLogger().warning("PLJRApi-Spigot is not enabled!");
            return false;
        }
        pljrApiSpigot = PLJRApiSpigot.get();
        return true;
    }

    private void setupConfig(){
        saveDefaultConfig();
        ConfigManager configManager = new ConfigManager(this, "config.yml");
        mobs = new Mobs(configManager);
        blocks = new Blocks(configManager);
    }

    private void setupListeners(){
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new BlockDestroy(blocks), this);
        pluginManager.registerEvents(new EntityDeathListener(mobs), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
