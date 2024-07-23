package ru.crystals;

import java.util.Arrays;
import java.util.Optional;

public class LocaleSetter {
    private static final String LOCALE_KEY = "locale"; // пример значения получаемого по ключу: ru_RU
    private static final String COUNTRY_KEY = "country"; // пример значения получаемого по ключу: RU
    private static final Country DEFAULT_COUNTRY = Country.RU;
    private static final String DEFAULT_LOCALE = "en_US";

    private static final LocaleHelper localeHelper = new LocaleHelperImpl();

    public static void main(String[] args) {
        setLocale();
    }

    private static void setLocale() {
        // Попытка получить локаль по ключу LOCALE_KEY
        Optional<String> localeTag = localeHelper.getProperty(LOCALE_KEY);
        if (localeTag.isPresent()) {
            applyLocaleOrDefault(localeTag.get());
            return;
        }

        // Если локаль не установлена, попытка получить локаль по коду страны
        String countryCode = localeHelper.getProperty(COUNTRY_KEY).orElse(null);
        String locale = Country.getByCode(countryCode, DEFAULT_COUNTRY).getLocale();
        applyLocaleOrDefault(locale);
    }

    private static void applyLocaleOrDefault(String locale) {
        boolean isValidLocale = Arrays.stream(Country.values())
                .anyMatch(country -> country.getLocale().equals(locale));

        if (isValidLocale) {
            localeHelper.setLocaleByString(locale);
        } else {
            localeHelper.setLocaleByString(DEFAULT_LOCALE);
        }
    }
}