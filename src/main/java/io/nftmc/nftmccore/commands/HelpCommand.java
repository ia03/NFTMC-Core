package io.nftmc.nftmccore.commands;

import io.nftmc.nftmccore.NFTMCCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {
    NFTMCCore Core;
    public HelpCommand(NFTMCCore core) {
        Core = core;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("");
            player.sendMessage(ChatColor.GRAY + "/view <" + ChatColor.BOLD + "NFTMC" + ChatColor.GRAY + " ID> or "
                    + "/v <" + ChatColor.BOLD + "NFTMC" + ChatColor.GRAY + " ID> "
                    + "to teleport to an " + ChatColor.BOLD + "NFTMC.");
            player.sendMessage(ChatColor.GRAY + "/viewrandom or /vr to teleport to a random " + ChatColor.BOLD
                    + "NFTMC.");
            Core.sendServerLinks(player);
        } else {
            sender.sendMessage("/op <user> - Makes the user a server operator.");
        }
        return true;
    }
}
