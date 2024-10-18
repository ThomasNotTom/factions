package com.factions.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.factions.Faction;
import com.factions.FactionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.command.Command;

public class FactionCommands implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("Please provide an argument");
            return false;
        }

        switch (args[0]) {
            case "create": {
                if (args.length <= 1) {
                    player.sendMessage("Please provide a faction name");
                    return false;
                }

                if (FactionHandler.getPlayerFaction(player.getUniqueId()) != null) {
                    player.sendMessage("You are already in a faction");
                    return false;
                }

                Pattern pattern = Pattern.compile("[a-zA-Z][_a-zA-Z0-9]*");
                Matcher matcher = pattern.matcher(args[1]);
                boolean matchFound = matcher.matches();

                if (!matchFound || args[1].length() <= 2 || args[1].length() >= 18) {
                    player.sendMessage("' +" + args[1] + "' is not a valid faction name");
                    return false;
                }

                Faction faction = new Faction(player.getUniqueId(), args[1], Faction.STARTING_MONEY);
                FactionHandler.addFaction(faction);

                for (Faction fac : FactionHandler.getFactions()) {
                    player.getServer().broadcastMessage(fac.getOwner().uuid + " owns " + fac.name);
                }
                break;
            }
            case "join": {
                if (args.length <= 1) {
                    player.sendMessage("Please provide a faction name");
                    return false;
                }

                if (FactionHandler.getPlayerFaction(player.getUniqueId()) != null) {
                    player.sendMessage("You are already in a faction");
                    return false;
                }

                Faction faction = FactionHandler.getFaction(args[1]);
                if (faction == null) {
                    player.sendMessage("You are already in a faction");
                    return false;
                }

                faction.join(player);
                break;
            }

            default:
                break;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> suggestions = new ArrayList<String>();
        System.out.println("-----");

        for (String arg : args) {
            System.out.println(arg);
        }
        System.out.println("-----");

        if (args.length == 1) {
            suggestions.add("create");
            suggestions.add("join");
        } else {
            switch (args[0]) {
                case "create":
                    break;
                case "join":
                    break;
                default:
                    break;
            }
        }

        return suggestions;
    }
}
