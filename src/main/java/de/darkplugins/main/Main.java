package de.darkplugins.main;

import de.darkplugins.Blacklist.CommandChecker;
import de.darkplugins.Config.Config;
import de.darkplugins.Listeners.JoinListener;
import de.darkplugins.Log.LogCreator;
import de.darkplugins.Updater.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Main extends JavaPlugin {

    String Prefix, Bypass, PlayerNotifyPerms, BlockMSG, Blacklist, UpdatePermInfo;
    boolean PlayerNotify, UpdateCheck, Logs, UpdateAvailable = false;

    Config config = new Config();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Config(), this);
        config.createFolder();
        config.createYML();

        Prefix = config.ReadYML("Prefix");
        Bypass = config.ReadYML("Bypass");
        PlayerNotify = Boolean.parseBoolean(config.ReadYML("PlayerNotify"));
        PlayerNotifyPerms = config.ReadYML("PlayerNotifyPerms");
        Logs = Boolean.parseBoolean(config.ReadYML("Logs"));
        BlockMSG = config.ReadYML("BlockMSG");
        Blacklist = config.ReadYML("Blacklist");
        UpdateCheck = Boolean.parseBoolean(config.ReadYML("Updates"));
        UpdatePermInfo = config.ReadYML("UpdatePerms");

        String[] splitCommands = Blacklist.split(",");
        ArrayList<String> BlacklistCommands = new ArrayList<>();
        for (String command : splitCommands) {
            BlacklistCommands.add(command);
        }
        getServer().getPluginManager().registerEvents(new CommandChecker(Prefix, Bypass, PlayerNotify, PlayerNotifyPerms, BlockMSG, BlacklistCommands, Logs), this);

        // UpdateChecker
        new UpdateChecker(this, 114144).getVersion(version -> {
            if (UpdateCheck) {
                String currentVersion = this.getDescription().getVersion();
                if (currentVersion.compareTo(version) >= 0) {
                    getLogger().info("Das Plugin ist auf der aktuellsten Version.");
                    UpdateAvailable = false;
                } else {
                    getLogger().info("Neuere Plugin-Version gefunden: v" + version);
                    getLogger().info("Aktuell: v" + currentVersion + " | Neu: v" + version);
                    UpdateAvailable = true;
                }
            } else {
                getLogger().info("Update-Suche ist deaktiviert.");
            }
            getServer().getPluginManager().registerEvents(new JoinListener(Prefix, UpdatePermInfo, UpdateAvailable), this);
        });

        Bukkit.getConsoleSender().sendMessage(Prefix + "§a§lBlacklist-Plugin aktiviert! §2(v" + this.getDescription().getVersion() + ")");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Prefix + "§4§lBlacklist-Plugin deaktiviert!");
    }
}
