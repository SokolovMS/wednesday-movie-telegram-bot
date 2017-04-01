package com.keshasosiska;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WednesdayMovieBot extends TelegramLongPollingBot {
    public static void main(String[] args) {

        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new WednesdayMovieBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            ArlekinoSearch arlekinoSearch = new ArlekinoSearch();
            List<Movie> movies;
            try {
                movies = arlekinoSearch.getFilms();
            } catch (IOException e) {
                e.printStackTrace();
                movies = new ArrayList<Movie>();
            }
            String moviesString = generateMessage(movies);

            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .enableHtml(true)
                    .setText(moviesString);
            try {
                sendMessage(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateMessage(final List<Movie> movies) {
        StringBuilder builder = new StringBuilder();
        for (Movie movie : movies) {
            builder.append(movie.toString());
        }
        return builder.toString();
    }

    public String getBotUsername() {
        return "WednesdayMovieBot";
    }

    public String getBotToken() {
        return "111111111:AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    }
}
