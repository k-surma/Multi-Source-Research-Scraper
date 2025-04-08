//package com.scraper;
//
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.LinkedBlockingQueue;
//
//public class TaskQueue {
//    private final BlockingQueue<String> urlQueue = new LinkedBlockingQueue<>();
//
//    public void addUrl(String url) {
//        urlQueue.add(url);
//    }
//
//    public String getNextUrl() throws InterruptedException {
//        return urlQueue.take();
//    }
//
//    public boolean isEmpty() {
//        return urlQueue.isEmpty();
//    }
//}
