package me.ITheKillerII.BlockLava;


import me.ITheKillerII.BlockLava.Listener.BlockLavaBlockPlaceEvent;
import me.ITheKillerII.BlockLava.Listener.BlockLavaPlayerBucketEmptyEvent;
import me.ITheKillerII.BlockLava.Tools.LanguageManager;
import me.ITheKillerII.BlockLava.Tools.Tools;

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
	
	/**
	 * Creates the Language manager
	 */
	private LanguageManager lang = new LanguageManager(this);
	
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
	 * Path to the debug enable (boolean)
	 */
	private String pathDebug = "Config.Debug.Enable";
	/**
	 * Path to the version
	 */
	private String pathVersion = "Config.NOTCHANGING.version";
	
// #################################################################################################################################################
	
	
	//ini Methodes
		
		//events
	/**
	 * This Method creates the events
	 */
	private void registerEvents() {
		blockPlace = new BlockLavaBlockPlaceEvent(this , lang);
		bucketEmpyEvent = new BlockLavaPlayerBucketEmptyEvent(this , lang);
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
		installConfig();
		if (!(this.getConfig().getString(pathVersion).equals(version)))
		{
			this.getConfig().addDefault(pathBlock, true);
			this.getConfig().addDefault(pathDebug, false);
			this.getConfig().addDefault(pathVersion, version);
			//save config
		
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
		}
	}
	/**
	 * This Method is the first time installation of the config
	 */
	private void installConfig()
	{
		if (!(this.getConfig().contains(pathVersion)))
		{
			lang.setDefault();
			this.getConfig().addDefault(pathVersion, "FIRST START");
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
		}
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
		System.out.println(lang.getOfflineText());
	}
	
	/**
	 * Main Enable method (required): initialize the plugin
	 */
	public void onEnable() 
	{
		
		//ini		
		getAuthorAndVersionFomPluginYml();
		loadConfig();
		
		debug=this.getConfig().getBoolean(pathDebug);
		if (debug){
			System.out.println("######################");
			System.out.println("DEBUG MODE ENABLED !!!");
			System.out.println("######################");
			System.out.println();
			System.out.println("[BlockLava] : "+this.getConfig().getString(pathBlock));
		}
		
		registerEvents();
		System.out.println(lang.getOnlineText());
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
	
	/**
	 * Check if the player has the Permission fo a cmd
	 * 
	 * @return allow cmd
	 */
	public boolean getCmdPerm()
	{
		return true;		//return true because permissions system not activated now
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
	public boolean onCommand(CommandSender sender, Command command, String lString, String[] args)
	{
		
		Player p = (Player)sender;
		
		if (command.getName().equalsIgnoreCase("BlockLava")){
			if (args.length == 0)
				return Tools.Help(p);
			
			if (args.length == 1){
				
				// /Blocklava info
				
				if (args[0].equals("info")&&getCmdPerm()){
					p.sendMessage(lang.getInfoText(author, version));
					if (debug)
						System.out.println("DEBUG: CMD: /blocklava info");
					return true;
				}
				
				// /Blocklava on
				
				if (args[0].equals("on")&&getCmdPerm()){
				this.getConfig().set(pathBlock, true);
				this.saveConfig();
				Tools.msgBlockStade(p, this.getConfig().getBoolean(pathBlock), lang);
				if (debug)
					System.out.println("DEBUG: pathBlock = "+this.getConfig().getBoolean(pathBlock));
				return true;
				}
				
				// /Blocklava off
				
				if (args[0].equals("off")&&getCmdPerm()){
					this.getConfig().set(pathBlock, false);
					this.saveConfig();
					Tools.msgBlockStade(p, this.getConfig().getBoolean(pathBlock), lang);
					if (debug)
						System.out.println("DEBUG: pathBlock = "+this.getConfig().getBoolean(pathBlock));
					return true;	
				}
				
				// /Blocklava toggle
				
				if (args[0].equals("toggle")){
					boolean work = this.getConfig().getBoolean(pathBlock);
					if (work)
						this.getConfig().set(pathBlock, false);
					else
						this.getConfig().set(pathBlock, true);
					
					this.saveConfig();
					Tools.msgBlockStade(p, this.getConfig().getBoolean(pathBlock), lang);
					if (debug)
						System.out.println("DEBUG: pathBlock = "+this.getConfig().getBoolean(pathBlock));
					return true;	
				}
				
			}
		}
		return false;
	}
}
