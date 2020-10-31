package me.pljr.customdrops.objects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class BlockDrop {
    private final boolean dropDefault;
    private final boolean checkForDrops;
    private final boolean dropExp;
    private final Material material;
    private final List<ItemStack> drops;

    public BlockDrop(boolean dropDefault, boolean checkForDrops, boolean dropExp, Material material, List<ItemStack> drops) {
        this.dropDefault = dropDefault;
        this.checkForDrops = checkForDrops;
        this.dropExp = dropExp;
        this.material = material;
        this.drops = drops;
    }

    public boolean isDropDefault() {
        return dropDefault;
    }

    public boolean isCheckForDrops() {
        return checkForDrops;
    }

    public boolean isDropExp() {
        return dropExp;
    }

    public Material getMaterial() {
        return material;
    }

    public List<ItemStack> getDrops() {
        return drops;
    }
}
