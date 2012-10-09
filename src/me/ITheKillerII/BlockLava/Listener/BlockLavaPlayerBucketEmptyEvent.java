package me.ITheKillerII.BlockLava.Listener;

import me.ITheKillerII.BlockLava.BlockLava;
import org.bukkit.Material;
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
	 * Register this event
	 * @param p transmit the plugin to the listener [here: BlockLava (Default use "this")]
	 */
	public BlockLavaPlayerBucketEmptyEvent (BlockLava p){
		this.plugin = p;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	/**
	 * Lookup if the used bucket is lava
	 * @param bucket get the type of Bucket to be compared
	 * @return if the bucket is lava
	 */
	private boolean getCancelled(Material bucket){
		if(bucket.equals(Material.LAVA_BUCKET))				//check if its lava && you have the permission to use lava
			return plugin.isBlocked();
		return false;
	}
	
	//###################################################################################################################################
	@EventHandler
	/**
	 * The Bucket empty event: if bucked is lava it cancel the action of using
	 * @param ev get the event from Server core
	 */
	public void onPlayerBucketEmpty (PlayerBucketEmptyEvent ev){
		ev.setCancelled(getCancelled(ev.getBucket()));
	}

}
