**This plugin was not made by me, it was coded by [davidmuehlan](https://www.fiverr.com/davidmuehlan) on Fiverr!**

# VanityClans

Minecraft Server Plugin that seeks to create groups of players that only exist for the sake of vanity, and do not effect land protection, pvp, etc.

Ideal use case would be for marking teams or base groups on an SMP server that doesn't focus on PVP & already has land protection plugins or chooses not to use them.

Inspiration is taken from this forum post:
https://bukkit.org/threads/iclans-dead-simple-factions-simpleclans-alternative.248688/

# Commands
Main command: **/vanityclans**

Alias: **/vc**


__Commands List:__


/vc help  
- shows all commands with a brief explaination  

/vanityclans create <name>  
- Create a new clan named \<name>  
- The user of this command becomes the leader of the clan

/vanityclans join <name>  
- Sends a join request to the clan \<name>  
- Request can be accepted by any clan member

/vanityclans membership <playername>  
- Find out which clan the player \<playername> is a member of

/vc members <clan name>
- Shows all members of \<clan name> and will highlight the leader

/vc kick \<member name>
- Kicks \<member> from the clan
- Only available to leader

/vc promote \<member name>
- Only usable by leader
- Clan leader resigns from the leader position and gives it to \<member name>

/vc inbox \<player name> <accept / deny>
- Join requests will appear in the inbox and can be accepted or denied there
- There is also a clickable button, if a player sends a join request, any member of the clan can click [accept] or [deny]

/vanityclans leave  
- Leave your current clan  

/vanityclans dissolve  
- Delete your current clan
