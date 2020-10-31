package me.pljr.customdrops.listeners;

import me.pljr.customdrops.config.CfgMobs;
import me.pljr.customdrops.objects.MobDrop;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDeathListener implements Listener {

    @EventHandler
    public void onDeath(EntityDeathEvent event){
        EntityType entityType = event.getEntityType();
        if (!CfgMobs.perEntityList.containsKey(entityType)) return;

        Entity entity = event.getEntity();
        World world = entity.getWorld();
        Location location = entity.getLocation();

        for (String drop : CfgMobs.perEntityList.get(entityType)){
            MobDrop mobDrop = CfgMobs.list.get(drop);
            if (mobDrop.isOnlyPlayer()){
                Player player = event.getEntity().getKiller();
                if (player == null || !player.hasPermission("customdrops.mobs."+drop)) continue;
            }
            if (!mobDrop.isDropDefault()) event.getDrops().clear();
            for (ItemStack item : mobDrop.getDrops()){
                world.dropItemNaturally(location, item);
            }
        }
    }
}
