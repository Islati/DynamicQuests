package com.caved_in.dynamicquests.npctrait;

import com.caved_in.commons.Commons;
import com.caved_in.dynamicquests.dynamicquests.DynamicQuestHandler;
import com.caved_in.dynamicquests.dynamicquests.DynamicQuestType;
import com.caved_in.dynamicquests.dynamicquests.QuestGenerator;
import com.caved_in.dynamicquests.dynamicquests.quests.CollectQuest;
import com.caved_in.dynamicquests.dynamicquests.quests.MobKillQuest;
import com.caved_in.dynamicquests.dynamicquests.quests.MobQuestTier;
import com.caved_in.dynamicquests.player.PlayerHandler;
import com.caved_in.dynamicquests.player.QuestPlayer;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import java.util.Random;
import java.util.UUID;

public class QuestNPC extends Trait {
	public QuestNPC() {
		super("questnpc");
	}

	private boolean isThisNpc(NPC npc) {
		return npc == getNPC();
	}

	@Override
	public void onAttach() {
		if (!DynamicQuestHandler.isNpcInUse(this.getNPC().getId())) {
			int questTypeNumber = new Random().nextInt(99);
			if (questTypeNumber >= 0 && questTypeNumber <= 49) //Make Collection Quest
			{
				CollectQuest collectQuest = QuestGenerator.generateCollectQuest(this.getNPC().getId());
                Commons.messageConsole(String.format("Collection quest for %s, NPCID[%s], " +
						"with quest ID[%s] requiring %s of %s", npc.getName(), npc.getId(), collectQuest.getQuestID(),
						collectQuest.getQuestMaterial().getQuestMaterialAmount(), collectQuest.getQuestMaterial()
						.getQuestMaterial().getItemType().name()));
				DynamicQuestHandler.addQuest(collectQuest);
			} else if (questTypeNumber >= 49 && questTypeNumber <= 99) //Make Mob-Kill quest
			{
				MobKillQuest mobKillQuest = QuestGenerator.generateMobKillQuest(this.getNPC().getId(),
						MobQuestTier.MEDIUM);
				Commons.messageConsole(String.format("Medium Hunting Quest generated for NPC %s requring %s kills " +
						"of %s", npc.getName() + "/" + npc.getId(), mobKillQuest.getEntityData().getEntityAmount(),
						mobKillQuest.getEntityData().getEntityType().name()));
				DynamicQuestHandler.addQuest(mobKillQuest);
			}
		}
	}

	@EventHandler
	public void onNpcRightClick(NPCRightClickEvent event) {
		if (event.getNPC() == this.getNPC()) //Make sure we're only handling for the npc that was clicked.
		{
            Commons.messageConsole("Is the same npc...");
			Player player = event.getClicker();
			UUID questForNpc = DynamicQuestHandler.getQuestForNpc(event.getNPC()).getQuestID(); //Get the quest ID for this NPC
			DynamicQuestType questType = DynamicQuestHandler.getQuestType(questForNpc); //Get questType for the npc's
			// quest
			if (player.getGameMode() != GameMode.CREATIVE) {
				QuestPlayer questPlayer = PlayerHandler.getData(player);
				if (!questPlayer.isPlayerOnQuest(questForNpc)) {
					//TODO prompt user if they want to help this npc or not
					//Make some fancy-ass text with a prompt and stuff to send to the player
					//Also check against local var's
					questPlayer.addQuest(questForNpc);
					player.sendMessage(ChatColor.GREEN + "You now has quest. Type[" + questType.name() + "]");
					switch (questType) {
						case GATHER_MATERIAL:
							CollectQuest collectQuest = DynamicQuestHandler.getCollectQuest(questForNpc);
							player.sendMessage("Collect " + collectQuest.getQuestMaterial().getQuestMaterialAmount() +
									" of " + collectQuest.getQuestMaterial().getQuestMaterial().getItemType().name() +
									" plz");
							break;
						case KILL_MOB:
							MobKillQuest mobKillQuest = DynamicQuestHandler.getMobKillQuest(questForNpc);
							player.sendMessage("Kill " + mobKillQuest.getEntityData().getEntityAmount() + " of " +
									mobKillQuest.getEntityData().getEntityType().name());
							break;
						default:
							player.sendMessage("Jk no quest");
							break;
					}
				} else {
					player.sendMessage("You've already got the quest attached to this npc.");
					player.sendMessage("Your progress: [" + questPlayer.getQuestProgress(questForNpc)
							.getPlayerProgress() + "] out of the required [" + questPlayer.getQuestProgress
							(questForNpc).getAmountRequired() + "] for quest type [" + questType.name() + "]");
				}
			} else {
				player.sendMessage("You're in creative... NO.");
			}

		} else {
            Commons.messageConsole("Not the same npc? " + event.getNPC().getId() + "/ " + this.getNPC().getId());
		}
	}
}
