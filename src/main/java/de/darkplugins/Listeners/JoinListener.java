package de.darkplugins.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private String Prefix, UpdatePermInfo;
    private boolean UpdateAvailable;

    public JoinListener(String prefix, String updatePermInfo, boolean updateAvailable) {
        this.Prefix = prefix;
        this.UpdatePermInfo = updatePermInfo;
        this.UpdateAvailable = updateAvailable;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(player.hasPermission(UpdatePermInfo)) {
            if(UpdateAvailable) {
                player.sendMessage(Prefix  + "§eEs ist eine neue Blacklist-Plugin Version verfügbar.");
                player.sendMessage(Prefix  + "§eDownload: §chttps://www.spigotmc.org/resources/blacklist-blocklist-de.114144/");
            }
        }
    }
}
