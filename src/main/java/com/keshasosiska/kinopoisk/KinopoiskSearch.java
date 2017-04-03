package com.keshasosiska.kinopoisk;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class KinopoiskSearch {
    public static KinopoiskEntry findMovie(final String movieName) {
        Document doc;
        try {
            String movieUrl = getMovieUrl(movieName);
            doc = Jsoup.connect("https://www.kinopoisk.ru/index.php?first=no&what=&kp_query=" + movieUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return parseSearchResults(doc);
    }

    /**
     *
     * @param movieName Name of the movie
     * @return movieName to be used in URL. Windows-1251.
     */
    static String getMovieUrl(final String movieName) {
        try {
            return URLEncoder.encode(movieName, "cp1251");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static KinopoiskEntry parseSearchResults(final Document doc) {
        Element firstSearchResult = doc.body().getElementsByClass("search_results").first();
        if (firstSearchResult == null) {
            return null;
        }

        Element ratingElement = firstSearchResult.getElementsByClass("rating").first();
        if (ratingElement == null) {
            return null;
        }
        String rating = ratingElement.text();

        Element linkElement = firstSearchResult.getElementsByClass("pic").first();
        if (linkElement == null) {
            return null;
        }
        // TODO: Can be empty.
        String offsetLink = linkElement.getElementsByAttributeStarting("data-url").attr("data-url");
        String fullLink = "https://www.kinopoisk.ru" + offsetLink;

        return new KinopoiskEntry(rating, fullLink);
    }
}
