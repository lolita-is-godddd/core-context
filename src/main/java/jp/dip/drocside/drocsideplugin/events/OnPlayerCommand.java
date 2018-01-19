package jp.dip.drocside.drocsideplugin.events;

import jp.dip.drocside.drocsideplugin.API;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class OnPlayerCommand implements Listener {
    JavaPlugin plugin;
    final static API API = new API();
    public OnPlayerCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        String command = e.getMessage();
        Player p = e.getPlayer();
        String[] args = command.split(" ", 0);
        API.sendMessageToAll(command.toLowerCase());
        switch (command.toLowerCase()) {
            case "//calc":
            case "/worldedit:calc":
                p.chat("最近算数できすぎて辛いわー。\n特に1+1が2だったことを知った時感動したね。");
                e.setCancelled(true);
                return;
            case "/god":
                p.chat("おいどんは神だからなんでもできちゃう！");
                e.setCancelled(true);
                return;
            case "/kill":
                if (args.length != 0) p.chat(args[0] + "を殺してやる・・・");
                e.setCancelled(true);
                return;
        }
    }
}
