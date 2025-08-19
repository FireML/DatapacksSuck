package uk.firedev.datapackssuck;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

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
    public void onLoad() {}

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

}
