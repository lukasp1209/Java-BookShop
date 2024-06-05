package onlineshop.enums;

public enum Genre {
        SPORTSWAGEN("Sportwagen"),
        OLDTIMER("Oldtimer"),
        RENNWAGEN("Rennwagen"),
        SUV("SUV"),
        US_CAR("US-Car");

    public final String label;

    Genre(String label) {
        this.label = label;
    }

    public static Genre fromString(String label) {
        final String labelUpperCase = label.toUpperCase();
        for(Genre genre : Genre.values()) {
            if(genre.toString().contains(labelUpperCase)) {
                return genre;
            }
        }
        return null;
    }
}
