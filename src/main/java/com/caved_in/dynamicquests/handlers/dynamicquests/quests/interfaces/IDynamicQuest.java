package com.caved_in.dynamicquests.handlers.dynamicquests.quests.interfaces;

import com.caved_in.dynamicquests.handlers.dynamicquests.DynamicQuestType;

import java.util.UUID;

public interface IDynamicQuest {

	public UUID getQuestID();

	public DynamicQuestType getQuestType();

	public int getQuestBeginNpc();
}
