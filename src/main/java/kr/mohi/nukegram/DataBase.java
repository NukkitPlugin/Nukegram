package kr.mohi.nukegram;

import java.util.LinkedHashMap;

import cn.nukkit.utils.Config;

public class DataBase {
	// public Config config;
	private String token;
	public Main plugin;

	public DataBase(Main plugin) {
		this.plugin = plugin;
	}

	public void loadDB() {
		this.token = (String) (new Config(plugin.getDataFolder() + "/data.yml", Config.YAML,
				new LinkedHashMap<String, Object>() {
					{
						put("Token", null);
					}
				})).get("Token");
		/*
		 * this.config = new Config(plugin.getDataFolder() + "/config.yml",
		 * Config.YAML, new LinkedHashMap<String, Object>() { { put(""); } });
		 */
	}

	public String getToken() {
		return this.token;
	}
}
