package de.mullun.VanityClans.commands;

import java.io.File;
import java.util.HashMap;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class PermissionHandler {

	private FileConfiguration config;
	private File file;
	
	private HashMap<CommandAction, String> permissions = new HashMap<CommandAction, String>();
	
	public PermissionHandler(FileConfiguration config, File file) {
		this.config = config;
		this.file = file;
		
		manageConfig();
	}
	
	private void manageConfig() {
		for(CommandAction action : CommandAction.getAllActions()) {
			if(!config.contains("commandActions." + action.toString())) {
				String permission = "no permission required";
				if(action.requiresPermissionOnDefault()) permission = "vanityclans.admin";
				config.set("commandActions." + action.toString(), permission);
				save();
			}
			String permission = config.getString("commandActions." + action.toString());
			if(!permission.equals("no permission required")) permissions.put(action, permission);
		}
	}
	
	public boolean hasPermisson(CommandSender sender, CommandAction action) {
		return permissions.containsKey(action) ? sender.hasPermission(permissions.get(action)) : true;
	}
	
	private void save() {
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
