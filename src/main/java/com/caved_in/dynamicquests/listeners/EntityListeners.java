package com.caved_in.dynamicquests.listeners;

import com.caved_in.dynamicquests.DynamicQuests;
import com.caved_in.dynamicquests.dynamicquests.quests.MobKillQuest;
import com.caved_in.dynamicquests.dynamicquests.quests.QuestProgress;
import com.caved_in.dynamicquests.player.PlayerHandler;
import com.caved_in.dynamicquests.player.QuestPlayer;
import com.caved_in.dynamicquests.utility.CommonUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;

public class EntityListeners implements Listener {

	public EntityListeners(DynamicQuests Plugin) {
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		LivingEntity eventEntity = event.getEntity();
		EntityType entityType = eventEntity.getType();
		if (eventEntity.getKiller() != null) {
			Player player = eventEntity.getKiller();
			if (PlayerHandler.hasHuntingQuest(entityType, player)) {
				QuestPlayer questPlayer = PlayerHandler.getData(player);
				List<MobKillQuest> mobKillQuests = questPlayer.getActiveMobKillQuests(entityType);
				if (mobKillQuests.size() > 0) //Make sure they have mob kill quests
				{
					for (MobKillQuest mobKillQuest : mobKillQuests) //Loop through all their active quests
					{
						QuestProgress questProgress = questPlayer.getQuestProgress(mobKillQuest.getQuestID());
						if (!questProgress.hasBeenRewarded()) {
							if (questProgress.getPlayerProgress() < questProgress.getAmountRequired()) {
								questProgress.addPlayerProgress(1); //Increment the progress a players made on any
								// kill quest with the given npc type
								CommonUtils.messageCosole("Added progress to [" + player.getName() + "] for quest [" +
										mobKillQuest.getQuestID() + "] (Mob Kill of [" + mobKillQuest.getEntityData()
										.getEntityType().name() + "]) by 1");
								questPlayer.updateQuestProgress(questProgress); //Make sure the player actually has
								// the data we updated
							}
						}
					}
				}
				PlayerHandler.updatePlayerData(questPlayer); //Now update the players data aswell
			}
		}
	}

}
