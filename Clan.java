import //paper
  
  public class Clan {
    
    public String clan_name;
    public String[] player_list;
    private String leader;
    
    public Clan(String name, String /*player who gave command*/ ){           //clan constructor
      name = clan_name;
      leader = /* player who gave command */;
      
    }
    
    public void invitePlayer(String /*player who gave command*/ , String player){
      
      if(/*player who gave command*/ == leader){
        sendInvitation(player);
      }
      
      else{
        // say you dont have access to this command
      }
    }
    
    
    public boolean sendInvitation(player){
      
      /*send message to player*/ (leader + " is asking you to join their clan called " + clan_name + ". /vcyes or /vcno ?")
      
    }
    
