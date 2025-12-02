package org.example.notes;

/**
 * LN05 – INPUT / OUTPUT (I/O)
 *
 * Standardowe wejście, wyjście, wyjście błędu,
 * deskryptory plików i wszystkie mechanizmy przekierowań w Linuxie.
 *
 * Notatki – bez logiki wykonywalnej.
 */
public class LN05_InputOutput {

    /*
    ======================================================================================
    1. PODSTAWA: CO TO JEST INPUT/OUTPUT (I/O) W LINUXIE
    ======================================================================================

    I/O (Input/Output) = mechanizm komunikacji poleceń z otoczeniem.

    W Linuxie każde polecenie pracuje na trzech podstawowych strumieniach:

    - STANDARD INPUT  (stdin)  -> dane, które program otrzymuje
    - STANDARD OUTPUT (stdout) -> dane, które program wypisuje
    - STANDARD ERROR  (stderr) -> dane, które program wypisuje jako błędy

    Linux reprezentuje je za pomocą **deskryptorów plików**.
    Deskryptor = liczba całkowita, która identyfikuje „uchwyt” do strumienia.
    Programy nie operują na „plikach”, tylko na deskryptorach.
    */

    /*
    ======================================================================================
    2. DESKRYPTORY PLIKÓW – TRZY STANDARDOWE
    ======================================================================================

    Każdy proces w Linuxie w chwili uruchomienia dostaje trzy deskryptory:

    +----------------------------+-----------------+--------------+
    | Nazwa strumienia           | Deskryptor (FD) | Opis         |
    +----------------------------+-----------------+--------------+
    | Standard Input  (stdin)    |        0        | wejście      |
    | Standard Output (stdout)   |        1        | zwykłe dane  |
    | Standard Error  (stderr)   |        2        | błędy        |
    +----------------------------+-----------------+--------------+

    Wyjaśnienie:
    - FD 0: dane wchodzące do programu (czytane)
    - FD 1: to, co program wypisuje normalnie
    - FD 2: to, co program wypisuje jako błędy

    Przykład:
      cat plik.txt     -> wypisuje zawartość na FD 1
      ls /root         -> błędy trafią na FD 2
    */

    /*
    ======================================================================================
    3. WYJŚCIA PROGRAMÓW – CO LĄDUJE NA STDOUT, A CO NA STDERR?
    ======================================================================================

    stdout:
        - zwykłe wyniki programu
        - informacje poprawne
        - dane, które użytkownik chce zobaczyć

    stderr:
        - błędy
        - komunikaty o braku dostępu
        - ostrzeżenia
        - wyjątki

    Przykład:
        ls /
             -> zwraca listę katalogów na stdout

        ls /root
             -> jeśli nie masz uprawnień:
                stdout: nic
                stderr: "ls: cannot open directory '/root': Permission denied"
    */

    /*
    ======================================================================================
    4. PRZEKIEROWANIA – NAJCZĘŚCIEJ UŻYWANY MECHANIZM SHELLA
    ======================================================================================

    Shell pozwala przechwycić wyniki programów i zapisać je do pliku,
    przekierować do innego programu lub odczytać z pliku.

    -------------------------------------------------------------------------------------------------
    PRZEKIEROWANIE WYJŚCIA (STDOUT)
    -------------------------------------------------------------------------------------------------

        polecenie > plik

    -> zapisuje wynik polecenia do pliku, NADPISUJĄC go.

    Przykład:
        echo "To jest tekst" > testowy_plik

    Teraz:
        cat testowy_plik
        -> wyświetli "To jest tekst"

    Jeśli wykonasz:
        echo "Nowa linia" > testowy_plik

    -> plik zostanie ***nadpisany***.

    -------------------------------------------------------------------------------------------------
    DOPISYWANIE DO PLIKU (APPEND)
    -------------------------------------------------------------------------------------------------

        polecenie >> plik

    -> dopisuje wynik na koniec pliku, nie kasując istniejącej zawartości.

    Przykład:
        echo "Druga linia" >> testowy_plik
    */

    /*
    ======================================================================================
    5. PRZEKIEROWANIA BŁĘDÓW (STDERR)
    ======================================================================================

    stderr ma deskryptor **2**, więc używamy:

        polecenie 2> plik_błędu

    Przykład:
        ls /root 2> ls_error.log

    Wyjaśnienie:
        - stdout jest pusty (nie było normalnych wyników)
        - stderr zostanie zapisany do pliku ls_error.log

    -------------------------------------------------------------------------------------------------
    Połączenie stdout + stderr w jeden plik:
    -------------------------------------------------------------------------------------------------

        polecenie > plik 2>&1

    Wyjaśnienie:
        - stdout idzie do „plik”
        - stderr kierujemy tam, gdzie aktualnie idzie stdout
    */

    /*
    ======================================================================================
    6. PRZEKIEROWANIA WEJŚCIA (STDIN)
    ======================================================================================

        polecenie < plik

    -> oznacza: zamiast wpisywać coś z klawiatury,
                program odczyta dane z pliku.

    Przykład:
        cat < ls_error.log

    To wyświetli zawartość pliku na ekranie (czyli na stdout),
    tak jakby użytkownik wpisał jego treść ręcznie.

    Przykład bardziej praktyczny:
        wc -l < testowy_plik
    (wc -l policzy linie na podstawie pliku, jako stdin)
    */

    /*
    ======================================================================================
    7. PRZYKŁADY KOMBINACJI PRZEKIEROWAŃ
    ======================================================================================

    1) Zapisanie wyników polecenia ls do pliku:
           ls > ls_output.txt

    2) Dopisanie nowych wyników:
           ls >> ls_output.txt

    3) Zapisanie błędów do pliku:
           ls /root 2> errors.txt

    4) Zapisanie zarówno stdout, jak i stderr:
           ls /root > all.txt 2>&1

    5) Błędy → stdout (zamiana kanałów):
           ls /root 2>&1

       To oznacza:
           FD2 idzie tam, gdzie FD1

    6) Wczytanie danych z pliku jako stdin:
           sort < nazwy.txt

    7) Połączenie komend (pipe) – przekazywanie stdout → stdin:
           ls /etc | grep conf

       Przykład działania:
           ls wypisuje listę
           grep filtruje ją szukając "conf"

    PIPE nie jest przekierowaniem, ale działa na strumieniach,
    więc jest częścią I/O mechaniki.
    */

    /*
    ======================================================================================
    8. ZAIMPLEMENTOWANE RODZAJE PRZEKIEROWAŃ – PODSUMOWANIE
    ======================================================================================

    >      NADPISANIE pliku (stdout)
    >>     DOPISANIE do pliku (stdout)
    2>     zapis stderr do pliku
    2>>    dopisanie stderr do pliku
    2>&1   stderr → stdout
    <      stdin z pliku
    |      pipe: stdout jednego programu → stdin drugiego

    Deskryptory:
        0  -> standard input
        1  -> standard output
        2  -> standard error
    */

    /*
    ======================================================================================
    9. DLACZEGO TO DZIAŁA – MECHANIZM DESKRYPTORÓW
    ======================================================================================

    Shell (np. bash) przypisuje deskryptory plików do strumieni:
        - FD 0 dla wejścia,
        - FD 1 dla wyjścia,
        - FD 2 dla błędów.

    Przekierowanie zmienia *źródło* lub *cel* deskryptora.

    Przykład:
        echo "tekst" > plik

        echo pisze na FD1 (stdout),
        bash ustawia FD1 tak, żeby wskazywał plik,
        więc echo pisze do pliku zamiast na ekran.

    To samo dotyczy FD2 przy błędach.
    */

    /*
    ======================================================================================
    10. PRAKTYCZNE SCENARIUSZE
    ======================================================================================

    1) Zbieranie logów błędów:
        program 2> errors.log

    2) Zapisywanie wyników skryptów:
        ./skrypt.sh > raport.txt

    3) Połączenie wszystkiego:
        ./skrypt.sh > wynik.log 2>&1

    4) Czytanie danych z pliku:
        while read linia; do echo $linia; done < dane.txt

    5) Przekazywanie wyniku między programami:
        dmesg | grep error | tee log.txt
    */

}
