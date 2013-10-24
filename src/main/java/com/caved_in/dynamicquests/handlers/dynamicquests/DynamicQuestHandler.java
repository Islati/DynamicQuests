package com.caved_in.dynamicquests.handlers.dynamicquests;

import com.caved_in.dynamicquests.handlers.dynamicquests.quests.CollectQuest;
import com.caved_in.dynamicquests.handlers.dynamicquests.quests.DeliverQuest;
import com.caved_in.dynamicquests.handlers.dynamicquests.quests.MobKillQuest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DynamicQuestHandler
{
	private static Map<UUID, DynamicQuestType> activeQuestTypes = new HashMap<UUID, DynamicQuestType>();
	private static Map<UUID, DeliverQuest> activeDeliveryQuests = new HashMap<UUID, DeliverQuest>();
	private static Map<UUID, MobKillQuest> activeMobKillQuests = new HashMap<UUID, MobKillQuest>();
	private static Map<UUID, CollectQuest> activeCollectQuests = new HashMap<UUID, CollectQuest>();

	public static DynamicQuestType getQuestType(UUID questID)
	{
		if (isActiveQuest(questID))
		{
			return activeQuestTypes.get(questID);
		}
		return DynamicQuestType.NO_QUEST;
	}

	public static DeliverQuest getDeliverQuest(UUID questID)
	{
		if (isActiveQuest(DynamicQuestType.DELIVER_GOODS, questID))
		{
			return activeDeliveryQuests.get(questID);
		}
		return null;
	}

	public static MobKillQuest getMobKillQuest(UUID questID)
	{
		if (isActiveQuest(DynamicQuestType.KILL_MOB, questID))
		{
			return activeMobKillQuests.get(questID);
		}
		return null;
	}

	public static CollectQuest getCollectQuest(UUID questID)
	{
		if (isActiveQuest(DynamicQuestType.GATHER_MATERIAL, questID))
		{
			return activeCollectQuests.get(questID);
		}
		return null;
	}

	public static boolean isActiveQuest(DynamicQuestType questType, UUID questID)
	{
		switch (questType)
		{

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

	public static boolean isActiveQuest(UUID uniqueID)
	{
		return activeQuestTypes.containsKey(uniqueID);
	}

	public static void addDeliverQuest(DeliverQuest deliverQuest)
	{
		activeQuestTypes.put(deliverQuest.getEventID(),deliverQuest.getEventType());
		activeDeliveryQuests.put(deliverQuest.getEventID(), deliverQuest);
	}

	public static void addMobKillQuest(MobKillQuest killQuest)
	{
		activeQuestTypes.put(killQuest.getEventID(),killQuest.getEventType());
		activeMobKillQuests.put(killQuest.getEventID(),killQuest);
	}

	public static void addCollectQuest(CollectQuest collectQuest)
	{
		activeQuestTypes.put(collectQuest.getEventID(), collectQuest.getEventType());
		activeCollectQuests.put(collectQuest.getEventID(), collectQuest);
	}
}
