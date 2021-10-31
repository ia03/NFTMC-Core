package io.nftmc.nftmccore.commands;

import io.nftmc.nftmccore.NFTMCCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Random;

public class ViewRandomCommand implements CommandExecutor {
    NFTMCCore Core;
    public ViewRandomCommand(NFTMCCore core) {
        Core = core;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You can't use this in the terminal.");
            return false;
        }
        Random rand = new Random();
        int upperBound = getNumNFT();
        int nftID = rand.nextInt(upperBound) + 1;

        Bukkit.dispatchCommand(sender, "view " + nftID);

        Core.sendServerLinks((Player)sender);
        return true;
    }

    private int getNumNFT() {
        File dir = new File("plugins/NFTMC");
        File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("NFT");
            }
        });
        int upperBound = 0;
        for (File nftYMLFile : files) {
            String name = nftYMLFile.getName();
            String NFTID = name.substring(name.lastIndexOf("T") + 1,
                    name.lastIndexOf("."));
            upperBound = Math.max(upperBound, Integer.parseInt(NFTID));
        }
        return upperBound;
    }
}
