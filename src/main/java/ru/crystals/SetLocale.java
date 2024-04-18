package ru.crystals;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SetLocale {

    private final static String RU_LOCALE = "ru_RU";
    private final static String EN_LOCALE = "en_US";

    private static final String LOCALE_KEY = "locale"; // пример значения получаемого по ключу: ru_RU
    private static final String COUNTRY_KEY = "country"; // пример значения получаемого по ключу: RU


    public static void main(String[] args) {
        setLocale();
    }

    // Начало
    private static void setLocale() {
            String localeTag = getProperty( LOCALE_KEY).orElse(null);
            if (applyLocaleOrDefault(localeTag)) {
                return;
            }

            Optional<String> countryCode = getProperty(COUNTRY_KEY);
            countryCode.ifPresent(country -> {
                String defaultLocale = Country.getByCode(country, Country.RU).getDefaultLocale();
                applyLocaleOrDefault(defaultLocale);
            });
    }

    private static boolean applyLocaleOrDefault(String locale) {
        if (locale == null) {
            return false;
        }
        if (RU_LOCALE.equals(locale) || EN_LOCALE.equals(locale)) {
            setLocaleByString(locale);
        } else {
            setLocaleByString(EN_LOCALE);
        }
        return true;
    }
    // Окончание


    // Вспомогательные методы
    private static Optional<String> getProperty(String key) {
        return Optional.empty();
    }

    private static void setLocaleByString(String locale) {
        // Установка локали
    }

    enum Country {
        RU("RU", "ru_RU"),
        BY("BY", "ru_RU"),
        DE("DE", "de_DE"),
        AZ("AZ", "az_AZ"),
        QA("QA", "en_US");
        /**
         * Двухбуквенный код страны
         */
        private final String code;
        /**
         * Локаль по умолчанию (значение согласно IETF BCP 47, должно быть понятно методу {@link java.util.Locale#forLanguageTag(String)})
         */
        private final String defaultLocale;

        private static final Map<String, Country> mapByCode = Arrays.stream(values())
                .collect(Collectors.toMap(Country::getCode, Function.identity()));

        Country(String code, String defaultLocale) {
            this.code = code;
            this.defaultLocale = defaultLocale;
        }

        public String getCode() {
            return code;
        }

        public String getDefaultLocale() {
            return defaultLocale;
        }

        public static Country getByCode(String country, Country defaultCountry) {
            if (country == null) {
                return defaultCountry;
            }
            return mapByCode.getOrDefault(country, defaultCountry);
        }
    }

}