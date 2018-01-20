/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.dip.drocside.drocsideplugin;

import jp.dip.drocside.drocsideplugin.events.OnPlayerCommand;
import jp.dip.drocside.drocsideplugin.events.OnPlayerDeath;
import jp.dip.drocside.drocsideplugin.events.OnPlayerLogIO;
import jp.dip.drocside.drocsideplugin.events.RightClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Obsidian550D
 */
/*
    TODO : 青森県産リンゴ
    TODO : とろ鯖、さば味噌鯖へのエイリアス
 */
public class DrocsidePlugin extends JavaPlugin {
    static final boolean DEBUG = true;
    static BukkitTask propideTeiki; // 定期
	@Override
	public void onEnable() {
		// カウントアップの追加
        Bukkit.getLogger().info("v");
        final MyCommandExecutor CE = new MyCommandExecutor(this);
		getCommand("kill").setExecutor(CE);
		if (DEBUG) {
            getCommand("serverinfo").setExecutor(CE);
            getCommand("testteiki").setExecutor(CE);
        }
        getCommand("tp").setExecutor(CE);
        getCommand("tpa").setExecutor(CE);
        getCommand("tpahere").setExecutor(CE);
        getCommand("tpt").setExecutor(CE);
        getCommand("toro").setExecutor(CE);
        getCommand("sabamiso").setExecutor(CE);
        getCommand("ban").setExecutor(CE);
        getCommand("unban").setExecutor(CE);
        getCommand("jail").setExecutor(CE);
        getCommand("unjail").setExecutor(CE);
        getCommand("mute").setExecutor(CE);
        getCommand("unmute").setExecutor(CE);
        new Teiki().runTaskTimer(this, 0, 20 * 60 * 5);

        //final OnPlayerCommand onPlayerCommand = new OnPlayerCommand(this);
        final OnPlayerDeath onPlayerDeath = new OnPlayerDeath(this);
        //final RightClickEvent rightClickEvent = new RightClickEvent(this);
        final OnPlayerLogIO onPlayerLogIO = new OnPlayerLogIO(this);
        getLogger().info("enable successful.");
        getLogger().info("Special Thanks: sjcl, toropon, and hamyuu");
        Bukkit.broadcastMessage("Enabled DrocsidePlugin!");
    }

	@Override
	public void onDisable() {
	    try {
            propideTeiki.cancel();
        } catch (NullPointerException e) {

        }
		super.onDisable();
	}
}
