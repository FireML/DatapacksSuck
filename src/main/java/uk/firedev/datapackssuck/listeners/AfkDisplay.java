package uk.firedev.datapackssuck.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.Conversation;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import uk.firedev.daisylib.events.PlayerMoveBlockEvent;
import uk.firedev.datapackssuck.DatapacksSuck;
import uk.firedev.datapackssuck.config.MainConfig;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AfkDisplay implements Listener {

    public static final AfkDisplay INSTANCE = new AfkDisplay();

    private final Map<UUID, Instant> tracker = new HashMap<>();
    private BukkitTask task;

    public void start() {
        if (task != null) {
            return; // Already started
        }
        Bukkit.getPluginManager().registerEvents(this, DatapacksSuck.getInstance());
        task = Bukkit.getScheduler().runTaskTimer(
            DatapacksSuck.getInstance(),
            () -> Bukkit.getOnlinePlayers().forEach(player -> {
                if (!MainConfig.getInstance().isAfkDisplayEnabled()) {
                    return;
                }
                Instant lastSeen = tracker.get(player.getUniqueId());
                if (lastSeen == null) {
                    return;
                }
                if (lastSeen.isBefore(Instant.now().minusSeconds(300))) {
                    player.playerListName(player.name().color(NamedTextColor.GRAY));
                }
            }),
            6000L, 6000L
        );
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMove(PlayerMoveBlockEvent event) {
        Player player = event.getPlayer();
        tracker.put(event.getPlayer().getUniqueId(), Instant.now());
        player.playerListName(null);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        tracker.put(event.getPlayer().getUniqueId(), Instant.now());
        player.playerListName(null);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        tracker.remove(event.getPlayer().getUniqueId());
        player.playerListName(null);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        tracker.put(event.getPlayer().getUniqueId(), Instant.now());
        player.playerListName(null);
    }

}
