package com.caved_in.dynamicquests.handlers.dynamicquests;

import com.caved_in.dynamicquests.handlers.dynamicquests.quests.CollectQuest;
import com.caved_in.dynamicquests.handlers.dynamicquests.quests.MobKillQuest;
import com.caved_in.dynamicquests.handlers.dynamicquests.quests.MobQuestTier;
import com.caved_in.dynamicquests.handlers.entity.QuestEntityWrapper;
import com.caved_in.dynamicquests.handlers.material.QuestMaterial;
import com.caved_in.dynamicquests.handlers.material.QuestMaterialType;
import com.caved_in.dynamicquests.handlers.utility.NumberUtil;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.material.MaterialData;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class QuestGenerator {
	//Initialization of quest materials (blocks) and their maximum limit
	private static List<QuestMaterial> questMaterialBlocks = Arrays.asList(
			new QuestMaterial(new MaterialData(Material.STONE), 64),
			new QuestMaterial(new MaterialData(Material.COBBLESTONE), 64),
			new QuestMaterial(new MaterialData(Material.CHEST), 28),
			new QuestMaterial(new MaterialData(Material.ANVIL), 7),
			new QuestMaterial(new MaterialData(Material.IRON_BLOCK), 12),
			new QuestMaterial(new MaterialData(Material.BOOKSHELF), 28),
			new QuestMaterial(new MaterialData(Material.DIRT), 64),
			new QuestMaterial(new MaterialData(Material.DISPENSER), 28),
			new QuestMaterial(new MaterialData(Material.LOG), 64),
			new QuestMaterial(new MaterialData(Material.LOG, (byte) 1), 48),
			new QuestMaterial(new MaterialData(Material.LOG, (byte) 2), 48),
			new QuestMaterial(new MaterialData(Material.LOG, (byte) 3), 48),
			new QuestMaterial(new MaterialData(Material.CAULDRON_ITEM), 3),
			new QuestMaterial(new MaterialData(Material.BREWING_STAND_ITEM), 6),
			new QuestMaterial(new MaterialData(Material.WOOD), 64),
			new QuestMaterial(new MaterialData(Material.WOOD, (byte) 1), 64),
			new QuestMaterial(new MaterialData(Material.WOOD, (byte) 2), 64),
			new QuestMaterial(new MaterialData(Material.WOOD, (byte) 3), 64),
			new QuestMaterial(new MaterialData(Material.COBBLESTONE_STAIRS), 16),
			new QuestMaterial(new MaterialData(Material.COBBLE_WALL), 16),
			new QuestMaterial(new MaterialData(Material.DROPPER), 28),
			new QuestMaterial(new MaterialData(Material.WOOD_STAIRS), 16),
			new QuestMaterial(new MaterialData(Material.BIRCH_WOOD_STAIRS), 16),
			new QuestMaterial(new MaterialData(Material.JUNGLE_WOOD_STAIRS), 16),
			new QuestMaterial(new MaterialData(Material.SPRUCE_WOOD_STAIRS), 16),
			new QuestMaterial(new MaterialData(Material.COAL_BLOCK), 8),
			new QuestMaterial(new MaterialData(Material.ENDER_STONE), 28),
			new QuestMaterial(new MaterialData(Material.QUARTZ_BLOCK), 12)
	);

	//Initialization of quest materials (Items) and their maximum limit
	private static List<QuestMaterial> questMaterialItems = Arrays.asList(
			new QuestMaterial(new MaterialData(Material.COAL), 64),
			new QuestMaterial(new MaterialData(Material.BLAZE_ROD), 18),
			new QuestMaterial(new MaterialData(Material.BLAZE_POWDER), 48),
			new QuestMaterial(new MaterialData(Material.IRON_INGOT), 48),
			new QuestMaterial(new MaterialData(Material.GOLD_INGOT), 48),
			new QuestMaterial(new MaterialData(Material.APPLE), 28),
			new QuestMaterial(new MaterialData(Material.ARROW), 64),
			new QuestMaterial(new MaterialData(Material.COOKED_BEEF), 17),
			new QuestMaterial(new MaterialData(Material.COOKED_CHICKEN), 17),
			new QuestMaterial(new MaterialData(Material.COOKED_FISH), 17),
			new QuestMaterial(new MaterialData(Material.COOKIE), 17),
			new QuestMaterial(new MaterialData(Material.BONE), 28),
			new QuestMaterial(new MaterialData(Material.TORCH), 64),
			new QuestMaterial(new MaterialData(Material.REDSTONE), 64),
			new QuestMaterial(new MaterialData(Material.FEATHER), 16),
			new QuestMaterial(new MaterialData(Material.EGG), 20),
			new QuestMaterial(new MaterialData(Material.MELON), 16),
			new QuestMaterial(new MaterialData(Material.EMERALD), 8)
	);

	//Quest entities for tier: Easy
	private static List<QuestEntityWrapper> questEntitiesEasy = Arrays.asList(
			new QuestEntityWrapper(EntityType.BAT,8),
			//new QuestEntityWrapper(EntityType.BLAZE, 6),
			new QuestEntityWrapper(EntityType.CAVE_SPIDER, 8),
			new QuestEntityWrapper(EntityType.CHICKEN, 12),
			new QuestEntityWrapper(EntityType.COW, 12),
			new QuestEntityWrapper(EntityType.SPIDER, 8),
			new QuestEntityWrapper(EntityType.ENDERMAN, 4),
			//new QuestEntityWrapper(EntityType.GHAST, 3),
			//new QuestEntityWrapper(EntityType.MAGMA_CUBE, 4),
			new QuestEntityWrapper(EntityType.SKELETON, 8),
			new QuestEntityWrapper(EntityType.CREEPER, 8),
			new QuestEntityWrapper(EntityType.SLIME, 4),
			new QuestEntityWrapper(EntityType.SHEEP, 12),
			new QuestEntityWrapper(EntityType.PIG, 12),
			new QuestEntityWrapper(EntityType.OCELOT, 8)
	);

	private static List<QuestEntityWrapper> questEntitiesMedium = Arrays.asList(
			//new QuestEntityWrapper(EntityType.BLAZE, 6),
			new QuestEntityWrapper(EntityType.CAVE_SPIDER, 18),
			new QuestEntityWrapper(EntityType.SPIDER, 18),
			new QuestEntityWrapper(EntityType.ENDERMAN, 9),
			//new QuestEntityWrapper(EntityType.GHAST, 3),
			//new QuestEntityWrapper(EntityType.MAGMA_CUBE, 4),
			new QuestEntityWrapper(EntityType.SKELETON, 16),
			new QuestEntityWrapper(EntityType.CREEPER, 12),
			new QuestEntityWrapper(EntityType.SLIME, 8),
			new QuestEntityWrapper(EntityType.PIG_ZOMBIE, 8));

	private static List<QuestEntityWrapper> questEntitiesHard = Arrays.asList(
			new QuestEntityWrapper(EntityType.BLAZE, 16),
			new QuestEntityWrapper(EntityType.CAVE_SPIDER, 38),
			new QuestEntityWrapper(EntityType.SPIDER, 38),
			new QuestEntityWrapper(EntityType.ENDERMAN, 28),
			new QuestEntityWrapper(EntityType.GHAST, 11),
			new QuestEntityWrapper(EntityType.MAGMA_CUBE, 28),
			new QuestEntityWrapper(EntityType.SKELETON, 34),
			new QuestEntityWrapper(EntityType.CREEPER, 26),
			new QuestEntityWrapper(EntityType.PIG_ZOMBIE, 24),
			new QuestEntityWrapper(EntityType.GIANT, 4),
			new QuestEntityWrapper(EntityType.WITCH, 2)
	);

	private static List<QuestEntityWrapper> questEntitiesExtreme = Arrays.asList(
			new QuestEntityWrapper(EntityType.BLAZE, 16),
			new QuestEntityWrapper(EntityType.CAVE_SPIDER, 52),
			new QuestEntityWrapper(EntityType.SPIDER, 52),
			new QuestEntityWrapper(EntityType.ENDERMAN, 38),
			new QuestEntityWrapper(EntityType.GHAST, 16),
			new QuestEntityWrapper(EntityType.MAGMA_CUBE, 34),
			new QuestEntityWrapper(EntityType.SKELETON, 46),
			new QuestEntityWrapper(EntityType.CREEPER, 38),
			new QuestEntityWrapper(EntityType.PIG_ZOMBIE, 46),
			new QuestEntityWrapper(EntityType.GIANT, 12),
			new QuestEntityWrapper(EntityType.WITCH, 8)
	);

	/**
	 * Generate a new Collection quest
	 *
	 * @return
	 */
	public static CollectQuest generateCollectQuest(int npcQuestHolder) {
		CollectQuest collectQuest = new CollectQuest(UUID.randomUUID(), npcQuestHolder);
		collectQuest.setQuestMaterial(generateQuestMaterial());
		return collectQuest;
	}

	/**
	 * Generate a random quest material with a random type
	 *
	 * @return
	 */
	private static QuestMaterial generateQuestMaterial() {
		return (new Random().nextInt(101) <= 50 ? generateQuestMaterial(QuestMaterialType.BLOCK) :
				generateQuestMaterial(QuestMaterialType.ITEM));
	}

	/**
	 * Generate a random quest material of the specified type
	 *
	 * @param materialType
	 * @return
	 */
	private static QuestMaterial generateQuestMaterial(QuestMaterialType materialType) {
		QuestMaterial questMaterial = null;
		switch (materialType) {
			case BLOCK:
				questMaterial = getQuestMaterialBlock();
				questMaterial.setQuestMaterialAmount(getRandomMaterialAmount(questMaterial));
				break;
			case ITEM:
				questMaterial = getQuestMaterialItem();
				questMaterial.setQuestMaterialAmount(getRandomMaterialAmount(questMaterial));
				break;
			default:
				break;
		}
		return questMaterial;
	}

	/**
	 * Get a random number within the range of the given quest material
	 * to be used in assigning a quest a material
	 *
	 * @param questMaterial
	 * @return
	 */
	private static int getRandomMaterialAmount(QuestMaterial questMaterial) {
		return NumberUtil.getRandomInRange(1, questMaterial.getQuestMaterialAmount());
	}

	/**
	 * Pull a random QuestMaterial element from the list of questMaterial Item
	 *
	 * @return
	 */
	private static QuestMaterial getQuestMaterialItem() {
		return questMaterialItems.get(new Random(System.currentTimeMillis()).nextInt(questMaterialItems.size()));
	}

	/**
	 * Pull a random QuestMaterial element from the list of questMaterial block
	 *
	 * @return
	 */
	private static QuestMaterial getQuestMaterialBlock() {
		return questMaterialBlocks.get(new Random(System.currentTimeMillis()).nextInt(questMaterialItems.size()));
	}

	private static QuestEntityWrapper getRandomQuestEntity(List<QuestEntityWrapper> questEntities) {
		QuestEntityWrapper questEntity = questEntities.get(new Random().nextInt(questEntities.size()));
		questEntity.setEntityAmount(NumberUtil.getRandomInRange(questEntity.getEntityAmount() / 2,
				questEntity.getEntityAmount()));
		return questEntity;
	}

	private static QuestEntityWrapper getRandomQuestEntity(MobQuestTier questTier) {
		switch (questTier) {
			case EASY:
				return getRandomQuestEntity(questEntitiesEasy);
			case MEDIUM:
				return getRandomQuestEntity(questEntitiesMedium);
			case HARD:
				return getRandomQuestEntity(questEntitiesHard);
			case EXTREME:
				return getRandomQuestEntity(questEntitiesExtreme);
			default:
				return getRandomQuestEntity(questEntitiesMedium);
		}
	}

	public static MobKillQuest generateMobKillQuest(int questHolderNPC) {
		MobKillQuest mobKillQuest = new MobKillQuest(UUID.randomUUID(), questHolderNPC);
		mobKillQuest.setEntityWrapper(getRandomQuestEntity(MobQuestTier.EASY));
		return mobKillQuest;
	}

	public static MobKillQuest generateMobKillQuest(int questHolderNPC, MobQuestTier questTier) {
		MobKillQuest mobKillQuest = new MobKillQuest(UUID.randomUUID(), questHolderNPC);
		mobKillQuest.setEntityWrapper(getRandomQuestEntity(questTier));
		return mobKillQuest;
	}
}
