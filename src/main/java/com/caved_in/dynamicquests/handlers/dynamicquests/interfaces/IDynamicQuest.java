package com.caved_in.dynamicquests.handlers.dynamicquests.interfaces;

import com.caved_in.dynamicquests.handlers.dynamicquests.DynamicQuestType;

import java.util.UUID;

public interface IDynamicQuest
{

	public UUID getEventID();

	public DynamicQuestType getEventType();

	public int getEventNpc();
}
