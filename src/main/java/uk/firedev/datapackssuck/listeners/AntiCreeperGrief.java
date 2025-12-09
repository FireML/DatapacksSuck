package uk.firedev.datapackssuck.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.TNTPrimeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import uk.firedev.datapackssuck.config.MainConfig;

public class AntiCreeperGrief implements Listener {

    @EventHandler
    public void onCreeperGrief(EntityExplodeEvent event) {
        if (!MainConfig.getInstance().isAntiCreeperGriefEnabled()) {
            return;
        }
        if (event.getEntityType() == EntityType.CREEPER) {
            event.blockList().clear();
        }
    }

    @EventHandler
    public void onBlockIgnite(TNTPrimeEvent event) {
        if (!MainConfig.getInstance().isAntiCreeperGriefEnabled()) {
            return;
        }
        Entity primingEntity = event.getPrimingEntity();
        if (primingEntity != null && primingEntity.getType() == EntityType.CREEPER) {
            event.setCancelled(true);
        }
    }

}
