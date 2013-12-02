package com.caved_in.dynamicquests.handlers.dynamicquests.quests;

import com.caved_in.dynamicquests.handlers.dynamicquests.DynamicQuestType;
import com.caved_in.dynamicquests.handlers.dynamicquests.quests.interfaces.IMaterialQuest;
import com.caved_in.dynamicquests.handlers.material.QuestMaterial;

import java.util.UUID;

public class CollectQuest implements IMaterialQuest {
	private UUID eventID; //Unique Quest ID

	private int questBeginNpc = 0; //Quest begin NPC
	private int deliveryNpc = 0; //Npc to deliver items to

	private QuestMaterial questMaterial; //Material(s) required for this quest

	public CollectQuest(UUID eventID) {
		this.eventID = eventID;
	}

	public CollectQuest(UUID eventID, int questBeginNpc) {
		this.questBeginNpc = questBeginNpc;
		this.deliveryNpc = questBeginNpc;
		this.eventID = eventID;
	}

	@Override
	public QuestMaterial getQuestMaterial() {
		return this.questMaterial;
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
		return this.eventID;
	}

	@Override
	public DynamicQuestType getQuestType() {
		return DynamicQuestType.GATHER_MATERIAL;
	}

	@Override
	public int getQuestBeginNpc() {
		return this.questBeginNpc;
	}
}
