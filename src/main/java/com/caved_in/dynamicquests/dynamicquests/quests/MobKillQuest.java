package com.caved_in.dynamicquests.dynamicquests.quests;

import com.caved_in.dynamicquests.dynamicquests.DynamicQuestType;
import com.caved_in.dynamicquests.dynamicquests.quests.interfaces.IMobQuest;
import com.caved_in.dynamicquests.entity.QuestEntityWrapper;
import org.bukkit.Location;

import java.util.UUID;

public class MobKillQuest implements IMobQuest {
	private UUID eventID;
	private int eventBeginNpc = 0;
	private int eventFinishNpc = 0;
	private QuestEntityWrapper entityWrapper = null;
	private Location mobLocationCenter = null;
	private double mobKillRadius = 0.0;
	private boolean isLocationSpecific = false;

	public MobKillQuest(UUID eventID) {
		this.eventID = eventID;
	}

	public MobKillQuest(UUID eventID, int eventBeginNpc) {
		this.eventID = eventID;
		this.eventBeginNpc = eventBeginNpc;
		this.eventFinishNpc = eventBeginNpc;
	}

	public MobKillQuest(UUID eventID, int eventBeginNpc, Location killLocation, double killRadius) {
		this.eventID = eventID;
		this.eventBeginNpc = eventBeginNpc;
		this.eventFinishNpc = eventBeginNpc;
		this.mobLocationCenter = killLocation;
		this.mobKillRadius = killRadius;
	}

	@Override
	public Location getKillLocation() {
		return this.mobLocationCenter;
	}

	@Override
	public double getKillLocationRadius() {
		return this.mobKillRadius;
	}

	@Override
	public int getEventFinishNpcID() {
		return this.eventFinishNpc;
	}

	@Override
	public UUID getQuestID() {
		return this.eventID;
	}

	@Override
	public DynamicQuestType getQuestType() {
		return DynamicQuestType.KILL_MOB;
	}

	@Override
	public int getQuestBeginNpc() {
		return this.eventBeginNpc;
	}

	@Override
	public QuestEntityWrapper getEntityData() {
		return this.entityWrapper;
	}

	@Override
	public void setEntityWrapper(QuestEntityWrapper entityWrapper) {
		this.entityWrapper = entityWrapper;
	}

	@Override
	public boolean isLocationSpecific() {
		return this.isLocationSpecific;
	}
}
