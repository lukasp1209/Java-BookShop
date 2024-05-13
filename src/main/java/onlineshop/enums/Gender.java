package onlineshop.enums;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    public final String label;

    Gender(String label) {
        this.label = label;
    }
}
