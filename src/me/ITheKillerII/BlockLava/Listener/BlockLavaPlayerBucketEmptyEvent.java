package me.ITheKillerII.BlockLava.Listener;

import me.ITheKillerII.BlockLava.BlockLava;
import me.ITheKillerII.BlockLava.Tools.LanguageManager;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;


/**
 * This is the Class of the Bucket empty event of the BlockLava
 * @author ITheKillerII
 *
 */
public class BlockLavaPlayerBucketEmptyEvent  implements Listener{
	
	/**
	 * Pointer for the Mainplugin here BlockLava
	 */
	private BlockLava plugin;
	/**
	 * Pointer for the Language Manager
	 */
	private LanguageManager lang;
	
	/**
	 * Register this event
	 * @param p transmit the plugin to the listener [here: BlockLava (Default use "this")]
	 * @param lm link to the Language Manager
	 */
	public BlockLavaPlayerBucketEmptyEvent (BlockLava p , LanguageManager lm){
		this.plugin = p;
		this.lang = lm;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	
	/**
	 * Lookup if the used bucket is lava
	 * @param bucket	get the type of Bucket to be compared
	 * @param p			get the player from event
	 * @return if the bucket is lava
	 */
	private boolean getCancelled(Material bucket , Player p){
		if(bucket.equals(Material.LAVA_BUCKET)&&(plugin.isBlocked()))			//check if its lava && you have the permission to use lava
		{
			p.sendMessage(lang.getLavaBlockedText());
			return true;
		}
		return false;
	}
	
	
	
	//###################################################################################################################################
	@EventHandler
	/**
	 * The Bucket empty event: if bucked is lava it cancel the action of using
	 * @param ev get the event from Server core
	 */
	public void onPlayerBucketEmpty (PlayerBucketEmptyEvent ev){
		ev.setCancelled(getCancelled(ev.getBucket() , ev.getPlayer()));
	}

}
