package me.ITheKillerII.BlockLava.Tools;

import me.ITheKillerII.BlockLava.BlockLava;

import org.bukkit.ChatColor;

/**
 * This Class manage the Language taken from the config.yml
 * @author ITheKillerII
 */
public class LanguageManager {
	
	//glogal Fields
	/**
	 * The pointer to the LavaBlocker 
	 */
	private BlockLava plugin;
	/**
	 * The Plugin  Prefix 
	 */
	private String pre = ChatColor.BLUE+"[LavaBlocker] "+ ChatColor.WHITE;
	
	
	// Paths
	/**
	 * Path to the Online Message
	 */
	private String pathOnlineText = "Config.Lang.onlineMessage";
	
	/**
	 * Path to the Offline Message
	 */
	private String pathOfflineText = "Config.Lang.offlineMessage";
	/**
	 * Path to the Enable text
	 */
	private String pathEnableText = "Config.Lang.EnableText";
	/**
	 * Path to the Disable text
	 */
	private String pathDisableText = "Config.Lang.DisableText";
	/**
	 * Path to the Insufficient Permissions text
	 */
	private String pathInsufficientPermText = "Config.Lang.InsufficientPermText";
	/**
	 * Path to the Lava Blocked text
	 */
	private String pathLavaBlockedText = "Config.Lang.LavaBlockedText";
	/**
	 * Path to the Welcome Message on server join
	 */
	private String pathJoinText = "Config.Lang.JoinText";
	
	public LanguageManager(BlockLava p){
		plugin = p;
	}
	
	public void setDefault(){
		plugin.getConfig().addDefault(pathOnlineText, "LavaBlocker is online!");
		plugin.getConfig().addDefault(pathOfflineText, "LavaBlocker is offline!");
		plugin.getConfig().addDefault(pathEnableText, "Enabled");
		plugin.getConfig().addDefault(pathDisableText, "Disabled");
		plugin.getConfig().addDefault(pathInsufficientPermText, "You dont have the permissions to use that");
		plugin.getConfig().addDefault(pathLavaBlockedText, "You cant pace Lava");
		plugin.getConfig().addDefault(pathJoinText, "Welcome This Server has BlockLava installed");
		
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
	}


	/**
	 * Send out the [LavaBlocker] + Enable text
	 * @return Enable text
	 */
	public String getEnableText(){
		return pre+ plugin.getConfig().getString(pathEnableText);
	}

	/**
	 * Send out the [LavaBlocker] + Disable text
	 * @return Disable text
	 */
	public String getDisableText(){
		return pre+ plugin.getConfig().getString(pathDisableText);
	}

	/**
	 * Send out the [LavaBlocker] + Insufficient Permissions text
	 * @return Insufficient Permissions text
	 */
	public String getInsufisantPermText(){
		return pre+ plugin.getConfig().getString(pathInsufficientPermText);
	}

	/**
	 * Send out the [LavaBlocker] + Lava Blocked text
	 * @return Lava Blocked text
	 */
	public String getLavaBlockedText(){
		return pre+ plugin.getConfig().getString(pathLavaBlockedText);
	}

	/**
	 * Send out the [LavaBlocker] + Welcome Message on server join
	 * @return Welcome Message on server join
	 */
	public String getJoinText(){
		return pre+ plugin.getConfig().getString(pathJoinText);
	}

	/**
	 * Send out the Online Message
	 * @return Online Message
	 */
	public String getOnlineText(){
		return plugin.getConfig().getString(pathOnlineText);
	}

	/**
	 * Send out the Offline Message
	 * @return Offline Message
	 */
	public String getOfflineText(){
		return plugin.getConfig().getString(pathOfflineText);
	}

	/**
	 * Send out the [LavaBlocker] + Info text
	 * @return Info text
	 */
	public String getInfoText(String author, String version){
		return pre+ "Lavablocker is enabled.  Info: "+author+ " " + version;
	}

}
