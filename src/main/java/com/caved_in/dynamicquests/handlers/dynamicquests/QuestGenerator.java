package com.caved_in.dynamicquests.handlers.dynamicquests;

import com.caved_in.dynamicquests.handlers.dynamicquests.quests.CollectQuest;
import com.caved_in.dynamicquests.handlers.material.QuestMaterial;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class QuestGenerator
{
	//Initialization of quest materials (blocks) and their maximum limit
	private static List<QuestMaterial> questMaterialBlocks = Arrays.asList(
			new QuestMaterial(new MaterialData(Material.STONE),64),
			new QuestMaterial(new MaterialData(Material.COBBLESTONE), 64),
			new QuestMaterial(new MaterialData(Material.CHEST), 28),
			new QuestMaterial(new MaterialData(Material.ANVIL),7),
			new QuestMaterial(new MaterialData(Material.IRON_BLOCK),12),
			new QuestMaterial(new MaterialData(Material.BOOKSHELF), 28),
			new QuestMaterial(new MaterialData(Material.DIRT),64),
			new QuestMaterial(new MaterialData(Material.DISPENSER),28),
			new QuestMaterial(new MaterialData(Material.LOG),64),
			new QuestMaterial(new MaterialData(Material.LOG,(byte)1),48),
			new QuestMaterial(new MaterialData(Material.LOG,(byte)2),48),
			new QuestMaterial(new MaterialData(Material.LOG,(byte)3),48),
			new QuestMaterial(new MaterialData(Material.CAULDRON_ITEM),3),
			new QuestMaterial(new MaterialData(Material.BREWING_STAND_ITEM),6),
			new QuestMaterial(new MaterialData(Material.WOOD),64),
			new QuestMaterial(new MaterialData(Material.WOOD,(byte)1), 64),
			new QuestMaterial(new MaterialData(Material.WOOD,(byte)2), 64),
			new QuestMaterial(new MaterialData(Material.WOOD,(byte)3), 64),
			new QuestMaterial(new MaterialData(Material.COBBLESTONE_STAIRS), 16),
			new QuestMaterial(new MaterialData(Material.COBBLE_WALL), 16),
			new QuestMaterial(new MaterialData(Material.DROPPER),28),
			new QuestMaterial(new MaterialData(Material.WOOD_STAIRS),16),
			new QuestMaterial(new MaterialData(Material.BIRCH_WOOD_STAIRS),16),
			new QuestMaterial(new MaterialData(Material.JUNGLE_WOOD_STAIRS),16),
			new QuestMaterial(new MaterialData(Material.SPRUCE_WOOD_STAIRS),16),
			new QuestMaterial(new MaterialData(Material.COAL_BLOCK),8),
			new QuestMaterial(new MaterialData(Material.ENDER_STONE),28),
			new QuestMaterial(new MaterialData(Material.QUARTZ_BLOCK),12)
	);

	private static List<QuestMaterial> questMaterialItems = Arrays.asList(
			new QuestMaterial(new MaterialData(Material.COAL), 64),
			new QuestMaterial(new MaterialData(Material.BLAZE_ROD), 18),
			new QuestMaterial(new MaterialData(Material.BLAZE_POWDER), 48),
			new QuestMaterial(new MaterialData(Material.IRON_INGOT),48),
			new QuestMaterial(new MaterialData(Material.GOLD_INGOT), 48)
	);



	public static CollectQuest generateCollectQuest()
	{
		CollectQuest collectQuest = new CollectQuest(UUID.randomUUID())
				.
	}
}
