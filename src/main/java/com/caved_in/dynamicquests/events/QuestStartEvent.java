package com.caved_in.dynamicquests.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

import java.util.UUID;

public class QuestStartEvent extends QuestEvent implements Cancellable {
	private boolean isCancelled = false;

	public QuestStartEvent(Player player, UUID questID) {
		super(player, questID);
	}

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
}
