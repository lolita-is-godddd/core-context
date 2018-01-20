package jp.dip.drocside.drocsideplugin.API;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class EconomyAPI {
    private Player player;
    private float money;
    private YamlConfiguration yml;
    EconomyAPI(Player p) {
        player = p;
        yml = new API().getYaml(p.getUniqueId().toString());
    }

    public void inc(double amount) {
        money += amount;
    }

    public void dec(double amount) {
        money -= amount;
    }

    @Override
    public void finalize() {
        this.player = null;
        try {
            yml.set("money", money);
            yml.save(player.getUniqueId().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
