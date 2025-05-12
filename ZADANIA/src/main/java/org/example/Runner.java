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


        // TODO 2: Stwórz tablicę 10 wątków


        // TODO 3: Inicjalizuj obiekty MyRun i wątki


        // TODO 4: Uruchom wszystkie wątki


        // TODO 5: Uśpij główny wątek na 2 sekundy


        // TODO 6: Zatrzymaj wszystkie wątki (wywołaj stop())


        // TODO 7: Poczekaj aż wszystkie wątki się zakończą (join)



        System.out.println("Wszystkie wątki zakończone.");
    }
}
