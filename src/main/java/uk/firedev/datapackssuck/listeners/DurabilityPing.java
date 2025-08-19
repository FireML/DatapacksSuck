package uk.firedev.datapackssuck.listeners;

import io.papermc.paper.datacomponent.DataComponentTypes;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import uk.firedev.daisylib.api.message.Message;
import uk.firedev.daisylib.api.message.component.ComponentMessage;

@SuppressWarnings("UnstableApiUsage")
public class DurabilityPing implements Listener {

    @EventHandler
    public void onItemDamaged(PlayerItemDamageEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Integer itemDamage = item.getData(DataComponentTypes.DAMAGE);
        Integer maxDamage = item.getData(DataComponentTypes.MAX_DAMAGE);
        if (itemDamage == null || maxDamage == null) {
            return;
        }

        int currentDamage = maxDamage - itemDamage;

        if (currentDamage == 6) {
            ComponentMessage.fromString("<gold>{item} <red>durability low! <gold>{current} <red>of {max} remaining.", Message.MessageType.TITLE)
                .replace("item", Component.translatable(item))
                .replace("current", Component.text(currentDamage))
                .replace("max", Component.text(maxDamage))
                .sendMessage(player);
        }
    }

}
