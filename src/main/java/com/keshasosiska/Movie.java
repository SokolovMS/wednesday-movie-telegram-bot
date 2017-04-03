package com.keshasosiska;

import com.keshasosiska.kinopoisk.KinopoiskEntry;

public class Movie {
    private final String name;
    private final TimeTable timeTable;
    private KinopoiskEntry kinopoiskEntry;

    public Movie(final String name, final TimeTable timeTable) {
        this.name = name;
        this.timeTable = timeTable;
    }

    public String getName() {
        return name;
    }

    public void setKinopoiskEntry(final KinopoiskEntry kinopoiskEntry) {
        this.kinopoiskEntry = kinopoiskEntry;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("<b>").append(name).append("</b>").append("\n");
        if (kinopoiskEntry != null) {
            builder.append(kinopoiskEntry).append("\n");
        }
        builder.append(timeTable);
        builder.append("\n");

        return builder.toString();
    }
}
