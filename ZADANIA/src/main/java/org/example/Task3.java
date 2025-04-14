package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// ZADANIE 3

public class Task3 {

    private static final int TOTAL_NUMBERS = 100;
    private static final int NUM_WORKERS = 4;

    public static void main(String[] args) throws InterruptedException {

        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        // TODO 1: Wygeneruj 100 losowych liczb (1–1000)

        // TODO 2: Uzupełnij dzielenie listy na równe części i tworzenie wątków

        List<Thread> threads = new ArrayList<>();
        List<SumWorker> workers = new ArrayList<>();

        int chunkSize = TOTAL_NUMBERS / NUM_WORKERS;

        for (int i = 0; i < NUM_WORKERS; i++) {
            int start = i * chunkSize;
            int end = (i + 1) * chunkSize;

            // TODO: Wytnij odpowiedni fragment listy numbers od start do end
            // TODO: Utwórz obiekt SumWorker z tą sublistą i numerem wątku
            // TODO: Utwórz wątek z tym workerem i uruchom go
            // TODO: Dodaj wątek i worker do odpowiednich list
        }

        // TODO 3: Czekaj na zakończenie wszystkich wątków

        // TODO 4: Oblicz i wypisz łączną sumę
        int totalSum = 0;

        // UZUPEŁNIJ

        System.out.println("Suma wszystkich liczb: " + totalSum);
    }
}


// tu nie uzupełniamy nic
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