package com.caved_in.dynamicquests.handlers.player;

import com.caved_in.dynamicquests.handlers.dynamicquests.quests.QuestProgress;
import com.caved_in.dynamicquests.handlers.dynamicquests.quests.interfaces.*;

import java.util.*;

public class QuestPlayer
{
	private String playerName = "";
	private Map<UUID, QuestProgress> activeQuestsProgress = new HashMap<UUID, QuestProgress>();

	public QuestPlayer(String playerName)
	{
		this.playerName = playerName;
	}

	/**
	 * Check if a player is currently on the given quest
	 * @param dynamicQuest
	 * @return
	 */
	public boolean isPlayerOnQuest(IDynamicQuest dynamicQuest)
	{
		return isPlayerOnQuest(dynamicQuest.getQuestID());
	}

	/**
	 * Check if a player is currently on the quest with the given UUID
	 * @param dynamicQuestID
	 * @return
	 */
	public boolean isPlayerOnQuest(UUID dynamicQuestID)
	{
		return activeQuestsProgress.containsKey(dynamicQuestID);
	}

	/**
	 * Start the player on the quest with the given UUID
	 * @param questID
	 */
	public void addQuest(UUID questID)
	{
		if (!isPlayerOnQuest(questID))
		{
			activeQuestsProgress.put(questID,new QuestProgress(playerName,questID));
		}
	}

	/**
	 * Start the player on the given quest
	 * @param dynamicQuest
	 */
	public void addQuest(IDynamicQuest dynamicQuest)
	{
		if (!isPlayerOnQuest(dynamicQuest))
		{
			activeQuestsProgress.put(dynamicQuest.getQuestID(), new QuestProgress(playerName,dynamicQuest.getQuestID()));
		}
	}

	public void removeQuest(IDynamicQuest dynamicQuest)
	{
		if (isPlayerOnQuest(dynamicQuest))
		{
			activeQuestsProgress.remove(dynamicQuest.getQuestID());
		}
	}

	/**
	 * Get the instanced players name
	 * @return
	 */
	public String getPlayerName()
	{
		return playerName;
	}
}
