package me.arboriginal.WBFillPause;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import com.wimbli.WorldBorder.Config;

public class Main extends JavaPlugin implements Listener {
  public static BukkitRunnable task;

  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(this, this);
  }

  @Override
  public void onDisable() {
    super.onDisable();

    HandlerList.unregisterAll((JavaPlugin) this);
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    if (playerBypass(event.getPlayer())) return;

    if (WBFill(false)) {
      Config.fillTask.pause();
      getLogger().info("================== WorldBorder fill => Pause ==================");
    }
  }

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    if (WBFill(true)) {
      if (task != null) task.cancel();
        
      task = new BukkitRunnable() {
        @Override
        public void run() {
          for (Player player : getServer().getOnlinePlayers()) {
            if (!playerBypass(player)) return;
          }

          Config.fillTask.pause(false);
          getLogger().info("================== WorldBorder fill => Resume ==================");
          task = null;
        }
      };

      task.runTaskLater(this, 300);
    }
  }

  private boolean playerBypass(Player player) {
    return player.hasPermission("wbfp.bypass");
  }

  private boolean WBFill(boolean paused) {
    return ((Config.fillTask != null) && Config.fillTask.valid() && (Config.fillTask.isPaused() == paused));
  }
}
