package com.caved_in.dynamicquests.handlers.dynamicquests.quests.interfaces;

import com.caved_in.dynamicquests.handlers.material.QuestMaterial;

public interface IMaterialQuest extends IDynamicQuest {

	public QuestMaterial getQuestMaterial();

	public int getDeliveryNpcId();

	public void setDeliveryNpc(int deliveryNpc);

	public void setQuestMaterial(QuestMaterial questMaterial);
}
