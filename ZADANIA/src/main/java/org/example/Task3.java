package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// ZADANIE 3
//
// Napisz program, który dzieli listę 100 losowych liczb pomiędzy kilka wątków. Każdy wątek oblicza sumę swojej części liczb,
// a po zakończeniu pracy wszystkich wątków wypisywana jest suma cząstkowa z każdego wątku oraz łączna suma wszystkich liczb.
// 1.   Wygeneruj listę 100 losowych liczb całkowitych z zakresu 1–1000.
// 2.   Podziel listę równomiernie na kilka części (np. 4 wątki → po 25 liczb na wątek).
// *3.  OPCJONALNE: Utwórz klasę SumWorker, która implementuje Runnable i oblicza sumę swojej części listy (sumuje liczby z danego
//      wątku). Jest to zadanie opcjonalne, gdyż SumWorker jest już zaimplementowany, jednak bardzoiej ambitni mogą nie
//      korzystać z gotowego rozwiązania tylko spróbować zaimplementować je samodzielnie.
// 4.   W klasie Task3 (main) uruchom odpowiednią liczbę wątków. Poczekaj na zakończenie każdego z nich (join()).
// 5    Oblicz i wypisz łączną sumę wszystkich liczb (skorzystaj z SumWorkera!).
//
// UWAGI: Klasa SumWorker została już poprawnie przygotowana i nie wymaga żadnych zmian.
// Kolejność wypisywanych sum z wątków może się różnić przy każdym uruchomieniu programu, ponieważ wątki działają równolegle.

public class Task3 {

    // TODO 0*: Uzupelnij o wybraną liczbę wątków - dla ułatwienia domyślnie ustawiono 4, możesz podzielić na inną ilość wątków
    //  oraz bawić się w inną ilość liczb
    private static final int TOTAL_NUMBERS = 100;
    private static final int NUM_WORKERS = 4;

    public static void main(String[] args) throws InterruptedException {

        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        // TODO 1: Wygeneruj 100 losowych liczb z zakresu 1–1000 i dodaj je do listy "numbers"
        ...

        // TODO 2: Podziel listę na równe części i utwórz dla każdej osobny wątek
        //  (w podpunktach bardziej szczegółowe instrukcje)

        List<Thread> threads = new ArrayList<>();
        List<SumWorker> workers = new ArrayList<>();

        int chunkSize = TOTAL_NUMBERS / NUM_WORKERS;

        for (int i = 0; i < NUM_WORKERS; i++) {
            int start = i * chunkSize;
            int end = (i + 1) * chunkSize;

            // TODO 2.1: Wyodrębnij fragment listy "numbers" — od indeksu start do end

            // TODO 2.2: Utwórz nowy obiekt SumWorker, przekaż mu fragment listy i numer wątku (i + 1)

            // TODO 2.3: Stwórz nowy wątek z tym SumWorkerem i uruchom go (start())

            // TODO 2.4: Dodaj wątek i obiekt SumWorker do odpowiednich list (threads i workers)

        }


        // TODO 3: Poczekaj na zakończenie działania wszystkich wątków — użyj metody join()
        // Przejdź pętlą po liście threads i dla każdego elementu wywołaj join()


        // TODO 4: Oblicz sumę wszystkich wyników z wątków i wypisz ją
        // Przejdź po liście workers, pobierz z każdego sumę (getSum()) i dodaj do totalSum

        int totalSum = 0;

        // UZUPEŁNIJ powyższy krok 4 tutaj
        ...

        System.out.println("Suma wszystkich liczb: " + totalSum);
    }
}


// tu nie uzupełniamy nic - chyba że jesteśmy ambitni :))
class SumWorker implements Runnable {

    private final List<Integer> numbers;
    private final int workerId;
    private int sum;

    public SumWorker(List<Integer> numbers, int workerId) {
        this.numbers = numbers;
        this.workerId = workerId;
        this.sum = 0;
    }

    @Override
    public void run() {
        for (int number : numbers) {
            sum += number;
        }
        System.out.println("[Worker-" + workerId + "] Suma = " + sum);
    }
    public int getSum() {
        return sum;
    }
}