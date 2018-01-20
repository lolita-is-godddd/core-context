package jp.dip.drocside.drocsideplugin.API;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerPenalty {
    Player player;
    public PlayerPenalty(Player p) {
        player = p;
    }

    public void ban() {
        ban("You have been banned!");
    }

    public void unban(String ip) {
        Bukkit.getServer().unbanIP(ip);
    }

    public void unban(OfflinePlayer offlinePlayer) {
        // TODO : IMPL
    }

    public void ban(String reason) {
        if (Bukkit.getBanList(BanList.Type.NAME).isBanned(player.getName())) {
            return;
        }
        player.getServer().banIP(player.getAddress().toString());
        Bukkit.broadcastMessage("Banned" + player.getName() + "\n" + "Reason: " + reason);
        player.kickPlayer("");
    }

    public void kick() {
        kick("");
    }

    public void kick(String reason) {
        player.kickPlayer(reason);
    }
    
    public void mute() {
        API API = new API();
        YamlConfiguration y = API.getYaml("muted");
        final String SECTION = "player";
        final String UID = player.getUniqueId().toString();
        if (y.getStringList(SECTION).contains(UID)) { // すでにミュート済み
            return;
        }
        if (!y.contains(SECTION)) {
            y.createSection(SECTION);
        }
        ArrayList<String> mutedList = new ArrayList<>();
        //mutedList = y.getStringList(SECTION);
        for (String s : y.getStringList(SECTION)) {
            mutedList.add(s);
        }
        mutedList.add(UID);
        y.set(SECTION, mutedList);
    }

    public boolean hasMuted(UUID u) {
        API API = new API();
        YamlConfiguration y = API.getYaml("muted");
        final String SECTION = "player";
        final String UID = player.getUniqueId().toString();
        if (y.getStringList(SECTION).contains(UID)) { // すでにミュート済み
            return true;
        } else {
            return false;
        }
    }

    public void unmute() {
    }
}
