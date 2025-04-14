package org.example;

// KLASA DO ZADANIA 2 - TU NIC NIE UZUPEŁNIAMY

public class MyRun implements Runnable {
    private int id;
    private volatile boolean running = true;

    public MyRun(int id) {
        this.id = id;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            System.out.println("Wątek " + id);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Wątek " + id + " został przerwany.");
                break;
            }
        }
        System.out.println("Wątek " + id + " zakończył pracę.");
    }
}
