package me.dubai.lunar.utils;

import java.io.File;

import me.dubai.lunar.Lunar;
import org.bukkit.plugin.Plugin;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Credits to RisasDev
 * Am a noob at configuration stuff because I usually don't make my stuff configurable.
 */

public class ConfigFile extends YamlConfiguration {

    private static ConfigFile config;
    private Plugin plugin;
    private File configFile;

    public static ConfigFile getConfig() {
        if (ConfigFile.config == null) {
            ConfigFile.config = new ConfigFile();
        }
        return ConfigFile.config;
    }

    private Plugin main() {
        return Lunar.getInstance();
    }

    public ConfigFile() {
        this.plugin = this.main();
        this.configFile = new File(this.plugin.getDataFolder(), "config.yml");
        this.saveDefault();
        this.reload();
    }

    public void reload() {
        try {
            super.load(this.configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            super.save(this.configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDefault() {
        this.plugin.saveResource("config.yml", false);
    }

    public void saveAll() {
        this.save();
        this.reload();
    }
}