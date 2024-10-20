package com.factions;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class FactionScoreboardManager {

    public static void updateScoreboard(UUID playerUUID) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();

        Scoreboard scoreboard = manager.getNewScoreboard();
        String balanceText = "Balance: " + ChatColor.GREEN + Finance.getMoney(playerUUID);

        Faction faction = FactionHandler.getPlayerFaction(playerUUID);
        String factionText = "Faction: ";
        if (faction == null) {
            factionText += ChatColor.GRAY + "" + ChatColor.ITALIC + "N/A";
        } else {
            factionText += faction.chatColor + faction.name;
        }

        Objective stats = scoreboard.registerNewObjective("stats", Criteria.DUMMY, ChatColor.BOLD + "- Player Stats -");
        stats.setDisplaySlot(DisplaySlot.SIDEBAR);
        stats.getScore(balanceText).setScore(1);
        stats.getScore(factionText).setScore(0);

        Player player = Bukkit.getPlayer(playerUUID);
        player.setScoreboard(scoreboard);
    }
}
