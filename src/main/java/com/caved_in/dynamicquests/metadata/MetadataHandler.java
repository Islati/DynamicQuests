package com.caved_in.dynamicquests.metadata;

import com.caved_in.dynamicquests.DynamicQuests;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.Metadatable;

public class MetadataHandler {
	public static boolean hasMetaData(Metadatable metadatable, QuestMetadata metadata) {
		return metadatable.hasMetadata(metadata.getKey());
	}

	public static Object getMetaData(Metadatable metadatable, QuestMetadata metadata) {
		return metadata.getValue(metadatable);
	}

	public static void setMetaData(Metadatable metadatable, QuestMetadata metadata, Object value) {
		metadatable.setMetadata(metadata.getKey(), new FixedMetadataValue(DynamicQuests.getPlugin(),
				value));
	}
}
