package by.epam.goalplanner.beans;

public enum Language {
    EN, RU;

    private static final Language DEFAULT_LANGUAGE = Language.EN;

    public static Language parse(String value) {
        for (Language language : Language.values()) {
            if (language.name().equals(value)) {
                return Language.valueOf(value);
            }
        }
        return DEFAULT_LANGUAGE;
    }
}
