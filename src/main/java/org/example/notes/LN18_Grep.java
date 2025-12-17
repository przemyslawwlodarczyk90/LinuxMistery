package org.example.notes;

/**
 * LN18 – GREP: WYSZUKIWANIE INFORMACJI W PLIKACH
 *
 * - czym jest grep i jak działa
 * - grep vs egrep vs fgrep
 * - wyszukiwanie w plikach i katalogach
 * - case-sensitive / insensitive
 * - odwracanie dopasowania
 * - liczenie wyników
 * - wyszukiwanie całych słów
 * - grep + pipe
 * - grep w logach systemowych
 * - wyrażenia regularne (regex)
 * - praktyczne scenariusze admin/dev
 */
public class LN18_Grep {

    /*
    ======================================================================================
    1. CZYM JEST GREP?
    ======================================================================================

    grep = Global Regular Expression Print

    grep:
        - przeszukuje tekst linia po linii
        - wypisuje linie, które PASUJĄ do wzorca
        - działa na plikach lub danych ze stdin (pipe)

    grep NIE:
        - nie modyfikuje plików
        - tylko czyta i filtruje

    Typowe zastosowania:
        - logi systemowe
        - pliki konfiguracyjne
        - wyniki poleceń (ps, ls, ip, docker)
    */

    /*
    ======================================================================================
    2. PODSTAWOWA SKŁADNIA
    ======================================================================================

        grep "wzorzec" plik

    Przykład:
        grep "error" app.log

    → wypisze wszystkie linie zawierające słowo "error"
    */

    /*
    ======================================================================================
    3. CASE SENSITIVE I INSENSITIVE
    ======================================================================================

    Domyślnie:
        grep jest case-sensitive

        grep "Error" plik.txt
        grep "error" plik.txt

    Ignorowanie wielkości liter:
        grep -i "error" plik.txt

    -i = ignore case
    */

    /*
    ======================================================================================
    4. WYSZUKIWANIE W KATALOGACH – REKURSYWNIE
    ======================================================================================

        grep -r "tekst" katalog/

    Przykład:
        grep -r "Listen" /etc

    Opcje pomocnicze:
        -n → numer linii
        -l → tylko nazwy plików
        -H → pokaż nazwę pliku

    Przykład:
        grep -rn "port" /etc
    */

    /*
    ======================================================================================
    5. ODWRÓCENIE WYNIKU – -v
    ======================================================================================

    -v = invert match

        grep -v "error" app.log

    → pokaże WSZYSTKO poza liniami z "error"

    Częste użycie:
        - filtrowanie śmieci
        - pomijanie komentarzy
    */

    /*
    ======================================================================================
    6. WYSZUKIWANIE CAŁYCH SŁÓW – -w
    ======================================================================================

        grep -w "port" config.txt

    Bez -w:
        port
        airport
        transport

    Z -w:
        tylko dokładne słowo "port"
    */

    /*
    ======================================================================================
    7. LICZENIE WYSTĄPIEŃ – -c
    ======================================================================================

        grep -c "error" app.log

    → liczba linii zawierających "error"

    Przydatne w logach.
    */

    /*
    ======================================================================================
    8. WYSZUKIWANIE WIELOKROTNYCH WZORCÓW
    ======================================================================================

    Kilka grepów:
        grep "error" log.txt | grep "database"

    Lub:
        grep -E "error|warning" log.txt

    -E = extended regex (egrep)
    */

    /*
    ======================================================================================
    9. GREP + PIPE – NAJWAŻNIEJSZY MECHANIZM
    ======================================================================================

    grep bardzo często działa na wyjściu innych poleceń.

    Przykłady:

        ps -ef | grep ssh
        ip a | grep inet
        ls -la | grep ".log"
        dmesg | grep -i error

    Mechanizm:
        stdout programu → stdin grep
    */

    /*
    ======================================================================================
    10. GREP W LOGACH SYSTEMOWYCH
    ======================================================================================

    Przykłady:

        grep "error" /var/log/syslog
        grep -i "fail" /var/log/auth.log
        grep "sshd" /var/log/auth.log

    W czasie rzeczywistym:
        tail -f /var/log/syslog | grep ssh
    */

    /*
    ======================================================================================
    11. NUMERY LINII – -n
    ======================================================================================

        grep -n "port" /etc/ssh/sshd_config

    → pokazuje numer linii (mega ważne przy debugowaniu)
    */

    /*
    ======================================================================================
    12. WYRAŻENIA REGULARNE (REGEX) – PODSTAWY
    ======================================================================================

    .       → dowolny znak
    ^       → początek linii
    $       → koniec linii
    *       → 0 lub więcej
    +       → 1 lub więcej
    []      → zbiór znaków

    Przykłady:

        grep "^Port" sshd_config
        grep "^[0-9]" plik.txt
        grep "\.log$" lista.txt
    */

    /*
    ======================================================================================
    13. GREP -E (EGREP) – ROZSZERZONE REGEXY
    ======================================================================================

        grep -E "error|warning" log.txt
        grep -E "[0-9]{3}" plik.txt

    Alternatywa:
        egrep "error|warning" log.txt
    */

    /*
    ======================================================================================
    14. GREP -F (FGREP) – BEZ REGEX
    ======================================================================================

    grep -F:
        - traktuje wzorzec jako ZWYKŁY TEKST
        - szybszy
        - bez interpretacji regexów

        grep -F "[error]" plik.txt
    */

    /*
    ======================================================================================
    15. IGNOROWANIE KOMENTARZY
    ======================================================================================

        grep -v "^#" config.txt
        grep -v "^#" /etc/ssh/sshd_config

    → usuwa linie zaczynające się od #
    */

    /*
    ======================================================================================
    16. GREP WIELU PLIKÓW
    ======================================================================================

        grep "main" *.java
        grep -r "TODO" .

    */

    /*
    ======================================================================================
    17. PRAKTYCZNE SCENARIUSZE (ADMIN / DEV)
    ======================================================================================

    1) Sprawdź port SSH:
        grep "^Port" /etc/ssh/sshd_config

    2) Znajdź błędy logowania:
        grep "Failed password" /var/log/auth.log

    3) Sprawdź procesy Javy:
        ps -ef | grep java

    4) Znajdź TODO w projekcie:
        grep -r "TODO" .

    5) Usuń komentarze:
        grep -v "^#" plik.conf
    */

    /*
    ======================================================================================
    18. GREP VS SED VS AWK
    ======================================================================================

    grep:
        - filtruje linie

    sed:
        - modyfikuje linie

    awk:
        - przetwarza kolumny

    grep = pierwszy filtr w pipeline.
    */

    /*
    ======================================================================================
    19. NAJCZĘSTSZE BŁĘDY
    ======================================================================================

    - brak cudzysłowów przy regex
    - zapominanie o -i
    - grep łapie samego siebie (ps | grep grep)
        → rozwiązanie:
            ps -ef | grep ssh | grep -v grep
    */

    /*
    ======================================================================================
    20. PODSUMOWANIE
    ======================================================================================

    - grep = podstawowe narzędzie Linuxa
    - działa na plikach i pipe
    - -i, -v, -r, -n, -c to MUST-HAVE
    - regexy dają ogromną moc
    - grep + pipe = codzienność admina
    */
}
