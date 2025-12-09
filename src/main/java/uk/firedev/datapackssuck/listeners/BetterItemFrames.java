package uk.firedev.datapackssuck.listeners;

import io.papermc.paper.event.player.PlayerItemFrameChangeEvent;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import uk.firedev.datapackssuck.config.MainConfig;

public class BetterItemFrames implements Listener {

    private final NamespacedKey justUnlockedKey = new NamespacedKey("datapackssuck", "just_unlocked");

    // shears = invisible
    // brush = visible
    // glass pane = locked
    @EventHandler
    public void onInteract(PlayerItemFrameChangeEvent event) {
        if (!MainConfig.getInstance().isBetterItemFramesEnabled()) {
            return;
        }
        ItemFrame itemFrame = event.getItemFrame();
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
                event.setCancelled(true);
                if (!itemFrame.isVisible()) {
                    player.sendPlainMessage("The item frame is already invisible.");
                    return;
                }
                itemFrame.setVisible(false);
                player.sendPlainMessage("The item frame is now invisible.");
            }
            case BRUSH -> {
                event.setCancelled(true);
                if (itemFrame.isVisible()) {
                    player.sendPlainMessage("The item frame is already visible.");
                    return;
                }
                itemFrame.setVisible(true);
                player.sendPlainMessage("The item frame is now visible.");
            }
            case GLASS_PANE -> {
                event.setCancelled(true);
                // This will only fire when the item frame is not locked
                if (isJustUnlocked(itemFrame)) {
                    setJustUnlocked(itemFrame, false);
                    return;
                }
                itemFrame.setFixed(true);
                player.sendPlainMessage("The item frame is now locked.");
            }
        }
    }

    @EventHandler
    public void onInteractLocked(PlayerInteractAtEntityEvent event) {
        if (!MainConfig.getInstance().isBetterItemFramesEnabled()) {
            return;
        }
        if (!(event.getRightClicked() instanceof ItemFrame itemFrame)) {
            return;
        }
        if (!itemFrame.isFixed()) {
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
        if (handItem.getType() == Material.GLASS_PANE) {
            itemFrame.setFixed(false);
            player.sendPlainMessage("The item frame is now unlocked.");
            setJustUnlocked(itemFrame, true);
        }
    }

    private void setJustUnlocked(ItemFrame itemFrame, boolean locked) {
        itemFrame.getPersistentDataContainer().set(justUnlockedKey, PersistentDataType.BOOLEAN, locked);
    }

    private boolean isJustUnlocked(ItemFrame itemFrame) {
        return itemFrame.getPersistentDataContainer().getOrDefault(justUnlockedKey, PersistentDataType.BOOLEAN, false);
    }

}
