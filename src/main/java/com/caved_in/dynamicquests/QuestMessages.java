package com.caved_in.dynamicquests;

import com.caved_in.dynamicquests.configuration.XmlLocation;

/**
 * Created By: TheGamersCave (Brandon)
 * Date: 13/02/14
 * Time: 11:35 PM
 */
public class QuestMessages {
    public static final String QUESTS_COMMAND_HELP = "&aUse &e/quests help&a to get help with the quests command.";

    public static String HUNTING_LOCATION_ADDED(XmlLocation location) {
        return String.format("&aHunting location in &7%s&a at &e%s&a, &e%s&a, &e%s&a with a radius of &e%s&a has been saved",location.getWorldName(),location.getX(),location.getY(),location.getZ(),location.getLocationRadius());
    }
}
