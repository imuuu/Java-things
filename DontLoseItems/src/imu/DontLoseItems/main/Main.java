package imu.DontLoseItems.main;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import imu.DontLoseItems.Commands.ExampleCmd;
import imu.DontLoseItems.Events.MainEvents;
import imu.DontLoseItems.Handlers.CommandHandler;
import imu.DontLoseItems.Other.ItemMetods;



public class Main extends JavaPlugin
{

	ItemMetods itemM = null;
    public void registerCommands() 
    {
 
        CommandHandler handler = new CommandHandler();

        handler.registerCmd("drop", new ExampleCmd());       
        handler.setPermissionOnLastCmd("dontloseitems.drop");
              
        getCommand("drop").setExecutor(handler);
   
        //TODO not implemented yet examples
        //expamle player <> give ..
        //expamle player <> get
        //expamle player <> take ..
        //asd
    }
	
	@Override
	public void onEnable() 
	{
		itemM = new ItemMetods(this);
		registerCommands();
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN +" Dont lose your items has been activated!");
		getServer().getPluginManager().registerEvents(new MainEvents(this, itemM), this);
	}
	
	public ItemMetods getItemMetods()
	{
		return itemM;
	}

}
