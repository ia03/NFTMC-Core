package io.nftmc.nftmccore;

import com.onarandombox.MultiverseCore.MultiverseCore;
import io.nftmc.nftmccore.commands.HelpCommand;
import io.nftmc.nftmccore.commands.LoginListener;
import io.nftmc.nftmccore.commands.ViewCommand;
import io.nftmc.nftmccore.commands.ViewRandomCommand;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class NFTMCCore extends JavaPlugin {
    public MultiverseCore mvcore;
    public File dataFolder;


    @Override
    public void onEnable() {
        mvcore = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        PluginManager pm = Bukkit.getPluginManager();
        if (mvcore == null) {
            this.getLogger().severe("Multiverse-Core is not installed, so NFTMC-Core will not work.");
            pm.disablePlugin(this);
        }

        dataFolder = new File("plugins/NFTMC");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        } else if (!dataFolder.isDirectory()) {
            this.getLogger().severe("plugins/NFTMC file conflicts with equivalently named folder.");
            this.getLogger().severe("NFTMC-Core will not work.");
            pm.disablePlugin(this);
        }

        this.getCommand("view").setExecutor(new ViewCommand(this));
        this.getCommand("viewrandom").setExecutor(new ViewRandomCommand());
        this.getCommand("help").setExecutor(new HelpCommand());
        pm.registerEvents(new LoginListener(), this);
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
