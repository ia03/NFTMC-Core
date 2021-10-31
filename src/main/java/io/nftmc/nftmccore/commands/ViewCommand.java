package io.nftmc.nftmccore.commands;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import com.onarandombox.MultiverseCore.api.SafeTTeleporter;
import io.nftmc.nftmccore.NFTMCCore;
import net.md_5.bungee.api.chat.*;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.Yaml;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

public class ViewCommand implements CommandExecutor {
    private NFTMCCore Core;
    private MultiverseCore mvCore;
    private SafeTTeleporter safeteleporter;
    private MVWorldManager worldmanager;

    public ViewCommand(NFTMCCore nftcore) {
        Core = nftcore;
        mvCore = Core.mvcore;
        safeteleporter = mvCore.getSafeTTeleporter();
        worldmanager = mvCore.getMVWorldManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length != 1) {
                player.sendMessage(ChatColor.RED + "Error: Invalid number of arguments. Correct usage: /view <NFTMC ID>");
                return true;
            }

            if (!StringUtils.isNumeric(args[0])) {
                player.sendMessage(ChatColor.RED + "Error: The NFT ID must be a number.");
            }
            MultiverseWorld world = worldmanager.getMVWorld("NFT" + args[0]);

            if (world == null) {
                player.sendMessage(ChatColor.RED + "Error: Invalid NFT ID.");
                return true;
            }

            safeteleporter.safelyTeleport(sender, (Entity) player, world.getSpawnLocation(), false);

            Core.clearChat(player);

            NFTInfo(player, args[0]);

        } else {
            sender.sendMessage("You can't use this in the terminal.");
        }
        return true;
    }

    private void NFTInfo(Player player, String ID) {
        Yaml yaml = new Yaml();
        File file;
        InputStream inputStream;
        File dataFile = new File("plugins/NFTMC/NFT" + ID + ".yml");
        try {
            inputStream = new FileInputStream(dataFile);
        } catch (java.io.FileNotFoundException ex) {

            player.sendMessage(ChatColor.RED + "Error: An internal error occurred preventing us from displaying "
                    + "data for this NFT.");
            return;
        }

        Map<String, Object> data = yaml.load(inputStream);
        Core.getLogger().info(data.toString());
        player.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "Currently viewing: NFTMC #" + ID);
        player.sendMessage(ChatColor.GRAY + "\"" + data.get("caption") + "\"");
        player.sendMessage(ChatColor.GRAY + "Original builder: " + data.get("builder"));
        player.sendMessage(ChatColor.GRAY + "Current owner: " + data.get("owner"));
        player.sendMessage(ChatColor.GRAY + "Current highest bid: " + data.get("highest-bid") + ChatColor.BOLD + " ETH");
        TextComponent openSeaLink = new TextComponent(ChatColor.GRAY + "Click here to view on "
                + ChatColor.BOLD + "OpenSea.");
        openSeaLink.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, (String) data.get("url")));
        openSeaLink.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("View on OpenSea").color(net.md_5.bungee.api.ChatColor.GRAY)
                        .italic(true).create()));
        player.spigot().sendMessage(openSeaLink);
        TextComponent msgViewRandom = new TextComponent(ChatColor.GRAY + "Click here to view a random "
                + ChatColor.BOLD + "NFTMC.");
        msgViewRandom.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("View a random NFTMC").color(net.md_5.bungee.api.ChatColor.GRAY)
                        .italic(true).create()));
        msgViewRandom.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/viewrandom"));
        player.spigot().sendMessage(msgViewRandom);
    }
}
