package com.factions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FactionHandler {
    public static List<Faction> factions = new ArrayList<Faction>();

    public static void addFaction(Faction faction) {
        factions.add(faction);
    }

    public static List<Faction> getFactions() {
        return factions;
    }

    public static Faction getPlayerFaction(UUID playerUUID) {
        for (Faction faction : factions) {
            for (FactionMember member : faction.members) {
                if (member.uuid.equals(playerUUID)) {
                    return faction;
                }
            }
        }
        return null;
    }

    public static Faction getFaction(String name) {
        for (Faction faction : factions) {
            if (faction.name == name) {
                return faction;
            }
        }
        return null;
    }
}
