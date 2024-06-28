package de.mullun.VanityClans.clans;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scoreboard.Team;

public class ClanManager {
	
	public List<Clan> clanList;
	
	private FileConfiguration config;
	public File file;
	
	public ClanManager(File file, FileConfiguration config) {
		this.config = config;
		this.file = file;
		clanList = getClans();
	}
	
	public void deleteClan(Clan clan) {
		Team t = clan.getTeam();
		for(String s : config.getConfigurationSection("clans").getKeys(false))
			if(config.getString("clans." + s + ".name").equals(t.getName()))
				config.set("clans." + s, null);
		for(String s : t.getEntries()) {
			t.removeEntry(s);
		}
		t.unregister();
		saveConfig();
		clanList.remove(clan);
	}
	
	public void addClan(String name, UUID owner) {
		Clan newClan = new Clan(config, file, UUID.randomUUID().toString(), name, owner);
		clanList.add(newClan);
	}
	
	public List<Clan> getClans() {
		List<Clan> result = new ArrayList<Clan>();
		if(!config.contains("clans")) return result;
		for(String s : config.getConfigurationSection("clans").getKeys(false)) {
			result.add(new Clan(config, file, s));
		}
		return result;
	}
	
	public Clan getClan(String name) {
		for(Clan c : clanList) {
			if(removeColorCodes(c.getName()).equals(removeColorCodes(name)))
				return c;
		}
		return null;
	}
	
	public Clan getClanOf(String name) {
		for(Clan c : clanList) {
			if(c.getTeam().hasEntry(name))
				return c;
		}
		return null;
	}
	
	public void saveConfig() {
		try {
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
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
	
}
