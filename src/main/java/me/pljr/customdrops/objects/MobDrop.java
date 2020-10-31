package me.pljr.customdrops.objects;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class MobDrop {
    private final boolean dropDefault;
    private final boolean onlyPlayer;
    private final EntityType mob;
    private final List<ItemStack> drops;

    public MobDrop(boolean dropDefault, boolean onlyPlayer, EntityType mob, List<ItemStack> drops) {
        this.dropDefault = dropDefault;
        this.onlyPlayer = onlyPlayer;
        this.mob = mob;
        this.drops = drops;
    }

    public boolean isDropDefault() {
        return dropDefault;
    }

    public boolean isOnlyPlayer() {
        return onlyPlayer;
    }

    public EntityType getMob() {
        return mob;
    }

    public List<ItemStack> getDrops() {
        return drops;
    }
}
