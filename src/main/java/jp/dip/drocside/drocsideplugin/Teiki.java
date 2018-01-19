package jp.dip.drocside.drocsideplugin;

import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import jp.dip.drocside.drocsideplugin.API;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class Teiki extends BukkitRunnable {
    static final API API;
    static final API.Math Math;
    static final String PREFIX = "["+ ChatColor.DARK_PURPLE + "定期" + ChatColor.RESET + "]";
    static {
        API = new API();
        Math = API.getMathLib();
    }

    @Override
    public void run() {
        test();
    }

    public void test() {
        ArrayList<String> messages = (ArrayList<String>) (API.getTeiki());
        if (messages.size() == 0) {
            return; //
        }
        API.sendMessageToAll(PREFIX + messages.get(Math.rnd(0, messages.size())));
    }
}
