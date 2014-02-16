package com.caved_in.dynamicquests.dynamicquests.quests;

import com.caved_in.dynamicquests.dynamicquests.DynamicQuestType;
import com.caved_in.dynamicquests.entity.QuestEntityWrapper;
import org.bukkit.Location;

import java.util.UUID;

public class PlayerKillQuest implements IMobQuest{



    @Override
    public QuestEntityWrapper getEntityData() {
        return null;
    }

    @Override
    public void setEntityWrapper(QuestEntityWrapper entityWrapper) {

    }

    @Override
    public boolean isLocationSpecific() {
        return false;
    }

    @Override
    public Location getKillLocation() {
        return null;
    }

    @Override
    public double getKillLocationRadius() {
        return 0;
    }

    @Override
    public int getEventFinishNpcID() {
        return 0;
    }

    @Override
    public UUID getQuestID() {
        return null;
    }

    @Override
    public DynamicQuestType getQuestType() {
        return null;
    }

    @Override
    public int getQuestBeginNpc() {
        return 0;
    }
}
