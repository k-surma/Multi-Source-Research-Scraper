package com.scraper;

import java.util.ArrayList;

import java.util.List;

import java.util.Random;



public class Main {

    private static final int TOTAL_NUMBERS = 100;

    private static final int NUM_WORKERS = 4;



    public static void main(String[] args) throws InterruptedException {



        List<Integer> numbers = new ArrayList<>();

        Random random = new Random();



        // TODO 1: Wygeneruj 100 losowych liczb (1–1000)

        for (int i = 0; i < TOTAL_NUMBERS; i++) {

            numbers.add(random.nextInt(1000) + 1);

        }



        // TODO 2: Podziel listę na równe części i utwórz wątki

        List<Thread> threads = new ArrayList<>();

        List<SumWorker> workers = new ArrayList<>();



        int chunkSize = TOTAL_NUMBERS / NUM_WORKERS;



        for (int i = 0; i < NUM_WORKERS; i++) {

            int start = i * chunkSize;

            int end = (i + 1) * chunkSize;



            List<Integer> sublist = numbers.subList(start, end);

            SumWorker worker = new SumWorker(sublist, i + 1);

            Thread thread = new Thread(worker);

            threads.add(thread);

            workers.add(worker);

            thread.start();

        }



        // TODO 3: Czekaj na zakończenie wszystkich wątków

        for (Thread thread : threads) {

            thread.join();

        }



        // TODO 4: Oblicz i wypisz łączną sumę

        int totalSum = 0;

        for (SumWorker worker : workers) {

            totalSum += worker.getSum();

        }



        System.out.println("Suma wszystkich liczb: " + totalSum);

    }

}



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

        // Oblicz sumę liczb w swojej części

        for (int number : numbers) {

            sum += number;

        }

        System.out.println("[Worker-" + workerId + "] Suma = " + sum);

    }



    public int getSum() {

        return sum;

    }

}