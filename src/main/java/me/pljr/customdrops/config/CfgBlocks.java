package me.pljr.customdrops.config;

import me.pljr.customdrops.objects.BlockDrop;
import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CfgBlocks {
    public static HashMap<String, BlockDrop> list;
    public static HashMap<Material, List<String>> perMaterialList;

    public static void load(ConfigManager config){
        list = new HashMap<>();
        perMaterialList = new HashMap<>();
        ConfigurationSection cs = config.getConfigurationSection("blocks");
        if (cs == null) return;
        for (String drop : cs.getKeys(false)){
            boolean dropDefault = config.getBoolean("blocks."+drop+".drop-default");
            boolean checkForDrops = config.getBoolean("blocks."+drop+".check-for-drops");
            boolean dropExp = config.getBoolean("blocks."+drop+".drop-exp");
            Material material = config.getMaterial("blocks."+drop+".material");
            ConfigurationSection csDrops = config.getConfigurationSection("blocks."+drop+".drops");
            List<ItemStack> drops = new ArrayList<>();
            if (csDrops != null){
                for (String item : csDrops.getKeys(false)){
                    drops.add(config.getSimpleItemStack("blocks."+drop+".drops."+item));
                }
            }
            list.put(drop, new BlockDrop(dropDefault, checkForDrops, dropExp, material, drops));
            List<String> perMaterial = perMaterialList.get(material);
            if (perMaterial == null) perMaterial = new ArrayList<>();
            if (!perMaterial.contains(drop)){
                perMaterial.add(drop);
            }
            perMaterialList.put(material, perMaterial);
        }
    }
}
