package uk.firedev.datapackssuck.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;
import uk.firedev.datapackssuck.config.MainConfig;

import java.util.List;

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
        ItemStack head = ItemType.PLAYER_HEAD.createItemStack(
            skullMeta -> {
                skullMeta.setPlayerProfile(player.getPlayerProfile());
                skullMeta.lore(List.of(
                    Component.text("Killed by " + killer.getName()).color(NamedTextColor.YELLOW)
                ));
            }
        );
        player.getWorld().dropItemNaturally(player.getLocation(), head);
    }

}
