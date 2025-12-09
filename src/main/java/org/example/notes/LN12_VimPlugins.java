package org.example.notes;

/**
 * LN12 – VIM: INSTALACJA I OBSŁUGA WTYCZEK (PATHOGEN, NERDTree, Lightline, itp.)
 *
 * - czym są wtyczki i w jakim języku są pisane
 * - jak działa pathogen.vim
 * - jak go zainstalować (curl + git)
 * - jak dodać wtyczkę do ~/.vim/bundle
 * - jak je uruchamiać w samym Vimie
 * - przykłady: eunuch.vim, NERDTree, vim-color-schemes, lightline
 * - wzmianka o vimawesome
 */
public class LN12_VimPlugins {

    /*
    ======================================================================================
    1. WTYCZKI DO VIMA – O CO CHODZI?
    ======================================================================================

    - Wtyczki (plugins) rozszerzają możliwości Vima.
    - Są pisane głównie w Vimscript (język skryptowy Vima).
    - Najczęściej hostowane są na GitHubie.

    Problem bez managera:
        - trzeba ręcznie wrzucać pliki do odpowiednich katalogów (~/.vim/plugin, ~/.vim/colors, ~/.vim/autoload, itd.)
        - bałagan, trudniej aktualizować

    Rozwiązanie:
        - użyć menedżera wtyczek, np. pathogen.vim (albo nvim-plug, Vundle, itp.)
    */

    /*
    ======================================================================================
    2. PATHOGEN – ZARZĄDZANIE WTYCZKAMI
    ======================================================================================

    Idea:
        pathogen.vim:
        - manipuluje 'runtimepath' Vima
        - pozwala trzymać każdą wtyczkę w osobnym katalogu w ~/.vim/bundle
        - dodaje te katalogi automatycznie do 'runtimepath'

    Standardowa struktura po pathogen:
        ~/.vim/
            autoload/
                pathogen.vim
            bundle/
                nerdtree/
                vim-eunuch/
                vim-color-schemes/
                lightline.vim/
                ...

    */

    /*
    ======================================================================================
    3. INSTALACJA PATHOGEN – KROK PO KROKU
    ======================================================================================

    1) Upewnij się, że masz curl:
        sudo apt install curl

    2) Utwórz katalogi na autoload i bundle:
        mkdir -p ~/.vim/autoload ~/.vim/bundle

    3) Pobierz pathogen.vim z GitHuba:
        curl -LSso ~/.vim/autoload/pathogen.vim https://tpo.pe/pathogen.vim

       -L   -> podążaj za przekierowaniami
       -S   -> pokazuj błędy
       -s   -> tryb cichy (bez zbędnego outputu)
       -o   -> zapisz do pliku

    4) Edytuj swój plik konfiguracyjny:
        vim ~/.vimrc

       Dodaj na górze (w trybie komend ESC, potem :):

        execute pathogen#infect()
        syntax on
        filetype plugin indent on

       - execute pathogen#infect()
           -> pathogen dodaje katalog ~/.vim/bundle/** do runtimepath
       - syntax on
           -> włącza kolorowanie składni
       - filetype plugin indent on
           -> włącza pluginy i automatyczne wcięcia zależne od typu pliku

    5) Zapisz i wyjdź:
        :wq

    Od teraz: każda wtyczka, którą wrzucisz do ~/.vim/bundle/<nazwa>, będzie automatycznie ładowana przy starcie Vima.
    */

    /*
    ======================================================================================
    4. DODAWANIE WTYCZEK Z GITA (Z PATHOGENEM)
    ======================================================================================

    Ogólny schemat:

        cd ~/.vim/bundle
        git clone <URL_do_repo>

    Przykład (z dokumentacji):
        cd ~/.vim/bundle && \
        git clone https://github.com/tpope/vim-sensible.git

    Po tym:
        - uruchamiasz vim
        - wtyczka jest aktywna (jeśli nie wymaga żadnej specjalnej komendy startowej)
    */

    /*
    ======================================================================================
    5. ALTERNATYWA: NATYWNY SYSTEM PAKIETÓW VIM 8
    ======================================================================================

    Vim 8 ma wbudowany system „packages” (bez Pathogena):

        ~/.vim/pack/<nazwa>/start/<plugin>

    Pathogen potrafi „udawać” podobne zachowanie, dodając też katalog pack/{}/start/{}.
    Ale my tutaj trzymamy się prostego:
        ~/.vim/bundle/<plugin>

    */

    /*
    ======================================================================================
    6. PRZYKŁADOWE WTYCZKI – JAK DZIAŁAJĄ I JAK JE URUCHAMIAĆ
    ======================================================================================

    --------------------------------------------------------------------------------------
    6.1. eunuch.vim – komendy UNIX-owe w Vimie
    --------------------------------------------------------------------------------------

    Repo: https://github.com/tpope/vim-eunuch

    Instalacja (z Pathogenem):
        cd ~/.vim/bundle
        git clone https://github.com/tpope/vim-eunuch.git

    Co daje?
        - umożliwia wykonywanie operacji na plikach w stylu Unix bez wychodzenia z Vima.
        Przykładowe komendy:
            :Rename newname.txt   -> zmiana nazwy pliku
            :Delete               -> usuwa bieżący plik
            :Move path/newname    -> przenosi plik
            :Chmod +x %           -> daje +x bieżącemu plikowi
            :Mkdir path           -> tworzy katalog
            :SudoWrite            -> zapis pliku z sudo

    Użycie:
        - po prostu odpalasz vim, edytujesz plik, wpisujesz np.:
            :Rename nowa_nazwa.java
            :Delete
            :Chmod +x %

    Eunuch nie wymaga koniecznie Pathogena – możesz go też wrzucić przez natywny system pakietów.
    */

    /*
    --------------------------------------------------------------------------------------
    6.2. NERDTree – drzewo katalogów w Vimie
    --------------------------------------------------------------------------------------

    Repo: https://github.com/preservim/nerdtree

    Instalacja (Pathogen):
        cd ~/.vim/bundle
        git clone https://github.com/preservim/nerdtree.git

    Czym jest?
        - eksplorator plików wewnątrz Vima
        - otwiera panel z drzewem katalogów z boku
        - nie musisz wychodzić z edytora, żeby przeglądać strukturę projektu

    Jak uruchomić?
        - w Vimie:
            :NERDTree
            -> otwiera drzewo plików

        - popularny skrót (możesz dodać do .vimrc):
            nnoremap <C-n> :NERDTreeToggle<CR>
            -> CTRL+N pokazuje/ukrywa drzewo

    Obsługa (najważniejsze klawisze w NERDTree):
        - Enter   -> otwórz plik
        - t       -> otwórz w nowym tabie
        - s       -> split (poziomy)
        - i       -> vsplit (pionowy)
        - m       -> menu (tworzenie/usuwanie/zmiana nazw)
    */

    /*
    --------------------------------------------------------------------------------------
    6.3. vim-color-schemes – kolorki do Vima
    --------------------------------------------------------------------------------------

    Przykładowe repo:
        https://github.com/flazz/vim-colorschemes
        (albo inne paczki z vimawesome)

    Instalacja:
        cd ~/.vim/bundle
        git clone https://github.com/flazz/vim-colorschemes.git

    Jak używać?
        - w vimie:
            :colorscheme <nazwa>

        Przykłady:
            :colorscheme desert
            :colorscheme wombat
            :colorscheme molokai

        Żeby ustawić domyślnie w .vimrc:
            colorscheme desert

    Dzięki temu łatwo zmieniasz wygląd Vima.
    */

    /*
    --------------------------------------------------------------------------------------
    6.4. lightline.vim – lepszy pasek statusu
    --------------------------------------------------------------------------------------

    Repo: https://github.com/itchyny/lightline.vim

    Instalacja:
        cd ~/.vim/bundle
        git clone https://github.com/itchyny/lightline.vim.git

    Co robi?
        - zamienia standardowy, brzydki pasek statusu Vima
          na czytelny, kolorowy, bardzo konfigurowalny
        - pokazuje:
            * nazwę pliku
            * typ pliku
            * pozycję w pliku
            * tryb Vima (NORMAL, INSERT, VISUAL)
            * gałąź gita, itp. (jak doinstalujesz integracje)

    Podstawowa konfiguracja w .vimrc:

        set laststatus=2
        let g:lightline = {
          \ 'colorscheme': 'wombat',
          \ }

    - set laststatus=2  -> pasek statusu zawsze widoczny
    - colorscheme lightline’a możesz zmieniać wedle upodobań

    Po instalacji:
        - po prostu uruchamiasz Vima
        - lightline ładuje się automatycznie jako plugin Pathogena
    */

    /*
    ======================================================================================
    7. SKĄD BRAĆ WTYCZKI? – vimawesome
    ======================================================================================

    Strona:
        https://vimawesome.com

    Co to?
        - katalog popularnych wtyczek Vim/Neovim
        - kategorie: statusbar, file explorer, git integration, autocompletion, itd.
        - większość ma instrukcje instalacji (często dla Pathogen, Vundle, Plug)

    Workflow:
        1) Wchodzisz na vimawesome
        2) Szukasz pluginu: np. "git", "python", "java", "autocompletion", "LSP"
        3) Kopiujesz adres repo (GitHub)
        4) cd ~/.vim/bundle && git clone URL
        5) Restart Vima → plugin dostępny
    */

    /*
    ======================================================================================
    8. JAK URUCHAMIAĆ I KONFIGUROWAĆ WTYCZKI W PRAKTYCE
    ======================================================================================

    Ogólny schemat:

        1) Zainstaluj Pathogena (albo inny plugin manager).
        2) Skopiuj repo do ~/.vim/bundle/NAZWA_WTYCZKI.
        3) Sprawdź README w repo → są tam:
            - komendy (np. :NERDTree)
            - mapowania klawiszy
            - zmienne konfiguracyjne (let g:...)
        4) Wpisz odpowiednie komendy w Vimie:
            :NERDTree
            :Rename
            :colorscheme nazwa
        5) Dodaj stałe ustawienia do ~/.vimrc:
            nnoremap, let g:lightline = {...}, colorscheme itd.
    */

    /*
    ======================================================================================
    9. PRZYKŁADOWY .vimrc Z PATHOGEN + PARĘ WTYCZEK
    ======================================================================================

        execute pathogen#infect()
        syntax on
        filetype plugin indent on

        " numery linii
        set number

        " NERDTree pod Ctrl+n
        nnoremap <C-n> :NERDTreeToggle<CR>

        " Lightline – status bar
        set laststatus=2
        let g:lightline = {
          \ 'colorscheme': 'wombat',
          \ }

        " Kolorystyka Vima:
        colorscheme desert

    */

    /*
    ======================================================================================
    10. PODSUMOWANIE
    ======================================================================================

    - Wtyczki Vima = skrypty Vimscript rozszerzające edytor.
    - Pathogen:
        - instalujesz do ~/.vim/autoload/pathogen.vim
        - w .vimrc: execute pathogen#infect()
        - wtyczki trzymasz w ~/.vim/bundle/<plugin>
    - eunuch.vim:
        - unixowe komendy jako polecenia Vima (Rename, Delete, Chmod, SudoWrite).
    - NERDTree:
        - eksplorator plików: :NERDTree, typowo mapowany pod <C-n>.
    - vim-color-schemes:
        - kolekcja schematów: :colorscheme <nazwa>.
    - lightline.vim:
        - ładny pasek statusu, bardzo konfigurowalny.
    - vimawesome:
        - katalog fajnych wtyczek.

    */
}
