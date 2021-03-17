package me.pljr.customdrops.listeners;

import lombok.AllArgsConstructor;
import me.pljr.customdrops.config.Mobs;
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

@AllArgsConstructor
public class EntityDeathListener implements Listener {

    private final Mobs mobs;

    @EventHandler
    public void onDeath(EntityDeathEvent event){
        EntityType entityType = event.getEntityType();
        if (!mobs.getPerEntityList().containsKey(entityType)) return;

        Entity entity = event.getEntity();
        World world = entity.getWorld();
        Location location = entity.getLocation();

        for (String drop : mobs.getPerEntityList().get(entityType)){
            MobDrop mobDrop = mobs.getList().get(drop);
            if (mobDrop.isOnlyPlayer()){
                Player player = event.getEntity().getKiller();
                if (player == null || !player.hasPermission("customdrops.mobs."+drop)) continue;
            }
            if (!mobDrop.isDropDefault()) event.getDrops().clear();
            for (ItemStack item : mobDrop.getDrops()){
                world.dropItem(location, item);
            }
        }
    }
}
