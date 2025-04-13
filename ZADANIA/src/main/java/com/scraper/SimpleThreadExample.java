package com.scraper;

public class SimpleThreadExample {



    public static void main(String[] args) {

        // Wątek wypisujący liczby od 1 do 5

        Thread numberThread = new Thread(new Runnable() {

            @Override

            public void run() {

                for (int i = 1; i <= 5; i++) {

                    System.out.println("Liczba: " + i);

                    try {

                        Thread.sleep(500); // opcjonalne opóźnienie

                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }

                }

            }

        });



        // Wątek wypisujący litery od A do E

        Thread letterThread = new Thread(new Runnable() {

            @Override

            public void run() {

                for (char c = 'A'; c <= 'E'; c++) {

                    System.out.println("Litera: " + c);

                    try {

                        Thread.sleep(500); // opcjonalne opóźnienie

                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }

                }

            }

        });



        // Uruchom oba wątki

        numberThread.start();

        letterThread.start();

    }

}
