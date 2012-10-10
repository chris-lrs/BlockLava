package me.ITheKillerII.BlockLava.Listener;

import me.ITheKillerII.BlockLava.BlockLava;
import me.ITheKillerII.BlockLava.Tools.LanguageManager;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
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
	 * Pointer for the Language Manager
	 */
	private LanguageManager lang;	
	
	/**
	 * Register <em>this</em> event
	 * @param p transmit the plugin to the listener   [here: BlockLava (Default use "this")]
	 * @param lm link to the Language Manager
	 */
	public BlockLavaBlockPlaceEvent (BlockLava p , LanguageManager lm) {
		this.plugin = p;
		this.lang = lm;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	
	/**
	 * Lookup if the placed Block is lava
	 * @param b			get the type of Block to be compared
	 * @param p			get the player from event
	 * @return if the bucket is lava
	 */
	private boolean getCancel(Block b , Player p){
		if (((b.getTypeId() == 11) || (b.getTypeId() == 10)) && (plugin.isBlocked()))			//check if its lava && you have the permission to use lava+
		{
			p.sendMessage(lang.getLavaBlockedText());
			return true;
		}
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
		ev.setCancelled(getCancel(placedBlock , ev.getPlayer()));
	}

}
