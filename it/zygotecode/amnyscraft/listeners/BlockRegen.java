package it.zygotecode.amnyscraft.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import it.zygotecode.amnyscraft.AmnysCraft;
import it.zygotecode.amnyscraft.utils.AmnysVariables;
import it.zygotecode.amnyscraft.utils.CompleteLoc;

public class BlockRegen implements Listener
{
	@EventHandler(priority = EventPriority.MONITOR)
	public void onInteract(PlayerInteractEvent event)
	{
		try
		{
			if (event.getPlayer().isOp())
			{
				if (event.getPlayer().getItemInHand() != null)
				{
					if (event.getPlayer().getItemInHand().getType().equals(Material.STICK) && event.getPlayer().getItemInHand().hasItemMeta())
					{
						if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§cBlockRegen Wand"))
						{
							if (event.getAction().equals(Action.LEFT_CLICK_BLOCK))
							{
								event.setCancelled(true);
								
								for (CompleteLoc loc: AmnysVariables.positions1)
								{
									if (loc.getPlayer().equals(event.getPlayer()))
									{
										AmnysVariables.positions1.remove(loc);
									}
								}
								
								AmnysVariables.positions1.add(new CompleteLoc(event.getPlayer(), event.getClickedBlock().getLocation()));
								AmnysCraft.sendMessage(event.getPlayer(), "§7Settata con successo la prima posizione.");
							}
							else if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
							{
								event.setCancelled(true);
								
								for (CompleteLoc loc: AmnysVariables.positions2)
								{
									if (loc.getPlayer().equals(event.getPlayer()))
									{
										AmnysVariables.positions2.remove(loc);
									}
								}
								
								AmnysVariables.positions2.add(new CompleteLoc(event.getPlayer(), event.getClickedBlock().getLocation()));
								AmnysCraft.sendMessage(event.getPlayer(), "§7Settata con successo la seconda posizione.");
							}
						}
					}
				}
			}
		}
		catch (Exception ex)
		{
			
		}
	}
}