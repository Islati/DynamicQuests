package com.caved_in.dynamicquests.dynamicquests.quests;

import com.caved_in.dynamicquests.entity.QuestEntityWrapper;
import org.bukkit.Location;

public interface IMobQuest extends IDynamicQuest {
	public QuestEntityWrapper getEntityData();

	public void setEntityWrapper(QuestEntityWrapper entityWrapper);

	public boolean isLocationSpecific();

	public Location getKillLocation();

	public double getKillLocationRadius();

	public int getEventFinishNpcID();
}
