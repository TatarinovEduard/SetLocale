package ru.crystals;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LocaleHelperImpl implements LocaleHelper {
    private final Map<String, String> properties = new HashMap<>();

    public LocaleHelperImpl() {
        // Здесь можно положить любые значения
        properties.put("locale", "");
        properties.put("country", "");
    }

    @Override
    public Optional<String> getProperty(String key) {
        return Optional.ofNullable(properties.get(key));
    }

    @Override
    public void setLocaleByString(String locale) {
        System.out.println("Установлена локаль: " + locale);
    }
}
