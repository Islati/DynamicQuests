package com.caved_in.dynamicquests.handlers.dynamicquests;

import com.caved_in.dynamicquests.handlers.dynamicquests.quests.CollectQuest;
import com.caved_in.dynamicquests.handlers.dynamicquests.quests.DeliverQuest;
import com.caved_in.dynamicquests.handlers.dynamicquests.quests.MobKillQuest;
import com.caved_in.dynamicquests.handlers.material.QuestItemRequirementWrapper;
import com.caved_in.dynamicquests.handlers.material.QuestMaterial;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DynamicQuestHandler {
	private static Map<UUID, DynamicQuestType> activeQuestTypes = new HashMap<UUID, DynamicQuestType>();
	private static Map<UUID, DeliverQuest> activeDeliveryQuests = new HashMap<UUID, DeliverQuest>();
	private static Map<UUID, MobKillQuest> activeMobKillQuests = new HashMap<UUID, MobKillQuest>();
	private static Map<UUID, CollectQuest> activeCollectQuests = new HashMap<UUID, CollectQuest>();
	private static Map<Integer, UUID> questsAssigned = new HashMap<>();

	public static boolean isNpcInUse(int npcID) {
		return questsAssigned.containsKey(npcID);
	}

	public static UUID getQuestIDForNPC(int npcID) {
		if (isNpcInUse(npcID)) {
			return questsAssigned.get(npcID);
		}
		return null;
	}

	public static UUID getQuestIDForNPC(NPC npc) {
		return getQuestIDForNPC(npc.getId());
	}

	public static DynamicQuestType getQuestType(UUID questID) {
		if (isActiveQuest(questID)) {
			return activeQuestTypes.get(questID);
		}
		return DynamicQuestType.NO_QUEST;
	}

	public static DeliverQuest getDeliverQuest(UUID questID) {
		if (isActiveQuest(DynamicQuestType.DELIVER_GOODS, questID)) {
			return activeDeliveryQuests.get(questID);
		}
		return null;
	}

	public static MobKillQuest getMobKillQuest(UUID questID) {
		if (isActiveQuest(DynamicQuestType.KILL_MOB, questID)) {
			return activeMobKillQuests.get(questID);
		}
		return null;
	}

	public static CollectQuest getCollectQuest(UUID questID) {
		if (isActiveQuest(DynamicQuestType.GATHER_MATERIAL, questID)) {
			return activeCollectQuests.get(questID);
		}
		return null;
	}

	public static boolean isActiveQuest(DynamicQuestType questType, UUID questID) {
		switch (questType) {

			case KILL_MOB:
				return activeMobKillQuests.containsKey(questID);
			case DELIVER_GOODS:
				return activeDeliveryQuests.containsKey(questID);
			case REACH_LOCATION:
				return false;
			case KILL_PLAYER:
				return false;
			case GATHER_MATERIAL:
				return activeCollectQuests.containsKey(questID);
			case NO_QUEST:
				return false;
			default:
				return false;
		}
	}

	public static boolean isActiveQuest(UUID uniqueID) {
		return activeQuestTypes.containsKey(uniqueID);
	}

	public static void addDeliverQuest(DeliverQuest deliverQuest) {
		activeQuestTypes.put(deliverQuest.getQuestID(), deliverQuest.getQuestType());
		activeDeliveryQuests.put(deliverQuest.getQuestID(), deliverQuest);
		questsAssigned.put(deliverQuest.getQuestBeginNpc(), deliverQuest.getQuestID());
	}

	public static void addMobKillQuest(MobKillQuest killQuest) {
		activeQuestTypes.put(killQuest.getQuestID(), killQuest.getQuestType());
		activeMobKillQuests.put(killQuest.getQuestID(), killQuest);
		questsAssigned.put(killQuest.getQuestBeginNpc(), killQuest.getQuestID());
	}

	public static void addCollectQuest(CollectQuest collectQuest) {
		activeQuestTypes.put(collectQuest.getQuestID(), collectQuest.getQuestType());
		activeCollectQuests.put(collectQuest.getQuestID(), collectQuest);
		questsAssigned.put(collectQuest.getQuestBeginNpc(), collectQuest.getQuestID());
	}

	/**
	 * Take all the required items from the player, and return any remaining items
	 * If and <b>ONLY</b> if they have the required amount
	 *
	 * @param questItemsWrapper
	 * @param player
	 */
	public static void removeQuestItems(QuestItemRequirementWrapper questItemsWrapper, Player player) {
		if (questItemsWrapper.hasRequiredAmount()) {
			for (Map.Entry<Integer, ? extends ItemStack> itemStacks : questItemsWrapper.getItemSlots().entrySet()) {
				player.getInventory().setItem(itemStacks.getKey(), null);
			}

			if (questItemsWrapper.getRemainderItems() > 0) {
				ItemStack itemStack = questItemsWrapper.getMaterialData().toItemStack(questItemsWrapper
						.getRemainderItems());
				player.getInventory().addItem(itemStack);
			}
			player.updateInventory();
		} else {
			player.sendMessage("YOU NO HAS ITEMS, GTFO BEECH");
		}
	}

	/**
	 * Get a requirements wrapper which holds the players data, if they have the items
	 * where the items are in their inventory, along with a remainder to put back if they have over the required
	 * amount in their inventory
	 *
	 * @param questID
	 * @param player
	 * @return
	 */
	public static QuestItemRequirementWrapper hasRequiredMaterials(UUID questID, Player player) {
		DynamicQuestType questType = getQuestType(questID);
		QuestItemRequirementWrapper questRequirementWrapper = null;
		if (questType == DynamicQuestType.DELIVER_GOODS || questType == DynamicQuestType.GATHER_MATERIAL) {
			QuestMaterial questWrapper = null; //Give it a null value to be safe
			//Get the materials required for the quest
			switch (questType) {
				case DELIVER_GOODS:
					questWrapper = getDeliverQuest(questID).getQuestMaterial();
					break;
				case GATHER_MATERIAL:
					questWrapper = getCollectQuest(questID).getQuestMaterial();
					break;
				default:
					break;
			}

			//Make sure we have the materials
			if (questWrapper != null) {
				int playerMaterialAmount = 0; //How many of the required items the player has
				int itemRemainder = 0; //How many, if any, we should give back
				int questRequiredAmount = questWrapper.getQuestMaterialAmount(); //The amount of items required by the
				// quest
				MaterialData questMaterial = questWrapper.getQuestMaterial(); //The material required by the quest

				Map<Integer, ? extends ItemStack> playerItems = player.getInventory().all(questMaterial.getItemType())
						; //Get all the items in the players inventory required by the quest

				Map<Integer, ItemStack> itemStacksToRemove = new HashMap<>(); //Map of ONLY the items we want to pass
				// to the requirements wrapper


				for (Map.Entry<Integer, ? extends ItemStack> inventoryItem : playerItems.entrySet()) {
					ItemStack itemStack = inventoryItem.getValue(); //Get the actual itemstack
					if (playerMaterialAmount < questRequiredAmount) //If they don't already have the required materials
					{
						if ((byte) itemStack.getDurability() == questMaterial.getData()) //Check the durability on the
						// item
						{
							playerMaterialAmount += itemStack.getAmount();
							itemStacksToRemove.put(inventoryItem.getKey(), inventoryItem.getValue());
						}
					} else {
						itemRemainder = (playerMaterialAmount > questRequiredAmount ? playerMaterialAmount -
								questRequiredAmount : 0); //Set the item remainder incase we need to give items back
								// to the player
						break;
					}
					questRequirementWrapper = new QuestItemRequirementWrapper(playerMaterialAmount,
							questRequiredAmount, itemStacksToRemove, questMaterial);
					questRequirementWrapper.setRemainderItems(itemRemainder);
				}
			}
		}
		return questRequirementWrapper;
	}
}
