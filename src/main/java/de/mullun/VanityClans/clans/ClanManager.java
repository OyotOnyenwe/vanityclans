package de.mullun.VanityClans.clans;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scoreboard.Team;

import de.mullun.VanityClans.main.Main;

public class ClanManager {
	
	public static List<Clan> clanList;
	
	public static FileConfiguration config;
	
	public ClanManager(FileConfiguration configg) {
		config = configg;
		clanList = getClans();
	}
	
	public static void deleteClan(Clan clan) {
		Team t = clan.getTeam();
		for(String s : config.getConfigurationSection("clans").getKeys(false))
			if(config.getString("clans." + s + ".name").equals(t.getName()))
				config.set("clans." + s, null);
		for(String s : t.getEntries()) {
			t.removeEntry(s);
		}
		t.unregister();
		Main.main.saveConfig();
		clanList.remove(clan);
	}
	
	public List<Clan> getClans() {
		List<Clan> result = new ArrayList<Clan>();
		if(!config.contains("clans")) return result;
		for(String s : config.getConfigurationSection("clans").getKeys(false)) {
			result.add(new Clan(config, s));
		}
		return result;
	}
	
	public static Clan getClan(String name) {
		for(Clan c : clanList) {
			if(removeColorCodes(c.getName()).equals(removeColorCodes(name)))
				return c;
		}
		return null;
	}
	
	public static Clan getClanOf(String name) {
		for(Clan c : clanList) {
			if(c.getTeam().hasEntry(name))
				return c;
		}
		return null;
	}
	
	public static String removeColorCodes(String s) {
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
