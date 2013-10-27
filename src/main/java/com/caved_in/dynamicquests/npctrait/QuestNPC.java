package com.caved_in.dynamicquests.npctrait;

import com.caved_in.dynamicquests.handlers.dynamicquests.DynamicQuestHandler;
import com.caved_in.dynamicquests.handlers.dynamicquests.QuestGenerator;
import com.caved_in.dynamicquests.handlers.dynamicquests.quests.CollectQuest;
import com.caved_in.dynamicquests.handlers.dynamicquests.quests.MobKillQuest;
import com.caved_in.dynamicquests.handlers.dynamicquests.quests.MobQuestTier;
import com.caved_in.dynamicquests.handlers.utility.CommonUtils;
import com.caved_in.dynamicquests.handlers.utility.StringUtil;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;

import java.util.Random;

public class QuestNPC extends Trait
{
	public QuestNPC()
	{
		super("questnpc");
	}

	private boolean isThisNpc(NPC npc)
	{
		return npc.getId() == getNPC().getId();
	}

	@Override
	public void onAttach()
	{
		if (!DynamicQuestHandler.isNpcInUse(this.getNPC().getId()))
		{
			int questTypeNumber = new Random().nextInt(99);
			if (questTypeNumber >= 0 && questTypeNumber <= 49) //Make Collection Quest
			{
				CollectQuest collectQuest = QuestGenerator.generateCollectQuest(this.getNPC().getId());
				CommonUtils.messageCosole(String.format("Collection quest for %s, NPCID[%s], with quest ID[%s] requiring %s of %s",npc.getName(),npc.getId(),collectQuest.getQuestID(),collectQuest.getQuestMaterial().getQuestMaterialAmount(),collectQuest.getQuestMaterial().getQuestMaterial().getItemType().name()));
				DynamicQuestHandler.addCollectQuest(collectQuest);
			}
			else if (questTypeNumber >= 49 && questTypeNumber <= 99) //Make Mob-Kill quest
			{
				MobKillQuest mobKillQuest = QuestGenerator.generateMobKillQuest(this.getNPC().getId(), MobQuestTier.MEDIUM);
				CommonUtils.messageCosole(String.format("Medium Hunting Quest generated for NPC %s requring %s kills of %s",npc.getName() + "/" + npc.getId(), mobKillQuest.getFirstEntityWrapper().getEntityAmount(),mobKillQuest.getFirstEntityWrapper().getEntityType().name()));
				DynamicQuestHandler.addMobKillQuest(mobKillQuest);
			}
		}
	}
}
