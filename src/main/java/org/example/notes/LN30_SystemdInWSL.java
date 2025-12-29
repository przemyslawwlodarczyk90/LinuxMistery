package org.example.notes;

/**
 * LN30 – SYSTEMD W WSL (CO DZIAŁA, CO NIE)
 *
 * - czym jest systemd
 * - dlaczego WSL długo NIE obsługiwał systemd
 * - WSL1 vs WSL2 a systemd
 * - aktualny stan (Windows 11 / nowe WSL)
 * - co działa z systemd
 * - co NIE działa
 * - alternatywy dla systemd w WSL
 * - kiedy WSL się nadaje, a kiedy NIE
 */
public class LN30_SystemdInWSL {

    /*
    ======================================================================================
    1. CZYM JEST SYSTEMD?
    ======================================================================================

    systemd:
        - menedżer init (PID 1)
        - pierwszy proces po starcie kernela
        - zarządza:
            • usługami (services)
            • socketami
            • timerami
            • montowaniem
            • targetami

    W klasycznym Linuxie:
        PID 1 = systemd
    */

    /*
    ======================================================================================
    2. DLACZEGO WSL DŁUGO NIE MIAŁ SYSTEMD?
    ======================================================================================

    WSL (pierwsze wersje):
        - NIE był pełnym systemem init
        - brak klasycznego bootowania
        - brak PID 1 w sensie Linuksa

    WSL uruchamia:
        - bezpośrednio shell użytkownika
        - NIE cały system

    Dlatego:
        systemctl = nie działało
    */

    /*
    ======================================================================================
    3. WSL1 A SYSTEMD
    ======================================================================================

    WSL1:
        - brak kernela Linux
        - translacja syscalli
        - systemd NIE MOŻE działać

    WSL1 = BRAK systemd
    */

    /*
    ======================================================================================
    4. WSL2 A SYSTEMD
    ======================================================================================

    WSL2:
        - prawdziwy kernel Linux
        - działa na lekkiej VM (Hyper-V)

    ALE:
        - długo systemd było WYŁĄCZONE
        - Microsoft startował tylko proces użytkownika
    */

    /*
    ======================================================================================
    5. AKTUALNY STAN – SYSTEMD W WSL2
    ======================================================================================

    Od:
        - Windows 11
        - nowe wersje WSL (2022+)

    systemd:
        ✔ JEST WSPIERANY
        ✔ opcjonalny
        ✔ trzeba go WŁĄCZYĆ

    To NIE jest domyślne.
    */

    /*
    ======================================================================================
    6. JAK WŁĄCZYĆ SYSTEMD W WSL2
    ======================================================================================

    Edytuj plik:
        /etc/wsl.conf

    Przykład:
        [boot]
        systemd=true

    Następnie:
        wsl --shutdown
        wsl

    Sprawdzenie:
        ps -p 1

    Jeśli widzisz:
        systemd

    → systemd działa
    */

    /*
    ======================================================================================
    7. CO DZIAŁA Z SYSTEMD W WSL?
    ======================================================================================

    Działa:
        ✔ systemctl start/stop/status
        ✔ usługi: ssh, cron, apache, mysql
        ✔ timery systemd
        ✔ journald (częściowo)

    Przykład:
        systemctl status ssh
        systemctl start apache2
    */

    /*
    ======================================================================================
    8. CO NIE DZIAŁA LUB DZIAŁA OGRANICZENIE?
    ======================================================================================

    Ograniczenia:
        ❌ brak pełnego boot procesu
        ❌ brak zarządzania sprzętem
        ❌ brak kernel modules (dkms)
        ❌ sieć inna niż bare metal
        ❌ logi nie zawsze trwałe

    systemd w WSL:
        ≠ systemd na serwerze
    */

    /*
    ======================================================================================
    9. JAKIE USŁUGI MAJĄ SENS W WSL?
    ======================================================================================

    Sensowne:
        - ssh (do testów)
        - apache/nginx (dev)
        - mysql/postgres (dev)
        - redis
        - cron

    NIESENSOWNE:
        - hardware daemons
        - low-level networking
        - produkcyjny monitoring
    */

    /*
    ======================================================================================
    10. JAK WYGLĄDA START USŁUG W WSL?
    ======================================================================================

    W Linux:
        boot → systemd → usługi

    W WSL:
        start WSL → systemd → usługi

    ALE:
        - WSL startuje na żądanie
        - po zamknięciu WSL → wszystko gaśnie
    */

    /*
    ======================================================================================
    11. ALTERNATYWY DLA SYSTEMD W WSL
    ======================================================================================

    Przed wsparciem systemd używano:
        - ręczne starty
        - nohup
        - supervisord
        - screen / tmux

    Nadal czasem użyteczne:
        - gdy nie chcesz systemd
    */

    /*
    ======================================================================================
    12. SYSTEMCTL – CO DZIAŁA REALNIE
    ======================================================================================

        systemctl list-units
        systemctl status
        systemctl start service
        systemctl stop service

    Co NIE ma sensu:
        systemctl reboot
        systemctl poweroff
    */

    /*
    ======================================================================================
    13. WSL + SYSTEMD A AUTOSTART
    ======================================================================================

    Autostart:
        - działa TYLKO w ramach sesji WSL
        - NIE przy starcie Windows

    Windows startuje WSL:
        - dopiero gdy go użyjesz
    */

    /*
    ======================================================================================
    14. KIEDY UŻYWAĆ SYSTEMD W WSL?
    ======================================================================================

    ✔ Nauka
    ✔ Dev
    ✔ Testy
    ✔ Lokalne serwery

    ❌ Produkcja
    ❌ Monitoring
    ❌ High availability
    */

    /*
    ======================================================================================
    15. WSL VS VM VS SERWER
    ======================================================================================

    WSL:
        - szybki
        - wygodny
        - dev only

    VM:
        - pełny Linux
        - testy prod-like

    Serwer:
        - produkcja
        - SLA
    */

    /*
    ======================================================================================
    16. PODSUMOWANIE
    ======================================================================================

    - systemd długo NIE działał w WSL
    - dziś działa TYLKO w WSL2
    - trzeba go ręcznie włączyć
    - działa częściowo
    - idealny do dev/test
    - NIE zastępuje serwera Linux
    */
}
