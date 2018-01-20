/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.dip.drocside.drocsideplugin.API;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.*;

import static org.bukkit.Bukkit.getLogger;

/**
 *
 * @author Obsidian550D
 */
public class API {
	static final String YAMLCONFIG_PATH = "plugins" + File.separatorChar + "drocside" + File.separatorChar + "$1.yml";
	static ArrayList<YamlConfiguration> configs = new ArrayList<>();
	public boolean isPlayer(CommandSender cs) {
		return cs instanceof Player;
	}
	public boolean isPlayer(LivingEntity le) {
		return le instanceof Player;
	}
	public boolean hasPermission(Player p, String perm) {
		return p.hasPermission(perm);
	}
	public void sendMessageToAll(String s) {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			p.sendMessage(s);
		}
	}

	public API() {
		getLogger().info("yaml path is " + YAMLCONFIG_PATH);
	}

	public void sendMessageToAll(Set<String> s) {
		sendMessageToAll((String[])(s.toArray()));
	}

	public void sendMessageToAll(String[] s) {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			p.sendMessage(s);
		}
	}

	public List<String> getTeiki() {
		return getYaml("teiki").getStringList("teiki");
	}

	public YamlConfiguration getYaml(File f) {
		if (f.exists()) {

		} else {
			f.mkdirs();
		}
		return YamlConfiguration.loadConfiguration(f);
	}

	public String getID(ItemStack is) {
	    return getID(is.getType());
    }

    public String getID(Material m) {
	    return m.toString();
    }

	/**
	 *
	 * @param s 読むファイルの名前。拡張子をつける必要はない。
	 * @return
	 */
	public YamlConfiguration getYaml(String s) {
		return getYaml(new File(YAMLCONFIG_PATH.replace("$1", s)));
	}

	public boolean command(Player p, String cmd) {
	    return p.performCommand(cmd);
    }

	public Math getMathLib() {
		return new Math();
	}

	public String getUID(Player p) {
		return p.getUniqueId().toString();
	}

	public Player searchPlayer(String name) {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}



	public class Math {
		public int rnd(int min, int max) throws IllegalArgumentException {
			if (min > max) {
				throw new IllegalArgumentException();
			} else if (min == max) {
				return min;
			} else {
				Random r = new Random();
				return r.nextInt(max-min) + min;
			}
		}
	}


}
