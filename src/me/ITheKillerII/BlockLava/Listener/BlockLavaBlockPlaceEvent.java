package me.ITheKillerII.BlockLava.Listener;

import me.ITheKillerII.BlockLava.BlockLava;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockLavaBlockPlaceEvent implements Listener{
	
	private BlockLava plugin;	
	
	/**
	 *  @param p	BlockLava (Default use "this")
	 */
	public BlockLavaBlockPlaceEvent (BlockLava p) {
		this.plugin = p;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	
	private boolean getCancel(Block b){
		if ((b.getTypeId() == 11) || (b.getTypeId() == 10))			//check if its lava && you have the permission to use lava
			return plugin.isBlocked();
		return false;
	}	
	
	@EventHandler
	
	public void onBlockPlace (BlockPlaceEvent ev){
		
		Block placedBlock = ev.getBlockPlaced();
		ev.setCancelled(getCancel(placedBlock));
	}

}
