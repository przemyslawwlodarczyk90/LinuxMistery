package org.example.notes;

/**
 * LN26 – NMAP: SKANOWANIE PORTÓW I USŁUG
 *
 * - czym jest nmap
 * - jak działa skanowanie portów
 * - TCP / UDP
 * - podstawowe i zaawansowane skany
 * - wykrywanie usług i systemów
 * - zenmap – graficzna nakładka
 * - aspekty bezpieczeństwa i legalności
 */
public class LN26_NmapPortScanning {

    /*
    ======================================================================================
    1. CZYM JEST NMAP?
    ======================================================================================

    nmap = Network Mapper

    nmap to narzędzie do:
        - skanowania portów
        - wykrywania usług
        - identyfikacji systemów operacyjnych
        - audytu bezpieczeństwa
        - diagnostyki sieci

    nmap NIE JEST:
        - exploitem
        - narzędziem do łamania haseł

    nmap:
        - wysyła pakiety sieciowe
        - analizuje odpowiedzi
        - wnioskuje o stanie portów
    */

    /*
    ======================================================================================
    2. INSTALACJA NMAP
    ======================================================================================

        sudo apt update
        sudo apt install nmap

    Sprawdzenie:
        nmap --version
    */

    /*
    ======================================================================================
    3. CO TO JEST PORT?
    ======================================================================================

    Port:
        - logiczny punkt komunikacji
        - zakres: 0–65535

    Podział:
        0–1023     → porty systemowe (root)
        1024–49151 → porty aplikacji
        49152–65535 → porty dynamiczne

    Przykłady:
        22  → SSH
        80  → HTTP
        443 → HTTPS
        3306 → MySQL
    */

    /*
    ======================================================================================
    4. PODSTAWOWY SKAN – NAJPROSTSZY
    ======================================================================================

        nmap nmap.scanme.org

    Co robi:
        - skanuje 1000 najpopularniejszych portów TCP
        - pokazuje:
            • port
            • stan
            • usługę

    Stany portów:
        open     → usługa nasłuchuje
        closed   → brak usługi
        filtered → firewall blokuje
    */

    /*
    ======================================================================================
    5. SKANOWANIE KONKRETNEGO HOSTA / IP
    ======================================================================================

        nmap 192.168.1.1
        nmap localhost
        nmap example.com
    */

    /*
    ======================================================================================
    6. SKANOWANIE KONKRETNYCH PORTÓW
    ======================================================================================

        nmap -p 22 nmap.scanme.org
        nmap -p 22,80,443 localhost
        nmap -p 1-1000 192.168.1.1
    */

    /*
    ======================================================================================
    7. TCP VS UDP
    ======================================================================================

    TCP:
        - połączeniowy
        - domyślny w nmap

    UDP:
        - bezpołączeniowy
        - wolniejszy
        - trudniejszy do wykrycia

    UDP scan:
        sudo nmap -sU 192.168.1.1
    */

    /*
    ======================================================================================
    8. RODZAJE SKANÓW TCP
    ======================================================================================

    SYN scan (najczęstszy):
        sudo nmap -sS host

    Full connect:
        nmap -sT host

    FIN scan:
        sudo nmap -sF host

    NULL scan:
        sudo nmap -sN host

    Xmas scan:
        sudo nmap -sX host

    Te skany:
        - omijają proste firewalle
        - wymagają uprawnień roota
    */

    /*
    ======================================================================================
    9. WYKRYWANIE USŁUG I WERSJI
    ======================================================================================

        nmap -sV nmap.scanme.org

    Pokazuje:
        - nazwę usługi
        - wersję
        - banner

    Przykład:
        Apache 2.4.x
        OpenSSH 8.x
    */

    /*
    ======================================================================================
    10. WYKRYWANIE SYSTEMU OPERACYJNEGO
    ======================================================================================

        sudo nmap -O nmap.scanme.org

    nmap analizuje:
        - TTL
        - flagi TCP
        - odpowiedzi ICMP

    Wynik:
        Linux / Windows / BSD (z prawdopodobieństwem)
    */

    /*
    ======================================================================================
    11. SKAN WSZYSTKICH PORTÓW
    ======================================================================================

        sudo nmap -p- 192.168.1.1

    -p-:
        → skanuje porty 1–65535

    UWAGA:
        - długo trwa
    */

    /*
    ======================================================================================
    12. AGRESYWNY TRYB SKANOWANIA
    ======================================================================================

        sudo nmap -A nmap.scanme.org

    Zawiera:
        - wykrywanie OS
        - wykrywanie usług
        - skrypty NSE
        - traceroute
    */

    /*
    ======================================================================================
    13. SKRYPTY NSE (NMAP SCRIPT ENGINE)
    ======================================================================================

        nmap --script default host
        nmap --script vuln host
        nmap --script http-enum host

    NSE:
        - automatyzacja testów
        - wykrywanie podatności
    */

    /*
    ======================================================================================
    14. SKANOWANIE SIECI LOKALNEJ
    ======================================================================================

        nmap 192.168.1.0/24

    Pokazuje:
        - aktywne hosty
        - dostępne usługi
    */

    /*
    ======================================================================================
    15. ZAPIS WYNIKÓW
    ======================================================================================

        nmap -oN wynik.txt host
        nmap -oX wynik.xml host
        nmap -oA raport host
    */

    /*
    ======================================================================================
    16. CZY TO JEST LEGALNE?
    ======================================================================================

    ✔ Własna maszyna
    ✔ Sieć testowa
    ✔ Lab / VM

    ❌ Cudze serwery bez zgody
    ❌ Produkcja bez pozwolenia

    nmap = narzędzie audytowe,
    ale może zostać uznane za atak.
    */

    /*
    ======================================================================================
    17. ZENMAP – CO TO JEST?
    ======================================================================================

    Zenmap:
        - graficzna nakładka na nmap
        - oficjalny GUI nmapa
        - generuje komendy nmap

    Instalacja:
        sudo apt install zenmap
    */

    /*
    ======================================================================================
    18. CO DAJE ZENMAP?
    ======================================================================================

    - profile skanów
    - wizualizacja topologii
    - historia skanów
    - łatwe porównywanie wyników

    Idealny do:
        - nauki
        - dokumentacji
        - raportów
    */

    /*
    ======================================================================================
    19. RELACJA NMAP ↔ ZENMAP
    ======================================================================================

    Zenmap:
        - NIE zastępuje nmap
        - tylko generuje komendy

    Najlepsza nauka:
        → Zenmap → podejrzyj komendę → terminal
    */

    /*
    ======================================================================================
    20. PODSUMOWANIE
    ======================================================================================

    - nmap = fundament diagnostyki sieci
    - skanuje porty, usługi, systemy
    - wymaga świadomości i odpowiedzialności
    - zenmap = GUI do nauki i raportów
    - każdy admin powinien znać nmap
    */
}
