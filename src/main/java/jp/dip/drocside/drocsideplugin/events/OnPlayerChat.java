package jp.dip.drocside.drocsideplugin.events;

import jp.dip.drocside.drocsideplugin.API.PlayerPenalty;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnPlayerChat implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        PlayerPenalty pp = new PlayerPenalty(e.getPlayer());
        if (pp.hasMuted(e.getPlayer().getUniqueId())) { // ミュートされているならキャンセル
            e.setCancelled(true);
            e.getPlayer().sendMessage("あなたはミュートされています。");
        }
    }
}
