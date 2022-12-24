package de.mullun.VanityClans.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import de.mullun.VanityClans.clans.Clan;
import de.mullun.VanityClans.clans.ClanManager;
import de.mullun.VanityClans.main.Lang;
import de.mullun.VanityClans.main.Main;

public class ClanCommand implements TabExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] text) {
		if(text.length==0) {
			sender.sendMessage(Main.PREFIX + "Syntax error. Use " + Lang.H + "/vanityclans help " + Lang.T + "to get an overview of available commands!");
			return false;
		}
		if(text.length==1) {
			if(text[0].equalsIgnoreCase("help")) {
				sender.sendMessage(Lang.T + "[------ " + Lang.H + "Vanity Clans " + Lang.T + "------]");
				sender.sendMessage(Lang.T + "- " + Lang.H + "/vanityclans create <clan name>");
				sender.sendMessage(Lang.T + "   -> Create a clan named <clan name>");
				sender.sendMessage(Lang.T + "- " + Lang.H + "/vanityclans join <clan name>");
				sender.sendMessage(Lang.T + "   -> Join a clan (send a request)");
				sender.sendMessage(Lang.T + "- " + Lang.H + "/vanityclans membership <player name>");
				sender.sendMessage(Lang.T + "   -> Check the clan membership of a player");
				sender.sendMessage(Lang.T + "- " + Lang.H + "/vanityclans members <clan name>");
				sender.sendMessage(Lang.T + "   -> Shows all members of <clan name>");
				sender.sendMessage(Lang.T + "- " + Lang.H + "/vanityclans leave");
				sender.sendMessage(Lang.T + "   -> Leave your current clan");
				sender.sendMessage(Lang.T + "- " + Lang.H + "/vanityclans kick");
				sender.sendMessage(Lang.T + "   -> Force someone to leave your clan (only for clan leader)");
				sender.sendMessage(Lang.T + "- " + Lang.H + "/vanityclans inbox <player name> <accept/deny>");
				sender.sendMessage(Lang.T + "   -> Accept or deny join requests");
				sender.sendMessage(Lang.T + "- " + Lang.H + "/vanityclans promote <player name>");
				sender.sendMessage(Lang.T + "   -> Degrade yourself and promote another player (only for clan leader)");
				sender.sendMessage(Lang.T + "- " + Lang.H + "/vanityclans dissolve");
				sender.sendMessage(Lang.T + "   -> Delete your clan (only for clan leader)");
				sender.sendMessage(Lang.T + "[------------------------]");
				return false;
			} else if(text[0].equalsIgnoreCase("membership")) {
				sender.sendMessage(Main.PREFIX + "Not enough arguments. Please use " + Lang.H + "/vanityclans membership <player name>" + Lang.T + "!");
				return false;
			} else if(text[0].equalsIgnoreCase("members")) {
				sender.sendMessage(Main.PREFIX + "Not enough arguments. Please use " + Lang.H + "/vanityclans member <clan name>" + Lang.T + "!");
				return false;
			} else if(text[0].equalsIgnoreCase("kick")) {
				sender.sendMessage(Main.PREFIX + "Not enough arguments. Please use " + Lang.H + "/vanityclans kick <member name>" + Lang.T + "!");
				return false;
			} else if(text[0].equalsIgnoreCase("inbox")) {
				sender.sendMessage(Main.PREFIX + "Not enough arguments. Please use " + Lang.H + "/vanityclans inbox <player name> <accept/deny>" + Lang.T + "!");
				return false;
			} else if(text[0].equalsIgnoreCase("create")) {
				sender.sendMessage(Main.PREFIX + "Not enough arguments. Please use " + Lang.H + "/vanityclans create <clan name>" + Lang.T + "!");
				return false;
			} else if(text[0].equalsIgnoreCase("promote")) {
				sender.sendMessage(Main.PREFIX + "Not enough arguments. Please use " + Lang.H + "/vanityclans promote <clan member>" + Lang.T + "!");
				return false;
			} else if(text[0].equalsIgnoreCase("dissolve")) {
				if(sender instanceof Player) {
					Clan c = ClanManager.getClanOf(sender.getName());
					if(c==null) {
						sender.sendMessage(Main.PREFIX + "You have to be a clan leader to use this command!");
						return false;
					}
					if(!c.isOwner(((Player) sender).getUniqueId())) {
						sender.sendMessage(Main.PREFIX + "You have to be a clan leader to use this command!");
						return false;
					}
					String clanName = c.getName();
					ClanManager.deleteClan(c);
					sender.sendMessage(Main.PREFIX + "§aSuccessfully dissolved " + Lang.C + clanName + Lang.T + "!");
					return false;
				} else {
					sender.sendMessage(Main.PREFIX + "Not enough arguments. Please use " + Lang.H + "/vanityclans dissolve <name>" + Lang.T + "!");
					return false;
				}
			} else if(text[0].equalsIgnoreCase("join")) {
				sender.sendMessage(Main.PREFIX + "Not enough arguments. Please use " + Lang.H + "/vanityclans join <name>" + Lang.T + "!");
				return false;
			} else if(text[0].equalsIgnoreCase("leave")) {
				if(!(sender instanceof Player)) {
					sender.sendMessage(Main.PREFIX + "This command is only available to players!");
					return false;
				}
				Clan c = ClanManager.getClanOf(sender.getName());
				if(c==null) {
					sender.sendMessage(Main.PREFIX + "You didn't join a clan yet!");
					return false;
				}
				if(c.getTeam().getEntries().size()==1) {
					sender.sendMessage(Main.PREFIX + "You can't leave the clan when you are the only member. Use " + Lang.H + "/vanityclans dissolve" + Lang.T + " instead!");
					return false;
				}
				if(c.getOwner().equals(((Player)sender).getUniqueId())) {
					sender.sendMessage(Main.PREFIX + "You can't leave the clan as long as you're the leader! Use " + Lang.H + "/vanityclans promote <clan member>" + Lang.T + "!");
					return false;
				}
				c.removePlayer(((Player)sender));
				sender.sendMessage(Main.PREFIX + "You left the clan " + Lang.C + c.getName() + Lang.T + "!");
				return false;
			} else {
				sender.sendMessage(Main.PREFIX + "Unknown command. Use " + Lang.H + "/vanityclans help " + Lang.T + "to get an overview of available commands!");
				return false;
			}
		} else if(text.length==2) {
			String name = text[1];
			if(text[0].equalsIgnoreCase("help")) {
				sender.sendMessage(Main.PREFIX + "Too many arguments. Please use " + Lang.H + "/vanityclans help" + Lang.T + "!");
				return false;
			} else if(text[0].equalsIgnoreCase("inbox")) {
				sender.sendMessage(Main.PREFIX + "Not enough arguments. Please use " + Lang.H + "/vanityclans inbox <player name> <accept/deny>" + Lang.T + "!");
				return false;
			} else if(text[0].equalsIgnoreCase("membership")) {
				if(ClanManager.getClanOf(name)==null) {
					sender.sendMessage(Main.PREFIX + "The player " + Lang.P + name + Lang.T + " did not join a clan yet!");
					return false;
				} else {
					sender.sendMessage(Main.PREFIX + "The player " + Lang.P + name + Lang.T + " is a member of " + Lang.C + ClanManager.getClanOf(name).getName());
					return false;
				}
			} else if(text[0].equalsIgnoreCase("members")) {
				Clan c = ClanManager.getClan(text[1]);
				if(c==null) {
					sender.sendMessage(Main.PREFIX + "No clan named " + Lang.C + text[1] + Lang.T + " found!");
					return false;
				}
				if(c.getTeam().getEntries().size()==0) {
					sender.sendMessage(Main.PREFIX + "The clan " + Lang.C + c.getName() + Lang.T + " does not have any members yet!");
					return false;
				}
				Set<String> members = c.getTeam().getEntries();
				List<String> membersList = new ArrayList<String>();
				for(String s : members)
					membersList.add(s);
				String message = Lang.C + c.getName() + Lang.T + " has the following members: ";
				String leaderName = Bukkit.getOfflinePlayer(c.getOwner()).getName();
				if(membersList.get(0).equals(leaderName))
					message += "§l" + membersList.get(0) + "§r" + Lang.T;
				else
					message += membersList.get(0);
				for(int i = 1; i<membersList.size(); i++) {
					if(!membersList.get(i).equals(leaderName))
						message += ", " + membersList.get(i);
					else
						message += ", §l" + membersList.get(i) + "§r" + Lang.T;
				}
				sender.sendMessage(message);
				return false;
			} else if(text[0].equalsIgnoreCase("promote")) {
				Clan c = ClanManager.getClanOf(sender.getName());
				if(c==null) {
					sender.sendMessage(Main.PREFIX + "You have to be a clan leader to use this command!");
					return false;
				}
				if(!c.isOwner(((Player) sender).getUniqueId())) {
					sender.sendMessage(Main.PREFIX + "You have to be a clan leader to use this command!");
					return false;
				}
				String target = text[1];
				if(target.equals(sender.getName())) {
					sender.sendMessage(Main.PREFIX + "You can't promote yourself!");
					return false;
				}
				if(Bukkit.getPlayer(target)==null) {
					sender.sendMessage(Main.PREFIX + "The player " + Lang.P + target + Lang.T + " is not online. Wait for them and try again!");
					return false;
				}
				Player t = Bukkit.getPlayer(target);
				for(String s : c.getTeam().getEntries()) {
					if(target.equals(s)) {
						c.setOwner(t.getUniqueId());
						t.sendMessage(Main.PREFIX + "You got §apromoted" + Lang.T + "! You are the leader of " + Lang.C + c.getName() + Lang.T + " now!");
						sender.sendMessage(Main.PREFIX + "§aSuccessfully " + Lang.T + "promoted " + Lang.P + target + Lang.T + "!");						
						return false;
					}
				}
				sender.sendMessage(Main.PREFIX + "The player " + Lang.P + target + Lang.T + " is not a member of your clan. Wait for them to join and try again!");
				return false;
			} else if(text[0].equalsIgnoreCase("kick")) {
				Clan c = ClanManager.getClanOf(sender.getName());
				if(c==null) {
					sender.sendMessage(Main.PREFIX + "You have to be a clan leader to use this command!");
					return false;
				}
				if(!c.isOwner(((Player) sender).getUniqueId())) {
					sender.sendMessage(Main.PREFIX + "You have to be a clan leader to use this command!");
					return false;
				}
				String target = text[1];
				if(target.equals(sender.getName())) {
					sender.sendMessage(Main.PREFIX + "You can't kick yourself!");
					return false;
				}
				for(String s : c.getTeam().getEntries()) {
					if(target.equals(s)) {
						c.removePlayer(target);
						if(Bukkit.getPlayer(target)!=null)
							Bukkit.getPlayer(target).sendMessage(Main.PREFIX + "You got §ckicked" + Lang.T + "! You are no longer a member of " + Lang.C + c.getName() + Lang.T + "!");
						sender.sendMessage(Main.PREFIX + "§aSuccessfully " + Lang.T + "kicked " + Lang.P + target + Lang.T + "!");						
						return false;
					}
				}
				sender.sendMessage(Main.PREFIX + "The player " + Lang.P + target + Lang.T + " is not a member of your clan!");
				return false;
			} else if(text[0].equalsIgnoreCase("create")) {
				if(sender instanceof Player && ClanManager.getClanOf(sender.getName())!=null) {
					sender.sendMessage(Main.PREFIX + "You have to leave your current clan first! Use " + Lang.H + "/vanityclans leave" + Lang.T + "!");
					return false;
				}
				if(!nameIsValid(name, sender)) return false;
				if(ClanManager.getClanOf(name)!=null) {
					sender.sendMessage(Main.PREFIX + "You can't name the clan " + Lang.C + ClanManager.removeColorCodes(name) + " " + Lang.T + "because this name is already in use!");
					return false;
				}
				if(sender instanceof Player) {
					ClanManager.clanList.add(new Clan(Main.main.getConfig(), UUID.randomUUID().toString(), name, ((Player)sender).getUniqueId()));
					ClanManager.getClan(name).addPlayer(((Player)sender));
				} else {
					sender.sendMessage(Main.PREFIX + "This command is only available as a player!");
					return false;
				}
				sender.sendMessage(Main.PREFIX + "§aSuccessfully" + Lang.T + " created a clan named " + Lang.C + ClanManager.getClan(name).getName() + Lang.T + "!");
				return false;
			} else if(text[0].equalsIgnoreCase("dissolve")) {
				if(!(sender instanceof Player)) {
					if(ClanManager.getClan(text[1])==null) {
						sender.sendMessage(Main.PREFIX + "No clan named " + Lang.C + text[1] + " " + Lang.T + "found!");
						return false;
					}
					ClanManager.deleteClan(ClanManager.getClan(text[1]));
					sender.sendMessage(Main.PREFIX + "§aSuccessfully" + Lang.T + " deleted the clan " + Lang.C + text[1] + Lang.T + "!");
					return false;
				} else {
					sender.sendMessage(Main.PREFIX + "Too many arguments. Please use " + Lang.H + "/vanityclans dissolve" + Lang.T + "!");
					return false;
				}
			} else if(text[0].equalsIgnoreCase("join")) {
				if(!(sender instanceof Player)) {
					sender.sendMessage(Main.PREFIX + "This command is only available as a player!");
					return false;
				}
				Clan c = ClanManager.getClan(text[1]);
				if(c==null) {
					sender.sendMessage(Main.PREFIX + "No clan named " + Lang.C + text[1] + Lang.T + " found!");
					return false;
				}
				if(ClanManager.getClanOf(sender.getName())!=null) {
					if(ClanManager.getClanOf(sender.getName()).equals(c))
						sender.sendMessage(Main.PREFIX + "You already joined this clan!");
					else
						sender.sendMessage(Main.PREFIX + "You have to leave your current clan first! Use " + Lang.H + "/vanityclans leave");
					return false;
				}
				if(c.getRequests().contains(((Player)sender).getUniqueId())) {
					sender.sendMessage(Main.PREFIX + "You already sent a join request to " + Lang.C + c.getName() + Lang.T + "!");
					return false;
				}
				c.addRequest(((Player)sender).getUniqueId());
				sender.sendMessage(Main.PREFIX + "You §asuccessfully" + Lang.T + " sent a join request to " + Lang.C + c.getName() + Lang.T + "!");
				for(String s : c.getTeam().getEntries()) {
					if(Bukkit.getPlayer(s)!=null)
						Lang.sendClickableCommand(Bukkit.getPlayer(s), Main.PREFIX + "New join request! " + Lang.P + sender.getName() + Lang.T + " would like to join your clan!", sender.getName());
				}
				return false;
			} else if(text[0].equalsIgnoreCase("leave")) {
				sender.sendMessage(Main.PREFIX + "Too many arguments. Please use " + Lang.H + "/vanityclans leave" + Lang.T + "!");
				return false;
			} else {
				sender.sendMessage(Main.PREFIX + "Unknown command. Use " + Lang.H + "/vanityclans help " + Lang.T + "to get an overview of available commands!");
				return false;
			}
		} else if(text.length==3) {
			if(text[0].equalsIgnoreCase("inbox")) {
				if(!(sender instanceof Player)) {
					sender.sendMessage(Main.PREFIX + "This command is only available as a player!");
					return false;
				}
				Clan c = ClanManager.getClanOf(sender.getName());
				if(c==null) {
					sender.sendMessage(Main.PREFIX + "You didn't join a clan yet. You have to be a clan member to use this command!");
					return false;
				}
				String targetName = text[1];
				for(UUID id : c.getRequests()) {
					if(Bukkit.getOfflinePlayer(id).getName().equals(targetName)) {
						OfflinePlayer t = Bukkit.getOfflinePlayer(id);
						if(text[2].equalsIgnoreCase("accept")) {
							if(t.isOnline()) {
								Player p = (Player) t;
								p.sendMessage(Main.PREFIX + "Your join request was accepted by " + Lang.P + sender.getName() + Lang.T + "! You officially are a member of " + Lang.C + c.getName() + Lang.T + " now!");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
								c.clanMessage(c.getTeam().getPrefix() + "The join request of " + Lang.P + targetName + Lang.T + " was accepted by " + Lang.P + sender.getName() + Lang.T + "!", p);
								c.removeRequest(id);
								c.addPlayer(t);
								return false;
							} else {
								c.clanMessage(c.getTeam().getPrefix() + "The join request of " + Lang.P + targetName + Lang.T + " was accepted by " + Lang.P + sender.getName() + Lang.T + "!", null);
								c.addPlayer(t);
								return false;
							}
						} else if(text[2].equalsIgnoreCase("deny")) {
							if(t.isOnline()) {
								Player p = (Player) t;
								p.sendMessage(Main.PREFIX + "Your join request was denied by " + Lang.P + sender.getName() + Lang.T + "!");
								sender.sendMessage(Main.PREFIX + "You denied the join request of " + Lang.P + targetName + Lang.T + "!");
								c.removeRequest(id);
								return false;
							} else {
								sender.sendMessage(Main.PREFIX + "You denied the join request of " + Lang.P + targetName + Lang.T + "!");
								return false;
							}
						} else {
							sender.sendMessage(Main.PREFIX + "Unknown command. Please use " + Lang.H + "/vanityclans inbox <player name> <accept/deny> " + Lang.T + "!");
							return false;
						}
					}
				}
				sender.sendMessage(Main.PREFIX + "The player " + Lang.P + targetName + Lang.T + " didn't send a join request!");
				return false;
			} else {
				sender.sendMessage(Main.PREFIX + "Unknown command. Use " + Lang.H + "/vanityclans help " + Lang.T + "to get an overview of available commands!");
				return false;
			}
		} else {
			sender.sendMessage(Main.PREFIX + "Too many arguments. Use " + Lang.H + "/vanityclans help " + Lang.T + "to get an overview of available commands!");
			return false;
		}
	}

	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] text) {
		List<String> result = new ArrayList<String>();
		boolean leader = false;
		Clan clan = null;
		if(sender instanceof Player) {
			clan = ClanManager.getClanOf(sender.getName());
			if(clan!=null&&clan.isOwner(((Player) sender).getUniqueId())) {
				leader = true;
			}
		}
		if(text.length==1) {
			if(compatible("help", text[0])) result.add("help");
			if(compatible("membership", text[0])) result.add("membership");
			if(compatible("members", text[0])) result.add("members");
			if(clan==null) {
				if(compatible("create", text[0])) result.add("create");
				if(compatible("join", text[0])) result.add("join");
			} else if(clan!=null) {
				if(compatible("leave", text[0])) result.add("leave");
				if(compatible("inbox", text[0])) result.add("inbox");
				if(compatible("promote", text[0])&&leader) result.add("promote");
				if(compatible("dissolve", text[0])&&leader||!(sender instanceof Player)) result.add("dissolve");
				if(compatible("kick", text[0])&&leader) result.add("kick");
			}
		} else if(text.length==2) {
			if(text[0].equalsIgnoreCase("membership")) {
				for(Player p : Bukkit.getOnlinePlayers())
					if(compatible(p.getName(), text[1])) result.add(p.getName());
				return result;
			}
			if(text[0].equalsIgnoreCase("inbox")) {
				if(clan==null) return result;
				for(UUID id : clan.getRequests()) {
					String name = Bukkit.getOfflinePlayer(id).getName();
					if(compatible(name, text[1])) result.add(name);
				}
				return result;
			}
			if(text[0].equalsIgnoreCase("promote")||text[0].equalsIgnoreCase("kick")&&leader) {
				for(String s : clan.getTeam().getEntries())
					if(compatible(s, text[1])&&!s.equals(sender.getName())) result.add(s);
				return result;
			}
			if(sender instanceof Player) {
				if(text[0].equalsIgnoreCase("join")||text[0].equalsIgnoreCase("members")) {
					for(Clan c : ClanManager.clanList) {
						String name = ClanManager.removeColorCodes(c.getName());
						if(compatible(name, text[1])) result.add(name);
					}
				}
			} else {
				if(text[0].equalsIgnoreCase("dissolve")||text[0].equalsIgnoreCase("members")) {
					for(Clan c : ClanManager.clanList) {
						String name = ClanManager.removeColorCodes(c.getName());
						if(compatible(name, text[1])) result.add(name);
					}
				}
			}
		} else if(text.length==3) {
			if(text[0].equalsIgnoreCase("inbox")) {
				if(compatible("accept", text[2])) result.add("accept");
				if(compatible("deny", text[2])) result.add("deny");
			}
		}
		return result;
	}
	
	public boolean nameIsValid(String s, CommandSender sender) {
		if(!Main.allowColors&&s.contains("&")) {
			sender.sendMessage(Main.PREFIX + "Illegal character: &");
			return false;
		}
		if(ClanManager.removeColorCodes(s).length()<3) {
			sender.sendMessage(Main.PREFIX + "The clan name must be longer than 3 characters (excluding color codes)");
			return false;
		} else if(ClanManager.removeColorCodes(s).length()>Main.maxLength) {
			sender.sendMessage(Main.PREFIX + "The clan name must be shorter than " + Main.maxLength + " characters (excluding color codes)");
			return false;
		} else if(s.length()>50) {
			sender.sendMessage(Main.PREFIX + "This name is way too long. The clan name must be shorter than 50 characters (including color codes)");
			return false;
		}
		return true;
	}
	
	public boolean compatible(String target, String current) {
		for(int i = 0; i<current.length()&&i<target.length(); i++) {
			if(current.charAt(i)!=target.charAt(i) && Character.toLowerCase(current.charAt(i))!=target.charAt(i) && Character.toUpperCase(current.charAt(i))!=target.charAt(i)) 
				return false;
		}
		return true;
	}
	
}
