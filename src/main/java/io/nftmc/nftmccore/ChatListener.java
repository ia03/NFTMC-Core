package io.nftmc.nftmccore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import io.papermc.paper.event.player.AsyncChatEvent;

public class ChatListener implements Listener {
    @EventHandler
    public void onPlayerMessage(AsyncChatEvent event) {
        Player player = event.getPlayer();
        event.setCancelled(true);
    }
}
