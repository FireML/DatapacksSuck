package uk.firedev.datapackssuck;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import uk.firedev.datapackssuck.command.MainCommand;
import uk.firedev.datapackssuck.config.MainConfig;
import uk.firedev.datapackssuck.listeners.AfkDisplay;
import uk.firedev.datapackssuck.listeners.AntiEndermanGrief;
import uk.firedev.datapackssuck.listeners.BetterItemFrames;
import uk.firedev.datapackssuck.listeners.BuddingAmethystSilkTouch;
import uk.firedev.datapackssuck.listeners.DurabilityPing;
import uk.firedev.datapackssuck.listeners.PlayerHeadDrops;

public final class DatapacksSuck extends JavaPlugin {

    private static DatapacksSuck INSTANCE;

    public DatapacksSuck() {
        if (INSTANCE != null) {
            throw new UnsupportedOperationException(getClass().getName() + " has already been assigned!");
        }
        INSTANCE = this;
    }

    public static @NotNull DatapacksSuck getInstance() {
        if (INSTANCE == null) {
            throw new UnsupportedOperationException(DatapacksSuck.class.getSimpleName() + " has not been assigned!");
        }
        return INSTANCE;
    }

    @Override
    public void onLoad() {
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register(MainCommand.get());
        });
    }

    @Override
    public void onEnable() {
        MainConfig.getInstance().performManualUpdates();
        loadListeners();
    }

    public void reload() {
        MainConfig.getInstance().reload();
    }

    @Override
    public void onDisable() {}

    private void loadListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new BuddingAmethystSilkTouch(), this);
        pm.registerEvents(new AntiEndermanGrief(), this);
        pm.registerEvents(new DurabilityPing(), this);
        pm.registerEvents(new PlayerHeadDrops(), this);
        pm.registerEvents(new BetterItemFrames(), this);

        AfkDisplay.INSTANCE.start();
    }

}
