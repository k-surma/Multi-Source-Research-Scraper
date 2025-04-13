package com.scraper;

// ZADANIE 2 - DO UZUPEŁNIENIA

public class Runner {

    public static void main(String[] args) throws InterruptedException {

        // TODO: Stwórz tablicę 10 obiektów MyRun

        MyRun[] runners = new MyRun[10];



        // TODO: Stwórz tablicę 10 wątków

        Thread[] threads = new Thread[10];



        // TODO: Inicjalizuj obiekty MyRun i wątki

        for (int i = 0; i < 10; i++) {

            runners[i] = new MyRun(i);

            threads[i] = new Thread(runners[i]);

        }



        // TODO: Uruchom wszystkie wątki

        for (int i = 0; i < 10; i++) {

            threads[i].start();

        }



        // TODO: Poczekaj 2 sekundy

        Thread.sleep(2000);



        // TODO: Zatrzymaj wszystkie wątki

        for (int i = 0; i < 10; i++) {

            runners[i].stop();

        }



        // TODO: Poczekaj aż wszystkie wątki się zakończą

        for (int i = 0; i < 10; i++) {

            threads[i].join();

        }



        System.out.println("Wszystkie wątki zakończyły działanie.");

    }

}
