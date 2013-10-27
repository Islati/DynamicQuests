package com.caved_in.dynamicquests.handlers.dynamicquests.quests;

public enum MobQuestTier
{
	EASY(1,12,false,0),
	MEDIUM(8, 24,true,2),
	HARD(24,46,true,8),
	EXTREME(48,76,true,16);

	private int minimumAmount = 1;
	private int maximumAmount = 12;
	private boolean isElite = false;
	private int eliteChances = 0;

	MobQuestTier(int minimumAmount, int maximumAmount, boolean elites, int eliteChance)
	{
		this.minimumAmount = minimumAmount;
		this.maximumAmount = maximumAmount;
		this.isElite = elites;
		this.eliteChances = eliteChance;
	}

	private int getMinimumAmount()
	{
		return minimumAmount;
	}

	private int getMaximumAmount()
	{
		return maximumAmount;
	}

	private boolean isElite()
	{
		return isElite;
	}

	private int getEliteChances()
	{
		return eliteChances;
	}
}
