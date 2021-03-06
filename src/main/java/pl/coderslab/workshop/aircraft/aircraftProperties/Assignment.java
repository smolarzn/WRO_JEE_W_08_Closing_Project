package pl.coderslab.workshop.aircraft.aircraftProperties;

public enum Assignment {
    MILITARY("military"),
    CIVIL("civil");

    private final String description;

    Assignment(String s) {
        description = s;
    }

    public String toString() {
        return description;
    }

}
