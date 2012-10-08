package me.ITheKillerII.BlockLava;


import me.ITheKillerII.BlockLava.Listener.*;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockLava extends JavaPlugin {
		
//Fields
	//global
	
	private String author = "ITheKillerII";
	private String version = "Version";
	private Boolean debug = false;
	
	//public PluginManager manager = this.getServer().getPluginManager();
	
	//events 
	
	public BlockLavaBlockPlaceEvent blockPlace;
	public BlockLavaPlayerBucketEmptyEvent bucketEmpyEvent;
//	public BlockLavaPlayerBucketEvent bucketEvent;								//error Here
	
	//config path
		//online
	private String pathBlock = "Config.online.globalEnable";
	private String pathEnableOnline = "Config.online.onlineMessage";
	private String pathDebug = "Config.Debug.Enable";
	
		//offline
	public String pathEnableOffline = "Config.offline.offlineMessage";
	
		//debug
	
	
// #################################################################################################################################################
	
	
	//ini Methodes
		
		//events
	
	private void registerEvents() {
		blockPlace = new BlockLavaBlockPlaceEvent(this);
		bucketEmpyEvent = new BlockLavaPlayerBucketEmptyEvent(this);
//		bucketEvent = new BlockLavaPlayerBucketEvent(this);
		}
	
	
	
		//Author and Version
	private void getAuthorAndVersion(){
		PluginDescriptionFile desc = this.getDescription();
		this.author= String.valueOf(desc.getAuthors());
		this.version = desc.getVersion();
	}
	
		//Config
	private void loadConfig(){
		this.getConfig().addDefault(pathEnableOnline, "[Lava Blocker] LavaBlocker is online!");
		this.getConfig().addDefault(pathBlock, true);
		this.getConfig().addDefault(pathDebug, false);
		
		
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	private void loadConfigDisable() {
		
		this.getConfig().addDefault(pathEnableOffline, "[Lava Blocker] LavaBlocker is offline!");
		
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
// #################################################################################################################################################
	
	// Main
	
	public void onDisable() {
		
		//ini		
		getAuthorAndVersion();
		loadConfigDisable();
		
		System.out.println(this.getConfig().getString(pathEnableOffline));
		
		
	}
	
	public void onEnable() {
		
		//ini
		debug=this.getConfig().getBoolean(pathDebug);
		
		getAuthorAndVersion();
		loadConfig();
		registerEvents();
		
		System.out.println(this.getConfig().getString(pathEnableOnline));
		System.out.println("Block Lava: "+this.getConfig().getString(pathBlock));
	}
	
	//Permission 
	
	public boolean isBlocked(){
		return (this.getConfig().getBoolean(pathBlock)); //pathBlock = global Enable (used with the commands)
	}
	
	
	//Command
	
	public boolean onCommand(CommandSender sender, Command command, String lString, String[] aStrings){
		
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
