package jp.dip.drocside.drocsideplugin.API;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

public class PlayerStatistics {
    private Player p;
    private YamlConfiguration yml;
    static String filePath;
    private int kill;
    private int death;
    private float kd;
    public PlayerStatistics(Player p) {
        this.p = p;
        filePath = API.YAMLCONFIG_PATH.replace("$1", p.getUniqueId().toString());
        yml = new API().getYaml(File.separator + "players" + filePath);
        this.kill = yml.getInt("kill", 0);
        this.death = yml.getInt("death", 0);
        try {
            this.kd = this.kill / this.death;
        } catch (ArithmeticException e) {
            this.kd = 0;
        }
    }

    public int getKill() {
        return kill;
    }

    public int getDeath() {
        return death;
    }

    public float getKD() {
        return kd;
    }

    public void addKill() {
        kill++;
        updateKD();
    }

    public void addDeath() {
        death++;
        updateKD();
    }

    public void updateKD() {
        try {
            this.kd = this.kill / this.death;
        } catch (ArithmeticException e) {
            this.kd = 0;
        }
    }

    @Override
    public void finalize() {
        yml.set("kill", kill);
        yml.set("death", death);

        p = null;
        yml = null;
        filePath = null;
    }
}
