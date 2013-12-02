package com.caved_in.dynamicquests.configuration;

import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

public class HuntingLocationConfiguration {
	@ElementList(name = "Hunting_Locations", type = XmlLocation.class, inline = true)
	List<XmlLocation> huntingLocations = new ArrayList<>();

	public HuntingLocationConfiguration(@ElementList(name = "Hunting_Locations", type = XmlLocation.class,
			inline = true) List<XmlLocation> huntingLocations) {
		this.huntingLocations = huntingLocations;
	}

	public HuntingLocationConfiguration() {

	}
}
