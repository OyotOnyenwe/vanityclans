package de.mullun.VanityClans.main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import de.mullun.VanityClans.clans.ClanManager;
import de.mullun.VanityClans.commands.ClanCommand;
import de.mullun.VanityClans.commands.PermissionHandler;

public class Main extends JavaPlugin implements Listener {

	public static String PREFIX = "§7[§8VanityClans§7] ";
	public static Main main;
	public static int maxLength;
	public static boolean allowColors;
	
	public static ClanManager clanManager;
	public static PermissionHandler permissionHandler;
	
	public File clanYMLFile;
	public FileConfiguration clanYML;
	
	@Override
	public void onEnable() {
		main = this;
		
		saveConfig();
		
		configOptions();
		clanManager = new ClanManager(clanYMLFile, clanYML);
		
		Bukkit.getPluginManager().registerEvents(this, this);
		
		getCommand("vanityclans").setExecutor(new ClanCommand());
		getCommand("vanityclans").setTabCompleter(new ClanCommand());
		
		getLogger().info("----========== Plugin enabled ==========----");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("----========== Plugin disabled ==========----");
	}
	
	public void configOptions() {
		reloadConfig();
		
		clanYMLFile = new File(getDataFolder(), "clans.yml");
		
		if (!clanYMLFile.exists()) {
			try {
				clanYMLFile.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		clanYML = YamlConfiguration.loadConfiguration(clanYMLFile);
		
		File permissionFile = new File(getDataFolder(), "permissions.yml");
		
		if (!permissionFile.exists()) {
			try {
				permissionFile.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		FileConfiguration permissionConfig = YamlConfiguration.loadConfiguration(permissionFile);
		
		permissionHandler = new PermissionHandler(permissionConfig, permissionFile);
		
		if(!getConfig().contains("settings.prefix")) {
			getConfig().set("settings.prefix", "&7[&8VanityClans&7] ");
			saveConfig();
		}
		PREFIX = getConfig().getString("settings.prefix").replace("&", "§");
		if(!getConfig().contains("settings.maxClanNameLength")) {
			getConfig().set("settings.maxClanNameLength", 15);
			saveConfig();
		}
		maxLength = getConfig().getInt("settings.maxClanNameLength");
		String p, c, h, t;
		if(!getConfig().contains("settings.defaultPlayerColor")) {
			getConfig().set("settings.defaultPlayerColor", "&5");
			saveConfig();
		}
		if(!getConfig().contains("settings.defaultClanColor")) {
			getConfig().set("settings.defaultClanColor", "&8");
			saveConfig();
		}
		if(!getConfig().contains("settings.defaultTextColor")) {
			getConfig().set("settings.defaultTextColor", "&7");
			saveConfig();
		}
		if(!getConfig().contains("settings.defaultCommandSuggestionColor")) {
			getConfig().set("settings.defaultCommandSuggestionColor", "&6");
			saveConfig();
		}
		p = getConfig().getString("settings.defaultPlayerColor");
		c = getConfig().getString("settings.defaultClanColor");
		h = getConfig().getString("settings.defaultCommandSuggestionColor");
		t = getConfig().getString("settings.defaultTextColor");
		new Lang(p, c, h, t);
		if(!getConfig().contains("settings.allowColorCodes")) {
			getConfig().set("settings.allowColorCodes", true);
			saveConfig();
		}
		allowColors = getConfig().getBoolean("settings.allowColorCodes");
	}
	
	public static ClanManager getClanManager() {
		return clanManager;
	}
	
	public static PermissionHandler getPermissionHandler() {
		return permissionHandler;
	}
	
}
