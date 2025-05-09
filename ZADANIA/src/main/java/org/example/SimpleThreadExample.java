package org.example;

// ZADANIE 1
//
// Uzupełnij program w języku Java, który tworzy i uruchamia dwa niezależne wątki:
// 1.   Uzupełnij pierwszy wątek, który w pętli wypisze na konsolę liczby od 1 do 5.
//      Pomiędzy kolejnymi wypisaniami możesz zastosować opóźnienie 0.5s (użyj Thread.sleep()).
//      PROTIP: Obejmij opóźnienie konstrukcją try/catch
// 2.   Utwórz drugi wątek, który w pętli wypisze na konsolę litery od A do E.
//      Pomiędzy kolejnymi wypisaniami również zastosuj opóźnienie 0.5s.
// 3.   Uruchom oba wątki i zaobserwuj ich współbieżne działanie
//      – wypisywane liczby i litery powinny przeplatać się w zależności od harmonogramu wątków.
//
//  UWAGI: Użyj klasy Thread oraz interfejsu Runnable do implementacji zadań dla wątków.



public class SimpleThreadExample {

    public static void main(String[] args) {

        // TODO 1: Utwórz wątek wypisujący liczby od 1 do 5
        Thread numberThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("Liczba: " + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // TODO 2: Utwórz wątek wypisujący litery od A do E
        Thread letterThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (char c = 'A'; c <= 'E'; c++) {
                    System.out.println("Litera: " + c);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // TODO 3: Uruchom oba wątki
        numberThread.start();
        letterThread.start();
    }
}
