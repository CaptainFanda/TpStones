package PolitGame.CaptainFanda;

import PolitGame.CaptainFanda.Commands.TpWayCommand;
import PolitGame.CaptainFanda.Listeners.StoneListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class TPStones extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        ConfigData.createConfigData();
        ConfigData.createNamesData();
        ConfigData.createMessagesFile();
        getServer().getPluginManager().registerEvents(new StoneListener(), this);
        getCommand("tpw").setExecutor(new TpWayCommand());
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
