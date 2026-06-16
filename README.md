# Blacklist / Blocklist

A lightweight command blacklist plugin for Spigot/Paper servers.

Designed for Minecraft **1.18+**, this plugin allows server owners to block specific commands from regular players while providing bypass and notification options for staff members.

**Only available in German**

---

## ✨ Features

* 🚫 Block specific commands such as `/plugins`, `/pl`, `/help`, and more
* ⚙️ Fully configurable command blacklist
* 👮 Bypass permission for admins and moderators
* 📢 Optional staff notifications when blocked commands are executed
* 📝 Optional console logging of blocked command attempts
* 🔄 Update notification system for administrators
* 🇩🇪 German language support

---

## 📦 Installation

1. Place the plugin `.jar` file into your `plugins/` folder
2. Start or restart your server
3. Open the generated `config.yml`
4. Configure the blocked commands and permissions as needed
5. Reload or restart the server

---

## 🧠 How it works

Whenever a player executes a command, the plugin checks whether it is listed in the blacklist.

If the command is blocked:

* The player receives a configurable message
* The command execution is cancelled
* Staff members can optionally be notified
* The action can optionally be logged in the console

Players with the configured bypass permission can continue using blocked commands normally.

---

## ⚙️ Configuration

The configuration file is automatically generated at:

```text
/plugins/Blacklist/config.yml
```

### Example Configuration

```yaml
Prefix: '&8[&4BLACKLIST&8] '

Bypass: 'system.blacklist.bypass'

PlayerNotify: 'true'
PlayerNotifyPerms: 'system.blacklist.MsgNotify'

Logs: 'true'

BlockMSG: '&cDieser Befehl ist &4gesperrt'

Blacklist: 'plugins,pl,help,?,bukkit:?,bukkit:help'

Updates: 'true'
UpdatePerms: 'system.blacklist.notify'
```

---

## 🔑 Permissions

| Permission                   | Description                                              |
| ---------------------------- | -------------------------------------------------------- |
| `system.blacklist.bypass`    | Allows players to bypass the blacklist                   |
| `system.blacklist.MsgNotify` | Receive notifications when blocked commands are executed |
| `system.blacklist.notify`    | Receive plugin update notifications                      |

---

## 📋 Default Blocked Commands

The following commands are blocked by default:

* `/plugins`
* `/pl`
* `/help`
* `/?`
* `/bukkit:?`
* `/bukkit:help`

You can modify this list at any time inside the configuration file.

---

## 📝 Logging & Notifications

The plugin can optionally:

* Notify staff members when a blocked command is used
* Log blocked command attempts to the console
* Inform administrators about new plugin updates

All options can be enabled or disabled in the configuration.

---

## 🌐 Compatibility

* Spigot 1.18+
* Paper 1.18+
* Tested on:

  * 1.18
  * 1.19
  * 1.20
