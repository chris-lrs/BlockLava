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
	
	@EventHandler
	
	public void onBlockPlace (BlockPlaceEvent ev){
		
		Block placedBlock = ev.getBlockPlaced();
		
		if ((placedBlock.getTypeId() == 11)||(placedBlock.getTypeId() == 10)){
			ev.setCancelled(true);
			}
	}

}
