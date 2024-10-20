package com.factions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.factions.FactionMember.Status;

public class Faction {
    public final static List<ChatColor> FACTION_COLORS = Arrays.asList(
            ChatColor.AQUA,
            ChatColor.BLUE,
            ChatColor.DARK_AQUA,
            ChatColor.DARK_BLUE,
            ChatColor.DARK_GRAY,
            ChatColor.DARK_GREEN,
            ChatColor.DARK_PURPLE,
            ChatColor.DARK_RED,
            ChatColor.GOLD,
            ChatColor.GRAY,
            ChatColor.GREEN,
            ChatColor.LIGHT_PURPLE,
            ChatColor.RED,
            ChatColor.WHITE,
            ChatColor.YELLOW);

    public final static int STARTING_MONEY = 0;

    public List<FactionMember> members = new ArrayList<FactionMember>();

    public String name;
    public int money;

    public ChatColor chatColor;

    public Faction(UUID ownerUUID, String name, int money) {
        this.members.add(new FactionMember(ownerUUID, Status.OWNER));
        this.name = name;
        this.money = money;

        this.chatColor = Faction.FACTION_COLORS.get((int) Math.floor(Math.random() * Faction.FACTION_COLORS.size()));
    }

    public FactionMember getOwner() {
        for (FactionMember member : this.members) {
            if (member.status == Status.OWNER) {
                return member;
            }
        }

        return null;
    }

    public boolean hasMember(Player player) {
        return this.hasMember(player.getUniqueId());
    }

    public boolean hasMember(UUID playerUUID) {
        for (FactionMember member : this.members) {
            if (member.uuid == playerUUID) {
                return true;
            }
        }
        return false;
    }

    public void broadcastMessage(String message) {
        for (FactionMember member : this.members) {
            Player player = Bukkit.getPlayer(member.uuid);
            if (player == null) {
                continue;
            }

            player.sendMessage(message);
        }
    }

    public Float getValue() {
        Float balance = (float) this.money;
        for (FactionMember member : this.members) {
            balance += Finance.getMoney(member.uuid);
        }
        return balance;
    }

    public void join(Player player) {
        this.members.add(new FactionMember(player.getUniqueId(), FactionMember.Status.MEMBER));

        this.broadcastMessage(player.getDisplayName() + " has joined");
    }

}
