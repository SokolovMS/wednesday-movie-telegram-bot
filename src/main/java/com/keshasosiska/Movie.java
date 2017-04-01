package com.keshasosiska;

public class Movie {
    private final String name;
    private final TimeTable timeTable;
    private String rate;

    public Movie(final String name, final TimeTable timeTable) {
        this.name = name;
        this.timeTable = timeTable;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("<b>").append(name).append("</b>");
        builder.append("\n");
        builder.append(timeTable);
        builder.append("\n");

        return builder.toString();
    }
}
