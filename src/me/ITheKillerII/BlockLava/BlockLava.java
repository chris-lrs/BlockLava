package me.ITheKillerII.BlockLava;


import me.ITheKillerII.BlockLava.Listener.*;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *  This is the Main class of the BlockLava Plugin
 * 
 * @author ITheKillerII
 *
 */

public class BlockLava extends JavaPlugin {
		
//Fields
	//global
	private String author = "ITheKillerII";
	private String version = "Version";
	
	/**
	 * Enable or disable the debug
	 */
	private Boolean debug = false;
	
//	public PluginManager manager = this.getServer().getPluginManager();
	
	//events 
	/**
	 * Pointer for the BlockLavaBlockPlaceEvent (Is used when a player place a block)
	 */
	public BlockLavaBlockPlaceEvent blockPlace;
	/**
	 * Pointer for the BlockLavaPlayerBucketEmptyEvent (Is used when a player empty a bucket)
	 */
	public BlockLavaPlayerBucketEmptyEvent bucketEmpyEvent;
//	public BlockLavaPlayerBucketEvent bucketEvent;								//error Here
	
	//config path
		//online
	/**
	 * Path to the global enable (boolean)
	 */
	private String pathBlock = "Config.online.globalEnable";
	/**
	 * Path to the Online Message (String)
	 */
	private String pathEnableOnline = "Config.online.onlineMessage";
	/**
	 * Path to the debug enable (boolean)
	 */
	private String pathDebug = "Config.Debug.Enable";
	
		//offline
	/**
	 * Path to the Offline Message (String)
	 */
	private String pathEnableOffline = "Config.offline.offlineMessage";
	
		//debug
	
	
// #################################################################################################################################################
	
	
	//ini Methodes
		
		//events
	/**
	 * This Method creates the events
	 */
	private void registerEvents() {
		blockPlace = new BlockLavaBlockPlaceEvent(this);
		bucketEmpyEvent = new BlockLavaPlayerBucketEmptyEvent(this);
//		bucketEvent = new BlockLavaPlayerBucketEvent(this);
		}
	
	
	
		//Author and Version
	/**
	 * This Method get the Author and the version from the plugin.yml
	 */
	private void getAuthorAndVersionFomPluginYml()
	{
		PluginDescriptionFile desc = this.getDescription();
		this.author= String.valueOf(desc.getAuthors());
		this.version = desc.getVersion();
	}
	
		//Config
	/**
	 * This Method sets the default values of the Configuration when the plugin is enabled
	 */
	private void loadConfig()
	{
		this.getConfig().addDefault(pathEnableOnline, "[Lava Blocker] LavaBlocker is online!");
		this.getConfig().addDefault(pathBlock, true);
		this.getConfig().addDefault(pathDebug, false);
		
		//save config
		
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	/**
	 * This Method sets the default values of the Configuration when the plugin is disabled
	 */
	private void loadConfigDisable()
	{
		this.getConfig().addDefault(pathEnableOffline, "[Lava Blocker] LavaBlocker is offline!");
		
		//save config
		
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
// #################################################################################################################################################
	
	// Main
	
	/**
	 * Main Disable method  (required)
	 */
	public void onDisable() 
	{
		//ini		
		getAuthorAndVersionFomPluginYml();
		loadConfigDisable();
		
		System.out.println(this.getConfig().getString(pathEnableOffline));
	}
	
	/**
	 * Main Enable method (required): initialize the plugin
	 */
	public void onEnable() 
	{
		
		//ini
		debug=this.getConfig().getBoolean(pathDebug);
		
		getAuthorAndVersionFomPluginYml();
		loadConfig();
		registerEvents();
		
		System.out.println(this.getConfig().getString(pathEnableOnline));
		System.out.println("Block Lava: "+this.getConfig().getString(pathBlock));
	}
	
	//Permission 
	/**
	 * Check from the config and from the permissions if lava should be blocked
	 *  
	 * @return if lava should be blocked
	 */
	public boolean isBlocked()
	{
		return (this.getConfig().getBoolean(pathBlock)); //pathBlock = global Enable (used with the commands)
	}
	
	
	//Command
	/**
	 * Command method:
	 * 	/BlockLava         -> send the help to the player <br>
	 * 	/BlockLava info    -> send the info to the player <br>
	 * 	/blocklava toogle  -> toggle the global blocking stade <br>
	 *  /blocklava on      -> set the global blocing stade to <em>true</em> <br>
	 * 	/blocklava off      -> set the global blocing stade to <em>false</em>
	 * @return Valid Command
	 */
	public boolean onCommand(CommandSender sender, Command command, String lString, String[] aStrings)
	{
		
		Player p = (Player)sender;
		
		if (command.getName().equalsIgnoreCase("BlockLava")){
			if (aStrings.length == 0)
				p.sendMessage(Tools.Help());
			if (String.valueOf(aStrings) == "info"){
				p.sendMessage(ChatColor.BLUE+"[LavaBlocker] "+ ChatColor.WHITE+"Lavablocker is enabled.  Info: "+author+ " " + version);
				if (debug)
					System.out.println("[LavaBlocker] Lavablocker is enabled.  Info: "+author+ " " + version);
				return true;
			}
			
			if (String.valueOf(aStrings) == "on"){
				this.getConfig().set(pathBlock, true);
				this.saveConfig();
				if (debug)
					System.out.println("DEBUG: pathBlock = "+this.getConfig().getBoolean(pathBlock));
				return true;
			}
			
			if (String.valueOf(aStrings) == "off"){
				this.getConfig().set(pathBlock, false);
				this.saveConfig();
				if (debug)
					System.out.println("DEBUG: pathBlock = "+this.getConfig().getBoolean(pathBlock));
				return true;	
			}
		}
		return false;
	}
}
