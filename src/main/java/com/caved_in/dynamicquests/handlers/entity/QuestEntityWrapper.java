package com.caved_in.dynamicquests.handlers.entity;

import org.bukkit.entity.EntityType;

public class QuestEntityWrapper
{
	private EntityType entityType = EntityType.UNKNOWN;
	private int entityAmount = 1;

	public QuestEntityWrapper(EntityType entityType, int entityAmount)
	{
		this.entityType = entityType;
		this.entityAmount = entityAmount;
	}

	public int getEntityAmount()
	{
		return entityAmount;
	}

	public void setEntityAmount(int entityAmount)
	{
		this.entityAmount = entityAmount;
	}

	public EntityType getEntityType()
	{
		return entityType;
	}

	public void setEntityType(EntityType entityType)
	{
		this.entityType = entityType;
	}
}
