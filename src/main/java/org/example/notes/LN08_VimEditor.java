package org.example.notes;

/**
 * LN08 – EDYTOR TEKSTOWY VIM (TRYBY, KOMENDY, PORUSZANIE, EDYCJA)
 *
 * Vim to rozszerzona wersja edytora vi.
 * Posiada tryb wprowadzania tekstu oraz tryb komend.
 * Poniżej kompletna ściąga z najważniejszych operacji.
 */
public class LN08_VimEditor {

    /*
    ======================================================================================
    1. URUCHAMIANIE VIM
    ======================================================================================

    vim
    -> uruchamia pusty edytor bez pliku

    vim nazwa_pliku
    -> otwiera plik. Jeśli plik nie istnieje, zostanie utworzony po zapisaniu.

    Przykłady:
        vim notes.txt
        vim /etc/fstab
        vim ~/projekty/kod.java
    */

    /*
    ======================================================================================
    2. TRYBY PRACY W VIM
    ======================================================================================

    Vim ma dwa podstawowe tryby:

    1) TRYB WPROWADZANIA (INSERT MODE)
        - pozwala pisać tekst zwyczajnie
        - uruchamiasz go:
            i   -> insert (pisanie od aktualnej pozycji)
            I   -> insert na początku linii
            a   -> append (pisanie ZA aktualną pozycją)
            A   -> append na końcu linii
            o   -> nowa linia poniżej + insert
            O   -> nowa linia powyżej + insert

    2) TRYB KOMEND (NORMAL MODE)
        - ESC aby wejść do trybu komend
        - tu wykonujesz polecenia:
            usuwanie, kopiowanie, przeskakiwanie, zapisywanie, wyjście itp.

    ***ZAPAMIĘTAJ:***
        ESC → zawsze przenosi do trybu komend
        i   → zawsze wraca do trybu wprowadzania
    */

    /*
    ======================================================================================
    3. ZAPISYWANIE I WYCHODZENIE
    ======================================================================================

    Po naciśnięciu ESC jesteś w trybie komend.

    :w
    -> zapis pliku (write)

    :w nowa_nazwa.txt
    -> zapis do nowego pliku

    :q
    -> wyjście z edytora (tylko jeśli nic nie zmieniono)

    :q!
    -> wyjście BEZ zapisywania (force quit)

    :wq
    -> zapis i wyjście

    :x
    -> zapis i wyjście (to samo co :wq)

    Duże ZZ (bez dwukropka):
        ESC → ZZ
    -> zapis i wyjście

    Duże ZQ:
        ESC → ZQ
    -> wyjście bez zapisywania
    */

    /*
    ======================================================================================
    4. PORUSZANIE SIĘ PO DOKUMENCIE
    ======================================================================================

    Kierunki:
        h -> lewo
        j -> dół
        k -> góra
        l -> prawo

    Strzałki działają też, ale prawdziwy vimowiec używa hjkl.

    Skoki:
        gg  -> początek pliku
        G   -> koniec pliku
        10G -> skok do linii 10
        :10 -> to samo (idź do linii 10)

    W linii:
        0   -> początek linii
        $   -> koniec linii
        w   -> następne słowo
        b   -> poprzednie słowo
        e   -> koniec słowa

    Przewijanie:
        CTRL + u  -> pół ekranu w górę
        CTRL + d  -> pół ekranu w dół
        CTRL + f  -> pełna strona w dół
        CTRL + b  -> pełna strona w górę
    */

    /*
    ======================================================================================
    5. USUWANIE TEKSTU
    ======================================================================================

    dd
    -> usuwa całą bieżącą linię

    3dd
    -> usuwa 3 linie od miejsca, gdzie stoisz

    x
    -> usuwa pojedynczy znak pod kursorem

    dw
    -> usuwa słowo

    d0
    -> usuwa do początku linii

    d$
    -> usuwa do końca linii

    dG
    -> usuwa od bieżącej pozycji do końca pliku

    dgg
    -> usuwa od bieżącej pozycji do początku pliku
    */

    /*
    ======================================================================================
    6. KOPIOWANIE, WYRAŻANIE, WKLEJANIE (YANK / PASTE)
    ======================================================================================

    yy
    -> kopiuje (yank) całą bieżącą linię

    3yy
    -> kopiuje 3 linie

    yw
    -> kopiuje słowo

    y$
    -> kopiuje od kursora do końca linii

    y0
    -> kopiuje od kursora do początku linii

    p
    -> wkleja tekst ZA kursorem lub pod bieżącą linią

    P
    -> wkleja PRZED kursorem
    */

    /*
    ======================================================================================
    7. ZAMIANA TEKSTU
    ======================================================================================

    rX
    -> zamienia bieżący znak na X

    R
    -> tryb nadpisywania (replace mode)

    cw
    -> zmiana słowa:
        usuwa słowo i przełącza do trybu insert

    cc
    -> czyści całą linię i przechodzi do insert

    C
    -> czyści do końca linii i przechodzi do insert
    */

    /*
    ======================================================================================
    8. COFANIE I POWTARZANIE
    ======================================================================================

    u
    -> cofa ostatnią zmianę

    CTRL + r
    -> ponawia cofniętą zmianę (redo)
    */

    /*
    ======================================================================================
    9. WYSZUKIWANIE W VIM
    ======================================================================================

    /tekst
    -> szuka napisu "tekst" w dół

    ?tekst
    -> szuka w górę

    n
    -> następne wystąpienie

    N
    -> poprzednie wystąpienie

    :%s/stare/nowe/g
    -> zamienia wszystkie wystąpienia w pliku
    */

    /*
    ======================================================================================
    10. ZAAWANSOWANE SKRÓTY — NAJPRZYDATNIEJSZE
    ======================================================================================

    >>      -> przesunięcie linii w prawo (indent)
    <<      -> przesunięcie w lewo

    .       -> powtórz ostatnią operację (super ważne!)

    %       -> skok między nawiasami (), {}, []
    */

    /*
    ======================================================================================
    11. PRZYKŁADOWY SCENARIUSZ PRACY
    ======================================================================================

    1) Otwórz plik:
        vim config.txt

    2) Przejdź do trybu insert:
        i
        (wpisujesz tekst)

    3) Wróć do trybu komend:
        ESC

    4) Usuń linię:
        dd

    5) Skopiuj linię:
        yy

    6) Wklej:
        p

    7) Zapisz plik:
        :w

    8) Wyjdź:
        :q
    */

    /*
    ======================================================================================
    12. PODSUMOWANIE – CO MUSISZ ZNAĆ NA PAMIĘĆ
    ======================================================================================

    Tryby:
        i  -> insert
        ESC -> komendy

    Wyjścia:
        :w     -> zapis
        :q     -> wyjście
        :q!    -> wyjście bez zapisu
        :wq    -> zapis i wyjście
        ZZ     -> zapis + wyjście

    Usuwanie:
        dd     -> usuń linię
        x      -> usuń znak

    Kopiowanie:
        yy     -> kopiuj linię
        p      -> wklej

    Poruszanie:
        gg     -> początek
        G      -> koniec
        w/b/e  -> słowa
        0/$    -> linia

    Vim jest szybki, precyzyjny i ultraskuteczny jeśli opanujesz te podstawy.
    */
}
