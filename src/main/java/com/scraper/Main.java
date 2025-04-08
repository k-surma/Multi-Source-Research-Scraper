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

        if (args.length == 0) {
            System.out.println("Please provide a search keyword as an argument.");
            return;
        }

        String keyword = args[0];
        Queue<String> taskQueue = new ConcurrentLinkedQueue<>();
        Queue<Article> resultQueue = new ConcurrentLinkedQueue<>();
        AtomicInteger counter = new AtomicInteger(0);

        // Read base URLs from resources
        List<String> baseUrls = new ArrayList<>();
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("urls.txt")) {
            if (inputStream == null) {
                System.out.println("The file 'urls.txt' was not found in resources.");
                return;
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    baseUrls.add(line.trim());
                }
            }
        }

        // Produce initial tasks based on keyword
        Thread producerThread = new Thread(new UrlProducer(taskQueue, baseUrls, keyword));
        producerThread.start();
        producerThread.join(); // wait until URLs are found

        int totalTasks = taskQueue.size();
        if (totalTasks == 0) {
            System.out.println("No matching URLs found for keyword: " + keyword);
            return;
        }

        System.out.println("Found " + totalTasks + " relevant URLs. Starting scraper...");

        // Start worker threads
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

        // Write results to JSON
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        String outputFileName = "scraped_articles_" + keyword + "_" + timestamp + ".json";

        try (FileWriter writer = new FileWriter(outputFileName)) {
            mapper.writeValue(writer, resultQueue);
        }

        System.out.println("Data written to " + outputFileName);
    }
}
