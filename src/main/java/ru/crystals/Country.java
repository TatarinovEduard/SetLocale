package ru.crystals;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Код страны
 */
public enum Country {
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
    private final String locale;

    private static final Map<String, Country> mapByCode = Arrays.stream(values())
            .collect(Collectors.toMap(Country::getCode, Function.identity()));

    Country(String code, String locale) {
        this.code = code;
        this.locale = locale;
    }

    public String getCode() {
        return code;
    }

    public String getLocale() {
        return locale;
    }

    public static Country getByCode(String country, Country defaultCountry) {
        if (country == null) {
            return defaultCountry;
        }
        return mapByCode.getOrDefault(country, defaultCountry);
    }
}
