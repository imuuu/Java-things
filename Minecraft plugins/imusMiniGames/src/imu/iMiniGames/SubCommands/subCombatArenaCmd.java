package imu.iMiniGames.SubCommands;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;

import imu.iMiniGames.Arenas.CombatArena;
import imu.iMiniGames.Interfaces.CommandInterface;
import imu.iMiniGames.Main.Main;
import imu.iMiniGames.Managers.CombatManager;
import imu.iMiniGames.Prompts.ConvPromptSetDescriptionArena;
import net.md_5.bungee.api.ChatColor;

public class subCombatArenaCmd implements CommandInterface
{
	Main _main = null;
	String _subCmd = "";
	CombatManager _com;
	String[] _subs; //{"create, spawn, middle, save, remove, lobby","desc"};
 	public subCombatArenaCmd(Main main, String[] sub_cmds) 
	{
		_main = main;
		_subs = sub_cmds;
		_com = _main.get_combatManager();
	}

 	
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) 
    {
        Player player = (Player) sender;
        
        _main.get_itemM().printArray("args", args);
        if(args.length < 3)
    	{
        	player.sendMessage(ChatColor.RED +"Remember: " +_subCmd + " arenaName");
    		return false;
    	}
        
        String arenaName = StringUtils.join(Arrays.copyOfRange(args, 2, args.length)," ");

    	CombatArena arena = _com.getArena(arenaName);
    	
    	if(args[1].equalsIgnoreCase(_subs[0]))
    	{
    		//create
    		_com.createArena(arenaName);
          player.sendMessage(ChatColor.GOLD + "You have created Combat arena named as "+ChatColor.AQUA +arenaName);
          player.sendMessage(ChatColor.GOLD + "Remember set spawnpoints, middle point and lobby. Then save!");
          return true;
    	}
    	
    	if(arena != null)
    	{
    		String arenaPrefix =ChatColor.GOLD + "Arena:  "+ChatColor.AQUA +arenaName + ChatColor.GOLD+" ";
    		if(args[1].equalsIgnoreCase(_subs[1]))
        	{
        		//spawn
    			
        		 String str_pos = arenaPrefix + "Added Spawnposition: "+ChatColor.DARK_PURPLE+(arena.getTotalSpawnPositions()+1);
        		 player.sendMessage(str_pos);
        		 arena.addSpawnPosition(player.getLocation());
        		 arena.set_maxPlayers(arena.get_maxPlayers()+1);
        		 arena.sendArenaCreationgINFO(player);
        		 return true;
        	}
    		if(args[1].equalsIgnoreCase(_subs[2]))
        	{
    			//middle  			
    			arena.setArenas_middleloc(player.getLocation());
    			player.sendMessage(arenaPrefix+"You have set middle location!");
    			arena.sendArenaCreationgINFO(player);
    			
    			return true;
        	}
    		if(args[1].equalsIgnoreCase(_subs[3]))
        	{
    			//save
    			_com.saveArena(arena);
    			player.sendMessage(arenaPrefix+"has been saved");
    			arena.sendArenaCreationgINFO(player);
    			return true;
        	}
    		if(args[1].equalsIgnoreCase(_subs[4]))
        	{
    			//remove
    			_com.removeArena(arena);
    			player.sendMessage(arenaPrefix+"has been removed");
    			arena.sendArenaCreationgINFO(player);
    			return true;
        	}
    		if(args[1].equalsIgnoreCase(_subs[5]))
        	{
    			//lobby
    			
    			arena.set_spectator_lobby(player.getLocation());
    			player.sendMessage(arenaPrefix+"lobby has been set");
    			arena.sendArenaCreationgINFO(player);
    			return true;
        	}
    		if(args[1].equalsIgnoreCase(_subs[6]))
        	{
    			//desc

    			ConversationFactory cf = null;
    			String question = null;
    			Conversation conv = null;
    			System.out.println("POP HERE");
    			cf = new ConversationFactory(_main);
    			question = ChatColor.DARK_PURPLE + "Give "+arena.get_name()+" description";
    			conv = cf.withFirstPrompt(new ConvPromptSetDescriptionArena(_main, player, question,arena)).withLocalEcho(true).buildConversation(player);
    			conv.begin();
    			return false;
        	}
    	}else
    	{
    		player.sendMessage(ChatColor.RED + "Couldn't find Combat arena with name: "+arenaName);
    	}
    	
  
        
		
        return false;
    }
    
    
    
   
   
}