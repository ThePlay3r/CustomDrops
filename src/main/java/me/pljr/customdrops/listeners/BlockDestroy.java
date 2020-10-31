package me.pljr.customdrops.listeners;

import me.pljr.customdrops.config.CfgBlocks;
import me.pljr.customdrops.objects.BlockDrop;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockDestroy implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Block block = event.getBlock();
        Material material = block.getType();
        if (!CfgBlocks.perMaterialList.containsKey(material)) return;

        Player player = event.getPlayer();
        World world = player.getWorld();
        Location location = event.getBlock().getLocation();

        for (String drop : CfgBlocks.perMaterialList.get(material)){
            if (!player.hasPermission("customdrops.blocks."+drop)) continue;
            BlockDrop blockDrop = CfgBlocks.list.get(drop);
            if (blockDrop.isCheckForDrops()){
                ItemStack brokenWith = player.getItemInHand();
                if (brokenWith == null){
                    brokenWith = new ItemStack(Material.AIR);
                }
                if (block.getDrops(brokenWith).isEmpty()) return;
            }
            if (!blockDrop.isDropDefault()){
                event.setCancelled(true);
                world.getBlockAt(location).setType(Material.AIR);
            }
            if (blockDrop.isDropExp() && event.isCancelled()){
                player.giveExp(event.getExpToDrop());
            }else if (!blockDrop.isDropExp() && !event.isCancelled()){
                event.setExpToDrop(0);
            }
            for (ItemStack item : blockDrop.getDrops()){
                world.dropItemNaturally(location, item);
            }
        }
    }
}
