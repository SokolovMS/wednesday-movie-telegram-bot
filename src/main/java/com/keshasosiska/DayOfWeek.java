package com.keshasosiska;

import com.google.common.base.Objects;

public enum DayOfWeek {
    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье");

    private final String dayOfWeek;

    DayOfWeek(final String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public static DayOfWeek from(final String name) {
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            if (Objects.equal(dayOfWeek.toString(), name)) {
                return dayOfWeek;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return dayOfWeek;
    }
}
