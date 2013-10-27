package com.caved_in.dynamicquests.handlers.player;

import com.caved_in.dynamicquests.handlers.dynamicquests.DynamicQuestHandler;
import com.caved_in.dynamicquests.handlers.dynamicquests.DynamicQuestType;
import com.caved_in.dynamicquests.handlers.dynamicquests.quests.MobKillQuest;
import com.caved_in.dynamicquests.handlers.entity.QuestEntityWrapper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerHandler
{
	private static Map<String, QuestPlayer> questPlayers = new HashMap<String, QuestPlayer>();

	public static void addData(String playerName)
	{
		if (!hasData(playerName))
		{
			questPlayers.put(playerName,new QuestPlayer(playerName));
		}
	}

	public static void addData(Player player)
	{
		if (!hasData(player))
		{
			addData(player.getName());
		}
	}

	public static boolean hasData(String playerName)
	{
		return questPlayers.containsKey(playerName);
	}

	public static boolean hasData(Player player)
	{
		return hasData(player.getName());
	}

	/**
	 * Gets the instanced QuestPlayer data for the player with the given name
	 * If they don't have a QuestPlayer instance, one will be created for them
	 * and then returned
	 * @param player
	 * @return
	 */
	public static QuestPlayer getData(Player player)
	{
		return getData(player.getName());
	}

	/**
	 * Gets the instanced QuestPlayer data for the player with the given name
	 * If they don't have a QuestPlayer instance, one will be created for them
	 * and then returned
	 * @param playerName
	 * @return
	 */
	public static QuestPlayer getData(String playerName)
	{
		if (hasData(playerName))
		{
			return questPlayers.get(playerName);
		}
		addData(playerName);
		return questPlayers.get(playerName);
	}

	/**
	 * Check if the player has a hunting quest with the given entityType
	 * @param entityType
	 * @param player
	 * @return
	 */
	public static boolean hasHuntingQuest(EntityType entityType, Player player)
	{
		QuestPlayer questPlayer = getData(player);
		for(UUID questID : questPlayer.getQuestIds())
		{
			if (DynamicQuestHandler.getQuestType(questID) == DynamicQuestType.KILL_MOB)
			{
				if (DynamicQuestHandler.getMobKillQuest(questID).getEntityData().getEntityType() == entityType)
				{
					return true;
				}
			}
		}
		return false;
	}

	public static void updatePlayerData(QuestPlayer questPlayer)
	{
		questPlayers.put(questPlayer.getPlayerName(),questPlayer);
	}
}
