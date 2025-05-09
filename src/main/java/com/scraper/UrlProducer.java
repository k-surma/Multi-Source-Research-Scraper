package com.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Queue;

public class UrlProducer implements Runnable {

    private final Queue<String> taskQueue;
    private final List<String> baseUrls;
    private final String keyword;

    public UrlProducer(Queue<String> taskQueue, List<String> baseUrls, String keyword) {
        this.taskQueue = taskQueue;
        this.baseUrls = baseUrls;
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public void run() {
        for (String baseUrl : baseUrls) {
            try {
                Document doc = Jsoup.connect(baseUrl).get();
                Elements links = doc.select("a[href]");
                for (Element link : links) {
                    String href = link.absUrl("href");
                    String linkText = link.text().toLowerCase();
                    if ((href.toLowerCase().contains(keyword) || linkText.contains(keyword))
                            && href.startsWith("http")) {
                        taskQueue.add(href);
                    }
                }
                System.out.println("URL Producer przetworzyl: " + baseUrl);
            } catch (Exception e) {
                System.out.println("Nie udalo sie przetworzyc: " + baseUrl + "; wyjatek: " + e.getMessage());
            }
        }
    }
}
