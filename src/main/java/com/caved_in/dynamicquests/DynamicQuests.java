package com.caved_in.dynamicquests;

import com.caved_in.dynamicquests.listeners.EntityListeners;
import com.caved_in.dynamicquests.npctrait.QuestNPC;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.TraitInfo;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;


public class DynamicQuests extends JavaPlugin {
	private static DynamicQuests dynamicQuests = null;

	@Override
	public void onEnable() {
		//Register the NPC traits for this plugin;
		traitRegister();
		//Register the listeners for this plugin
		registerListeners();
		//Create the singleton instance of our dynamic events plugin
		dynamicQuests = getDynamicEventPlugin();
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

	public static DynamicQuests getDynamicEventPlugin() {
		if (dynamicQuests == null) {
			dynamicQuests = (DynamicQuests) Bukkit.getPluginManager().getPlugin("DynamicEvents");
		}
		return dynamicQuests;
	}
}
