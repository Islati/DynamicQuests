package com.caved_in.dynamicquests.handlers.dynamicquests.quests.interfaces;

import com.caved_in.dynamicquests.handlers.entity.QuestEntityWrapper;
import org.bukkit.Location;

import java.util.List;

public interface IMobQuest extends IDynamicQuest
{
	public QuestEntityWrapper getEntityData();

	public void setEntityWrapper(QuestEntityWrapper entityWrapper);

	public boolean isLocationSpecific();

	public Location getKillLocation();

	public double getKillLocationRadius();

	public int getEventFinishNpcID();
}
