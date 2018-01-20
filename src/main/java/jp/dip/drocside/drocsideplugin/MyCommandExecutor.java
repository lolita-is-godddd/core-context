/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.dip.drocside.drocsideplugin;

import jp.dip.drocside.drocsideplugin.API.API;
import jp.dip.drocside.drocsideplugin.API.PlayerPenalty;
import jp.dip.drocside.drocsideplugin.enums.CommandError;
import jp.dip.drocside.drocsideplugin.enums.CommandInfo;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

import static jp.dip.drocside.drocsideplugin.enums.CommandError.*;

/**
 *
 * @author Obsidian550D
 */
public class MyCommandExecutor implements CommandExecutor {
	private final JavaPlugin plugin;
	static final jp.dip.drocside.drocsideplugin.API.API API = new API();
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
		PlayerPenalty pp;
		if (API.isPlayer(cs)) { p = (Player)cs; }
		switch (cmd.getName().toLowerCase()) {
			case "kill":
				if (API.isPlayer(cs)) {
					cs.sendMessage(CommandError.PLAYER_ONLY.toString());
					return true;
				}
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
            	cs.sendMessage("サーバーの状態");
                cs.sendMessage("IP: " + (Bukkit.getServer().getIp().equals("") ? "localhost" : Bukkit.getServer().getIp()));
                cs.sendMessage("PORT: " + Bukkit.getServer().getPort());
                cs.sendMessage("PLAYER: " + Bukkit.getServer().getMaxPlayers());
                cs.sendMessage(String.format("MEMORY: %.0fMB/%.0fMB", Runtime.getRuntime().freeMemory() / 1e6, Runtime.getRuntime().maxMemory() / 1e6));
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
				if (API.isPlayer(cs)) {
					cs.sendMessage(CommandError.PLAYER_ONLY.toString());
					return true;
				}
				if (!tpt.containsKey(p)) {
					initForTpt(p);
				} else {
					tpt.put(p, new Bool(!tpt.get(p).get())); // 反転
				}
				if (new Bool(tpt.get(p).get()).get()) {
					p.sendMessage(CommandInfo.TPT_ON.toString());
				} else {
					p.sendMessage(CommandInfo.TPT_OFF.toString());
				}
				return true;
			case "tp":
				if (API.isPlayer(cs)) {
					cs.sendMessage(CommandError.PLAYER_ONLY.toString());
					return true;
				}
				if (args.length == 0) {
					p.sendMessage("プレイヤー名を指定してください。");
					return false;
				}
				initForTpt(p);
				if (tpt.get(args[0]).get() && !p.hasPermission("drocside.tp.ignore")) {
					p.sendMessage(TP_DENY.toString().replace("$1", args[0]));
					return true;
				} else {
					p.teleport(API.searchPlayer(args[0]));
					p.sendMessage(PLAYER_NOT_FOUND.toString().replace("$1", args[0]));
				}
			case "tphere":
				if (API.isPlayer(cs)) {
					cs.sendMessage(CommandError.PLAYER_ONLY.toString());
					return true;
				}
				if (args.length == 0) {
					p.sendMessage("プレイヤー名を指定してください。");
					return false;
				}
				if (!tpt.containsKey(p)) {
					initForTpt(p);
				}
				if (tpt.get(args[0]).get() && !p.hasPermission("drocside.tp.ignore")) {
					p.sendMessage(TP_DENY.toString().replace("$1", args[0]));
					return true;
				} else {
					Player target = API.searchPlayer(args[0]);
					if (target == null) {
						p.sendMessage(PLAYER_NOT_FOUND.toString().replace("$1", args[0]));
					} else {
						target.teleport(p);
					}
					return true;
				}
			case "sabamiso":
				if (!API.isPlayer(cs)) {
					cs.sendMessage(CommandError.PLAYER_ONLY.toString());
					return true;
				}
				p.sendMessage("さばみそサーバーに接続中・・・");
				p.performCommand("server sabamiso");
				return true;
			case "toro":
				if (!API.isPlayer(cs)) {
					cs.sendMessage(CommandError.PLAYER_ONLY.toString());
					return true;
				}
				p.sendMessage("TORO Serverに接続中・・・");
				p.performCommand("server toro");
				return true;
			case "mute":
				if (args.length == 0) {
					return false;
				}
				pp = new PlayerPenalty(API.searchPlayer(args[0]));
				pp.mute();
				return true;
			case "unmute":
				if (args.length == 0) {
					return false;
				}
				pp = new PlayerPenalty(API.searchPlayer(args[0]));
				pp.unmute();
				return true;
			case "ban":
				if (args.length == 0) {
					return false;
				}
				pp = new PlayerPenalty(API.searchPlayer(args[0]));
				pp.ban();
				return true;
			case "unban":
				/*
				if (args.length == 0) {
					return false;
				}
				pp = new PlayerPenalty(API.searchPlayer(args[0]));
				pp.unban(); */
				cs.sendMessage(NOT_IMPL.toString());
				return true;
			case "kick":
				pp = new PlayerPenalty(API.searchPlayer(args[0]));
				pp.kick();
				return true;
			case "jail":
				cs.sendMessage(NOT_IMPL.toString());
				return true;
			case "unjail":
				cs.sendMessage(NOT_IMPL.toString());
				return true;
			default:
				return false;
		}
	}

	public void initForTpt(Player p) {
		tpt.put(p, new Bool(false));
	}
}
