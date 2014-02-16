package com.caved_in.dynamicquests.configuration;

import org.simpleframework.xml.Element;

public class Configuration {

    @Element(name = "hunting_config", type = HuntingLocationConfiguration.class)
    private HuntingLocationConfiguration huntingLocationConfiguration;

    public Configuration(@Element(name = "hunting_config", type = HuntingLocationConfiguration.class) HuntingLocationConfiguration locationConfiguration) {
        this.huntingLocationConfiguration = locationConfiguration;
    }

    public Configuration() {
        this.huntingLocationConfiguration = new HuntingLocationConfiguration();
    }


    public HuntingLocationConfiguration getHuntingLocationConfiguration() {
        return huntingLocationConfiguration;
    }
}
