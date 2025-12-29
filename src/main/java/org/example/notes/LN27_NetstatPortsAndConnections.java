package org.example.notes;

/**
 * LN27 – NETSTAT: PORTY, POŁĄCZENIA, NASŁUCH, PROCESY
 *
 * - czym jest netstat
 * - co pokazuje
 * - jak czytać wyjście
 * - opcje: -t -u -l -n -p -a
 * - netstat -plnt (najważniejsze!)
 * - przykłady realnych use-case
 * - netstat vs ss
 */
public class LN27_NetstatPortsAndConnections {

    /*
    ======================================================================================
    1. CZYM JEST NETSTAT?
    ======================================================================================

    netstat = network statistics

    netstat:
        - wbudowane narzędzie systemowe
        - pokazuje:
            • połączenia sieciowe
            • porty w stanie LISTEN
            • procesy używające portów
            • statystyki sieci

    netstat NIE:
        - nie skanuje sieci (to robi nmap)
        - nie wysyła pakietów

    netstat:
        → pokazuje CO JUŻ DZIAŁA w systemie
    */

    /*
    ======================================================================================
    2. INSTALACJA NETSTAT (PAKIET NET-TOOLS)
    ======================================================================================

    W nowych systemach netstat NIE jest domyślny.

    Instalacja:
        sudo apt install net-tools

    Sprawdzenie:
        netstat --version
    */

    /*
    ======================================================================================
    3. PODSTAWOWE URUCHOMIENIE NETSTAT
    ======================================================================================

        netstat

    Co pokazuje:
        - aktywne połączenia TCP
        - adres lokalny
        - adres zdalny
        - stan połączenia

    To wyjście jest MAŁO czytelne → używa się opcji.
    */

    /*
    ======================================================================================
    4. NAJWAŻNIEJSZE OPCJE NETSTAT
    ======================================================================================

    -t  → TCP
    -u  → UDP
    -l  → LISTEN (nasłuch)
    -n  → numery (bez DNS)
    -p  → proces (PID / nazwa)
    -a  → wszystkie (listen + active)

    Najczęściej:
        -plnt
    */

    /*
    ======================================================================================
    5. NETSTAT -PLNT – ZŁOTY STANDARD ADMINA
    ======================================================================================

        sudo netstat -plnt

    Co oznacza:
        p → process
        l → listening
        n → numeric
        t → tcp

    Pokazuje:
        - które porty są OTWARTE
        - jaki proces na nich słucha
        - PID + nazwa programu

    Przykład:
        tcp 0 0 0.0.0.0:80 0.0.0.0:* LISTEN 1234/apache2
    */

    /*
    ======================================================================================
    6. JAK CZYTAĆ WYJŚCIE NETSTAT
    ======================================================================================

    Kolumny:

        Proto      → protokół (tcp/udp)
        Local Addr → adres lokalny + port
        Foreign    → adres zdalny
        State      → stan
        PID/Prog   → proces

    0.0.0.0:
        → nasłuch na WSZYSTKICH interfejsach

    127.0.0.1:
        → tylko localhost
    */

    /*
    ======================================================================================
    7. STANY POŁĄCZEŃ TCP
    ======================================================================================

    LISTEN:
        - serwer czeka na połączenia

    ESTABLISHED:
        - aktywne połączenie

    TIME_WAIT:
        - zamknięte, czeka na cleanup

    CLOSE_WAIT:
        - druga strona zamknęła

    SYN_SENT / SYN_RECV:
        - handshake TCP
    */

    /*
    ======================================================================================
    8. SPRAWDZENIE KONKRETNEGO PORTU
    ======================================================================================

        sudo netstat -plnt | grep 80
        sudo netstat -plnt | grep 22
        sudo netstat -plnt | grep mysql

    Użycie:
        - sprawdzenie czy serwer działa
        - debug konfliktów portów
    */

    /*
    ======================================================================================
    9. UDP – NASŁUCH BEZ POŁĄCZEŃ
    ======================================================================================

        sudo netstat -plnu

    UDP:
        - brak stanu ESTABLISHED
        - tylko LISTEN
    */

    /*
    ======================================================================================
    10. WSZYSTKIE POŁĄCZENIA (AKTYWNE + NASŁUCH)
    ======================================================================================

        netstat -ant

    Przydatne do:
        - debugowania problemów sieciowych
    */

    /*
    ======================================================================================
    11. NETSTAT + PIPE (PRAKTYKA)
    ======================================================================================

        netstat -plnt | grep java
        netstat -plnt | grep apache
        netstat -plnt | grep 3306

    Typowy scenariusz:
        „Dlaczego port jest zajęty?”
    */

    /*
    ======================================================================================
    12. NETSTAT A UPRAWNIENIA ROOTA
    ======================================================================================

    Bez sudo:
        - NIE zobaczysz PID/procesu

    Z sudo:
        - pełne informacje

    Dlatego:
        sudo netstat -plnt
    */

    /*
    ======================================================================================
    13. NETSTAT VS SS (NOWOCZESNE SYSTEMY)
    ======================================================================================

    netstat:
        - starsze narzędzie
        - pakiet net-tools

    ss:
        - nowoczesne
        - szybsze
        - domyślne w systemach

    Przykład ss:
        ss -plnt

    Składnia prawie identyczna.
    */

    /*
    ======================================================================================
    14. KIEDY UŻYWAĆ NETSTAT?
    ======================================================================================

    - szybki podgląd portów
    - debug serwera
    - sprawdzenie konfliktów
    - analiza co nasłuchuje

    netstat ≠ nmap

    netstat:
        → co działa lokalnie

    nmap:
        → co widać z zewnątrz
    */

    /*
    ======================================================================================
    15. NAJCZĘSTSZE USE-CASE
    ======================================================================================

    1) Czy Apache działa?
        netstat -plnt | grep 80

    2) Czy SSH działa?
        netstat -plnt | grep 22

    3) Co zajmuje port 8080?
        netstat -plnt | grep 8080

    4) Czy MySQL nasłuchuje?
        netstat -plnt | grep 3306
    */

    /*
    ======================================================================================
    16. PODSUMOWANIE
    ======================================================================================

    - netstat = podgląd lokalnej sieci
    - -plnt = MUST HAVE
    - pokazuje port → proces
    - wymaga sudo
    - dziś wypierany przez ss
    - nadal bardzo użyteczny
    */
}
