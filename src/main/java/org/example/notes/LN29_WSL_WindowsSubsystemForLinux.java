package org.example.notes;

/**
 * LN29 – WSL (Windows Subsystem for Linux)
 *
 * - czym jest WSL i jak działa
 * - WSL1 vs WSL2
 * - gdzie przechowywane są dane
 * - dostęp do dysków NTFS
 * - ograniczenia względem „prawdziwego” Linuxa
 * - instalacja WSL
 * - praca w terminalu Windows (ConEmu / Windows Terminal)
 * - aplikacje graficzne Linux (X Server)
 * - firewall i komunikacja WSL ↔ Windows
 */
public class LN29_WSL_WindowsSubsystemForLinux {

    /*
    ======================================================================================
    1. CZYM JEST WSL?
    ======================================================================================

    WSL = Windows Subsystem for Linux

    To WARSTWA KOMPATYBILNOŚCI umożliwiająca:
        - uruchamianie Linuksa na Windowsie
        - bez maszyny wirtualnej (WSL1)
        - albo na lekkiej VM (WSL2)

    WSL NIE JEST:
        - pełnym Linuxem
        - zamiennikiem serwera produkcyjnego

    WSL JEST:
        - narzędziem developerskim
        - środowiskiem do nauki
        - bridge’em Windows ↔ Linux
    */

    /*
    ======================================================================================
    2. WSL1 VS WSL2 – KLUCZOWA RÓŻNICA
    ======================================================================================

    WSL1:
        - translacja syscalli Linux → Windows
        - brak kernela Linux
        - szybki dostęp do plików Windows
        - ograniczenia systemowe

    WSL2:
        - PRAWDZIWY kernel Linux
        - działa na lekkiej VM (Hyper-V)
        - pełna kompatybilność systemowa
        - wolniejszy dostęp do NTFS

    REKOMENDACJA:
        → zawsze WSL2
    */

    /*
    ======================================================================================
    3. INSTALACJA WSL
    ======================================================================================

    Windows 10/11:

        wsl --install

    Domyślnie:
        - instaluje WSL2
        - instaluje Ubuntu

    Sprawdzenie:
        wsl --list --verbose

    Ustawienie WSL2:
        wsl --set-default-version 2
    */

    /*
    ======================================================================================
    4. DYSTRYBUCJE LINUXA W WSL
    ======================================================================================

    Dostępne:
        - Ubuntu
        - Debian
        - Kali
        - openSUSE
        - Arch (społeczność)

    Instalacja:
        Microsoft Store
        LUB
        wsl --install -d Ubuntu
    */

    /*
    ======================================================================================
    5. GDZIE WSL PRZECHOWUJE DANE?
    ======================================================================================

    WSL2:
        - pliki są w obrazie VHDX

    Lokalizacja:
        C:\Users\<user>\AppData\Local\Packages\...\LocalState\ext4.vhdx

    To jest:
        - wirtualny dysk EXT4
        - montowany przez kernel Linux
    */

    /*
    ======================================================================================
    6. DOSTĘP DO DYSKÓW NTFS (WINDOWS)
    ======================================================================================

    Dyski Windows są MONTOWANE automatycznie:

        /mnt/c
        /mnt/d
        /mnt/e

    Przykład:
        cd /mnt/c/Users/Przemek/Desktop

    Odpowiedź:
        TAK – WSL ma dostęp do NTFS

    UWAGA:
        - prawa plików są emulowane
        - chmod/chown działa „logicznie”
    */

    /*
    ======================================================================================
    7. OGRANICZENIA WSL VS PRAWDZIWY LINUX
    ======================================================================================

    OGRANICZENIA:
        - brak pełnego systemd (częściowo w nowszych wersjach)
        - brak kernel modules (dkms)
        - inna sieć (NAT)
        - inne zachowanie firewalli
        - NIE na produkcję

    WSL to:
        - DEV
        - TEST
        - EDUKACJA
    */

    /*
    ======================================================================================
    8. TERMINAL W WINDOWS – CONEMU / WINDOWS TERMINAL
    ======================================================================================

    ConEmu:
        - zaawansowany terminal
        - zakładki
        - profile
        - wsparcie WSL

    Windows Terminal (rekomendowany):
        - oficjalny
        - WSL, PowerShell, CMD
        - Unicode, GPU, tabs

    WSL uruchamiasz:
        wsl
        lub
        Ubuntu (profil)
    */

    /*
    ======================================================================================
    9. APLIKACJE GRAFICZNE LINUX – CZY TO MOŻLIWE?
    ======================================================================================

    Linux GUI:
        - działa na architekturze klient–serwer

    Aplikacja Linux:
        - klient X
        - wysyła RYSUNEK

    X Server:
        - odbiera obraz
        - wyświetla okno

    WSL:
        - NIE ma własnego X Servera
        - trzeba go uruchomić w Windows
    */

    /*
    ======================================================================================
    10. CZYM JEST X SERVER?
    ======================================================================================

    X Server:
        - serwer graficzny
        - renderuje okna
        - przyjmuje połączenia sieciowe

    Model:
        Aplikacja (Linux) → X Server (Windows)

    To ODWROTNOŚĆ niż Windows:
        - serwer = GUI
        - klient = aplikacja
    */

    /*
    ======================================================================================
    11. INSTALACJA X SERVERA NA WINDOWS
    ======================================================================================

    Popularne X Servery:
        - VcXsrv
        - Xming

    Po instalacji:
        - uruchom X Server
        - zezwól w firewallu
    */

    /*
    ======================================================================================
    12. KONFIGURACJA DISPLAY W WSL
    ======================================================================================

    Zmienna środowiskowa:
        DISPLAY

    Przykład:
        export DISPLAY=$(cat /etc/resolv.conf | grep nameserver | awk '{print $2}'):0

    Dodaj do:
        ~/.bashrc
    */

    /*
    ======================================================================================
    13. FIREWALL – DLACZEGO JEST POTRZEBNY?
    ======================================================================================

    WSL ↔ Windows:
        - komunikacja sieciowa
        - X Server nasłuchuje na porcie 6000+

    Firewall MUSI:
        - pozwolić na połączenie
        - z WSL do X Servera

    Inaczej:
        - GUI nie ruszy
    */

    /*
    ======================================================================================
    14. TEST APLIKACJI GUI
    ======================================================================================

    Instalacja:
        sudo apt install x11-apps

    Test:
        xeyes
        xclock
        xterm

    Jeśli okno się pojawi:
        → GUI działa
    */

    /*
    ======================================================================================
    15. WSL A SERWERY (APACHE, MYSQL)
    ======================================================================================

    Można:
        - Apache
        - MySQL
        - PHP
        - Node
        - Java

    Dostęp:
        - localhost z Windows
        - przekierowanie portów

    UWAGA:
        - IP WSL się zmienia
    */

    /*
    ======================================================================================
    16. PODSUMOWANIE
    ======================================================================================

    - WSL = Linux na Windows
    - WSL2 = kernel Linux
    - dane w VHDX
    - NTFS dostępny przez /mnt
    - GUI przez X Server
    - firewall MUSI przepuszczać
    - DEV TOOL, nie produkcja
    */
}
