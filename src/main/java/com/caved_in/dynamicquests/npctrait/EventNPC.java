package com.caved_in.dynamicquests.npctrait;

import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;

public class EventNPC extends Trait
{
	public EventNPC()
	{
		super("eventnpc");
	}

	private boolean isThisNpc(NPC npc)
	{
		return npc.getId() == getNPC().getId();
	}



}
