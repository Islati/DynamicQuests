package com.caved_in.dynamicquests;

import com.caved_in.dynamicquests.listeners.EntityListeners;
import com.caved_in.dynamicquests.npctrait.QuestNPC;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.TraitInfo;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class DynamicQuests extends JavaPlugin {
	@Override
	public void onEnable() {
		traitRegister(); //Register the NPC traits for this plugin;
		registerListeners(); //Register the listeners for this plugin
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
		return (DynamicQuests) Bukkit.getPluginManager().getPlugin("DynamicEvents");
	}
}
