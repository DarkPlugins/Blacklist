package de.darkplugins.Blacklist;

import de.darkplugins.Log.LogCreator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CommandChecker implements Listener {

    private String Prefix, Bypass, PlayerNotifyPerms, BlockMSG;
    private boolean PlayerNotify, Log;
    private ArrayList<String> Blacklist;

    public CommandChecker(String prefix, String bypass, boolean playerNotify, String playerNotifyPerms, String blockMSG, ArrayList<String> commands, boolean log) {
        this.Prefix = prefix;
        this.Bypass = bypass;
        this.PlayerNotify = playerNotify;
        this.PlayerNotifyPerms = playerNotifyPerms;
        this.BlockMSG = blockMSG;
        this.Blacklist = commands;
        this.Log = log;
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String InputCMD = event.getMessage();
        for (String blacklistedCommand : Blacklist) {
            blacklistedCommand = "/" + blacklistedCommand;
            if (blacklistedCommand.equals(event.getMessage())) {
                if(!(player.hasPermission(Bypass))) {
                    event.setCancelled(true);
                    player.sendMessage(Prefix + BlockMSG);
                    LogCreator log = new LogCreator();
                    log.CreateLog(player, InputCMD, Log);
                    if(PlayerNotify) {
                        for(Player PlayerNotify : Bukkit.getOnlinePlayers()) {
                            if(PlayerNotify.hasPermission(PlayerNotifyPerms)) {
                                if(PlayerNotify != player) {
                                    PlayerNotify.sendMessage(Prefix + "§a" + player.getName() + " §7-> §4" + InputCMD + " §c[GESPERRT]");
                                }
                            }
                        }
                    }
                    Bukkit.getConsoleSender().sendMessage(Prefix + "§a" + player.getName() + " §7-> §4" + InputCMD + " §c[GESPERRT]");
                } else {
                    if(PlayerNotify) {
                        for(Player PlayerNotify : Bukkit.getOnlinePlayers()) {
                            if(PlayerNotify.hasPermission(PlayerNotifyPerms)) {
                                if(PlayerNotify != player) {
                                    PlayerNotify.sendMessage(Prefix + "§a" + player.getName() + " §7-> §4" + InputCMD + " §e[BYPASS]");
                                }
                            }
                        }
                    }
                    Bukkit.getConsoleSender().sendMessage(Prefix + "§a" + player.getName() + " §7-> §4" + InputCMD + " §e[BYPASS]");
                }
            }
        }
    }
}
