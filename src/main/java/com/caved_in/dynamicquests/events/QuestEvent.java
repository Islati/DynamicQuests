package com.caved_in.dynamicquests.events;

import com.caved_in.dynamicquests.handlers.dynamicquests.DynamicQuestHandler;
import com.caved_in.dynamicquests.handlers.dynamicquests.DynamicQuestType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import java.util.UUID;

public abstract class QuestEvent extends PlayerEvent implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private boolean isCancelled = false;
	private UUID questID = null;
	private DynamicQuestType questType;

	public QuestEvent(Player player, UUID questID) {
		super(player);
		this.questID = questID;
		this.questType = DynamicQuestHandler.getQuestType(questID);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlersList() {
		return handlers;
	}

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public DynamicQuestType getQuestType() {
		return this.questType;
	}

	public UUID getQuestID() {
		return this.questID;
	}
}
