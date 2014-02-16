package com.caved_in.dynamicquests.dynamicquests.quests;

import com.caved_in.dynamicquests.material.QuestMaterial;

public interface IMaterialQuest extends IDynamicQuest {

	public QuestMaterial getQuestMaterial();

	public int getDeliveryNpcId();

	public void setDeliveryNpc(int deliveryNpc);

	public void setQuestMaterial(QuestMaterial questMaterial);
}
