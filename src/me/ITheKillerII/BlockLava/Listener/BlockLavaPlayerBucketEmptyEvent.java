package me.ITheKillerII.BlockLava.Listener;

import me.ITheKillerII.BlockLava.BlockLava;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

public class BlockLavaPlayerBucketEmptyEvent  implements Listener{
	
	private BlockLava plugin;
	
	public BlockLavaPlayerBucketEmptyEvent (BlockLava p){
		this.plugin = p;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	
	public void onPlayerBucketEmpty (PlayerBucketEmptyEvent ev){
		ev.setCancelled(ev.getBucket().equals(Material.LAVA_BUCKET));
	}

}
