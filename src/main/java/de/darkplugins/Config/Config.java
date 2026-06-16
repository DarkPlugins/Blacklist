package de.darkplugins.Config;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Config implements Listener {

    private String folderPath = "plugins/Blacklist";
    private String configName = "config.yml";
    private boolean Backup;

    public void createFolder() {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
        }
    }

    public void createYML() {
        File file = new File(folderPath, configName);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        if(!file.exists()) {
            try (FileWriter fw = new FileWriter(file)) {
                fw.write("Prefix: '&8[&4BLACKLIST&8] '\n\n");
                fw.write("# Dies Berechtigung, die ein Spieler benötigt, um die Blacklist zu umgehen #\n");
                fw.write("Bypass: 'system.blacklist.bypass'\n\n");
                fw.write("# Entscheidet, ob du Spielern mit einer bestimmten Berechtigung über das ausführen von gesperrten Befehlen informieren möchtest. #\n");
                fw.write("# true = ja | false = nein #\n");
                fw.write("PlayerNotify: 'true'\n\n");
                fw.write("# Dies Berechtigung, die ein Spieler benötigt, um über das ausführen von gesperrten Befehlen anderer Informiert zu werden #\n");
                fw.write("PlayerNotifyPerms: 'system.blacklist.MsgNotify'\n\n");
                fw.write("# Entscheidet, ob Logs erstellt werden sollen. #\n");
                fw.write("Logs: 'true'\n\n");
                fw.write("# Die Nachricht, die angezeigt wird, wenn ein Spieler nicht über die erforderlichen Berechtigungen verfügt. #\n");
                fw.write("BlockMSG: '&cDieser Befehl ist &4gesperrt'\n\n");
                fw.write("# Blacklist-Commands [Trenne die Befehle mit einem Komma und benutze keine Leerzeichen!] #\n");
                fw.write("Blacklist: 'plugins,pl,help,?,bukkit:?,bukkit:help'\n\n");
                fw.write("# Entscheidet, ob du Informationen zu neueren Versionen des Plugins erhalten möchtest. true = ja | false = nein #\n");
                fw.write("Updates: 'true'\n\n");
                fw.write("# Die Berechtigung, die ein Spieler benötigt, um über neue Plugin-Updates informiert zu werden #\n");
                fw.write("UpdatePerms: 'system.blacklist.notify'\n\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                List<String> lines = Files.readAllLines(file.toPath(), Charset.defaultCharset());

                boolean hasPrefix = false;
                boolean hasBypass = false;
                boolean hasPlayerNotify = false;
                boolean hasPlayerNotifyPerms = false;
                boolean hasLogs = false;
                boolean hasBlockMSG = false;
                boolean hasBlacklist = false;
                boolean hasUpdates = false;
                boolean hasUpdatePerms = false;

                for (String line : lines) {
                    if (!line.trim().startsWith("#")) {
                        if (line.startsWith("Prefix:")) {
                            hasPrefix = true;
                        } else if (line.startsWith("Bypass:")) {
                            hasBypass = true;
                        } else if (line.startsWith("PlayerNotify:")) {
                            hasPlayerNotify = true;
                        } else if (line.startsWith("PlayerNotifyPerms:")) {
                            hasPlayerNotifyPerms = true;
                        } else if (line.startsWith("Logs:")) {
                            hasLogs = true;
                        } else if (line.startsWith("BlockMSG:")) {
                            hasBlockMSG = true;
                        } else if (line.startsWith("Blacklist:")) {
                            hasBlacklist = true;
                        } else if (line.startsWith("Updates:")) {
                            hasUpdates = true;
                        } else if (line.startsWith("UpdatePerms:")) {
                            hasUpdatePerms = true;
                        }
                    }
                }

                if (!hasPrefix) {
                    String wert = Backup("Prefix");
                    String value = "Prefix: '" + wert + "'\n\n";
                    write(file, value);
                }
                if (!hasBypass) {
                    String wert = Backup("Bypass");
                    String value1 = "# Dies Berechtigung, die ein Spieler benötigt, um die Blacklist zu umgehen #\n";
                    String value2 = "Bypass: '" + wert + "'\n\n";
                    write(file, value1);
                    write(file, value2);
                }
                if (!hasPlayerNotify) {
                    String wert = Backup("PlayerNotify");
                    String value1 = "# Entscheidet, ob du Spielern mit einer bestimmten Berechtigung über das ausführen von gesperrten Befehlen informieren möchtest. #\n";
                    String value2 = "# true = ja | false = nein #\n";
                    String value3 = "PlayerNotify: '" + wert + "'\n\n";
                    write(file, value1);
                    write(file, value2);
                    write(file, value3);
                }
                if (!hasPlayerNotifyPerms) {
                    String wert = Backup("PlayerNotifyPerms");
                    String value1 = "# Dies Berechtigung, die ein Spieler benötigt, um über das ausführen von gesperrten Befehlen anderer Informiert zu werden #\n";
                    String value2 = "PlayerNotifyPerms: '" + wert + "'\n\n";
                    write(file, value1);
                    write(file, value2);
                }
                if (!hasLogs) {
                    String wert = Backup("Logs");
                    String value1 = "# Entscheidet, ob Logs erstellt werden sollen. #\n";
                    String value2 = "Logs: 'true'\n\n";
                    write(file, value1);
                    write(file, value2);
                }
                if (!hasBlockMSG) {
                    String wert = Backup("BlockMSG");
                    String value1 = "# Die Nachricht, die angezeigt wird, wenn ein Spieler nicht über die erforderlichen Berechtigungen verfügt. #\n";
                    String value2 = "BlockMSG: '" + wert + "'\n\n";
                    write(file, value1);
                    write(file, value2);
                }
                if (!hasBlacklist) {
                    String wert = Backup("Blacklist");
                    String value1 = "# Blacklist-Commands [Trenne die Befehle mit einem Komma und benutze keine Leerzeichen!] #\n";
                    String value2 = "Blacklist: '" + wert + "'\n\n";
                    write(file, value1);
                    write(file, value2);
                }
                if (!hasUpdates) {
                    String wert = Backup("Updates");
                    String value1 = "# Entscheidet, ob du Informationen zu neueren Versionen des Plugins erhalten möchtest. true = ja | false = nein #\n";
                    String value2 = "Updates: '" + wert + "'\n\n";
                    write(file, value1);
                    write(file, value2);
                }
                if (!hasUpdatePerms) {
                    String wert = Backup("UpdatePerms");
                    String value1 = "# Die Berechtigung, die ein Spieler benötigt, um über neue Plugin-Updates informiert zu werden #\n";
                    String value2 = "UpdatePerms: '" + wert + "'\n\n";
                    write(file, value1);
                    write(file, value2);
                }
                EndBackup();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String ReadYML(String wert) {
        File configPath = new File(folderPath, configName);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configPath);

        if (config.isString(wert)) {
            String rawValue = config.getString(wert);
            if (rawValue != null) {
                return ChatColor.translateAlternateColorCodes('&', rawValue);
            }
        }
        return null;
    }

    private String Backup(String wert) {
        if (!Backup) {
            Backup = true;
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
            String backupTime = currentTime.format(formatter);
            String backupName = "Backup_" + configName + "_" + backupTime + ".yml";
            File backupFile = new File(folderPath, backupName);
            File originalFile = new File(folderPath, configName);

            if (originalFile.exists()) {
                try (InputStream in = Files.newInputStream(originalFile.toPath());
                     OutputStream out = Files.newOutputStream(backupFile.toPath())) {

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0) {
                        out.write(buffer, 0, length);
                    }

                    try (FileWriter writer = new FileWriter(backupFile, true)) {
                        String value1 = "\n\n\n########################################################################################\n";
                        String value2 = "#####          Dieses Backup wurde aufgrund eines fehlenden Wert erstellt.         #####\n";
                        String value3 = "##### Es dient lediglich als Sicherung falls Einstellungen verloren gehen sollten. #####\n";
                        String value4 = "########################################################################################\n";
                        writer.write(value1);
                        writer.write(value2);
                        writer.write(value3);
                        writer.write(value4);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to write additional information to the backup file.");
                    }

                } catch (IOException e) {
                    throw new RuntimeException("Failed to create a backup.");
                }
            } else {
                throw new RuntimeException("Original file does not exist.");
            }
        }
        String wertContent = ReadYML(wert);
        if(wertContent == null) {
            switch (wert) {
                case "Prefix":
                    wertContent = "&8[&4BLACKLIST&8] ";
                    break;
                case "Bypass":
                    wertContent = "system.blacklist.bypass";
                    break;
                case "PlayerNotify":
                    wertContent = "true";
                    break;
                case "PlayerNotifyPerms":
                    wertContent = "system.blacklist.MsgNotify";
                    break;
                case "Logs":
                    wertContent = "true";
                    break;
                case "BlockMSG":
                    wertContent = "&cDieser Befehl ist &4gesperrt";
                    break;
                case "Blacklist":
                    wertContent = "plugins,pl,help,?,bukkit:?,bukkit:help";
                    break;
                case "Updates":
                    wertContent = "true";
                    break;
                case "UpdatePerms":
                    wertContent = "system.blacklist.notify";
                    break;
            }
        }
        return wertContent;
    }

    private void EndBackup() {
        if(Backup) {
            Backup = false;
        }
    }

    private void write(File file, String value) {
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
