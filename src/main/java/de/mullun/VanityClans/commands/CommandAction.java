package de.mullun.VanityClans.commands;

public enum CommandAction {

	HELP_COMMAND, CREATE_CLAN, JOIN_CLAN, SEE_MEMBERSHIP, LIST_CLANS, SEE_CLAN_MEMBERS, LEAVE_CLAN, KICK_MEMBER, 
	MANAGE_INBOX, PROMOTE_MEMBER, DISSOLVE_CLAN, RELOAD_PLUGIN;
	
	public boolean requiresClanMembership() {
		return (this==LEAVE_CLAN||this==KICK_MEMBER||this==MANAGE_INBOX||this==PROMOTE_MEMBER||this==DISSOLVE_CLAN);
	}
	
	public boolean requiresLeadership() {
		return (requiresClanMembership()&&(this==KICK_MEMBER||this==PROMOTE_MEMBER||this==DISSOLVE_CLAN));
	}
	
	public boolean requiresClanlessness() {
		return (this == CREATE_CLAN || this == JOIN_CLAN);
	}
	
	public boolean requiresPermissionOnDefault() {
		return (this==RELOAD_PLUGIN||this==LIST_CLANS);
	}
	
	public static CommandAction[] getAllActions() {
		return new CommandAction[] {HELP_COMMAND, CREATE_CLAN, JOIN_CLAN, SEE_MEMBERSHIP, LIST_CLANS, SEE_CLAN_MEMBERS, LEAVE_CLAN, KICK_MEMBER, 
				MANAGE_INBOX, PROMOTE_MEMBER, DISSOLVE_CLAN, RELOAD_PLUGIN};
	}
	
}
