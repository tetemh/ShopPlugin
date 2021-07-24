package fr.tetemh.shop;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class ShopListener implements Listener {
    private Main main;
    private HashMap<Player, Integer> money = new HashMap<>();

    public ShopListener(Main main) {
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if(!money.containsKey(player)){
            money.put(player, 0);
            player.sendMessage("§eTu as : §7" + money.get(player) + " §ePi");
        }

        event.setJoinMessage("§8[§a+§8]§7 " + player.getName());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        event.setQuitMessage("§8[§4-§8]§7 " + player.getName());
    }

}
