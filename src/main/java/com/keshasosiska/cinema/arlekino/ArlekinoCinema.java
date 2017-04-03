package com.keshasosiska.cinema.arlekino;

import com.keshasosiska.DayOfWeek;
import com.keshasosiska.Movie;
import com.keshasosiska.Session;
import com.keshasosiska.TimeTable;
import com.keshasosiska.cinema.Cinema;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArlekinoCinema implements Cinema {
    public List<Movie> getMovies() {
        Document doc;
        try {
            doc = Jsoup.connect("http://arlekino52.ru/#schedule_id").get();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Movie>();
        }

        return getMoviesFromSite(doc);
    }

    private List<Movie> getMoviesFromSite(final Document doc) {
        Elements elMovies = doc.body().select("#schedule_id")
                .select(".schedule-table__row");

        List<DayOfWeek> dayOfWeeks = getWeekDays(doc);
        List<Movie> movies = new ArrayList<Movie>();
        for (Element elMovie : elMovies) {
            if (elMovie.getElementsByClass("schedule-table__row schedule-table__row--day").size() > 0) {
                continue;
            }

            movies.add(getMovie(elMovie, dayOfWeeks));
        }
        return movies;
    }

    private List<DayOfWeek> getWeekDays(final Document doc) {
        Elements elWeekDays = doc.body().select("#schedule_id")
                .select(".schedule-table__day");
        List<DayOfWeek> weekDays = new ArrayList<DayOfWeek>();
        for (Element element : elWeekDays) {
            weekDays.add(DayOfWeek.from(element.text()));
        }
        return weekDays;
    }

    private Movie getMovie(final Element elMovie, final List<DayOfWeek> dayOfWeeks) {
        String name = elMovie.getElementsByClass("schedule-table__column schedule-table__column--first").text();

        TimeTable timeTable = new TimeTable(dayOfWeeks);
        for (Element element : elMovie.getElementsByClass("schedule-table__column")) {
            if (element.getElementsByClass("schedule-table__column schedule-table__column--first").size() > 0) {
                continue;
            }

            List<Session> sessions = new ArrayList<Session>();
            for (Element elTime : element.getElementsByClass("session-time seance")) {
                String price = getPrice(element).replaceAll(" ", "");
                sessions.add(new Session(elTime.text(), price));
            }
            timeTable.fillDayWithSessions(sessions);
        }

        return new Movie(name, timeTable);
    }

    private String getPrice(final Element element) {
        String prices = element.getElementsByClass("session-time seance").get(0).attr("data-content");
        Document html = Jsoup.parse(prices);
        return html.body().getElementsByClass("cost").text();
    }
}
