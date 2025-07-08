package PolitGame.CaptainFanda.Commands;

import PolitGame.CaptainFanda.ConfigData;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.List;
import java.util.Set;

public class TpWayCommand implements CommandExecutor {
    private static final String prefix = ConfigData.getMessages().getString("prefix");
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if(sender instanceof Player p) {
            if(args.length == 0){

            } else if(args[0].equals("list")) {
                YamlConfiguration ymlPut = ConfigData.getStonesFile();
                Set<String> stone = ymlPut.getConfigurationSection(p.getName()).getKeys(false);
                List<String> stones = stone.stream().toList();
                String message = prefix + ConfigData.getMessages().getString("list-stone");
                message = message.replace("&", "§");
                message = message.replace("%stones%", stones.toString());
                p.sendMessage(message);
            }
            else {
                YamlConfiguration ymlPut = ConfigData.getStonesFile();
                String name = args[0].toLowerCase();
                Location location = ymlPut.getLocation(p.getName() + "." + name + ".location");
                if(location != null) {
                    p.teleport(location);
                    String message = prefix + ConfigData.getMessages().getString("tp-stone");
                    message = message.replace("&", "§");
                    message = message.replace("%stone%", name);
                    p.sendMessage(message);
                }
            }
        }
        return true;
    }
}
