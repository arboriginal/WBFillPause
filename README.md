# WBFillPause

WBFillPause is a small plugin for [Spigot](https://www.spigotmc.org) Minecraft servers. It simply pause [WorldBorder's fill command](https://www.spigotmc.org/threads/worldborder.339635/#post-3162182) when a player join the server, then resume when nobody is online.

## How to install

Simply drop the jar file into your plugin directory, then restart (or reload) your server.

You can download the last release here: [WBFillPause.jar](https://github.com/arboriginal/WBFillPause/releases).

## Permissions

 - **wbfp.bypass**: Players with this permission will not pause the fill command.
   (default: op)
   
## Note

The fill command doesn't resume immediately when the player disconnect, but after 15 seconds. It prevent to re-launch the process if the player had a short connection loss.
