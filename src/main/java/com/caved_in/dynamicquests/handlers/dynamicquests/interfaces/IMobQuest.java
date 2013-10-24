package com.caved_in.dynamicquests.handlers.dynamicquests.interfaces;

import com.caved_in.dynamicquests.handlers.entity.QuestEntityWrapper;
import org.bukkit.Location;

import java.util.List;

public interface IMobQuest extends IDynamicQuest
{
	public List<QuestEntityWrapper> getEntityData();

	public void setEntityWrappers(List<QuestEntityWrapper> entityWrappers);

	public void addEntityWrapper(QuestEntityWrapper entityData);

	public boolean isLocationSpecific();

	public Location getKillLocation();

	public double getKillLocationRadius();

	public int getEventFinishNpcID();
}
