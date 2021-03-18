package com.onyenwe.vanityclans;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        getLogger().info("VanityClans v0.0.1 has been enabled.");
    }
    @Override
    public void onDisable()
    {
        getLogger().info("VanityClans v0.0.1 has been disabled.");
    }
}
