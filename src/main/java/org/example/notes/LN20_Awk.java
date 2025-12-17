package org.example.notes;

/**
 * LN20 – AWK: PRZETWARZANIE TEKSTU, KOLUMN, RAPORTY, LOGI
 *
 * AWK to:
 * - narzędzie do pracy na KOLUMNACH
 * - mini język programowania
 * - idealne do logów, CSV, plików systemowych
 *
 * grep → filtruje linie
 * awk  → analizuje STRUKTURĘ linii
 */
public class LN20_Awk {

    /*
    ======================================================================================
    1. CZYM JEST AWK?
    ======================================================================================

    awk to:
        - interpreter języka awk
        - czyta dane linia po linii
        - dzieli każdą linię na pola (kolumny)
        - wykonuje akcje na tych polach

    awk jest IDEALNY do:
        - /etc/passwd
        - logów (nginx, apache, system)
        - CSV
        - raportów
        - statystyk

    Myślenie:
        grep  -> "czy linia pasuje?"
        awk   -> "co jest w tej linii i w której kolumnie?"
    */

    /*
    ======================================================================================
    2. PODSTAWOWA SKŁADNIA AWK
    ======================================================================================

        awk 'pattern { action }' plik

    pattern:
        - warunek (opcjonalny)

    action:
        - co zrobić, gdy warunek pasuje

    Jeśli nie podasz pattern:
        → akcja wykona się dla KAŻDEJ linii
    */

    /*
    ======================================================================================
    3. POLA (KOLUMNY) W AWK
    ======================================================================================

    $0  → cała linia
    $1  → pierwsza kolumna
    $2  → druga kolumna
    $3  → trzecia kolumna
    ...
    $NF → ostatnia kolumna
    NF  → liczba kolumn
    NR  → numer linii (rekordu)

    Przykład:
        awk '{ print $1 }' plik.txt
    */

    /*
    ======================================================================================
    4. AWK NA /etc/passwd – IDEALNY PRZYKŁAD
    ======================================================================================

    /etc/passwd:
        login:x:UID:GID:comment:/home:/shell

    Domyślny separator pól = spacja
    passwd używa :

    Dlatego:
        -F:  → separator = :

    Przykłady:

        awk -F: '{ print $1 }' /etc/passwd
        → nazwy użytkowników

        awk -F: '{ print $1, $3 }' /etc/passwd
        → login + UID

        awk -F: '{ print $1 " -> " $7 }' /etc/passwd
        → login -> shell
    */

    /*
    ======================================================================================
    5. WARUNKI (FILTRACJA) W AWK
    ======================================================================================

    Możesz filtrować linie:

        awk '$3 >= 1000 { print $1 }' /etc/passwd

    → użytkownicy normalni (UID >= 1000)

        awk -F: '$7 == "/bin/bash" { print $1 }' /etc/passwd

    → tylko użytkownicy z bash
    */

    /*
    ======================================================================================
    6. AWK + PIPE (NAJWAŻNIEJSZY USE-CASE)
    ======================================================================================

    awk najczęściej działa na danych z pipe:

        ps -ef | awk '{ print $2, $8 }'

    → PID + nazwa procesu

        ls -la | awk '{ print $9, $5 }'

    → nazwa pliku + rozmiar

    grep filtruje, awk analizuje.
    */

    /*
    ======================================================================================
    7. ZMIANA SEPARATORA WYJŚCIA (OFS)
    ======================================================================================

    OFS = Output Field Separator

        awk -F: 'BEGIN { OFS=" | " } { print $1, $3, $7 }' /etc/passwd

    BEGIN:
        - wykona się PRZED przetwarzaniem pliku
    */

    /*
    ======================================================================================
    8. BEGIN I END – BLOKI SPECJALNE
    ======================================================================================

    BEGIN:
        - wykonuje się przed pierwszą linią

    END:
        - wykonuje się po ostatniej linii

    Przykład:

        awk 'BEGIN { print "START" }
             { print NR, $0 }
             END { print "KONIEC" }' plik.txt
    */

    /*
    ======================================================================================
    9. LICZENIE I SUMOWANIE (STATYSTYKI)
    ======================================================================================

    Zliczanie linii:
        awk 'END { print NR }' plik.txt

    Sumowanie kolumny:
        awk '{ sum += $5 } END { print sum }' dane.txt

    Średnia:
        awk '{ sum += $5 } END { print sum / NR }' dane.txt
    */

    /*
    ======================================================================================
    10. AWK Z REGEXAMI
    ======================================================================================

    awk używa regexów jak grep:

        awk '/error/ { print }' log.txt

        awk '/^ERROR/ { print NR, $0 }' log.txt

    Warunek + kolumny:
        awk '/sshd/ { print $1, $9 }' auth.log
    */

    /*
    ======================================================================================
    11. WARUNKI IF / ELSE
    ======================================================================================

        awk '{
            if ($3 > 1000)
                print $1, "USER"
            else
                print $1, "SYSTEM"
        }' /etc/passwd
    */

    /*
    ======================================================================================
    12. FORMATOWANIE – printf
    ======================================================================================

    printf działa jak w C:

        awk '{ printf "%-20s %10s\n", $1, $5 }' plik.txt

    Przydatne do raportów.
    */

    /*
    ======================================================================================
    13. PRAKTYCZNE SCENARIUSZE (REAL LIFE)
    ======================================================================================

    1) Lista użytkowników + UID:
        awk -F: '{ print $1, $3 }' /etc/passwd

    2) Procesy Javy:
        ps -ef | grep java | awk '{ print $2, $8 }'

    3) Największe pliki:
        ls -lh | awk '{ print $9, $5 }'

    4) Logi SSH – IP:
        grep "Failed password" /var/log/auth.log | awk '{ print $11 }'

    5) Liczba błędów:
        grep error log.txt | awk 'END { print NR }'
    */

    /*
    ======================================================================================
    14. AWK VS GREP VS SED
    ======================================================================================

    grep:
        - filtruje linie

    sed:
        - zmienia tekst

    awk:
        - analizuje strukturę danych

    Pipeline:
        cat log | grep error | awk '{ print $1, $5 }'
    */

    /*
    ======================================================================================
    15. NAJCZĘSTSZE BŁĘDY
    ======================================================================================

    - zapomniany -F:
    - złe kolumny ($2 zamiast $3)
    - spacje w danych
    - brak cudzysłowów
    */

    /*
    ======================================================================================
    16. PODSUMOWANIE
    ======================================================================================

    - awk = kolumny + logika
    - $1, $2, $NF, NR, NF to podstawa
    - BEGIN/END = potężne narzędzia
    - awk + grep = adminowski standard
    - idealny do logów i raportów
    */
}
