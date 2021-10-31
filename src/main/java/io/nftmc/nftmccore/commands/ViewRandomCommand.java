package io.nftmc.nftmccore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Random;

public class ViewRandomCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Random rand = new Random();
        int upperBound = getNumNFT();
        int nftID = rand.nextInt(upperBound) + 1;

        Bukkit.dispatchCommand(sender, "view " + nftID);
        return true;
    }

    private Integer getNumNFT() {
        File dir = new File("plugins/NFTMC");
        File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("NFT");
            }
        });
        int highestID = 0;
        for (File nftYMLFile : files) {
            String name = nftYMLFile.getName();
            String NFTID = name.substring(name.lastIndexOf("T") + 1,
                    name.lastIndexOf("."));
            highestID = Math.max(highestID, Integer.parseInt(NFTID));
        }
        return highestID;
    }
}
