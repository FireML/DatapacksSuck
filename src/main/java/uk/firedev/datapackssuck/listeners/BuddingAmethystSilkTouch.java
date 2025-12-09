package uk.firedev.datapackssuck.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;
import uk.firedev.datapackssuck.config.MainConfig;

public class BuddingAmethystSilkTouch implements Listener {

    @EventHandler
    public void onBuddingBroken(BlockBreakEvent event) {
        if (!MainConfig.getInstance().isBuddingAmethystSilkTouchEnabled()) {
            return;
        }
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (!block.getType().equals(Material.BUDDING_AMETHYST)) {
            return;
        }
        ItemStack handItem = player.getInventory().getItemInMainHand();
        if (handItem.isEmpty() || !handItem.containsEnchantment(Enchantment.SILK_TOUCH)) {
            return;
        }
        block.getWorld().dropItemNaturally(block.getLocation(), ItemType.BUDDING_AMETHYST.createItemStack());
    }

}
