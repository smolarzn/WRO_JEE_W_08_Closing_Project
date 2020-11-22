package pl.coderslab.workshop.aircraft.aircraftProperties;

public enum AircraftRole {
    FIGHTER("fighter"),
    BOMBER("bomber"),
    ATTACK("attack"),
    ELECTRONIC_WARFARE("electronic warfare"),
    MARITIME_PATROL("maritime patrol"),
    AWACS("AWACS"),
    RECONNAISSANCE_AND_SURVEILLANCE("reconnaissance and surveillance"),
    EXPERIMENTAL("experimental"),
    TRAINER("trainer"),
    AIRLINER("airliner"),
    AGRICULTURAL("agricultural"),
    BUSINESS("business"),
    CARGO("cargo"),
    SAILPLANE("sailplane"),
    SPECIAL_PURPOSE("special purpose"),
    SPORT("sport"),
    UTILITY("utility");

    private final String description;

    AircraftRole(String s) {
        description = s;
    }

    public String toString() {
        return description;
    }
}
