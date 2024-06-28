package de.mullun.VanityClans.main;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Lang {

	public static String P;
	public static String C;
	public static String H;
	public static String T;
	
	public Lang(String p, String c, String h, String t) {
		P = p.replace("&", "§");
		C = c.replace("&", "§");
		H = h.replace("&", "§");
		T = t.replace("&", "§");
		Main.PREFIX += T;
	}
	
	public static void sendClickableCommand(Player player, String message, String targetName) {
        // Make a new component (Bungee API).
		TextComponent messageC = new TextComponent(TextComponent.fromLegacyText(message + " "));
        TextComponent accept = new TextComponent(TextComponent.fromLegacyText("§7[§aaccept§7]"));
        TextComponent decline = new TextComponent(TextComponent.fromLegacyText("§7[§cdecline§7]"));
        // Add a click event to the component.
        accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/vc inbox " + targetName + " accept"));
        decline.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/vc inbox " + targetName + " deny"));

        // Send it!
        player.spigot().sendMessage(messageC, accept, new TextComponent(TextComponent.fromLegacyText(" ")), decline);
    }
	
}
