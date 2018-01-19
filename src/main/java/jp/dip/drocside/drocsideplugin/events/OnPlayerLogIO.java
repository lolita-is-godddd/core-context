package jp.dip.drocside.drocsideplugin.events;

import jp.dip.drocside.drocsideplugin.API;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public class OnPlayerLogIO implements Listener {
    JavaPlugin plugin;
    final static jp.dip.drocside.drocsideplugin.API API = new API();
    public OnPlayerLogIO(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
   public void onPlayerLogin(PlayerJoinEvent e) {
        final int TOO_MANY_ACCOUNT = 8;
        YamlConfiguration y = API.getYaml(e.getPlayer().getUniqueId().toString());
        //ArrayList<UUID> ids = new ArrayList<>();
        ArrayList<String> a = new ArrayList<>();
        for (String w : y.getStringList("uuids")) {
            a.add(w);
        }

        if (a.size() >= TOO_MANY_ACCOUNT) {
            API.getUtil().kick(e.getPlayer(), "Too many account(" + a.size() + ") !");
            Bukkit.broadcastMessage(ChatColor.YELLOW + e.getPlayer().getName() + "はアカウントが多すぎるのでキックしました。");
        }

        e.setJoinMessage(ChatColor.YELLOW + e.getPlayer().getName() + "が参加しました");
        a = null;
        y = null;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(ChatColor.YELLOW + e.getPlayer().getName() + "が退出しました");
    }
}
