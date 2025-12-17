package org.example.notes;

/**
 * LN19 – PODSTAWY WYRAŻEŃ REGULARNYCH (REGEX) + GREP
 *
 * - czym są wyrażenia regularne
 * - jak grep używa regexów
 * - liczenie linii w pliku
 * - kropka jako dowolny znak
 * - kotwice ^ $
 * - zakresy []
 * - ilości: *, +, ?, {n,m}
 * - grupy i alternatywy
 * - praktyczne ćwiczenia na pliku GPL-3
 */
public class LN19_RegexBasics {

    /*
    ======================================================================================
    1. CZYM SĄ WYRAŻENIA REGULARNE?
    ======================================================================================

    Wyrażenia regularne (regex) to:
        - język opisu WZORCÓW tekstu
        - sposób wyszukiwania, dopasowywania i filtrowania tekstu

    Regex NIE JEST:
        - programem
        - językiem programowania

    Regex JEST:
        - wzorcem
        - używanym przez grep, sed, awk, edytory, języki programowania

    grep = narzędzie
    regex = język, którym mówisz grepowi CZEGO szukać
    */

    /*
    ======================================================================================
    2. PRZYGOTOWANIE – PLIK GPL-3
    ======================================================================================

    Załóżmy, że masz plik:
        GPL-3.txt

    (GNU General Public License v3 – dużo tekstu, idealny do ćwiczeń)

    Podgląd:
        less GPL-3.txt

    Liczba linii:
        wc -l GPL-3.txt
    */

    /*
    ======================================================================================
    3. LICZENIE LINII PASUJĄCYCH DO REGEXU
    ======================================================================================

    grep -c → liczy linie

    Przykład:
        grep -c "license" GPL-3.txt

    → ile linii zawiera słowo "license"
    */

    /*
    ======================================================================================
    4. NAJPROSTSZY REGEX – ZWYKŁY TEKST
    ======================================================================================

        grep "GNU" GPL-3.txt

    → dopasuje każdą linię zawierającą GNU
    */

    /*
    ======================================================================================
    5. KROPKA . – DOWOLNY ZNAK
    ======================================================================================

    . = dokładnie jeden dowolny znak

    Przykład:
        grep "G.U" GPL-3.txt

    Dopasuje:
        GNU
        GxU
        G-U

    NIE dopasuje:
        GU
        GXXU
    */

    /*
    ======================================================================================
    6. KOTWICE LINII: ^ i $
    ======================================================================================

    ^ → początek linii
    $ → koniec linii

    Linie zaczynające się od "GNU":
        grep "^GNU" GPL-3.txt

    Linie kończące się na "license":
        grep "license$" GPL-3.txt
    */

    /*
    ======================================================================================
    7. ZAKRESY ZNAKÓW – []
    ======================================================================================

    [abc]     → a lub b lub c
    [A-Z]     → wielkie litery
    [a-z]     → małe litery
    [0-9]     → cyfry

    Przykład:
        grep "[Gg]eneral" GPL-3.txt

    → dopasuje:
        General
        general
    */

    /*
    ======================================================================================
    8. ZAPECZENIE – [^...]
    ======================================================================================

    [^a-z] → wszystko oprócz małych liter

    Przykład:
        grep "[^a-zA-Z]" GPL-3.txt

    → linie zawierające znaki inne niż litery
    */

    /*
    ======================================================================================
    9. GWIAZDKA * – ZERO LUB WIĘCEJ
    ======================================================================================

    * → zero lub więcej powtórzeń POPRZEDNIEGO znaku

    Przykład:
        grep "licen*se" GPL-3.txt

    Dopasuje:
        license
        licese
    */

    /*
    ======================================================================================
    10. PLUS + – JEDEN LUB WIĘCEJ (grep -E)
    ======================================================================================

    + → jeden lub więcej powtórzeń

    Wymaga:
        grep -E

    Przykład:
        grep -E "licen+se" GPL-3.txt

    Dopasuje:
        license
        licennnse
    */

    /*
    ======================================================================================
    11. ZNAK ? – ZERO LUB JEDEN
    ======================================================================================

    ? → znak opcjonalny

    Przykład:
        grep -E "licen?se" GPL-3.txt

    Dopasuje:
        license
        licese
    */

    /*
    ======================================================================================
    12. DOKŁADNA ILOŚĆ – {n,m}
    ======================================================================================

    {n}     → dokładnie n
    {n,m}   → od n do m

    Przykład:
        grep -E "l{2}" GPL-3.txt

    → linie zawierające dokładnie dwa l pod rząd
    */

    /*
    ======================================================================================
    13. ALTERNATYWA – |
    ======================================================================================

    | = LUB

    Przykład:
        grep -E "license|copyright" GPL-3.txt

    → linie zawierające jedno lub drugie
    */

    /*
    ======================================================================================
    14. GRUPY – ()
    ======================================================================================

    () grupują wzorce

    Przykład:
        grep -E "(GNU|Free) Software" GPL-3.txt

    → GNU Software
    → Free Software
    */

    /*
    ======================================================================================
    15. DOWOLNA LICZBA ZNAKÓW – .*
    ======================================================================================

    .* → dowolny ciąg znaków (najczęściej używany!)

    Przykład:
        grep "GNU.*License" GPL-3.txt

    → GNU cokolwiek License
    */

    /*
    ======================================================================================
    16. USUWANIE KOMENTARZY / PUSTYCH LINII (ĆWICZENIE)
    ======================================================================================

    Puste linie:
        grep "^$" GPL-3.txt

    Niepuste linie:
        grep -v "^$" GPL-3.txt
    */

    /*
    ======================================================================================
    17. LICZENIE LINII SPEŁNIAJĄCYCH WARUNEK
    ======================================================================================

        grep -c "^GNU" GPL-3.txt
        grep -c "License$" GPL-3.txt
        grep -c -E "free|redistribute" GPL-3.txt
    */

    /*
    ======================================================================================
    18. GREP + PIPE + REGEX
    ======================================================================================

    Przykład:
        cat GPL-3.txt | grep -E "^[A-Z].*:$"

    → linie zaczynające się wielką literą i kończące dwukropkiem
    */

    /*
    ======================================================================================
    19. JAK ĆWICZYĆ REGEXY?
    ======================================================================================

    1) Weź duży plik tekstowy (GPL, logi)
    2) Zacznij od prostych wzorców
    3) Dodawaj:
        - ^
        - $
        - .
        - *
        - +
        - []
        - |
    4) ZAWSZE testuj grep -n lub grep -c
    */

    /*
    ======================================================================================
    20. PODSUMOWANIE
    ======================================================================================

    - regex = język wzorców
    - grep = narzędzie do ich używania
    - .  → dowolny znak
    - ^  → początek linii
    - $  → koniec linii
    - [] → zakres
    - * + ? {n,m} → ilości
    - |  → alternatywa
    - .* → wszystko

    Opanowanie regexów = ogromny boost w Linuxie i programowaniu.
    */
}
