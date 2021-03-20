package com.onyenwe.vanityclans;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin
{
    String pluginVersion = getDescription().getVersion();
    String pluginName = getName();
    
    @Override
    public void onEnable()
    {
        getLogger().info("VanityClans has been enabled.");
    }
    @Override
    public void onDisable()
    {
        getLogger().info("VanityClans has been disabled.");
    }
}
