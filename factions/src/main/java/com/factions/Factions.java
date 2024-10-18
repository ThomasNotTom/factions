package com.factions;

import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import com.factions.commands.FactionCommands;

public class Factions extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("MyPlugin has been enabled!");
        getServer().getPluginManager().registerEvents(new ChatHandler(), this);

        FactionCommands factionCommands = new FactionCommands();
        getCommand("faction").setExecutor(factionCommands);
        getCommand("faction").setTabCompleter(factionCommands);

        FactionHandler.addFaction(new Faction(new UUID(0, 1), "MyFaction", 0));
    }

    @Override
    public void onDisable() {
        // This method is called when the plugin is disabled
        getLogger().info("MyPlugin has been disabled!");
    }

}
