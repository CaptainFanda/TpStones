package PolitGame.CaptainFanda;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ConfigData {
    private static final File TPStones = new File("plugins/TPStones/stones.yml");
    private static final File options = new File("plugins/TPStones/options.yml");
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
    public static void createOptionsData() {
        YamlConfiguration ymlPut;
        if(!options.exists()) {
            ymlPut = YamlConfiguration.loadConfiguration(options);
            List<String> namesList = Arrays.asList("ray", "raystone", "widths", "Yrtpay", "gold", "goldenstone", "netherite", "sword", "stone", "wearstone", "near", "klassno", "tpstone");
            ymlPut.addDefault("names", namesList);
            List<String> Stone = Arrays.asList("LIGHTNING_ROD", "GOLD_BLOCK", "STONE");
            ymlPut.addDefault("stone", Stone);
            ymlPut.options().copyDefaults(true);
            try {
                ymlPut.save(options);
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
    public static void removeStone(Player player, String name) {
        YamlConfiguration ymlPut = getStonesFile();
        Location location = ymlPut.getLocation(player.getName() + "." + name + ".location");
        if(location != null) {
            location.add(-2, 1, -2);
            for (int i = 0; i < 7; i++) {
                location.getBlock().setType(Material.AIR);
                location.add(0, -1, 0);
            }
            ymlPut.set(player.getName() + "." + name, null);
            ymlPut.options().copyDefaults(true);
            try {
                ymlPut.save(TPStones);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static List<String> getNames() {
        YamlConfiguration ymlPut = YamlConfiguration.loadConfiguration(options);
        return ymlPut.getStringList("names");
    }
    public static List<String> getStone() {
        YamlConfiguration ymlPut = YamlConfiguration.loadConfiguration(options);
        return ymlPut.getStringList("stone");
    }


}
