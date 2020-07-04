package com.dfsek.scatmansworld;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        com.dfsek.betterend.Metrics metrics = new com.dfsek.betterend.Metrics(this, 8080);
        getLogger().info("Ski ba da bada bop.");
    }

    public ScatmanChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new ScatmanChunkGenerator();
    }

    @Override
    public void onDisable() {
        getLogger().info("Ba bapity ba ba ba......");
    }
}
