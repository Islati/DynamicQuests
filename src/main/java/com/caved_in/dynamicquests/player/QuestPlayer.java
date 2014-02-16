package com.caved_in.dynamicquests.player;


import com.caved_in.dynamicquests.dynamicquests.DynamicQuestHandler;
import com.caved_in.dynamicquests.dynamicquests.DynamicQuestType;
import com.caved_in.dynamicquests.dynamicquests.quests.CollectQuest;
import com.caved_in.dynamicquests.dynamicquests.quests.MobKillQuest;
import com.caved_in.dynamicquests.dynamicquests.quests.QuestProgress;
import com.caved_in.dynamicquests.dynamicquests.quests.IDynamicQuest;
import org.bukkit.entity.EntityType;

import java.util.*;

public class QuestPlayer {
	private String playerName = "";
	private Map<UUID, QuestProgress> activeQuestsProgress = new HashMap<UUID, QuestProgress>();

	public QuestPlayer(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * Check if a player is currently on the given quest
	 *
	 * @param dynamicQuest
	 * @return
	 */
	public boolean isPlayerOnQuest(IDynamicQuest dynamicQuest) {
		return isPlayerOnQuest(dynamicQuest.getQuestID());
	}

	/**
	 * Check if a player is currently on the quest with the given UUID
	 *
	 * @param dynamicQuestID
	 * @return
	 */
	public boolean isPlayerOnQuest(UUID dynamicQuestID) {
		return activeQuestsProgress.containsKey(dynamicQuestID);
	}

	/**
	 * Start the player on the quest with the given UUID
	 *
	 * @param questID
	 */
	public void addQuest(UUID questID) {
		if (!isPlayerOnQuest(questID)) {
			activeQuestsProgress.put(questID, new QuestProgress(playerName, questID));
		}
	}

	/**
	 * Start the player on the given quest
	 *
	 * @param dynamicQuest
	 */
	public void addQuest(IDynamicQuest dynamicQuest) {
		if (!isPlayerOnQuest(dynamicQuest)) {
			activeQuestsProgress.put(dynamicQuest.getQuestID(), new QuestProgress(playerName,
					dynamicQuest.getQuestID()));
		}
	}

	public void removeQuest(UUID questID) {
		if (isPlayerOnQuest(questID)) {
			activeQuestsProgress.remove(questID);
		}
	}

	public void removeQuest(IDynamicQuest dynamicQuest) {
		removeQuest(dynamicQuest.getQuestID());
	}

	public List<UUID> getQuestIds() {
		return new ArrayList<UUID>(activeQuestsProgress.keySet());
	}

	/**
	 * Get the instanced players name
	 *
	 * @return
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Get a list of all the active Mob kill quests the player is on
	 *
	 * @return
	 */
	public List<MobKillQuest> getActiveMobKillQuests() {
		List<MobKillQuest> mobKillQuests = new ArrayList<>();
		for (UUID questID : getQuestIds()) {
			if (DynamicQuestHandler.getQuestType(questID) == DynamicQuestType.KILL_MOB) {
				mobKillQuests.add(DynamicQuestHandler.getMobKillQuest(questID));
			}
		}
		return mobKillQuests;
	}

	/**
	 * Get a list of the active mob kill quests a player is on with the specified type
	 *
	 * @param entityType
	 * @return
	 */
	public List<MobKillQuest> getActiveMobKillQuests(EntityType entityType) {
		List<MobKillQuest> mobKillQuests = new ArrayList<>();
		for (UUID questID : getQuestIds()) {
			if (DynamicQuestHandler.getQuestType(questID) == DynamicQuestType.KILL_MOB) {
				MobKillQuest mobKillQuest = DynamicQuestHandler.getMobKillQuest(questID);
				if (mobKillQuest.getEntityData().getEntityType() == entityType) {
					mobKillQuests.add(DynamicQuestHandler.getMobKillQuest(questID));
				}
			}
		}
		return mobKillQuests;
	}

	/**
	 * Get a collection of the active CollectQuests a player is embarked on
	 *
	 * @return
	 */
	public List<CollectQuest> getActiveCollectionQuests() {
		List<CollectQuest> collectQuests = new ArrayList<>();
		for (UUID questID : getQuestIds()) {

		}
		return collectQuests;
	}

	/**
	 * Get the players progress in the given quest
	 *
	 * @param questID
	 * @return
	 */
	public QuestProgress getQuestProgress(UUID questID) {
		if (isPlayerOnQuest(questID)) {
			return activeQuestsProgress.get(questID);
		}
		return null;
	}

	/**
	 * Update a players quest progress to whatever's in the given quest progress
	 *
	 * @param questProgress
	 */
	public void updateQuestProgress(QuestProgress questProgress) {
		if (isPlayerOnQuest(questProgress.getQuestID())) {
			activeQuestsProgress.put(questProgress.getQuestID(), questProgress);
		}
	}
}
