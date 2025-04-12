package com.scraper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws Exception {

        // Pobranie słowa kluczowego od użytkownika
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj słowo kluczowe do scrapowania: ");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("Nie podano żadnego słowa kluczowego. Kończenie programu.");
            return;
        }

        Queue<String> taskQueue = new ConcurrentLinkedQueue<>();
        Queue<Article> resultQueue = new ConcurrentLinkedQueue<>();
        AtomicInteger counter = new AtomicInteger(0);

        // Wczytanie URLi z pliku resources/urls.txt
        List<String> baseUrls = new ArrayList<>();
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("urls.txt")) {
            if (inputStream == null) {
                System.out.println("Plik 'urls.txt' nie został znaleziony w katalogu resources.");
                return;
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    baseUrls.add(line.trim());
                }
            }
        }

        // Produkcja URLi do odwiedzenia na podstawie słowa kluczowego
        Thread producerThread = new Thread(new UrlProducer(taskQueue, baseUrls, keyword));
        producerThread.start();
        producerThread.join();

        int totalTasks = taskQueue.size();
        if (totalTasks == 0) {
            System.out.println("Nie znaleziono pasujących URLi dla słowa kluczowego: " + keyword);
            return;
        }

        System.out.println("Znaleziono " + totalTasks + " URLi. Rozpoczynanie scrapowania...");

        // Uruchomienie workerów
        int numWorkers = 5;
        List<Thread> workers = new ArrayList<>();
        for (int i = 0; i < numWorkers; i++) {
            Thread worker = new Thread(new ScraperWorker(taskQueue, resultQueue, counter, totalTasks));
            workers.add(worker);
            worker.start();
        }

        for (Thread worker : workers) {
            worker.join();
        }

        // Zapis wyników do pliku JSON
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        String outputFileName = "scraped_articles_" + keyword + "_" + timestamp + ".json";

        try (FileWriter writer = new FileWriter(outputFileName)) {
            mapper.writeValue(writer, resultQueue);
        }

        System.out.println("Dane zapisane do pliku: " + outputFileName);
    }
}
