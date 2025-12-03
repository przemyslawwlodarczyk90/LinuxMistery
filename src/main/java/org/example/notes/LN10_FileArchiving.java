package org.example.notes;

/**
 * LN10 – ARCHIWIZACJA PLIKÓW (gzip, gunzip, tar, tar.gz, bzip2, wget)
 *
 * Jak tworzyć archiwa, rozpakowywać, łączyć kompresję z archiwami,
 * jak działa tar, gzip, bzip2, gunzip i wget.
 */
public class LN10_FileArchiving {

    /*
    ======================================================================================
    1. gzip – KOMPRACJA JEDNEGO PLIKU
    ======================================================================================

    gzip plik.txt
        -> kompresuje plik.txt do pliku:
           plik.txt.gz
        -> oryginalny plik jest USUWANY po kompresji

    WAŻNE:
        gzip kompresuje TYLKO POJEDYNCZE pliki.
        Jeśli podasz kilka plików:
            gzip a.txt b.txt c.txt
        → powstaną:
            a.txt.gz
            b.txt.gz
            c.txt.gz
        → NIE powstanie jedno archiwum wieloplikowe!

    Aby zachować oryginał:
        gzip -k plik.txt
        -> tworzy plik.txt.gz bez usuwania plik.txt
    */

    /*
    ======================================================================================
    2. gunzip – ROZPAKOWYWANIE gzip
    ======================================================================================

    gunzip plik.txt.gz
        -> wypakowuje do:
           plik.txt
        -> usuwa plik .gz

    Opcja bez usuwania:
        gunzip -k plik.txt.gz
    */

    /*
    ======================================================================================
    3. tar – ARCHIWIZACJA WIELOPLIKOWA
    ======================================================================================

    gzip kompresuje 1 plik, ale tar:
        - nie kompresuje, tylko ARCHIWIZUJE
        - potrafi tworzyć archiwum zawierające:
            * pliki
            * katalogi
            * podkatalogi
            * całe systemy plików

    SYNTAX:
        tar cvf archiwum.tar lista_plików_i_katalogów

    Co oznaczają litery?

        c -> create (tworzenie archiwum)
        v -> verbose (wypisywanie zawartości podczas pracy)
        f -> file (nazwa archiwum, ZAWSZE ostatni argument przed listą plików)

    PRZYKŁADY:

        tar cvf backup.tar plik1.txt plik2.txt katalog/

        tar cvf projekt.tar src/ README.md .gitignore

        tar cvf home_backup.tar /home/przemek/

    UWAGA:
        tar NIE kompresuje.
        Tworzy „gołe” archiwum .tar
    */

    /*
    ======================================================================================
    4. PRZEGLĄDANIE ARCHIWUM TAR
    ======================================================================================

    tar tvf archiwum.tar

    litery:
        t -> test / list (pokaż zawartość)
        v -> verbose (szczegóły)
        f -> file (archiwum)

    Wyświetla:
        - nazwy plików
        - rozmiary
        - typy plików
        - strukturę katalogów
    */

    /*
    ======================================================================================
    5. ROZPAKOWANIE ARCHIWUM TAR
    ======================================================================================

    tar xvf archiwum.tar

    x -> extract (rozpakowanie)
    v -> verbose
    f -> file (nazwa archiwum)

    Przykład:
        tar xvf backup.tar
    */

    /*
    ======================================================================================
    6. KOMBINACJA: TAR + GZIP -> TAR.GZ
    ======================================================================================

    Dlaczego tar.gz?
        - gzip nie obsługuje wielu plików
        - tar archiwizuje wiele plików
        - gzip kompresuje archiwum

    Krok po kroku:

        1) tar cvf archiwum.tar katalog/
        2) gzip archiwum.tar
           -> archiwum.tar.gz

    Rozpakowanie ręczne:
        1) gunzip archiwum.tar.gz
        2) tar xvf archiwum.tar

    ALE MOŻNA TO ZROBIĆ JEDNYM POLECENIEM:

        tar xzvf archiwum.tar.gz

    gdzie:
        x -> extract
        z -> użyj gzip (rozpakuj)
        v -> verbose
        f -> file

    Zrobienie archiwum tar.gz jednym poleceniem:

        tar czvf archiwum.tar.gz katalog/

    c    -> create
    z    -> gzip
    v    -> verbose
    f    -> file
    */

    /*
    ======================================================================================
    7. STRUMIENIOWE ROZPAKOWANIE – zcat | tar
    ======================================================================================

    zcat archiwum.tar.gz | tar xvf -

    - zcat wypakowuje gzip na stdout
    - "|" przekazuje dane do tar
    - tar xvf - oznacza: czytaj archiwum z wejścia standardowego
    */

    /*
    ======================================================================================
    8. BZIP2 – KOMPREJA LEPSZA NIŻ GZIP
    ======================================================================================

    bzip2 plik.txt
        -> kompresuje do plik.txt.bz2
        -> usuwa oryginał

    bunzip2 plik.txt.bz2
        -> rozpakowuje

    tar + bzip2:

        tar cjvf archiwum.tar.bz2 katalog/

    litera „j”:
        j -> użyj bzip2

    Rozpakowanie:
        tar xjvf archiwum.tar.bz2

    Porównanie gzip vs bzip2:

        - gzip → szybszy, gorsza kompresja
        - bzip2 → wolniejszy, lepsza kompresja
    */

    /*
    ======================================================================================
    9. wget – POBIERANIE PLIKÓW Z INTERNETU
    ======================================================================================

    wget URL
        -> pobiera plik z internetu

    Przykłady:
        wget https://example.com/plik.txt
        wget https://cdn.site.com/app.tar.gz

    Niektóre opcje wget:

        wget -O nazwa.txt URL
            -> pobranie i zapis do konkretnej nazwy

        wget -c URL
            -> wznawia przerwane pobieranie (continue)

        wget -r URL
            -> pobiera całą stronę rekurencyjnie (do scrapingu)

        wget --limit-rate=200k URL
            -> ograniczenie prędkości

        wget -q URL
            -> pobieranie w trybie cichym (quiet)
    */

    /*
    ======================================================================================
    10. PORÓWNANIE PRĘDKOŚCI I KOMPRACJI FORMATÓW
    ======================================================================================

    1) GZIP
        + szybki
        + niski narzut CPU
        - słabsza kompresja
        Idealny do:
            - logów
            - kopii szybkich
            - dużych plików tekstowych

    2) BZIP2
        + dużo lepsza kompresja
        - zauważalnie wolniejszy
        Idealny do:
            - backupów serwerowych
            - archiwów kodu
            - archiwów bibliotecznych

    3) TAR (bez kompresji)
        + najszybszy
        - brak zmniejszenia rozmiaru
        Idealny gdy:
            - kopiujesz strukturę katalogów
            - robisz snapshot systemu

    TYPOWE RÓŻNICE:

        format      kompresja   szybkość
        -------------------------------------
        tar         brak        ultraszybki
        tar.gz      średnia     szybki
        tar.bz2     najlepsza   wolniejszy

    ŚREDNIE RÓŻNICE (dla dużych plików):

        gzip  → 30–60% kompresji
        bzip2 → 50–70% kompresji
    */

    /*
    ======================================================================================
    11. PODSUMOWANIE
    ======================================================================================

    - gzip → kompresja jednego pliku (.gz)
    - gunzip → rozpakowanie
    - tar → archiwizacja wieloplikowa
    - tar czvf → tworzy tar.gz
    - tar xzvf → rozpakowuje tar.gz jednym poleceniem
    - bzip2 → lepsza kompresja niż gzip (wolniejszy)
    - wget → pobieranie danych z internetu
    - zcat i pipe → rozpakowanie strumieniowe

    To fundament administracji i pracy na Linuxie.
    */
}
