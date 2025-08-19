package uk.firedev.datapackssuck.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;

public class PlayerHeadDrops implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        Player killer = event.getPlayer().getKiller();
        if (killer == null) {
            return;
        }
        ItemStack head = ItemType.PLAYER_HEAD.createItemStack(
            skullMeta -> skullMeta.setPlayerProfile(player.getPlayerProfile())
        );
        player.getWorld().dropItemNaturally(player.getLocation(), head);
    }

}
