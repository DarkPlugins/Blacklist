package de.darkplugins.Log;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogCreator implements Listener{

    private String folderPath = "plugins/Blacklist/logs";
    private String logName = "log.txt";

    private boolean Log;

    public LogCreator() {

    }

    public void CreateLog(Player player, String cmd, boolean log) {
        this.Log = log;
        if (Log) {
            File file = new File(folderPath, logName);
            try {
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }

                LocalDateTime currentTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss");
                String formattedTime = currentTime.format(formatter);

                try (FileWriter fw = new FileWriter(file, true)) {
                    fw.write("[" + formattedTime + "] " + player.getName() + " -> " + cmd + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void createFolder() {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
        }
    }
}
