package com.example.carracinggame;

import java.io.*;
import java.util.*;

public class Leaderboard {
    private String filename; // Filnavn for å lagre poengsummer
    private List<Long> scores; // Liste over poengsummer

    public Leaderboard(String filename) {
        this.filename = filename;
        this.scores = new ArrayList<>();
        loadScores(); // Leser eksisterende poengsummer fra filen
    }

    private void loadScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(Long.parseLong(line)); // Konverterer og legger til poengsummer
            }
        } catch (IOException ignored) {} // Ignorerer feil hvis filen ikke finnes
    }

    public void addScore(long score) {
        scores.add(score);
        Collections.sort(scores, Collections.reverseOrder()); // Sorterer poengsummene i synkende rekkefølge
        if (scores.size() > 5) scores = scores.subList(0, 5); // Beholder kun de 5 beste poengsummene
        saveScores(); // Lagrer de oppdaterte poengsummene til filen
    }

    private void saveScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Long s : scores) writer.write(s + "\n"); // Skriver poengsummer til filen
        } catch (IOException ignored) {} // Ignorerer feil ved skriving
    }

    public void display() {
        System.out.println("Leaderboard:");
        for (int i = 0; i < scores.size(); i++) {
            System.out.println((i + 1) + ". " + scores.get(i) + "s"); // Viser poengsummene
        }
    }
}
