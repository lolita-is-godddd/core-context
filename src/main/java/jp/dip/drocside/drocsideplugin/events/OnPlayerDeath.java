package jp.dip.drocside.drocsideplugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import jp.dip.drocside.drocsideplugin.API;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class OnPlayerDeath {
    /*
    JavaPlugin plugin;
    static final API API;

    static {
        API = new API();
    }

    public OnPlayerDeath(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerDeath (EntityDeathEvent e) {
        if (API.isPlayer(e.getEntity())) {
            //Player death = (Player)(e.getEntity());
            //Player kill = e.getEntity().getKiller();
            //String killerName = kill.getName();
            //death.sendMessage("You have killed by" + (killerName == null ? "null" : killerName));
            //death.teleport(death.getBedSpawnLocation());
        }
    }
    */
}
