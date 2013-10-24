package com.caved_in.dynamicquests.handlers.dynamicquests.interfaces;

import org.bukkit.material.MaterialData;

public interface IMaterialQuest extends IDynamicQuest
{

	public MaterialData getEventMaterial();

	public int getEventMaterialAmount();

	public int getDeliveryNpcId();

	public void setDeliveryNpc(int deliveryNpc);

	public void setEventMaterialAmount(int eventMaterialAmount);

	public void setEventMaterial(MaterialData material);

}
