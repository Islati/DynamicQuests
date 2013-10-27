package com.caved_in.dynamicquests;

import com.caved_in.dynamicquests.npctrait.QuestNPC;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.TraitInfo;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DynamicQuests extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		traitRegister();
	}

	private void traitRegister()
	{
		CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(QuestNPC.class).withName("questnpc"));
	}

	@Override
	public void onDisable()
	{

	}

	public static DynamicQuests getDynamicEventPlugin()
	{
		return (DynamicQuests) Bukkit.getPluginManager().getPlugin("DynamicEvents");
	}
}
