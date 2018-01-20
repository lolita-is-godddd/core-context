package jp.dip.drocside.drocsideplugin.events;

import jp.dip.drocside.drocsideplugin.API.PlayerStatistics;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import jp.dip.drocside.drocsideplugin.API.API;

public class OnPlayerDeath {

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
            Player death = (Player)(e.getEntity());
            Player kill = e.getEntity().getKiller();
            new PlayerStatistics(death).addDeath();
            new PlayerStatistics(kill).addKill();
            String killerName = kill.getName();
            death.sendMessage("You have killed by" + (killerName == null ? "null" : killerName));
            death.teleport(death.getBedSpawnLocation());
        }
    }
}
