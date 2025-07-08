package PolitGame.CaptainFanda.Listeners;

import PolitGame.CaptainFanda.ConfigData;
import PolitGame.CaptainFanda.TPStones;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.List;
import java.util.Random;

public class StoneListener implements Listener {
    private static final String prefix = ConfigData.getMessages().getString("prefix");
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player =  e.getPlayer();
        Block block = e.getBlock();
        Material material = block.getType();
        if(material == Material.OAK_SIGN) {
            Location loc = block.getLocation();
            if(loc.add(0, -1, 0).getBlock().getType() == Material.LIGHTNING_ROD) {
                if(loc.add(0, -1, 0).getBlock().getType() == Material.GOLD_BLOCK) {
                    if(loc.add(0, -1, 0).getBlock().getType() == Material.COBBLESTONE) {
                        List<String> names = ConfigData.getNames();
                        Random random = new Random();
                        int randomNameIndex = random.nextInt(0, names.size());
                        String name = names.get(randomNameIndex);
                        Location spawn = loc.add(1, 0, 1);
                        ConfigData.addStone(player, spawn, name);
                        String message = prefix + ConfigData.getMessages().getString("new-stone");
                        message = message.replace("&", "§");
                        message = message.replace("%stone%", name);
                        player.sendMessage(message);
                    }
                }
            }
        }
    }
}
