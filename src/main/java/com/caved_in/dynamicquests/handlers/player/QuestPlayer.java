package com.caved_in.dynamicquests.handlers.player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestPlayer
{
	private String playerName = "";
	private List<UUID> activeQuests = new ArrayList<UUID>();

	public QuestPlayer(String playerName)
	{
		this.playerName = playerName;
	}
}
