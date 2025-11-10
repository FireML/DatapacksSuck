package uk.firedev.datapackssuck.listeners;

import io.papermc.paper.datacomponent.DataComponentTypes;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import uk.firedev.daisylib.command.CooldownHelper;
import uk.firedev.daisylib.libs.messagelib.message.ComponentMessage;
import uk.firedev.daisylib.libs.messagelib.message.MessageType;

import java.time.Duration;

@SuppressWarnings("UnstableApiUsage")
public class DurabilityPing implements Listener {

    private final CooldownHelper cooldownHelper = CooldownHelper.create();

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
        double percentDamaged = (itemDamage / (double) maxDamage) * 100.0;

        if (percentDamaged >= 90.0) {
            if (cooldownHelper.hasCooldown(player.getUniqueId())) {
                return;
            }
            cooldownHelper.applyCooldown(player.getUniqueId(), Duration.ofSeconds(15));
            ComponentMessage.componentMessage("<gold>{item} <red>durability low! <gold>{current} <red>of {max} remaining.", MessageType.SUBTITLE)
                .replace("{item}", Component.translatable(item))
                .replace("{current}", Component.text(currentDamage))
                .replace("{max}", Component.text(maxDamage))
                .send(player);
            player.playSound(
                Sound.sound()
                    .type(Key.key("minecraft:block.anvil.land"))
                    .volume(1)
                    .pitch(2)
                    .build()
            );
        }
    }

}
