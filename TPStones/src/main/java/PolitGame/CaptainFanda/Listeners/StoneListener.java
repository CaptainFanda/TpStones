package PolitGame.CaptainFanda.Listeners;

import PolitGame.CaptainFanda.ConfigData;
import PolitGame.CaptainFanda.TPStones;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.block.Structure;
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
        Player player = e.getPlayer();
        Block placedBlock = e.getBlock();
        List<String> blocks = ConfigData.getStone();
        for (int i = 0; i < blocks.size(); i++) {
            Block currentBlock = placedBlock.getRelative(0, -i, 0);
            Material expectedMaterial;
            try {
                expectedMaterial = Material.valueOf(blocks.get(i));
            } catch (IllegalArgumentException e1) {
                return;
            }

            if (currentBlock.getType() != expectedMaterial) {

                return;
            }
        }
        List<String> names = ConfigData.getNames();
        Random random = new Random();
        int randomNameIndex = random.nextInt(0, names.size());
        String name = names.get(randomNameIndex);
        Location spawn = placedBlock.getLocation().add(2, -1, 2);
        ConfigData.addStone(player, spawn, name);
        String message = prefix + ConfigData.getMessages().getString("new-stone");
        message = message.replace("&", "§");
        message = message.replace("%stone%", name);
        player.sendMessage(message);
    }


}



