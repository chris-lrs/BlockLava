package me.ITheKillerII.BlockLava.Listener;

import me.ITheKillerII.BlockLava.BlockLava;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEvent;

/*
 * Not used Now
 */

/**
 * <strong>NOT USED NOW</strong>
 * @author ITheKillerII
 *
 */


public class BlockLavaPlayerBucketEvent  implements Listener{
	
	public BlockLava plugin;
	
	public BlockLavaPlayerBucketEvent (BlockLava p){
		this.plugin = p;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	
	public void onPlayerBucket (PlayerBucketEvent ev){
		System.out.println("Plaver Bukkit Event");
	}

}


/* error In this .java
 * unable to load Event Handler
 */
