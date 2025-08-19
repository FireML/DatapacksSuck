package uk.firedev.datapackssuck.listeners;

import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class BetterItemFrames implements Listener {

    // shears = invisible
    // brush = visible
    // glass pane = locked
    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) {
        if (!(event.getRightClicked() instanceof ItemFrame itemFrame)) {
            return;
        }
        Player player = event.getPlayer();
        if (!player.isSneaking()) {
            return;
        }
        ItemStack handItem = player.getInventory().getItemInMainHand();
        if (handItem.isEmpty()) {
            return;
        }
        switch (handItem.getType()) {
            case SHEARS -> {
                if (!itemFrame.isVisible()) {
                    player.sendPlainMessage("The item frame is already invisible.");
                    return;
                }
                itemFrame.setVisible(false);
                player.sendPlainMessage("The item frame is now invisible.");
            }
            case BRUSH -> {
                if (itemFrame.isVisible()) {
                    player.sendPlainMessage("The item frame is already visible.");
                    return;
                }
                itemFrame.setVisible(true);
                player.sendPlainMessage("The item frame is now visible.");
            }
            case GLASS_PANE -> {
                if (itemFrame.isFixed()) {
                    itemFrame.setFixed(false);
                    player.sendPlainMessage("The item frame is now unlocked.");
                } else {
                    itemFrame.setFixed(true);
                    player.sendPlainMessage("The item frame is now locked.");
                }
            }
        }
    }

}
