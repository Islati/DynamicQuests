package com.caved_in.dynamicquests.commands;

import com.caved_in.commons.Messages;
import com.caved_in.commons.commands.CommandController;
import com.caved_in.commons.commands.HelpMenus;
import com.caved_in.commons.menu.HelpScreen;
import com.caved_in.commons.player.PlayerHandler;
import com.caved_in.dynamicquests.DynamicQuests;
import com.caved_in.dynamicquests.QuestMessages;
import com.caved_in.dynamicquests.configuration.XmlLocation;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created By: TheGamersCave (Brandon)
 * Date: 13/02/14
 * Time: 11:30 PM
 */
public class QuestsCommand {
    @CommandController.CommandHandler(name = "quests")
    public void onQuestsCommand(Player player, String[] args) {
        if (args.length == 0) {
            PlayerHandler.sendMessage(player, QuestMessages.QUESTS_COMMAND_HELP);
        }
    }

    @CommandController.SubCommandHandler(name = "help", parent = "quests")
    public void onQuestsHelpCommand(Player player, String[] args) {
        HelpScreen helpScreen = HelpMenus.generateHelpScreen("Quests Help", HelpMenus.PageDisplay.SHORTHAND, HelpMenus.ItemFormat.SINGLE_DASH, ChatColor.GREEN, ChatColor.DARK_GREEN);
        helpScreen.setEntry("/quests addlocation <radius>","Add a location where mobs will be killed for quests");
    }

    @CommandController.SubCommandHandler(name = "addlocation", parent = "quests", permission = "quests.addlocation")
    public void onAddLocationCommand(Player player, String[] args) {
        if (args.length > 1) {
            //Get the radius the player passed
            String radiusArg = args[1];
            if (StringUtils.isNumeric(radiusArg)) {
                //Parse the argument passed
                int radius = Integer.parseInt(radiusArg);
                //New xmlLocation based on where the player is standing, and the radius passed
                XmlLocation location = new XmlLocation(player.getLocation(),radius);
                DynamicQuests.getConfiguration().getHuntingLocationConfiguration().addLocation(location);
                PlayerHandler.sendMessage(player, QuestMessages.HUNTING_LOCATION_ADDED(location));
            } else {
                PlayerHandler.sendMessage(player, Messages.INVALID_COMMAND_USAGE("radius (number)"));
            }
        } else {
            PlayerHandler.sendMessage(player, Messages.INVALID_COMMAND_USAGE("radius"));
        }
    }
}
