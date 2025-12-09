package uk.firedev.datapackssuck.config;

import org.jetbrains.annotations.NotNull;
import uk.firedev.daisylib.config.ConfigBase;
import uk.firedev.datapackssuck.DatapacksSuck;

public class MainConfig extends ConfigBase {

    private static final MainConfig instance = new MainConfig();

    private MainConfig() {
        super("config.yml", "config.yml", DatapacksSuck.getInstance());
    }

    public static @NotNull MainConfig getInstance() {
        return instance;
    }

    public boolean isAfkDisplayEnabled() {
        return getConfig().getBoolean("afk-display", true);
    }

    public boolean isAntiEndermanGriefEnabled() {
        return getConfig().getBoolean("anti-enderman-grief", true);
    }

    public boolean isBetterItemFramesEnabled() {
        return getConfig().getBoolean("better-item-frames", true);
    }

    public boolean isBuddingAmethystSilkTouchEnabled() {
        return getConfig().getBoolean("budding-amethyst-silk-touch", true);
    }

    public boolean isDurabilityPingEnabled() {
        return getConfig().getBoolean("durability-ping", true);
    }

    public boolean isPlayerHeadDropsEnabled() {
        return getConfig().getBoolean("player-head-drops", true);
    }

}
