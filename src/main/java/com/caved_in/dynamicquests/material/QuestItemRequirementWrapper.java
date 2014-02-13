package com.caved_in.dynamicquests.material;

import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.HashMap;
import java.util.Map;

public class QuestItemRequirementWrapper {
	private Map<Integer, ? extends ItemStack> itemSlots = new HashMap<>();
	private int playerAmount = 0;
	private int questRequiredAmount = 0;
	private int remainderItems = 0;
	private MaterialData materialData;

	public QuestItemRequirementWrapper(int playerAmount, int questRequiredAmount, Map<Integer,
			? extends ItemStack> itemSlots, MaterialData materialData) {
		this.playerAmount = playerAmount;
		this.questRequiredAmount = questRequiredAmount;
		this.itemSlots = itemSlots;
		this.materialData = materialData;
	}

	public int getQuestRequiredAmount() {
		return questRequiredAmount;
	}

	public Map<Integer, ? extends ItemStack> getItemSlots() {
		return itemSlots;
	}

	public int getPlayerAmount() {
		return playerAmount;
	}

	public boolean hasRequiredAmount() {
		return getPlayerAmount() >= getQuestRequiredAmount();
	}

	public int getRemainderItems() {
		return remainderItems;
	}

	public void setRemainderItems(int remainderItems) {
		this.remainderItems = remainderItems;
	}

	public MaterialData getMaterialData() {
		return materialData;
	}
}
