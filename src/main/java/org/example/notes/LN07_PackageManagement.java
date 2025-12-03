package org.example.notes;

/**
 * LN07 – INSTALACJA PAKIETÓW W LINUXIE (APT, REPOZYTORIA, UPDATE/UPGRADE)
 *
 * Kompletna lekcja o:
 * - APT (Advanced Package Tool)
 * - repozytoriach
 * - /etc/apt/sources.list
 * - update vs upgrade
 * - wyszukiwaniu pakietów
 * - zależnościach
 * - plikach ukrytych
 */
public class LN07_PackageManagement {

    /*
    ======================================================================================
    1. PLIKI UKRYTE – DLACZEGO ZACZYNAJĄ SIĘ OD KROPki
    ======================================================================================

    Każdy plik/katalog zaczynający się od "." jest ukryty.
    Przykłady:
        ~/.bashrc
        ~/.ssh/
        ~/.cache
        ~/.config/

    Po co?
        - przechowują ustawienia programów (config)
        - cache
        - dane dla aplikacji
        - preferencje użytkownika

    Wyświetlanie plików ukrytych:
        ls -a
        ls -la
    */

    /*
    ======================================================================================
    2. APT – NARZĘDZIE DO INSTALACJI PAKIETÓW
    ======================================================================================

    APT = Advanced Package Tool
    Narzędzie używane w systemach opartych o DEBIANA:
        - Debian
        - Ubuntu
        - Linux Mint
        - Pop!_OS
        - Raspberry Pi OS

    APT pobiera pakiety w formie binarnej .deb
    (w przeciwieństwie do np. Arch Linux, gdzie jest pacman i kompilacja ze źródeł).
    */

    /*
    ======================================================================================
    3. GDZIE JEST APT? – which apt
    ======================================================================================

    which apt
    -> pokazuje pełną ścieżkę do polecenia apt
       np. /usr/bin/apt

    Każde polecenie w systemie to plik binarny (wykonywalny).
    */

    /*
    ======================================================================================
    4. POMOC – apt --help
    ======================================================================================

    apt --help
    -> lista najważniejszych podkomend
    */

    /*
    ======================================================================================
    5. LISTA ZAINSTALOWANYCH PAKIETÓW – apt list --installed
    ======================================================================================

    apt list --installed
    -> wypisuje WSZYSTKIE pakiety zainstalowane w systemie

    Przykład fragmentu:
        bash/focal,now 5.0-6ubuntu1 amd64 [installed]
    */

    /*
    ======================================================================================
    6. UPDATE – AKTUALIZACJA LISTY PAKIETÓW
    ======================================================================================

    Bardzo ważne:
    APT UPDATE **nie aktualizuje systemu**.
    APT UPDATE **porównuje listy pakietów z repozytoriów**.

    Dlaczego?
    - system musi wiedzieć, jakie są najnowsze dostępne wersje pakietów
      zanim je zainstaluje.

    Polecenia:
        sudo apt update

    Działa to tak:
        - czyta plik /etc/apt/sources.list
        - łączy się z repozytoriami
        - pobiera aktualne listy pakietów
        - porównuje z lokalnymi informacjami

    /etc/apt/sources.list — omówienie poniżej.

    Bez update system nie wie o nowych wersjach.
    */

    /*
    ======================================================================================
    7. UPGRADE – INSTALACJA NOWYCH WERSJI
    ======================================================================================

    Po update:
        sudo apt upgrade

    Co robi upgrade?
    - pobiera nowsze wersje pakietów
    - instaluje je
    - instaluje potrzebne zależności

    Różnica:
        update  → odświeża listy pakietów
        upgrade → instaluje aktualizacje

    */

    /*
    ======================================================================================
    8. /etc/apt/sources.list — SERCE APT-a
    ======================================================================================

    Katalog:
        /etc/apt/
            apt.conf
            sources.list
            sources.list.d/

    Główny plik repozytoriów:
        /etc/apt/sources.list

    Wejście:
        cd /etc/apt
        cat sources.list

    Jakie linie mają znaczenie?
        TYLKO te NIE zaczynające się od "#"

    Przykład linijek które działają:

        deb http://archive.ubuntu.com/ubuntu focal main restricted
        deb http://security.ubuntu.com/ubuntu focal-security main
        deb http://archive.ubuntu.com/ubuntu focal-updates main restricted

    Znaczenie:
        deb      → binarne pakiety DEB
        adres    → repozytorium
        nazwa    → wersja (focal = Ubuntu 20.04)
        main     → podstawowe repo Ubuntu
        restricted → dodatkowe sterowniki

    Jeśli pakietu nie ma w repozytorium:
        - apt go nie widzi
        - apt go nie znajdzie
        - apt go nie zainstaluje

    Można dodać repozytorium ręcznie:
        sudo add-apt-repository ppa:nazwa/ppa
        sudo apt update
    */

    /*
    ======================================================================================
    9. WYSZUKIWANIE PAKIETÓW – apt search
    ======================================================================================

    apt search nazwa
    -> wyszukuje pakiety powiązane z daną frazą

    Przykłady:
        apt search python
        apt search nginx
        apt search docker

    Wynik:
        pokazuje:
            - nazwy pakietów
            - krótkie opisy
    */

    /*
    ======================================================================================
    10. INSTALACJA PAKIETÓW – apt install
    ======================================================================================

    apt install package
    -> instaluje pakiet wraz ze wszystkimi zależnościami

    Przykłady:
        sudo apt install nginx
        sudo apt install htop
        sudo apt install python3-pip

    Mechanika:
        - pobiera pakiet .deb
        - pobiera wszystkie niezbędne zależności
        - instaluje wszystko
        - konfiguruje pakiet

    Dlaczego to dobre?
        Pakiety ZAWSZE działają, bo mają komplet bibliotek.
    */

    /*
    ======================================================================================
    11. ZALEŻNOŚCI – DLACZEGO APT JEST TAK DOBRY
    ======================================================================================

    System zależności = powiązania między pakietami

    Jeśli instalujesz pakiet X, który potrzebuje bibliotek:
        libA
        libB
        libC

    APT:
        - znajdzie brakujące biblioteki
        - pobierze je
        - zainstaluje je automatycznie
        - zapobiegnie „program nie działa, bo nie ma biblioteki”

    Przykład:
        sudo apt install vlc

    APT pobierze:
        - VLC
        - libavcodec
        - libqt
        - libpulse
        - masa innych zależności
    */

    /*
    ======================================================================================
    12. USUWANIE – apt remove / purge
    ======================================================================================

    sudo apt remove nazwa
    -> usuwa pakiet, ale zostawia konfigurację

    sudo apt purge nazwa
    -> usuwa pakiet i jego konfiguracje

    Przykłady:
        sudo apt remove nano
        sudo apt purge firefox
    */

    /*
    ======================================================================================
    13. AUTOMATYCZNE CZYSZCZENIE – apt autoremove
    ======================================================================================

    sudo apt autoremove
    -> usuwa nieużywane zależności, które zostały w systemie
       po odinstalowaniu programów.
    */

    /*
    ======================================================================================
    14. JAK DZIAŁA APT W PRAKTYCE – PROCES KROK PO KROKU
    ======================================================================================

    1. Użytkownik:
        sudo apt update
    2. System:
        - sprawdza repozytoria z sources.list
        - pobiera aktualne listy pakietów
    3. Użytkownik:
        sudo apt upgrade
    4. System:
        - instaluje nowe wersje wszystkich pakietów
    5. Użytkownik:
        sudo apt install program
    6. System:
        - sprawdza zależności
        - pobiera program i biblioteki
        - instaluje i konfiguruje

    Jeśli pakietu nie ma → dodajesz repozytorium.
    */

    /*
    ======================================================================================
    15. PODSUMOWANIE
    ======================================================================================

    - Pliki ukryte (.) → konfiguracje programów
    - APT → główne narzędzie instalacji pakietów DEB
    - which apt → gdzie jest apt
    - apt list --installed → lista pakietów
    - sudo apt update → aktualizacja list repozytoriów
    - sudo apt upgrade → instalacja aktualizacji
    - sudo apt install → instalacja pakietu + zależności
    - /etc/apt/sources.list → lista repozytoriów
    - apt search → wyszukiwanie pakietów
    - apt remove / purge → usuwanie
    - apt autoremove → czyszczenie zależności

    */
}
