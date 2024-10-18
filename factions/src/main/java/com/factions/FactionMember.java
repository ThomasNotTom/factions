package com.factions;

import java.util.UUID;

public class FactionMember {
    public static enum Status {
        OWNER,
        MEMBER
    }

    public UUID uuid;
    public Status status;

    public FactionMember(UUID uuid, Status status) {
        this.uuid = uuid;
        this.status = status;
    }
}
