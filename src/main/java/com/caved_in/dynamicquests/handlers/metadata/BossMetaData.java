package com.caved_in.dynamicquests.handlers.metadata;

import org.bukkit.entity.Entity;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;

import java.util.List;

public class BossMetaData implements QuestMetadata {
	@Override
	public String getKey() {
		return "BossEntity";
	}

	@Override
	public Object getValue(Metadatable entity) {
		List<MetadataValue> entityMetaData = entity.getMetadata(getKey());
		for (MetadataValue metaValue : entityMetaData) {
			return metaValue.asString();
		}
		return null;
	}

	public String getBossType(Entity entity) {
		return String.valueOf(getValue(entity));
	}
}
