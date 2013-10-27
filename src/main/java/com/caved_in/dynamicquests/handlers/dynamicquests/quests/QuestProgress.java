package com.caved_in.dynamicquests.handlers.dynamicquests.quests;

import com.caved_in.dynamicquests.handlers.dynamicquests.DynamicQuestHandler;
import com.caved_in.dynamicquests.handlers.dynamicquests.DynamicQuestType;

import java.util.UUID;

public class QuestProgress
{
	private String playerName = "";
	private UUID questID;
	private int playerProgress = 0;
	private DynamicQuestType questType;
	private boolean hasBeenRewarded = false;

	public QuestProgress(String playerName, UUID questID)
	{
		this.playerName = playerName;
		this.questID = questID;
		this.questType = DynamicQuestHandler.getQuestType(questID);
	}

	public DynamicQuestType getQuestType()
	{
		return questType;
	}

	public int getPlayerProgress()
	{
		return playerProgress;
	}

	public UUID getQuestID()
	{
		return questID;
	}

	public String getPlayerName()
	{
		return playerName;
	}

	public void addPlayerProgress(int progressAmount)
	{
		this.playerProgress += progressAmount;
	}

	public boolean hasBeenRewarded()
	{
		return this.hasBeenRewarded;
	}

	public void setHasBeenRewarded(boolean hasBeenRewarded)
	{
		this.hasBeenRewarded = hasBeenRewarded;
	}
}
