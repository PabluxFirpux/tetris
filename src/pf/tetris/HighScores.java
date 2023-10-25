package pf.tetris;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HighScores {

    public static ScoreToSubmit[] getScores() {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://tetrisapi.onrender.com/highscores")).GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            ScoreToSubmit[] scores = gson.fromJson(response.body(), ScoreToSubmit[].class);
            return scores;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static String[] getScoresString() {
        ScoreToSubmit[] scores = getScores();
        if(scores.length < 3){
            String[] errorString = new String[1];
            errorString[0] = "Error getting scores";
            return errorString;
        } else {
            String[] scoresString = new String[scores.length];
            for (int i = 0; i < scores.length; i++) {
                scoresString[i] = (i+1)+ ". "+scores[i].name + " " + scores[i].score;
            }
            return scoresString;
        }
    }

    public static int getLowestScore() {
        ScoreToSubmit[] scores = getScores();
        int lowestScore = 0;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i].score < lowestScore) {
                lowestScore = scores[i].score;
            }
        }
        return lowestScore;
    }

    public static void addScore(String name, int score) {
        ScoreToSubmit scoreToSubmit = new ScoreToSubmit(name, score);
        Gson gson = new Gson();
        String json = gson.toJson(scoreToSubmit);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://tetrisapi.onrender.com/submit")).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(json)).build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
