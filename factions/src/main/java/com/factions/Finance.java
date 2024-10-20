package com.factions;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

class Finance implements Serializable {
    public static Map<UUID, Float> finances = new HashMap<UUID, Float>();

    public static void addPlayer(UUID playerUUID) {
        Finance.finances.put(playerUUID, 10F);
    }

    public static Float getMoney(UUID playerUUID) {
        if (!Finance.finances.containsKey(playerUUID)) {
            Finance.addPlayer(playerUUID);
        }
        return Finance.finances.get(playerUUID);
    }
}