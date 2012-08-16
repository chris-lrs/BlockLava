package me.ITheKillerII.BlockLava;


import me.ITheKillerII.BlockLava.Listener.*;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockLava extends JavaPlugin {
	
	private String author = "ITheKillerII";
	private String version = "Version";
	
	//public PluginManager manager = this.getServer().getPluginManager();
	
	//events 
	
	public BlockLavaBlockPlaceEvent blockPlace;
	public BlockLavaPlayerBucketEmptyEvent bucketEmpyEvent;
//	public BlockLavaPlayerBucketEvent bucketEvent;								//error Here
	
	//config path
		//online
	private String pathEnableOnline = "Config.online.onlineMessage";
	
		//offline
	public String pathEnableOffline = "Config.offline.offlineMessage";
	
	
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
		
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	private void loadConfigDisable() {
		
		this.getConfig().addDefault(pathEnableOffline, "[Lava Blocker] LavaBlocker is offline!");
		
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	// Main
	
	public void onDisable() {
		
		//ini		
		getAuthorAndVersion();
		loadConfigDisable();
		
		System.out.println(this.getConfig().getString(pathEnableOffline));
		
		
	}
	
	public void onEnable() {
		
		//ini
		getAuthorAndVersion();
		loadConfig();
		registerEvents();
		
		System.out.println(this.getConfig().getString(pathEnableOnline));
	}

	
	public boolean onCommand(CommandSender sender, Command command, String lString, String[] aStrings){
		
		Player p = (Player)sender;
		
		if ((command.getName().equalsIgnoreCase("info"))&&(aStrings.length==0)){
			System.out.println("[LavaBlocker] Lavablocker is enabled.  Info: "+author+ " " + version);
			p.sendMessage(ChatColor.BLUE+"[LavaBlocker] "+ ChatColor.WHITE+"Lavablocker is enabled.  Info: "+author+ " " + version);
			return true;
		}
		return false;
	}
}
