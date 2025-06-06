**This plugin was my idea, but was coded by [davidmuehlan](https://www.fiverr.com/davidmuehlan) on Fiverr!**

# VanityClans

Minecraft Server Plugin that seeks to create groups of players that only exist for the sake of vanity, and do not effect land protection, pvp, etc.

Ideal use case would be for marking teams or base groups on an SMP server that doesn't focus on PVP & already has land protection plugins or chooses not to use them.

Inspiration is taken from this forum post:
https://bukkit.org/threads/iclans-dead-simple-factions-simpleclans-alternative.248688/

# Commands
Main command: **/vanityclans**

Alias: **/vc**

## Commands List:

/vc help
- shows all commands with a brief explaination  

/vc create \<name>
- Create a new clan named \<name>  
- The user of this command becomes the leader of the clan

/vc join \<name>
- Sends a join request to the clan \<name>  
- Request can be accepted by any clan member

/vc membership \<playername>
- Find out which clan the player \<playername> is a member of

/vc members \<clan name>
- Shows all members of \<clan name> and will highlight the leader

/vc list
- Shows all clans and their members, highlights the leader of each

/vc kick \<member name>
- Kicks \<member> from the clan
- Only available to leader

/vc promote \<member name>
- Only usable by leader
- Clan leader resigns from the leader position and gives it to \<member name>

/vc inbox \<player name> <accept / deny>
- Join requests will appear in the inbox and can be accepted or denied there
- There is also a clickable button, if a player sends a join request, any member of the clan can click [accept] or [deny]

/vc leave
- Leave your current clan  

/vc dissolve
- Delete your current clan

/vc reload
- Reloads the plugin's config

# Contributing

I am not actively developing the plugin and neither is the original author. Any help is welcomed in the form of issues/pull requests.

I will try my best to get the plugin working on new Minecraft versions as quickly as possible.
