package com.caved_in.dynamicquests.dynamicquests.quests;

import com.caved_in.dynamicquests.dynamicquests.DynamicQuestHandler;
import com.caved_in.dynamicquests.dynamicquests.DynamicQuestType;

import java.util.UUID;

public class QuestProgress {
	private String playerName = "";
	private UUID questID;
	private int playerProgress = 0;
	private int amountRequired = 0;
	private DynamicQuestType questType;
	private boolean hasBeenRewarded = false;

	public QuestProgress(String playerName, UUID questID) {
		this.playerName = playerName;
		this.questID = questID;
		this.questType = DynamicQuestHandler.getQuestType(questID);
		switch (this.questType) {
			case KILL_MOB:
				this.amountRequired = DynamicQuestHandler.getMobKillQuest(questID).getEntityData().getEntityAmount();
				break;
			case DELIVER_GOODS:
				this.amountRequired = DynamicQuestHandler.getDeliverQuest(questID).getQuestMaterial().getQuestMaterialAmount();
				break;
			case REACH_LOCATION:
				break;
			case KILL_PLAYER:
				break;
			case GATHER_MATERIAL:
				this.amountRequired = DynamicQuestHandler.getCollectQuest(questID).getQuestMaterial().getQuestMaterialAmount();
				break;
			case NO_QUEST:
				break;
			default:
				break;
		}
	}

	public DynamicQuestType getQuestType() {
		return questType;
	}

	public int getPlayerProgress() {
		return playerProgress;
	}

	public UUID getQuestID() {
		return questID;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void addPlayerProgress(int progressAmount) {
		this.playerProgress += progressAmount;
	}

	public boolean hasBeenRewarded() {
		return this.hasBeenRewarded;
	}

	public void setHasBeenRewarded(boolean hasBeenRewarded) {
		this.hasBeenRewarded = hasBeenRewarded;
	}

	public int getAmountRequired() {
		return amountRequired;
	}

	public void setAmountRequired(int amountRequired) {
		this.amountRequired = amountRequired;
	}
}
