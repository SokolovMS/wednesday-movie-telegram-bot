package com.keshasosiska.kinopoisk;

import static com.google.common.base.Preconditions.checkArgument;

public class KinopoiskEntry {
    private final String rating;
    private final String link;

    public KinopoiskEntry(final String rating, final String link) {
        checkArgument(rating != null);
        checkArgument(link != null);

        this.rating = rating;
        this.link = link;
    }

    public String getRating() {
        return rating;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "<b>" + rating + "</b> : " + link;
    }
}
