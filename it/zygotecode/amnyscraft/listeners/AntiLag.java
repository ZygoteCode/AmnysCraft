package it.zygotecode.amnyscraft.listeners;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class AntiLag implements Listener
{
    @SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerTeleport(PlayerTeleportEvent event)
    {
        if (event.isCancelled())
        {
            return;
        }
        
        final Location loc = event.getTo();
        
        if (loc == null)
        {
            return;
        }
        
        final World world = loc.getWorld();
        final Chunk chunk = world.getChunkAt(loc);
        
        if (!world.isChunkLoaded(chunk))
        {
            world.loadChunk(chunk);
        }
        
        world.refreshChunk(chunk.getX(), chunk.getZ());
    }
}