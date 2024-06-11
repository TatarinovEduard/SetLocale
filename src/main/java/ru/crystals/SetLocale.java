package ru.crystals;

import java.util.Optional;

public class SetLocale {

    private final static String RU_LOCALE = "ru_RU";
    private final static String EN_LOCALE = "en_US";

    private static final String LOCALE_KEY = "locale"; // пример значения получаемого по ключу: ru_RU
    private static final String COUNTRY_KEY = "country"; // пример значения получаемого по ключу: RU

    private static final LocaleHelper localeHelper = new LocaleHelper() {};

    public static void main(String[] args) {
        setLocale();
    }

    private static void setLocale() {
            String localeTag = localeHelper.getProperty( LOCALE_KEY).orElse(null);
            if (applyLocaleOrDefault(localeTag)) {
                return;
            }

            Optional<String> countryCode = localeHelper.getProperty(COUNTRY_KEY);
            countryCode.ifPresent(country -> {
                String defaultLocale = Country.getByCode(country, Country.RU).getLocale();
                applyLocaleOrDefault(defaultLocale);
            });
    }

    private static boolean applyLocaleOrDefault(String locale) {
        if (locale == null) {
            return false;
        }
        if (RU_LOCALE.equals(locale) || EN_LOCALE.equals(locale)) {
            localeHelper.setLocaleByString(locale);
        } else {
            localeHelper.setLocaleByString(EN_LOCALE);
        }
        return true;
    }
}