package com.scraper;

// ZADANIE 1

public class SimpleThreadExample {
    public static void main(String[] args) {

        // TODO 1: Utwórz wątek wypisujący liczby od 1 do 5
        Thread numberThread = new Thread(new Runnable() {
            // TU UZUPEŁNIJ
        });

        // TODO 2: Utwórz wątek wypisujący litery od A do E

        Thread letterThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (char c = 'A'; c <= 'E'; c++) {
                    // TU UZUPEŁNIJ
                }
            }
        });

        // TODO 3: Uruchom oba wątki
    }
}
