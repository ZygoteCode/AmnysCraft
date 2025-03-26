package it.zygotecode.amnyscraft.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CompleteLoc
{
	private Player player;
	private Location location;
	
	public CompleteLoc(Player player, Location location)
	{
		this.player = player;
		this.location = location;
	}

	public Player getPlayer()
	{
		return player;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}

	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}
}