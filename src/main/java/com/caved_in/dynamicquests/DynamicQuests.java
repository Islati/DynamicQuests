package com.caved_in.dynamicquests;

import com.caved_in.commons.Commons;
import com.caved_in.dynamicquests.configuration.Configuration;
import com.caved_in.dynamicquests.listeners.EntityListeners;
import com.caved_in.dynamicquests.npctrait.QuestNPC;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.TraitInfo;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;


public class DynamicQuests extends JavaPlugin {
	private static DynamicQuests dynamicQuests = null;
    private static Configuration configuration;
    private static final String PLUGIN_DATA_FOLDER = "plugin/DynamicQuests/";

	@Override
	public void onEnable() {
        if (initConfig()) {
            Commons.messageConsole("Configuration loaded for Dynamic Quests");
            //Register the NPC traits for this plugin;
            traitRegister();
            //Register the listeners for this plugin
            registerListeners();
            //Create the singleton instance of our dynamic events plugin
            dynamicQuests = getPlugin();
        } else {
            //Disable the Dynamic quests plugin if the config failed to init
            Commons.messageConsole("Disabling Dynamic Quests as the config failed to initialize");
            getPluginLoader().disablePlugin(this);
        }
	}

    private boolean initConfig() {
        Serializer configSerializer = new Persister();
        try {
            File configFile = new File(PLUGIN_DATA_FOLDER + "Config.xml");
            if (!configFile.exists()) {
                configSerializer.write(new Configuration(), configFile);
            }
            configuration = configSerializer.read(Configuration.class, configFile);
            return true;
        } catch (Exception Ex) {
            Ex.printStackTrace();
            return false;
        }
    }

	private void traitRegister() {
		CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(QuestNPC.class).withName("questnpc"));
	}

	private void registerListeners() {
		new EntityListeners(this);
	}

	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
	}

	public static DynamicQuests getPlugin() {
		if (dynamicQuests == null) {
			dynamicQuests = (DynamicQuests) Bukkit.getPluginManager().getPlugin("DynamicEvents");
		}
		return dynamicQuests;
	}

    public static Configuration getConfiguration() {
        return configuration;
    }
}
