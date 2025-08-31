package ch.timeo.arrowUpgrade;

import ch.timeo.arrowUpgrade.listeners.ArrowListener;
import ch.timeo.arrowUpgrade.listeners.FletchingListener;
import ch.timeo.arrowUpgrade.registry.ArrowRegistry;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArrowUpgrade extends JavaPlugin {
    private static ArrowUpgrade instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        ArrowRegistry.registerAll();

        Bukkit.getPluginManager().registerEvents(new ArrowListener(instance), this);
        Bukkit.getPluginManager().registerEvents(new FletchingListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getInstance(){
        return instance;
    }
}
