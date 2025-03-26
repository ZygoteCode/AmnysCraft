package it.zygotecode.amnyscraft.rank;

import org.bukkit.entity.Player;

public class Rank
{
	private Player player;
	private RankType rankType;
	
	public Rank(Player player, RankType rankType)
	{
		this.player = player;
		this.rankType = rankType;
	}

	public Player getPlayer()
	{
		return player;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}

	public RankType getRankType()
	{
		return rankType;
	}

	public void setRankType(RankType rankType)
	{
		this.rankType = rankType;
	}
}