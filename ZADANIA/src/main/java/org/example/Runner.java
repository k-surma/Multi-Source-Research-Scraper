package org.example;

// ZADANIE 2
//
// Uzupełnij program w języku Java, który tworzy 10 wątków:
// 1.   Utwórz tablicę 10 obiektów klasy MyRun.
// 2.   Stwórz tablicę 10 wątków (obiekty klasy Thread), każdy z przypisanym obiektem MyRun.
// 3.   Uruchom wszystkie wątki.
// 4.   Uśpij główny wątek na 2 sekundy.
// 5.   Zatrzymaj działanie wszystkich wątków, wywołując stop() na każdym obiekcie MyRun.
// 6.   Poczekaj na zakończenie wszystkich wątków (join()).

// Masz już gotową klasę MyRun, która posiada zmienną running oraz metodę stop(), dzięki której można zakończyć działanie pętli wątku.
// Twoim zadaniem jest uzupełnienie klasy Runner w odpowiednich miejscach.

public class Runner {
    public static void main(String[] args) throws InterruptedException {

        // TODO 1: Stwórz tablicę 10 MyRun
        MyRun[] runners = new MyRun[10];

        // TODO 2: Stwórz tablicę 10 wątków
        Thread[] threads = new Thread[10];

        // TODO 3: Inicjalizuj obiekty MyRun i wątki
        for (int i = 0; i < 10; i++) {
            runners[i] = new MyRun(i);
            threads[i] = new Thread(runners[i]);
        }

        // TODO 4: Uruchom wszystkie wątki
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        // TODO 5: Uśpij główny wątek na 2 sekundy
        Thread.sleep(2000);

        // TODO 6: Zatrzymaj wszystkie wątki (wywołaj stop())
        for (int i = 0; i < 10; i++) {
            runners[i].stop();
        }

        // TODO 7: Poczekaj aż wszystkie wątki się zakończą (join)
        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        System.out.println("Wszystkie wątki zakończone.");
    }
}
