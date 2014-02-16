package com.caved_in.dynamicquests.configuration;

import org.bukkit.Location;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuntingLocationConfiguration {
    @ElementList(name = "Hunting_Locations", type = XmlLocation.class, inline = true)
    private List<XmlLocation> huntingLocations = new ArrayList<>();

    public HuntingLocationConfiguration(@ElementList(name = "Hunting_Locations", type = XmlLocation.class,
            inline = true) List<XmlLocation> huntingLocations) {
        this.huntingLocations = huntingLocations;
    }

    public HuntingLocationConfiguration() {

    }

    public void addLocation(Location location, double radius) {
        huntingLocations.add(new XmlLocation(location, radius));
    }

    public void addLocation(XmlLocation location) {
        Collections.addAll(huntingLocations,location);
    }

    public void addLocations(XmlLocation... locations) {
        Collections.addAll(huntingLocations,locations);
    }
}
