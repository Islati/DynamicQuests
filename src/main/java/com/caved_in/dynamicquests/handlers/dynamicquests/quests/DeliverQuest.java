package com.caved_in.dynamicquests.handlers.dynamicquests.quests;

import com.caved_in.dynamicquests.handlers.dynamicquests.DynamicQuestType;
import com.caved_in.dynamicquests.handlers.dynamicquests.interfaces.IMaterialQuest;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;

import java.util.UUID;

public class DeliverQuest implements IMaterialQuest
{
	private UUID eventID;
	private int eventBeginNpc = 0;
	private int deliveryNpc = 0;
	private MaterialData eventMaterial = new MaterialData(Material.AIR);
	private int materialAmount = 1;

	public DeliverQuest(UUID eventID)
	{
		this.eventID = eventID;
	}

	public DeliverQuest(UUID eventID, int eventBeginNpc, int deliveryNpc)
	{
		this.eventBeginNpc = eventBeginNpc;
		this.deliveryNpc = deliveryNpc;
		this.eventID = eventID;
	}

	@Override
	public MaterialData getEventMaterial()
	{
		return this.eventMaterial;
	}

	@Override
	public int getEventMaterialAmount()
	{
		return this.materialAmount;
	}

	@Override
	public int getDeliveryNpcId()
	{
		return this.deliveryNpc;
	}

	@Override
	public void setDeliveryNpc(int deliveryNpc)
	{
		this.deliveryNpc = deliveryNpc;
	}

	@Override
	public void setEventMaterialAmount(int eventMaterialAmount)
	{
		this.materialAmount = eventMaterialAmount;
	}

	@Override
	public void setEventMaterial(MaterialData material)
	{
		this.eventMaterial = material;
	}

	@Override
	public UUID getEventID()
	{
		return this.eventID;
	}

	@Override
	public DynamicQuestType getEventType()
	{
		return DynamicQuestType.DELIVER_GOODS;
	}

	@Override
	public int getEventNpc()
	{
		return this.deliveryNpc;
	}
}
