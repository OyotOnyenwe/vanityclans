import //paper
  
  public class Clan {
    
    public String clan_name;
    public String[] player_list = new String[1];
    private String leader;
    
    public Clan(String name, String /*player who gave command*/ ){           //clan constructor
      name = clan_name;
      leader = /* player who gave command */;
      player_list[0] = leader;
      
    }
    
    public void invitePlayer(String /*player who gave command*/ , String player){         //invites player
      
      if(/*player who gave command*/ == leader){                                             //only leader can invite
        if(sendInvitation(player)){
            String[] temp = new String[player_list.length + 1];                              //makes temporary array
            for(int i = 0; i < player_list.length; i++){                                     //for loop to make temp array
              temp[i] = player_list[i];
            }
            temp[temp.length - 1] = player;
            player_list = temp;                                                              //sets temp array as the player list, including the new player
            /* send messsage to player who gave command, leader */ (player + " has joined your clan!");
        }
        else{
          /* send messsage to player who gave command, leader */ (player + " has decided to not join your clan.");    
      }
      
      else{
        // say you dont have access to this command
      }
    }
    
    
    private boolean sendInvitation(player){                                            //sends an invitation to the requested player, needs timer to be implemented so you cant say yes or no after a long time
      
      /*send message to player*/ (leader + " is asking you to join their clan called " + clan_name + ". /vcyes or /vcno ?")
      
      if (/*message*/ == "/vcyes"){
        return true;
      elseif(/*message*/ == "/vcno"){
        return false;
      }
    }
    
