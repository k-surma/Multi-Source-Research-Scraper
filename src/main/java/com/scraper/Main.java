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
    private static final int NUM_WORKERS = 10;

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj słowo kluczowe do scrapowania: ");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("Nie podano słowa kluczowego. Kończenie.");
            return;
        }

        Queue<String> taskQueue = new ConcurrentLinkedQueue<>();
        Queue<Article> resultQueue = new ConcurrentLinkedQueue<>();
        AtomicInteger counter = new AtomicInteger(0);

        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            System.err.println("Błąd w wątku [" + thread.getName() + "]: " + throwable.getMessage());
            throwable.printStackTrace();
        });

        List<String> baseUrls = new ArrayList<>();
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("urls.txt")) {
            if (inputStream == null) {
                System.out.println("Plik 'urls.txt' nie został znaleziony.");
                return;
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    baseUrls.add(line.trim());
                }
            }
        }

        Thread producerThread = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("[" + threadName + "] Startuję produkcję URLi...");
            new UrlProducer(taskQueue, baseUrls, keyword).run();
            System.out.println("[" + threadName + "] Zakończono produkcję URLi.");
        }, "Producer-Thread");

        producerThread.setUncaughtExceptionHandler((t, e) -> {
            System.err.println("Błąd producenta [" + t.getName() + "]: " + e.getMessage());
        });

        producerThread.start();
        producerThread.join();

        int totalTasks = taskQueue.size();
        if (totalTasks == 0) {
            System.out.println("Brak URLi do przetworzenia.");
            return;
        }

        System.out.println("Znaleziono " + totalTasks + " URLi. Uruchamiam " + NUM_WORKERS + " scraperów...");

        List<Thread> workers = new ArrayList<>();
        for (int i = 0; i < NUM_WORKERS; i++) {
            int id = i + 1;
            Thread worker = new Thread(() -> {
                String name = Thread.currentThread().getName();
                System.out.println("[" + name + "] Rozpoczynam pracę.");
                try {
                    new ScraperWorker(taskQueue, resultQueue, counter, totalTasks).run();
                } catch (Exception e) {
                    System.err.println("[" + name + "] Wystąpił wyjątek: " + e.getMessage());
                    e.printStackTrace();
                }
                System.out.println("[" + name + "] Zakończono.");
            }, "Scraper-Worker-" + id);

            worker.setUncaughtExceptionHandler((t, e) -> {
                System.err.println("Niezłapany wyjątek w wątku [" + t.getName() + "]: " + e.getMessage());
            });

            workers.add(worker);
            worker.start();
        }

        ThreadMonitor monitor = new ThreadMonitor(workers);
        Thread monitorThread = new Thread(monitor, "Monitor");
        monitorThread.start();

        for (Thread worker : workers) {
            worker.join();
        }

        System.out.println("Wszystkie wątki zakończone. Zapisuję JSON...");

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        String outputFileName = "scraped_articles_" + keyword + "_" + timestamp + ".json";

        try (FileWriter writer = new FileWriter(outputFileName)) {
            mapper.writeValue(writer, resultQueue);
        }

        System.out.println("Dane zapisane do: " + outputFileName);
    }
}
