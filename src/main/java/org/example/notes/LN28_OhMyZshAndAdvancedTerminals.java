package org.example.notes;

/**
 * LN28 – OH MY ZSH, POWERLEVEL, TERMINATOR, GUAKE
 *
 * - czym jest shell
 * - bash vs zsh
 * - oh-my-zsh jako framework
 * - wtyczki i motywy (Powerlevel10k)
 * - Terminator – terminal dla power-userów
 * - Guake – terminal typu drop-down
 */
public class LN28_OhMyZshAndAdvancedTerminals {

    /*
    ======================================================================================
    1. CZYM JEST SHELL?
    ======================================================================================

    Shell:
        - powłoka systemowa
        - program pośredniczący między użytkownikiem a kernelem
        - interpretuje polecenia

    Najpopularniejsze shelle:
        - sh   (historyczny)
        - bash (domyślny w Linuxie)
        - zsh  (rozszerzony, nowoczesny)

    Terminal ≠ Shell

    Terminal:
        - okno / interfejs (gnome-terminal, xterm, terminator)

    Shell:
        - interpreter poleceń (bash, zsh)
    */

    /*
    ======================================================================================
    2. BASH VS ZSH
    ======================================================================================

    bash:
        - stabilny
        - domyślny
        - prosty

    zsh:
        - kompatybilny z bash
        - DUŻO lepsze autouzupełnianie
        - lepsza historia poleceń
        - pluginy
        - motywy
        - git integration out-of-the-box

    zsh = shell dla power-userów
    */

    /*
    ======================================================================================
    3. CZYM JEST OH MY ZSH?
    ======================================================================================

    Oh My Zsh:
        - FRAMEWORK dla zsh
        - NIE jest shellem
        - zestaw:
            • pluginów
            • aliasów
            • motywów
            • konfiguracji

    Cel:
        - przyspieszyć pracę w terminalu
        - dać gotowe rozszerzenia
    */

    /*
    ======================================================================================
    4. INSTALACJA ZSH + OH MY ZSH
    ======================================================================================

    Instalacja zsh:
        sudo apt install zsh

    Ustawienie jako domyślny shell:
        chsh -s $(which zsh)

    Instalacja Oh My Zsh:
        sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"

    Konfiguracja:
        ~/.zshrc
    */

    /*
    ======================================================================================
    5. ZSHRC – CENTRUM STEROWANIA
    ======================================================================================

    ~/.zshrc:
        - odpowiednik ~/.bashrc
        - pluginy
        - motyw
        - aliasy

    Przykład:
        plugins=(git docker sudo)
        ZSH_THEME="powerlevel10k/powerlevel10k"
    */

    /*
    ======================================================================================
    6. PLUGINY OH MY ZSH – PRAKTYKA
    ======================================================================================

    Najważniejsze pluginy:

    git:
        - status gita w prompt
        - skróty:
            gst = git status
            gco = git checkout
            gcm = git commit -m

    sudo:
        - wciskasz ESC + ESC
        - polecenie dostaje sudo na początek

    docker:
        - skróty do docker ps, images, logs

    z:
        - szybka nawigacja po katalogach
        - z proj → skok do projektu
    */

    /*
    ======================================================================================
    7. POWERLEVEL10K – MOTYW PRO
    ======================================================================================

    Powerlevel10k:
        - NAJPOPULARNIEJSZY motyw zsh
        - ekstremalnie szybki
        - czytelny
        - konfigurowalny

    Pokazuje w prompt:
        - użytkownik
        - host
        - katalog
        - git branch
        - status repo
        - exit code
        - czas
        - venv / java / node

    Instalacja:
        git clone https://github.com/romkatv/powerlevel10k.git \
        ${ZSH_CUSTOM:-$HOME/.oh-my-zsh/custom}/themes/powerlevel10k

    Konfiguracja:
        p10k configure
    */

    /*
    ======================================================================================
    8. CO REALNIE DAJE OH MY ZSH?
    ======================================================================================

    - szybsza nawigacja
    - mniej literowania
    - wizualny feedback
    - kontekst (git, docker, ssh)
    - MNIEJ BŁĘDÓW

    Dla admina/dev:
        → ogromny boost produktywności
    */

    /*
    ======================================================================================
    9. TERMINATOR – TERMINAL DLA POWER-USERÓW
    ======================================================================================

    Terminator:
        - terminal emulator
        - fokus na multitasking

    Instalacja:
        sudo apt install terminator
    */

    /*
    ======================================================================================
    10. NAJWAŻNIEJSZE FUNKCJE TERMINATORA
    ======================================================================================

    - dzielenie ekranu:
        Ctrl + Shift + O → pionowo
        Ctrl + Shift + E → poziomo

    - wiele sesji SSH w jednym oknie
    - synchronizacja wpisywania
    - profile (kolory, fonty)
    - scrollback per panel

    Idealny do:
        - administracji serwerami
        - pracy na kilku VM
    */

    /*
    ======================================================================================
    11. GUAKE – TERMINAL DROP-DOWN
    ======================================================================================

    Guake:
        - terminal wysuwany z góry
        - inspirowany Quake
        - działa w tle

    Instalacja:
        sudo apt install guake
    */

    /*
    ======================================================================================
    12. JAK DZIAŁA GUAKE?
    ======================================================================================

    - nie zajmuje miejsca
    - wysuwany jednym klawiszem (F12)
    - idealny do:
        • szybkich poleceń
        • git
        • grep
        • ssh

    Guake NIE startuje domyślnie.
    */

    /*
    ======================================================================================
    13. AUTOSTART GUAKE
    ======================================================================================

    Metoda GUI:
        - Autostart aplikacji
        - dodaj guake

    Metoda terminal:
        ~/.config/autostart/guake.desktop

    Przykład:
        [Desktop Entry]
        Type=Application
        Exec=guake
        Hidden=false
        NoDisplay=false
        X-GNOME-Autostart-enabled=true
        Name=Guake
    */

    /*
    ======================================================================================
    14. TERMINATOR VS GUAKE
    ======================================================================================

    Terminator:
        - ciężka praca
        - wiele sesji
        - monitoring

    Guake:
        - szybki dostęp
        - narzędzie pomocnicze

    Najlepszy setup:
        → zsh + oh-my-zsh + powerlevel10k
        → Terminator do pracy
        → Guake jako quick console
    */

    /*
    ======================================================================================
    15. PODSUMOWANIE
    ======================================================================================

    - shell = interpreter
    - zsh > bash (dla power-usera)
    - oh-my-zsh = framework
    - powerlevel10k = najlepszy motyw
    - terminator = multitasking
    - guake = szybki drop-down terminal
    */
}
