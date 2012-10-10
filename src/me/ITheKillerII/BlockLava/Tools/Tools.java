package me.ITheKillerII.BlockLava.Tools;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * This is the Tool Class and is used to make the code more compact
 * @author ITheKillerII
 */
public class Tools {
	
	/**
	 * This Class show the help
	 * @param p Name of the Player
	 * @return true that the Bukkit command can be displayed
	 */
	public static boolean Help(Player p){
		p.sendMessage(ChatColor.BLUE+"BlockLava:  "+ChatColor.WHITE+"/BlockLava <args>   "+ChatColor.RED+"  args: info : "+ChatColor.WHITE+"shows the info /   "+ChatColor.RED+" on: "+ChatColor.WHITE+" enable the blocking of lava on the whole Server /" +
				ChatColor.RED+" off: "+ChatColor.WHITE+" disable the blocking of lava on the whole Server / "+ChatColor.RED+" toggle: "+ChatColor.WHITE+" toggle the stade of blocking lava");
		return true;
	}
	/**
	 * 
	 * @param p 		Name of the Player that used the Cmd
	 * @param stade		Stade of the Global Enable
	 * @param lang		Link to the LanguageManager
	 */
	public static void msgBlockStade(Player p , boolean stade , LanguageManager lang)
	{
		String work = lang.getDisableText();
			if (stade)
				work = lang.getEnableText();
		p.sendMessage(work);
				
	}

}
