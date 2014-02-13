package com.caved_in.dynamicquests.dynamicquests.quests;

import com.caved_in.dynamicquests.dynamicquests.DynamicQuestType;
import com.caved_in.dynamicquests.dynamicquests.quests.interfaces.IMaterialQuest;
import com.caved_in.dynamicquests.material.QuestMaterial;

import java.util.UUID;

public class DeliverQuest implements IMaterialQuest {
	private UUID questID; //Unique quest ID
	private int eventBeginNpc = 0; // ID of npc where the quest begins
	private int deliveryNpc = 0; //ID of npc where the quest ends

	private QuestMaterial questMaterial = null; // Material required for this quest

	public DeliverQuest(UUID questID) {
		this.questID = questID;
	}

	public DeliverQuest(UUID questID, int questBeginNpc, int deliveryNpc) {
		this.eventBeginNpc = questBeginNpc;
		this.deliveryNpc = deliveryNpc;
		this.questID = questID;
	}

	@Override
	public QuestMaterial getQuestMaterial() {
		return questMaterial;
	}

	@Override
	public int getDeliveryNpcId() {
		return this.deliveryNpc;
	}

	@Override
	public void setDeliveryNpc(int deliveryNpc) {
		this.deliveryNpc = deliveryNpc;
	}

	@Override
	public void setQuestMaterial(QuestMaterial questMaterial) {
		this.questMaterial = questMaterial;
	}

	@Override
	public UUID getQuestID() {
		return this.questID;
	}

	@Override
	public DynamicQuestType getQuestType() {
		return DynamicQuestType.DELIVER_GOODS;
	}

	@Override
	public int getQuestBeginNpc() {
		return this.deliveryNpc;
	}
}
