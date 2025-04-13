# Java Multithreading – Zadania i Projekt

## Folder: `ZADANIA`

### Zadanie 1 – Dwa wątki

**Opis:**  
Napisz program, który tworzy dwa wątki:  
- jeden wypisuje liczby od 1 do 5,  
- drugi wypisuje litery od A do E.

**Wymagania:**  
Użyj klasy `Thread` lub implementacji `Runnable`.

**Plik:**  
`SimpleThreadExample.java`

---

### Zadanie 2 – Uruchamianie wielu wątków i ich synchronizacja

**Opis:**  
Napisz program w języku Java, który uruchamia 10 wątków.  
Każdy wątek wypisuje swoje ID co 100 ms. Po 2 sekundach wszystkie wątki mają zakończyć działanie w sposób kontrolowany (nie przez `interrupt()`), wykorzystując metodę `stop()` obiektu `MyRun`.

**Kroki do wykonania:**
1. Stwórz tablicę 10 obiektów klasy `MyRun`.
2. Stwórz tablicę 10 obiektów klasy `Thread`, przypisując do każdego odpowiedni obiekt `MyRun`.
3. Uruchom wszystkie wątki.
4. Uśpij główny wątek na 2 sekundy.
5. Zatrzymaj działanie wszystkich wątków wywołując `stop()` na każdym `MyRun`.
6. Poczekaj na zakończenie wątków (metoda `join()`).

**Pliki:**  
- `MyRun.java`  
- `Runner.java`

---

### Zadanie 3 – Wielowątkowe przetwarzanie liczb

**Opis:**  
Napisz program, który dzieli przetwarzanie listy liczb pomiędzy kilka wątków. Każdy wątek oblicza sumę swojej części. Po zakończeniu działania, program wypisuje sumy cząstkowe i łączną sumę.

**Wymagania:**
- Wygeneruj 100 losowych liczb całkowitych (1–1000)
- Podziel listę równomiernie (np. 4 wątki → po 25 liczb)
- Utwórz klasę `SumWorker implements Runnable`
- W `Main` uruchom wątki, poczekaj na nie (`join()`) i wypisz wyniki

**Pliki:**  
- `SumWorker.java`  
- `Main.java`

---

## Projekt: Wielowątkowy web scraper

### Opis

Aplikacja w Javie do równoległego scrapowania treści z różnych stron internetowych. Na podstawie słowa kluczowego generuje URL-e, uruchamia wątki i pobiera dane w szybki sposób, zapisując je do pliku `.json`.

---

### Funkcjonalności

- Wczytywanie bazowych adresów URL z pliku `urls.txt`
- Generowanie końcowych adresów URL na podstawie słowa kluczowego
- Równoległe scrapowanie danych za pomocą wielu wątków (`ScraperWorker`)
- Monitorowanie działania wątków (`ThreadMonitor`)
- Zapis wyników do pliku JSON z datą i słowem kluczowym

---

### Uruchomienie projektu

1. Skonfiguruj środowisko JDK (Java 11+).
2. Umieść plik `urls.txt` w folderze `resources`.
3. Uruchom klasę `Main.java`.
4. Podaj słowo kluczowe w konsoli.
5. Poczekaj na zakończenie scrapowania.
6. Wynik zostanie zapisany do pliku `.json`.

