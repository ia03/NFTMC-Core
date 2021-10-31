package io.nftmc.nftmccore.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class LoginListener implements Listener {


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.joinMessage(null);
        Bukkit.dispatchCommand(player, "viewrandom");

        TextComponent websiteLink = new TextComponent(ChatColor.GRAY + "Click here to visit our website.");
        websiteLink.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://nftmc.io/"));
        websiteLink.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("Visit website").color(net.md_5.bungee.api.ChatColor.GRAY)
                        .italic(true).create()));
        player.spigot().sendMessage(websiteLink);


        TextComponent discordLink = new TextComponent(ChatColor.GRAY + "Click here to join our Discord server.");
        discordLink.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://PLACEHOLDER.com"));
        discordLink.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("Join Discord server").color(net.md_5.bungee.api.ChatColor.GRAY)
                        .italic(true).create()));
        player.spigot().sendMessage(discordLink);
    }
}
