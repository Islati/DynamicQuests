package com.caved_in.dynamicquests.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

import java.util.UUID;

public class QuestCompleteEvent extends QuestEvent implements Cancellable {
	private boolean isCancelled = false;

	public QuestCompleteEvent(Player player, UUID questID) {
		super(player, questID);
	}

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.isCancelled = cancelled;
	}
}
