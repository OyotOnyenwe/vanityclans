package de.mullun.VanityClans.clans;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import de.mullun.VanityClans.main.Lang;
import de.mullun.VanityClans.main.Main;

public class Clan {

	public String name;
	public Team team;
	public UUID owner;
	public List<UUID> requests;
	public FileConfiguration config;
	public File file;
	public String key;
	
	public Clan(FileConfiguration config, File file, String key) {
		this.config = config;
		this.file = file;
		this.key = key;
		readConfig();
		team = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(name);
		if(team==null) {
			Main.main.getLogger().warning("Team " + name + " not found!");		
		}
	}
	
	public Clan(FileConfiguration config, File file, String key, String name, UUID owner) {
		this.config = config;
		this.file = file;
		this.key = key;
		if(Main.allowColors)
			this.name = name.replace("&", "§");
		else
			this.name = name;
		this.owner = owner;
		requests = new ArrayList<UUID>();
		config.set("clans." + key + ".name", this.name);
		if(owner!=null)
			config.set("clans." + key + ".owner", owner.toString());
		else
			config.set("clans." + key + ".owner", "NONE");
		config.set("clans." + key + ".requests", new ArrayList<String>());
		Team tteam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(this.name);
		tteam.setPrefix("§7[" + Lang.C + this.name  + "§7] ");
		tteam.setAllowFriendlyFire(true);
		team = tteam;
		saveConfig();
	}
	
	public void addPlayer(OfflinePlayer p) {
		team.addEntry(p.getName());
		if(owner==null)
			setOwner(p.getUniqueId());
		for(Clan c : Main.getClanManager().getClans()) {
			if(c.getRequests().contains(p.getUniqueId()))
				c.removeRequest(p.getUniqueId());
		}
	}
	
	public void removePlayer(OfflinePlayer p) {
		team.removeEntry(p.getName());
	}
	
	public void removePlayer(String s) {
		team.removeEntry(s);
	}
	
	public void addRequest(UUID id) {
		requests.add(id);
		List<String> configList = new ArrayList<String>();
		for(UUID current : requests)
			configList.add(current.toString());
		config.set("clans." + key + ".requests", configList);
		saveConfig();
	}
	
	public void removeRequest(UUID id) {
		requests.remove(id);
		List<String> configList = new ArrayList<String>();
		for(UUID current : requests)
			configList.add(current.toString());
		config.set("clans." + key + ".requests", configList);
		saveConfig();
	}
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getName() {
		return name;
	}
	
	public UUID getOwner() {
		return owner;
	}
	
	public void clanMessage(String s, Player ignore) {
		for(String current : team.getEntries()) {
			if(Bukkit.getPlayer(current)!=null&&!Bukkit.getPlayer(current).equals(ignore))
				Bukkit.getPlayer(current).sendMessage(s);
		}
	}
	
	public void setOwner(UUID owner) {
		this.owner = owner;
		config.set("clans." + key + ".owner", owner.toString());
		saveConfig();
	}
	
	public List<UUID> getRequests() {
		return requests;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public boolean isOwner(UUID id) {
		if(owner == null) return false;
		if(owner.equals(id))
			return true;
		else return false;
	}
	
	private void readConfig() {
		this.name = config.getString("clans." + key + ".name");
		try {
			this.owner = UUID.fromString(config.getString("clans." + key + ".owner"));
		} catch (Exception e) {
			this.owner = null;
		}
		requests = new ArrayList<UUID>();
		List<String> ids = config.getStringList("clans." + key + ".requests");
		if(ids!=null&&!ids.isEmpty()) {
			for(String s : ids) {
				requests.add(UUID.fromString(s));
			}
		}
	}
	
	public String removeColorCodes(String s) {
		boolean delete = false;
		String result = "";
		for(int i = 0; i<s.length(); i++) {
			if(!delete) {
				if(s.charAt(i)=='§'||s.charAt(i)=='&') {
					delete = true;
				} else {
					result += s.charAt(i);
				}
			} else {
				delete = false;
				if(s.charAt(i)=='§'||s.charAt(i)=='&')
					delete = true;
			}
		}
		return result;
	}
	
	private void saveConfig() {
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
