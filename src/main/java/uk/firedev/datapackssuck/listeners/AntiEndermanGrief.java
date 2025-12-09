package uk.firedev.datapackssuck.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import uk.firedev.datapackssuck.config.MainConfig;

public class AntiEndermanGrief implements Listener {

    @EventHandler
    public void onEndermanGrief(EntityChangeBlockEvent event) {
        if (!MainConfig.getInstance().isAntiEndermanGriefEnabled()) {
            return;
        }
        if (event.getEntityType() == EntityType.ENDERMAN) {
            event.setCancelled(true);
        }
    }

}
