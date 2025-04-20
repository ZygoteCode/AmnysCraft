package it.zygotecode.amnyscraft.rank;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class RankManager
{
	private ArrayList<Rank> ranks;
	
	public RankManager()
	{
		ranks = new ArrayList<Rank>();
	}
	
	public ArrayList<Rank> getRanks()
	{
		return ranks;
	}
	
	@SuppressWarnings("deprecation")
	public void addRank(Rank rank)
	{
		ranks.add(rank);
		
		for (PermissionGroup pg: PermissionsEx.getPermissionManager().getUser(rank.getPlayer()).getGroups())
		{
			PermissionsEx.getPermissionManager().getUser(rank.getPlayer()).removeGroup(pg);
		}
		
		if (rank.getRankType().equals(RankType.FANTASY))
		{
			PermissionsEx.getPermissionManager().getUser(rank.getPlayer()).addGroup("Medea");
		}
		else if (rank.getRankType().equals(RankType.MEDIEVAL))
		{
			PermissionsEx.getPermissionManager().getUser(rank.getPlayer()).addGroup("Troiano");
		}
	}
	
	public void removeRank(Rank rank)
	{
		ranks.remove(rank);
	}
	
	@SuppressWarnings("deprecation")
	public void addRank(Player player, RankType rankType)
	{
		ranks.add(new Rank(player, rankType));
		
		for (PermissionGroup pg: PermissionsEx.getPermissionManager().getUser(player).getGroups())
		{
			PermissionsEx.getPermissionManager().getUser(player).removeGroup(pg);
		}
		
		if (rankType.equals(RankType.FANTASY))
		{
			PermissionsEx.getPermissionManager().getUser(player).addGroup("Medea");
		}
		else if (rankType.equals(RankType.MEDIEVAL))
		{
			PermissionsEx.getPermissionManager().getUser(player).addGroup("Troiano");
		}
	}
	
	public void removeRank(Player player, RankType rankType)
	{
		for (Rank rank: getRanks())
		{
			if (rank.getPlayer().equals(player) && rank.getRankType().equals(rankType))
			{
				ranks.remove(rank);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void addRank(Player player)
	{
		ranks.add(new Rank(player, RankType.MEDIEVAL));
		
		for (PermissionGroup pg: PermissionsEx.getPermissionManager().getUser(player).getGroups())
		{
			PermissionsEx.getPermissionManager().getUser(player).removeGroup(pg);
		}
		
		PermissionsEx.getPermissionManager().getUser(player).addGroup("Troiano");
	}
	
	public void removeRank(Player player)
	{
		for (Rank rank: getRanks())
		{
			if (rank.getPlayer().equals(player))
			{
				ranks.remove(rank);
			}
		}
	}
	
	public boolean hasRank(Player player)
	{
		for (Rank rank: getRanks())
		{
			if (rank.getPlayer().equals(player))
			{
				return true;
			}
		}
		
		String[] excludedRanks = {"Tetes", "Agoghe", "Iren", "Krypteia", "Spartiato", "Oplita"};
		boolean coso = true;
		
		for (String s: excludedRanks)
		{
			if (PermissionsEx.getPermissionManager().getUser(player).inGroup(s))
			{
				coso = false;
			}
		}
		
		if (coso)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Rank getRank(Player player)
	{
		for (Rank rank: getRanks())
		{
			if (rank.getPlayer().equals(player))
			{
				return rank;
			}
		}
		
		return null;
	}
	
	public RankType getRankType(Player player)
	{
		try
		{
			for (Rank rank: getRanks())
			{
				if (rank.getPlayer().equals(player))
				{
					return rank.getRankType();
				}
			}	
		}
		catch (Exception ex)
		{
			
		}
		
		String[] medievalRanks = {"Troiano", "Pretendente", "Enea", "Chanson", "Mythos"};
		String[] fantasyRanks = {"Medea", "Veggente", "Amazzoni", "Calcante", "Circe"};
		
		for (String s: medievalRanks)
		{
			if (PermissionsEx.getPermissionManager().getUser(player).inGroup(s))
			{
				return RankType.MEDIEVAL;
			}
		}
		
		for (String s: fantasyRanks)
		{
			if (PermissionsEx.getPermissionManager().getUser(player).inGroup(s))
			{
				return RankType.FANTASY;
			}
		}
		
		return null;
	}
}