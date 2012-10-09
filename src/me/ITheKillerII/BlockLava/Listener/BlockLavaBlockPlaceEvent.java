package me.ITheKillerII.BlockLava.Listener;

import me.ITheKillerII.BlockLava.BlockLava;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;


/**
 * This is the Class of the Block place event of the BlockLava
 * @author ITheKillerII
 *
 */
public class BlockLavaBlockPlaceEvent implements Listener{
	
	/**
	 * Pointer for the Mainplugin   [here: BlockLava]
	 */
	private BlockLava plugin;	
	
	/**
	 * Register <em>this</em> event
	 * @param p transmit the plugin to the listener   [here: BlockLava (Default use "this")]
	 */
	public BlockLavaBlockPlaceEvent (BlockLava p) {
		this.plugin = p;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	/**
	 * Lookup if the placed Block is lava
	 * @param b get the type of Block to be compared
	 * @return if the bucket is lava
	 */
	private boolean getCancel(Block b){
		if ((b.getTypeId() == 11) || (b.getTypeId() == 10))			//check if its lava && you have the permission to use lava
			return plugin.isBlocked();
		return false;
	}	
	
	//###################################################################################################################################
	
	@EventHandler
	/**
	 * The Block place event: if the placed is lava it cancel the action of placing
	 * @param ev get the event from Server core
	 */
	public void onBlockPlace (BlockPlaceEvent ev){
		
		Block placedBlock = ev.getBlockPlaced();
		ev.setCancelled(getCancel(placedBlock));
	}

}
