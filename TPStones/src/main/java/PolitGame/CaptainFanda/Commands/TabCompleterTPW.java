package PolitGame.CaptainFanda.Commands;

import PolitGame.CaptainFanda.ConfigData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class TabCompleterTPW implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s1, @NotNull String @NotNull [] args) {
        YamlConfiguration ymlPut = ConfigData.getStonesFile();
        Player p = (Player) sender;
        Set<String> stone = null;
        ConfigurationSection section = ymlPut.getConfigurationSection(p.getName());
        if (section != null) {
            stone = section.getKeys(false);
        }
        List<String> tab = new ArrayList<>();
        if(stone != null) {
            for (String string : stone) {
                if(ymlPut.get(p.getName() + "." + string + ".location" ) != null) {
                    tab.add(string);
                }
            }
        }
        List<String> completions = new ArrayList<>();
        switch (args.length) {
            case 1:
                completions.addAll(tab);
                completions.add("list");
                completions.add("remove");
                break;
            case 2:
                if(args[0].equals("remove")) {
                    completions.addAll(tab);
                }
                break;
        }

        String lastArg = args[args.length - 1].toLowerCase();
        return completions.stream()
                .filter(s -> s.toLowerCase().startsWith(lastArg))
                .collect(Collectors.toList());
    }
}
