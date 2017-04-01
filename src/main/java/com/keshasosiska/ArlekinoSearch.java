package com.keshasosiska;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArlekinoSearch {
    public List<Movie> getFilms() throws IOException {
        Document doc = Jsoup.connect("http://arlekino52.ru/#schedule_id").get();
        return getMovies(doc);
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

    private List<Movie> getMovies(final Document doc) {
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

    private Movie getMovie(final Element elMovie, final List<DayOfWeek> dayOfWeeks) {
        String name = elMovie.getElementsByClass("schedule-table__column schedule-table__column--first").text();

        TimeTable timeTable = new TimeTable(dayOfWeeks);
        for (Element element : elMovie.getElementsByClass("schedule-table__column")) {
            if (element.getElementsByClass("schedule-table__column schedule-table__column--first").size() > 0) {
                continue;
            }

            List<String> sessions = new ArrayList<String>();
            for (Element elTime : element.getElementsByClass("session-time seance")) {
                sessions.add(elTime.text());
            }
            timeTable.fillDayWithSessions(sessions);
        }

        return new Movie(name, timeTable);
    }
}
