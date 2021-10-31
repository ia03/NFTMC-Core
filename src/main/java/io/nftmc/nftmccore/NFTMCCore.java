package io.nftmc.nftmccore;

import com.onarandombox.MultiverseCore.MultiverseCore;
import io.nftmc.nftmccore.commands.ViewCommand;
import io.nftmc.nftmccore.commands.ViewRandomCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class NFTMCCore extends JavaPlugin {
    public MultiverseCore mvcore;
    public File dataFolder;

    @Override
    public void onEnable() {
        mvcore = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        if (mvcore == null) {
            this.getLogger().severe("Multiverse-Core is not installed, so NFTMC-Core will not work.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        dataFolder = new File("plugins/NFTMC");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        } else if (!dataFolder.isDirectory()) {
            this.getLogger().severe("plugins/NFTMC file conflicts with equivalently named folder.");
            this.getLogger().severe("NFTMC-Core will not work.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        this.getCommand("view").setExecutor(new ViewCommand(this));
        this.getCommand("viewrandom").setExecutor(new ViewRandomCommand());
    }

    @Override
    public void onDisable() {
    }

    public void clearChat(Player player) {
        for (int i = 0; i < 100; i++)
        {
            player.sendMessage("\n");
        }

    }
}
