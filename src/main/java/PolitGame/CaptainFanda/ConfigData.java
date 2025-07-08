package PolitGame.CaptainFanda;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ConfigData {
    private static final File TPStones = new File("plugins/TPStones/stones.yml");
    private static final File names = new File("plugins/TPStones/names.yml");
    private static final File messages = new File("plugins/TPStones/messages.yml");

    public static void createConfigData() {
        YamlConfiguration ymlPut;
        if(!TPStones.exists()) {
            ymlPut = YamlConfiguration.loadConfiguration(TPStones);
            ymlPut.options().copyDefaults(true);
            try {
                ymlPut.save(TPStones);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void createNamesData() {
        YamlConfiguration ymlPut;
        if(!names.exists()) {
            ymlPut = YamlConfiguration.loadConfiguration(names);
            List<String> namesList = Arrays.asList("ray", "raystone", "widths", "Yrtpay", "gold", "goldenstone", "netherite", "sword", "stone", "wearstone", "near", "klassno", "tpstone");
            ymlPut.addDefault("names", namesList);
            ymlPut.options().copyDefaults(true);
            try {
                ymlPut.save(names);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void createMessagesFile() {
        YamlConfiguration ymlPut;
        if(!messages.exists()) {
            ymlPut = YamlConfiguration.loadConfiguration(messages);
            ymlPut.set("prefix", "&7[&6FoxCompany&7]&f ");
            ymlPut.set("new-stone", "&eУспешно создан новый обелиск: &6%stone%");
            ymlPut.set("tp-stone", "&eУспешно телепортировались на обелиск: &6%stone%");
            ymlPut.set("list-stone", "&eВаши Обелиски: &6%stones%");
            ymlPut.options().copyDefaults(true);
            try {
                ymlPut.save(messages);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static YamlConfiguration getStonesFile() {
        return YamlConfiguration.loadConfiguration(TPStones);
    }
    public static YamlConfiguration getMessages() {
        return YamlConfiguration.loadConfiguration(messages);
    }

    public static void addStone(Player player, Location location, String name) {
        YamlConfiguration ymlPut = YamlConfiguration.loadConfiguration(TPStones);
        ymlPut.set(player.getName() + "." + name + ".name", name);
        ymlPut.set(player.getName() + "." + name + ".location", location);
        ymlPut.options().copyDefaults(true);
        try {
            ymlPut.save(TPStones);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<String> getNames() {
        YamlConfiguration ymlPut = YamlConfiguration.loadConfiguration(names);
        return ymlPut.getStringList("names");
    }


}
