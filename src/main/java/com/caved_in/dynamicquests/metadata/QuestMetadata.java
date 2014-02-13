package com.caved_in.dynamicquests.metadata;

import org.bukkit.metadata.Metadatable;

public interface QuestMetadata {
	/**
	 * Get the key for the instanced metadata type
	 *
	 * @return
	 */
	public String getKey();

	/**
	 * Get the value for the instanced metadata in object format
	 *
	 * @return
	 */
	public Object getValue(Metadatable metadatableObject);
}
