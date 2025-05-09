package com.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ScraperWorker implements Runnable {

    private final Queue<String> taskQueue;
    private final Queue<Article> resultQueue;
    private final AtomicInteger counter;
    private final int totalTasks;

    public ScraperWorker(Queue<String> taskQueue, Queue<Article> resultQueue, AtomicInteger counter, int totalTasks) {
        this.taskQueue = taskQueue;
        this.resultQueue = resultQueue;
        this.counter = counter;
        this.totalTasks = totalTasks;
    }

    @Override
    public void run() {
        String url;
        while ((url = taskQueue.poll()) != null) {
            try {
                Document doc = Jsoup.connect(url).get();
                String title = doc.title();
                String content = doc.body().text();
                resultQueue.add(new Article(url, title, content));
                int done = counter.incrementAndGet();
                System.out.println("[" + done + "/" + totalTasks + "] Przetworzony: " + url);
            } catch (Exception e) {
                System.out.println("Nie udalo sie zescrapowac URL: " + url + "; wyjatek: " + e.getMessage());
            }
        }
    }
}
