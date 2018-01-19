/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.dip.drocside.drocsideplugin.enums;

/**
 *
 * @author Obsidian550D
 */
public enum PluginName {
	PERMISSION_EX("PermissionEX"),
	WORLD_EDIT("WorldEdit"),
	CORE_PROTECT("CoreProtect"),;
	private final String name;
	private PluginName(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
