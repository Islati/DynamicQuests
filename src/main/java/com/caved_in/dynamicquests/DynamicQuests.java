package com.caved_in.dynamicquests;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DynamicQuests extends JavaPlugin
{
	@Override
	public void onEnable()
	{

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
