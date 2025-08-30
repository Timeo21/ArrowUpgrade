package ch.timeo.arrowCrafting;

import ch.timeo.arrowCrafting.listeners.ArrowListener;
import ch.timeo.arrowCrafting.listeners.FletchingListener;
import ch.timeo.arrowCrafting.registry.ArrowRegistry;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArrowCrafting extends JavaPlugin {
    private static ArrowCrafting instance;

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
