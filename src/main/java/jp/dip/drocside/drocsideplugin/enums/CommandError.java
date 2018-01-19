/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.dip.drocside.drocsideplugin.enums;

import org.bukkit.ChatColor;

/**
 *
 * @author Obsidian550D
 */
public enum CommandError {
	DONT_HAVE_PERM("You don't have permission."),
	ILL_KILL_YOU("お前を殺してやる！！"),
	AINT_ABLE_ALL_KILL("Should be burning in hell !!!!!!!!!"),
	UNKNOWN("不明なコマンドです。"),
	NOT_IMPL("実装されていません。"),
	TP_DENY("$1さんはTPを拒否しています。\n" + ChatColor.GOLD + "/tpa" + ChatColor.RESET + "を使ってみてください。"),
	PLAYER_NOT_FOUND("$1さんは見つかりませんでした。"),;
	private String message;
	CommandError(String message) {
		this.message = message;
	}

	@Deprecated
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return getMessage();
	}

}
