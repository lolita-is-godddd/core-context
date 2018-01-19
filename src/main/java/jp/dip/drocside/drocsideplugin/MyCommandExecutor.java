/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.dip.drocside.drocsideplugin;

import jp.dip.drocside.drocsideplugin.enums.CommandError;
import jp.dip.drocside.drocsideplugin.enums.CommandInfo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

import static jp.dip.drocside.drocsideplugin.enums.CommandError.*;

/**
 *
 * @author Obsidian550D
 */
public class MyCommandExecutor implements CommandExecutor {
	private final JavaPlugin plugin;
	static final API API = new API();
	/**
	 * false = 許可 / true = 不許可
	 */
	static HashMap<Player, Bool> tpt = new HashMap<>(); // 基本形で入れられないので回避
	MyCommandExecutor(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	/**
	 * コマンド実行処理
	 * @param cs 送信者
	 * @param cmd コマンド名
	 * @param commandLabel
	 * @param args
	 * @return
	 */
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String commandLabel, String[] args){
		// test コマンドの処理
		Player p = null;
		if (API.isPlayer(cs)) { p = (Player)cs; }
		switch (cmd.getName().toLowerCase()) {
			case "kill":
				if (args.length == 0) {
					plugin.getLogger().info("( ^o^)<ﾝﾝﾝﾝﾝﾝﾝﾝﾝﾝﾝﾝﾝﾝﾝﾝｗｗｗ");
				} else {
					switch (args[0]) {
						default:
							p.sendMessage(CommandError.UNKNOWN.toString());
							p.setHealth(0.0d);
							return false;
					}
				}
				return true;
            case "serverinfo":
                cs.sendMessage("IP:" + Bukkit.getServer().getIp());
                cs.sendMessage("PORT:" + Bukkit.getServer().getPort());
                cs.sendMessage("MAX:" + Bukkit.getServer().getMaxPlayers());
                return true;
            case "testteiki":
                new Teiki().test();
                return true;
			case "tpa":
				p.sendMessage(NOT_IMPL.toString());
				return true;
			case "tpahere":
				p.sendMessage(NOT_IMPL.toString());
				return true;
			case "tpt":
				tpt.put(p, new Bool(!tpt.get(p).get())); // 反転
				if (new Bool(tpt.get(p).get()).get()) {
					p.sendMessage(CommandInfo.TPT_ON.toString());
				} else {
					p.sendMessage(CommandInfo.TPT_OFF.toString());
				}
				return true;
			case "tp":
				if (args.length == 0) {
					return false;
				}
				if (tpt.get(args[0]).get() && !p.hasPermission("drocside.tp.ignore")) {
					p.sendMessage(TP_DENY.toString().replace("$1", args[0]));
					return true;
				} else {
					for (Entity e : p.getWorld().getEntities()) {
						if (e instanceof Player) {
							if (e.getName().equals(args[0])) {
								p.teleport(e);
								return true;
							}
						}
					}
					p.sendMessage(PLAYER_NOT_FOUND.toString().replace("$1", args[0]));
				}
			case "tphere":
				if (args.length == 0) {
					return false;
				}
				if (tpt.get(args[0]).get() && !p.hasPermission("drocside.tp.ignore")) {
					p.sendMessage(TP_DENY.toString().replace("$1", args[0]));
					return true;
				} else {
					for (Entity e : p.getWorld().getEntities()) {
						if (e instanceof Player) {
							if (e.getName().equals(args[0])) {
								p.teleport(e);
								return true;
							}
						}
					}
					p.sendMessage(PLAYER_NOT_FOUND.toString().replace("$1", args[0]));
				}
			case "sabamiso":
				p.sendMessage("さばみそサーバーに接続中・・・");
				p.performCommand("server sabamiso");
				return true;
			case "toro":
				p.sendMessage("TORO Serverに接続中・・・");
				p.performCommand("server toro");
				return true;
			default:
				return false;
		}
	}

}
