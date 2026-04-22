package uk.firedev.datapackssuck.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import uk.firedev.daisylib.builders.ItemBuilder;
import uk.firedev.datapackssuck.config.MainConfig;

public class PlayerHeadDrops implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        if (!MainConfig.getInstance().isPlayerHeadDropsEnabled()) {
            return;
        }
        Player player = event.getPlayer();
        Player killer = event.getPlayer().getKiller();
        if (killer == null) {
            return;
        }
        ItemStack head = ItemBuilder.skull(player)
            .addLore("<yellow>Killed by " + killer.getName())
            .getItem();
        player.getWorld().dropItemNaturally(player.getLocation(), head);
    }

}
