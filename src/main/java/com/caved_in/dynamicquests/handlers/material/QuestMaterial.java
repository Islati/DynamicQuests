package com.caved_in.dynamicquests.handlers.material;

import org.bukkit.material.MaterialData;

public class QuestMaterial {
	private int questMaterialAmount = 0;
	private MaterialData questMaterial;

	public QuestMaterial(MaterialData questMaterial, int questMaterialAmount) {
		this.questMaterial = questMaterial;
		this.questMaterialAmount = questMaterialAmount;
	}

	public MaterialData getQuestMaterial() {
		return questMaterial;
	}

	public void setQuestMaterial(MaterialData questMaterial) {
		this.questMaterial = questMaterial;
	}

	public int getQuestMaterialAmount() {
		return questMaterialAmount;
	}

	public void setQuestMaterialAmount(int questMaterialAmount) {
		this.questMaterialAmount = questMaterialAmount;
	}
}
