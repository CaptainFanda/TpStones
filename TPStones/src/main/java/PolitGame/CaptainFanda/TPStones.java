package PolitGame.CaptainFanda;

import PolitGame.CaptainFanda.Commands.TabCompleterTPW;
import PolitGame.CaptainFanda.Commands.TpWayCommand;
import PolitGame.CaptainFanda.Listeners.StoneListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.*;
import java.io.File;

public final class TPStones extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        ConfigData.createConfigData();
        ConfigData.createOptionsData();
        ConfigData.createMessagesFile();
        getServer().getPluginManager().registerEvents(new StoneListener(), this);
        getCommand("tpw").setExecutor(new TpWayCommand());
        getCommand("tpw").setTabCompleter(new TabCompleterTPW());
        Bukkit.getLogger().info("[TpStones] --TP Stones--");
        Bukkit.getLogger().info("[TpStones] --Enabled--");

    }




    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("[TpStones] --TP Stones--");
        Bukkit.getLogger().info("[TpStones] --Disabled--");
    }
}
