package com.caved_in.dynamicquests.handlers.dynamicquests;

import com.caved_in.dynamicquests.handlers.dynamicquests.quests.CollectQuest;
import com.caved_in.dynamicquests.handlers.dynamicquests.quests.DeliverQuest;
import com.caved_in.dynamicquests.handlers.dynamicquests.quests.MobKillQuest;
import com.caved_in.dynamicquests.handlers.dynamicquests.quests.interfaces.IDynamicQuest;
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
	private static Map<UUID, IDynamicQuest> activeQuests = new HashMap<>();

	private static Map<UUID, DynamicQuestType> activeQuestTypes = new HashMap<UUID, DynamicQuestType>();
	private static Map<UUID, DeliverQuest> activeDeliveryQuests = new HashMap<UUID, DeliverQuest>();
	private static Map<UUID, MobKillQuest> activeMobKillQuests = new HashMap<UUID, MobKillQuest>();
	private static Map<UUID, CollectQuest> activeCollectQuests = new HashMap<UUID, CollectQuest>();

	//Map of all the quests assigned to NPC's
	private static Map<Integer, UUID> questsAssigned = new HashMap<>();

	public static boolean isNpcInUse(int npcID) {
		return questsAssigned.containsKey(npcID);
	}

	public static IDynamicQuest getQuestForNpc(NPC npc) {
		return getQuestForNPC(npc.getId());
	}

	public static IDynamicQuest getQuestForNPC(int npcID) {
		//Get the active quest with the UUID that's been assigned to the requested NPC
		return activeQuests.get(questsAssigned.get(npcID));
	}

	public static IDynamicQuest getQuest(UUID questID) {
		return activeQuests.get(questID);
	}

	/**
	 * Get the Type of the quest for the given questID
	 *
	 * @param questID
	 * @return The quests type if the quest if an active quest, otherwise the type NO_QUEST
	 */
	public static DynamicQuestType getQuestType(UUID questID) {
		if (isActiveQuest(questID)) {
			return activeQuests.get(questID).getQuestType();
		}
		return DynamicQuestType.NO_QUEST;
	}

	public static DeliverQuest getDeliverQuest(UUID questID) {
		if (getQuestType(questID) == DynamicQuestType.DELIVER_GOODS) {
			return (DeliverQuest)getQuest(questID);
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
		//Check that the quest UUID given is active
		if (activeQuests.containsKey(questID)) {
			//Check that the quest with the given UUID is of the active type.
			return activeQuests.get(questID).getQuestType() == questType;
		} else {
			//The quest ID given isn't an active quest.
			return false;
		}
	}

	public static boolean isActiveQuest(UUID uniqueID) {
		return activeQuests.containsKey(uniqueID);
	}

	public static void addQuest(IDynamicQuest quest) {
		UUID questID = quest.getQuestID();
		activeQuests.put(questID, quest);
		questsAssigned.put(quest.getQuestBeginNpc(), questID);
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
				//How many of the required items the player has
				int playerMaterialAmount = 0;
				//How many, if any, we should give back
				int itemRemainder = 0;
				//The amount of items required by the quest
				int questRequiredAmount = questWrapper.getQuestMaterialAmount();
				//The material required by the quest
				MaterialData questMaterial = questWrapper.getQuestMaterial();

				//Get all the items in the players inventory required by the quest
				Map<Integer, ? extends ItemStack> playerItems = player.getInventory().all(questMaterial.getItemType());

				//Map of ONLY the items we want to pass to the requirements wrapper
				Map<Integer, ItemStack> itemStacksToRemove = new HashMap<>();

				for (Map.Entry<Integer, ? extends ItemStack> inventoryItem : playerItems.entrySet()) {
					//Get the actual itemstack
					ItemStack itemStack = inventoryItem.getValue();
					//If they don't already have the required materials
					if (playerMaterialAmount < questRequiredAmount) {
						//Check the durability on the item
						if ((byte) itemStack.getDurability() == questMaterial.getData()) {
							playerMaterialAmount += itemStack.getAmount();
							itemStacksToRemove.put(inventoryItem.getKey(), inventoryItem.getValue());
						}
					} else {
						//Set the item remainder incase we need to give items back to the player
						itemRemainder = (playerMaterialAmount > questRequiredAmount ? playerMaterialAmount -
								questRequiredAmount : 0);
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
