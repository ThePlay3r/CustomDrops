package me.pljr.customdrops.config;

import me.pljr.customdrops.objects.MobDrop;
import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CfgMobs {
    public static HashMap<String, MobDrop> list;
    public static HashMap<EntityType, List<String>> perEntityList;

    public static void load(ConfigManager config){
        list = new HashMap<>();
        perEntityList = new HashMap<>();
        ConfigurationSection cs = config.getConfigurationSection("mobs");
        if (cs == null) return;
        for (String drop : cs.getKeys(false)){
            boolean dropDefault = config.getBoolean("mobs."+drop+".drop-default");
            boolean onlyPlayer = config.getBoolean("mobs."+drop+".only-player");
            EntityType mob = config.getEntityType("mobs."+drop+".mob");
            ConfigurationSection csDrops = config.getConfigurationSection("mobs."+drop+".drops");
            List<ItemStack> drops = new ArrayList<>();
            if (csDrops != null){
                for (String item : csDrops.getKeys(false)){
                    drops.add(config.getSimpleItemStack("mobs."+drop+".drops."+item));
                }
            }
            list.put(drop, new MobDrop(dropDefault, onlyPlayer, mob, drops));
            List<String> perEntity = perEntityList.get(mob);
            if (perEntity == null) perEntity = new ArrayList<>();
            if (!perEntity.contains(drop)){
                perEntity.add(drop);
            }
            perEntityList.put(mob, perEntity);
        }
    }
}
