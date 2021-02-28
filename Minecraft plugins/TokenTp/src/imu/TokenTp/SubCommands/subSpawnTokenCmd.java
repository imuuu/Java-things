package imu.TokenTp.SubCommands;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import imu.TokenTp.CustomItems.ItemTeleTokenToken;
import imu.TokenTp.Enums.TeleTokenType;
import imu.TokenTp.Enums.TokenType;
import imu.TokenTp.Interfaces.CommandInterface;
import imu.TokenTp.Other.ItemMetods;
import imu.TokenTp.main.Main;

public class subSpawnTokenCmd implements CommandInterface
{
	Main _main = null;
	ItemMetods _itemM = null;
	
	String[] codes = {"pos", "bind","player"};
	String _sub_str = "";
	public subSpawnTokenCmd(Main main, String subStr) 
	{
		_main = main;
		_itemM = _main.getItemM();
		_sub_str = subStr;
	}
	
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) 
    {
    	Player player = (Player) sender;

    	if(args.length < 2)
    	{
    		player.sendMessage("/"+cmd.getName());
    	}
    	ItemTeleTokenToken card = null;
    	if(args.length > 2 && _itemM.doesStrArrayCointainStr(args, codes[0]))
    	{
    		card = new ItemTeleTokenToken(_main);
    		card.setAllData("Not set", "Not set", player.getLocation(), TeleTokenType.TOKEN, TokenType.TOKEN_TO_LOCATION);
    		card.setTokenDesc();
    	}
    	else if(args.length > 2 && _itemM.doesStrArrayCointainStr(args, codes[1]))
    	{
    		card = new ItemTeleTokenToken(_main);
    		card.setAllData("Not set", "Not set", null, TeleTokenType.TOKEN, TokenType.TOKEN_TO_LOCATION);
    		card.setTokenDesc();
    	}
    	else if(args.length > 2 && _itemM.doesStrArrayCointainStr(args, codes[2]))
    	{
    		card = new ItemTeleTokenToken(_main);
    		card.setAllData("Not set", "Not set", new Location(player.getWorld(),0,0,0), TeleTokenType.TOKEN, TokenType.TOKEN_TO_PLAYER);
    		card.setTokenDesc();
    	}else
    	{
    		player.sendMessage(ChatColor.GOLD+ "What kind of type you want to spawn?");
    		
    		HashMap<String, String> hMap = new HashMap<String, String>();
    		
    		hMap.put("This_position", "/"+cmd.getName() +" "+ _sub_str+ " " +codes[0]);
    		hMap.put("Bind", "/"+cmd.getName() +" "+ _sub_str+ " " +codes[1]);
    		hMap.put("PlayerTp", "/"+cmd.getName() +" "+ _sub_str+ " " +codes[2]);
    		
    		_itemM.SendMessageCommands(player, hMap, "/");
    	}
    	
    	if(card != null)
    	{
    		player.sendMessage(ChatColor.DARK_PURPLE + "Here is your card!");
    		player.getInventory().addItem(card);
    	}
    	
        return false;
    }
    
   
   
}