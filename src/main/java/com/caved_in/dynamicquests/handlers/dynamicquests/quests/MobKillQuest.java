package com.caved_in.dynamicquests.handlers.dynamicquests.quests;

import com.caved_in.dynamicquests.handlers.dynamicquests.DynamicQuestType;
import com.caved_in.dynamicquests.handlers.dynamicquests.quests.interfaces.IMobQuest;
import com.caved_in.dynamicquests.handlers.entity.QuestEntityWrapper;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.*;

public class MobKillQuest implements IMobQuest
{
	private UUID eventID;
	private int eventBeginNpc = 0;
	private int eventFinishNpc = 0;
	private Map<EntityType, Integer> eventEntities = new HashMap<EntityType,Integer>();
	private QuestEntityWrapper firstWrapper = null;
	private Location mobLocationCenter = null;
	private double mobKillRadius = 0.0;
	private boolean isLocationSpecific = false;

	public MobKillQuest(UUID eventID)
	{
		this.eventID = eventID;
	}

	public MobKillQuest(UUID eventID, int eventBeginNpc)
	{
		this.eventID = eventID;
		this.eventBeginNpc = eventBeginNpc;
		this.eventFinishNpc = eventBeginNpc;
	}

	public MobKillQuest(UUID eventID, int eventBeginNpc, Location killLocation, double killRadius)
	{
		this.eventID = eventID;
		this.eventBeginNpc = eventBeginNpc;
		this.eventFinishNpc = eventBeginNpc;
		this.mobLocationCenter = killLocation;
		this.mobKillRadius = killRadius;
	}

	@Override
	public List<QuestEntityWrapper> getEntityData()
	{
		List<QuestEntityWrapper> entityWrappers = new ArrayList<QuestEntityWrapper>();
		for(Map.Entry<EntityType, Integer> entityData : this.eventEntities.entrySet())
		{
			entityWrappers.add(new QuestEntityWrapper(entityData.getKey(),entityData.getValue()));
		}
		return entityWrappers;
	}

	@Deprecated
	public QuestEntityWrapper getFirstEntityWrapper()
	{
		return this.firstWrapper;
	}

	@Override
	public void setEntityWrappers(List<QuestEntityWrapper> entityWrappers)
	{
		for(QuestEntityWrapper entityWrapper : entityWrappers)
		{
			this.eventEntities.put(entityWrapper.getEntityType(),entityWrapper.getEntityAmount());
		}
	}

	@Override
	public void addEntityWrapper(QuestEntityWrapper entityData)
	{
		if (this.eventEntities.size() <= 0)
		{
			this.firstWrapper = entityData;
		}
		this.eventEntities.put(entityData.getEntityType(),entityData.getEntityAmount());
	}

	@Override
	public Location getKillLocation()
	{
		return this.mobLocationCenter;
	}

	@Override
	public double getKillLocationRadius()
	{
		return this.mobKillRadius;
	}

	@Override
	public int getEventFinishNpcID()
	{
		return this.eventFinishNpc;
	}

	@Override
	public UUID getQuestID()
	{
		return this.eventID;
	}

	@Override
	public DynamicQuestType getQuestType()
	{
		return DynamicQuestType.KILL_MOB;
	}

	@Override
	public int getQuestBeginNpc()
	{
		return this.eventBeginNpc;
	}

	@Override
	public boolean isLocationSpecific()
	{
		return this.isLocationSpecific;
	}
}
