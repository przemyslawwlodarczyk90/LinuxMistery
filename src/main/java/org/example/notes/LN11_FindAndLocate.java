package org.example.notes;

/**
 * LN11 – WYSZUKIWANIE PLIKÓW I KATALOGÓW (find, locate, regex, size, mtime, exec)
 *
 * Kompletny opis działania find + locate:
 * - wyszukiwanie po nazwach,
 * - typach plików,
 * - datach modyfikacji,
 * - rozmiarach,
 * - wyrażenia regularne,
 * - działanie updatedb,
 * - locate vs find,
 * - komendy exec i -ls.
 */
public class LN11_FindAndLocate {

    /*
    ======================================================================================
    1. find – NAJPOTĘŻNIEJSZE NARZĘDZIE DO WYSZUKIWANIA W LINUXIE
    ======================================================================================

    find SZUKAJ_GDZIE OPCJE

    Najprostsze:
        find .
        -> przeszukuje bieżący katalog i WSZYSTKIE podkatalogi
           wypisując całą strukturę

    Dlaczego find jest POWOLNY?
        - bo chodzi fizycznie po dysku
        - sprawdza każdy plik i katalog
        - nie używa indeksów

    find działa w czasie rzeczywistym.
    locate działa z indeksu.

    */

    /*
    ======================================================================================
    2. PODSTAWY: WYSZUKIWANIE PO NAZWIE
    ======================================================================================

    Szukasz pliku dokładnie po nazwie:

        find . -name "plik.txt"

    -name → bierze pod uwagę wielkość liter

    Przykłady:
        find /etc -name "ssh_config"
        find . -name "*.log"
        find /var/log -name "*.gz"

    ***WERSJA NIEWRAŻLIWA NA WIELKOŚĆ LITER***

        find . -iname "plik.txt"
        find . -iname "*.TXT"
        find . -iname "rEpOrT*.pdf"

    -iname → ignoruje uppercase/lowercase

    */

    /*
    ======================================================================================
    3. WYSZUKIWANIE Z REGEX – -regex
    ======================================================================================

    find . -regex ".*\\.txt"

    Uwaga: regex musi pasować do pełnej ścieżki.

    Przykłady praktyczne:
        find . -regex ".*[0-9]{4}\\.log"
        -> pliki kończące się na 4 cyfry + .log

        find /var -regex ".*error.*"
        -> pliki zawierające „error” w nazwie lub ścieżce
    */

    /*
    ======================================================================================
    4. WYSZUKIWANIE PO TYPACH – -type
    ======================================================================================

    -type f  → zwykłe pliki (files)
    -type d  → katalogi (directories)
    -type l  → linki symboliczne
    -type b  → block devices
    -type c  → character devices

    Przykłady:

        find . -type f -name "*.txt"
        -> znajdujemy tylko pliki

        find /etc -type d -name "ssh"
        -> tylko katalogi

        find . -type l
        -> tylko dowiązania
    */

    /*
    ======================================================================================
    5. WYSZUKIWANIE PO ROZMIARZE – -size
    ======================================================================================

    Format:
        +  → większe niż
        -  → mniejsze niż
        nic → dokładnie

    Jednostki:
        c → bajty
        k → kilobajty
        M → megabajty
        G → gigabajty

    Przykłady:

        find . -size +10M
        -> pliki większe niż 10 MB

        find . -size -1k
        -> pliki mniejsze niż 1 KB

        find /var/log -size +100k -size -10M
        -> pliki między 100 KB a 10 MB
    */

    /*
    ======================================================================================
    6. WYSZUKIWANIE PO DACIE MODYFIKACJI – -mtime, -mmin
    ======================================================================================

    mtime → dni
    mmin  → minuty

    ZASADA:
        -mtime +10  -> starsze niż 10 dni
        -mtime -10  -> nowsze niż 10 dni
        -mtime 10   -> dokładnie 10 dni

    Przykłady:

        find . -mtime -1
        -> pliki modyfikowane w ciągu ostatnich 24 godzin

        find . -mtime +7
        -> pliki starsze niż tydzień

        find . -mtime +10 -mtime -13
        -> starsze niż 10 dni, ale nie starsze niż 13

    minuty:

        find . -mmin -30
        -> plik modyfikowany w ostatnich 30 minutach
    */

    /*
    ======================================================================================
    7. WYSZUKIWANIE PO ŚCIEŻCE ZAWIERAJĄCEJ "/" – PRZYKŁADY
    ======================================================================================

    find /bin -name "*v" -ls

    Co się dzieje:

    -name "*v"
        -> pliki kończące się literą "v"

    -ls
        -> wyświetla szczegółowe informacje w stylu ls -l

    Wynik zawiera:
        - uprawnienia
        - właściciela
        - rozmiar
        - datę
        - pełną ścieżkę
    */

    /*
    ======================================================================================
    8. WYKONYWANIE KOMEND NA ZNALEZIONYCH PLIKACH – -exec
    ======================================================================================

    Składnia:
        find . -name "*.log" -exec rm {} \;

    {}   → miejsce gdzie find wstawia ścieżkę pliku
    \;   → zakończenie komendy exec

    Przykłady:

        find . -type f -name "*.tmp" -exec rm {} \;
        -> usuwa wszystkie pliki .tmp

        find /var/log -name "*.gz" -exec gunzip {} \;
        -> rozpakuj wszystkie .gz

        find . -size +100M -exec ls -lh {} \;
        -> pokaż duże pliki z rozmiarem

    Wiele komend:

        find . -name "*.txt" -exec sh -c 'echo PLIK: "$1"; cat "$1"' _ {} \;
    */

    /*
    ======================================================================================
    9. find + xargs – SUPER WYDAJNE OPERACJE
    ======================================================================================

    xargs wykonuje polecenie na wielu plikach naraz (exec robi to pojedynczo).

    Przykład:
        find . -name "*.log" | xargs rm

    Dokumentacja:
        find -> generuje listę
        xargs rm -> usuwa wszystkie w jednym wywołaniu rm
    */

    /*
    ======================================================================================
    10. locate – SZYBKIE WYSZUKIWANIE Z INDEKSU
    ======================================================================================

    locate szukana_rzecz
    -> działa błyskawicznie, bo nie przeszukuje dysku
       TYLKO gotową bazę plików

    Dlaczego locate jest szybkie?
        - korzysta z bazy danych (indeks plików)
        - nie dotyka fizycznego systemu plików

    DLACZEGO locate NIE ZNA NOWYCH PLIKÓW?
        - baza nie jest aktualizowana na bieżąco
        - nowe pliki nie istnieją dla locate, dopóki nie odświeżysz indeksu

    Aktualizacja bazy:
        sudo updatedb

    Jak działa updatedb?
        - skanuje cały system plików
        - buduje bazę nazw plików
        - zapisuje w:
            /var/lib/mlocate/mlocate.db

    Po updatedb locate zaczyna widzieć nowe pliki.
    */

    /*
    ======================================================================================
    11. PORÓWNANIE – find vs locate
    ======================================================================================

    find:
        + działa natychmiast na systemie plików
        + realne dane, zawsze aktualne
        + potężne opcje: size, time, type, exec, regex
        - wolny przy dużych folderach

    locate:
        + ultraszybkie (indeks)
        - baza nie jest aktualna
        - nie nadaje się do skomplikowanych warunków

    Kiedy używać locate?
        gdy chcesz szybko znaleźć ścieżkę do programu, pliku konfiga, nazwy

    Kiedy find?
        gdy chcesz precyzyjne filtrowanie: rozmiar, typ, data, regex, exec
    */

    /*
    ======================================================================================
    12. polecenie "file" – SPRAWDZANIE TYPU PLIKU
    ======================================================================================

    file nazwa

    Przykład:
        file obraz.png
        -> PNG image data

        file skrypt.sh
        -> ASCII text, executable

        file /bin/ls
        -> ELF 64-bit LSB executable

    To nie używa rozszerzeń!
    Odczytuje realny format pliku.
    */

    /*
    ======================================================================================
    13. PRAKTYCZNE PRZYKŁADY – NAJWAŻNIEJSZE
    ======================================================================================

    1) Znajdź wszystkie .txt w katalogu:
        find . -type f -name "*.txt"

    2) Znajdź katalogi zawierające nazwę "backup":
        find / -type d -iname "*backup*"

    3) Znajdź pliki większe niż 1 GB:
        find / -size +1G

    4) Znajdź pliki mniejsze niż 100 KB, sprzed więcej niż 3 dni:
        find . -size -100k -mtime +3

    5) Znajdź pliki starsze niż 10 dni, ale nie starsze niż 13:
        find . -mtime +10 -mtime -13

    6) Znajdź i usuń wszystkie .tmp:
        find . -name "*.tmp" -exec rm {} \;

    7) Znajdź wszystkie pliki w /bin kończące się na "v":
        find /bin -name "*v" -ls

    8) Rozpoznaj typ pliku:
        file archiwum.tgz

    9) locate – wyszukaj pliki konfiguracyjne ssh:
        locate ssh_config

    10) locate po aktualizacji:
        sudo updatedb
        locate nazwa
    */

    /*
    ======================================================================================
    14. PODSUMOWANIE
    ======================================================================================

    - find działa na żywo → ZAWSZE aktualne wyniki
    - locate działa z indeksu → błyskawiczne, ale nieaktualne dane
    - updatedb → odświeża bazę locate
    - find ma potężne filtry: name, iname, regex, mtime, size, type
    - exec wykonuje komendy na znalezionych plikach
    - -ls pokazuje szczegóły
    - file pokazuje REALNY typ pliku

    */
}
