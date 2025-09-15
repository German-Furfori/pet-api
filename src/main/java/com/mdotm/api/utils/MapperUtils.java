package com.mdotm.api.utils;

import org.mapstruct.Condition;

public class MapperUtils {
    @Condition
    public static boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }

    @Condition
    public static boolean isPositiveOrZero(Integer value) {
        return value != null && value >= 0;
    }
}