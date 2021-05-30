package com.geektrust.enums;

public enum KingdomEmblem {
    SPACE("GORILLA"),
    LAND("PANDA"),
    WATER("OCTOPUS"),
    ICE("MAMMOTH"),
    AIR("OWL"),
    FIRE("DRAGON");

    final String value;

    KingdomEmblem(String value) {
        this.value = value;
    }

    public String getEmblem(){
        return value;
    }
}
