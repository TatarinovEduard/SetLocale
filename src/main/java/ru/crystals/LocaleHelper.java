package ru.crystals;

import java.util.Optional;

public interface LocaleHelper {

    /**
     * Получить значение настройки по ключу
     * @param key - ключ
     * @return значение
     */
    default Optional<String> getProperty(String key) {
        return Optional.empty();
    }

    /**
     * Установить локаль
     * @param locale текстовое представление локали (пример ru_RU)
     */
    default void setLocaleByString(String locale) {
        // Установка локали
    }
}
