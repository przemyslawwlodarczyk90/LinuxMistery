package org.example.notes;

/**
 * LN09 – VIMRC: PEŁNA KONFIGURACJA VIM + WYJAŚNIENIA OPCJI "set"
 *
 * Jak edytować vimrc, jak działa konfiguracja VIM, po co jest każda opcja,
 * jakie są najważniejsze ustawienia i jak je aktywować.
 */
public class LN09_VimConfig {

    /*
    ======================================================================================
    1. GDZIE JEST PLIK KONFIGURACYJNY VIM?
    ======================================================================================

    Systemowy vimrc (globalny, wpływa na wszystkich użytkowników):
        /etc/vim/vimrc

    Ten plik jest tworzony podczas instalacji VIM przez APT.

    Aby go edytować:
        cd /etc/vim
        sudo vim vimrc

    Dlaczego sudo?
        - plik należy do użytkownika root
        - tylko root może zmieniać globalną konfigurację

    Po zapisaniu ustawienia działają dla każdego uruchomienia VIM.
    */

    /*
    ======================================================================================
    2. TRYBY EDYCJI – JAK ZMIENIAĆ VIMRC
    ======================================================================================

    Po wejściu:
        sudo vim vimrc

    - znajdź linie zaczynające się od:
        "set xyz"

      Cudzysłów (`"`) oznacza komentarz w vimrc.

    Aby aktywować opcję:
        usuń cudzysłów na początku linii.

    Przykład:
        "set number"   -> wyłączone
         set number    -> włączone

    Po zmianach:
        ESC
        :wq
        -> zapis i wyjście
    */

    /*
    ======================================================================================
    3. WYJAŚNIENIE OPCJI "set", KTÓRE MUSISZ WŁĄCZYĆ
    ======================================================================================

    set showcmd
        - pokazuje w prawym dolnym rogu komendy, które wpisujesz w trybie NORMAL
          np.: d2w, y$, ci" itp.
        - bardzo ułatwia naukę VIM

    set showmatch
        - gdy wpisujesz nawias (, {, [, VIM podświetla pasujący nawias
        - pomocne przy pisaniu kodu

    set smartcase
        - gdy używasz wyszukiwania:
            /tekst
          to:
          * jeśli wpisujesz same małe litery → ignoruje wielkość liter
          * jeśli użyjesz dużej litery → wyszukiwanie staje się czułe na wielkość liter

    set incsearch
        - wyszukiwanie „na żywo”
        - po wpisaniu /tekst od razu podświetla pasujące fragmenty podczas pisania

    set autowrite
        - automatycznie zapisuje plik, gdy:
            - przechodzisz do innego pliku
            - wykonujesz część poleceń Ex
        - chroni przed utratą danych

    set mouse
        - włącza obsługę myszki:
            * przewijanie
            * zaznaczanie
            * klikanie, by przesunąć kursor

    set number
        - pokazuje numery linii
        - najważniejsza opcja podczas pracy z kodem
        - kompilator zwróci błąd w linii 43 → widzisz od razu

    Po aktywacji numerów linii zostaną wyświetlone w każdym pliku TXT/C/C++/Java itp.
    */

    /*
    ======================================================================================
    4. INNE POPULARNE OPCJE "set" – PEŁNA LISTA PRZYDATNYCH USTAWIEŃ
    ======================================================================================

    Każdą z tych opcji możesz dopisać w vimrc:

    set relativenumber
        - pokazuje linię, na której jesteś (normalnie),
          a reszta linii ma numery RELATYWNE (odległość od kursora)
        - genialne do nawigacji

    set cursorline
        - podświetla aktualną linię

    set hlsearch
        - podświetla wszystkie wyniki wyszukiwania
        - działa z /tekst

    set ignorecase
        - ignoruje wielkość liter podczas wyszukiwania
        - zwykle łączy się z smartcase

    set tabstop=4
        - liczba spacji, jakie reprezentuje tabulacja przy wyświetlaniu

    set shiftwidth=4
        - ile spacji ma dodawać automatyczne wcięcie (>> lub <<)

    set expandtab
        - zamienia TAB na spacje
        - 100% standard w programowaniu

    set autoindent
        - utrzymuje automatyczne wcięcia po przejściu do nowej linii

    set smartindent
        - inteligentne formatowanie linii w kodzie

    set nowrap
        - nie zawija długich linii
        - przydatne przy kodzie

    set wrap
        - zawija linie, jeśli są zbyt długie

    set ruler
        - pokazuje na dole: linia, kolumna

    set syntax on
        - włącza kolorowanie składni (domyślnie włączone w Ubuntu, ale warto znać)

    set background=dark
        - lepsze kolory na ciemnym tle

    set history=3000
        - pamięć historii poleceń zwiększona do 3000

    set clipboard=unnamedplus
        - umożliwia kopiowanie między VIM a systemowym schowkiem
        - np. działa copy/paste między VIM → Windows → przeglądarka
    */

    /*
    ======================================================================================
    5. JAK WYGLĄDA DOBRY VIMRC W PRAKTYCE?
    ======================================================================================

    Przykład finalnej konfiguracji:

        set number
        set relativenumber
        set showcmd
        set showmatch
        set autowrite
        set smartcase
        set incsearch
        set hlsearch
        set mouse=a

        set tabstop=4
        set shiftwidth=4
        set expandtab
        set smartindent
        set autoindent

        set cursorline
        set ruler
        set background=dark

        syntax on

    Dzięki temu:
        - widzisz numery linii
        - szybciej się przemieszczasz
        - masz wyszukiwanie na żywo
        - automatycznie robisz wcięcia
        - masz kolorowanie składni
        - masz mysz i highlight nawiasów
    */

    /*
    ======================================================================================
    6. SCENARIUSZ – JAK TO ZROBIĆ KROK PO KROKU
    ======================================================================================

    1) Przejdź do katalogu:
        cd /etc/vim

    2) Otwórz vimrc:
        sudo vim vimrc

    3) ESC → /set
        -> wyszukaj linie z "set"

    4) Usuń cudzysłowy przy potrzebnych opcjach:
        "set showcmd"  →  set showcmd
        "set showmatch"→  set showmatch
        dopisać:
        set number

    5) Zapisz:
        ESC :wq

    6) Otwórz jakikolwiek plik:
        vim test.txt

    7) Sprawdź numery linii i nowe ustawienia.
    */

    /*
    ======================================================================================
    7. PODSUMOWANIE – CO MUSISZ ZAPAMIĘTAĆ
    ======================================================================================

    - vimrc = konfiguracja Vim
    - systemowy plik → /etc/vim/vimrc
    - edytujesz go: sudo vim vimrc
    - linie z " na początku są komentarzem
    - najważniejsze opcje:
        set number
        set showcmd
        set showmatch
        set incsearch
        set smartcase
        set autowrite
        set mouse=a
    - po odkomentowaniu i zapisaniu ustawienia działają globalnie
    */
}
