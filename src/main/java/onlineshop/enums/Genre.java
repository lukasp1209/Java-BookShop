package onlineshop.enums;

public enum Genre {
    ARTS("Arts & Photography"),
    BUSINESS("Business & Money"),
    CALENDARS("Calendars"),
    COMIC("Comics & Graphic Novels"),
    COMPUTER_SCIENCE("Computers & Technology"),
    COOKBOOKS("Cookbooks, Food & Wine"),
    CRAFTS("Crafts, Hobbies & Home"),
    DATA_SCIENCE("Data Science"),
    ECONOMICS("Economics"),
    FICTION("Fiction"),
    HISTORY("History"),
    HUMOR("Humor & Entertainment"),
    MATHEMATICS("Mathematics"),
    PHILOSOPHY("Philosophy"),
    PSYCHOLOGY("Psychology"),
    THRILLER("Mystery, Thriller & Suspense"),
    NONFICTION("Non-Fiction"),
    POLITICS("Politics & Social Sciences"),
    ROMANCE("Romance"),
    SCIENCE("Science"),
    SCIFI("Science Fiction & Fantasy"),
    SIGNAL_PROCESSING("Signal Processing"),
    TEEN("Teen & Young Adult"),
    TRAVEL("Travel");

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
