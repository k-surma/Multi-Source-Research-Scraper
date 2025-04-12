package com.scraper;

import java.util.List;

public class ThreadMonitor implements Runnable {

    private final List<Thread> threads;

    public ThreadMonitor(List<Thread> threads) {
        this.threads = threads;
    }

    @Override
    public void run() {
        try {
            while (true) {
                long aliveCount = threads.stream().filter(Thread::isAlive).count();

                System.out.println("─────────────────────────────────────");
                System.out.println("[Monitor] Aktywne wątki: " + aliveCount + "/" + threads.size());
                System.out.println("─────────────────────────────────────");

                if (aliveCount == 0) {
                    break;
                }

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("[Monitor] Przerwano monitorowanie wątków: " + e.getMessage());
        }
    }
}
