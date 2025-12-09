package org.example.notes;

/**
 * LN15 – PROCESY W LINUXIE: ps, top, htop, kill, nice, renice, scheduler, rodzice/dzieci
 *
 * Najbardziej kompletna wiedza o procesach:
 * - jak działa scheduler CPU
 * - co posiada każdy proces
 * - PID, PPID, TTY, czas pracy, deskryptory
 * - ps, ps -ef, ps aux, pstree
 * - top i htop
 * - kill, killall
 * - nice i renice – priorytety procesów
 * - jobs, &, fg, bg
 * - procesy w tle i demony
 */
public class LN15_Processes {

    /*
    ======================================================================================
    1. CO TO JEST PROCES W LINUXIE?
    ======================================================================================

    Proces to uruchomiony program w pamięci RAM, posiadający własny kontekst funkcjonowania.

    Każdy proces posiada:

      • PID — identyfikator procesu (process ID)
      • PPID — identyfikator procesu rodzica (parent process ID)
      • UID/GID — właściciel procesu
      • nazwę procesu / command line
      • deskryptory plików (0 = stdin, 1 = stdout, 2 = stderr + otwarte pliki)
      • stan procesu (running, sleeping, zombie, stopped)
      • liczniki czasu CPU
      • przestrzeń adresową (pamięć)
      • powiązanie z terminalem (TTY)

    Proces może tworzyć procesy potomne → powstaje drzewo procesów.
    */

    /*
    ======================================================================================
    2. SCHEDULER – JAK KERNEL PRZYDZIELA CZAS CPU?
    ======================================================================================

    Linux używa tzw. CFS — Completely Fair Scheduler.

    Zasada:
      - Każdy proces dostaje „udział” czasu CPU.
      - CFS próbuje przydzielać procesom czas sprawiedliwie.
      - Typowy kwant czasu: ok. 2–6 ms (może być więcej/mniej).
      - Procesy przełączają się mega szybko → wygląda jakby działały jednocześnie.

    W przypadku wielu rdzeni:
      - scheduler rozkłada procesy równomiernie po rdzeniach
      - każdy rdzeń ma własną kolejkę zadań
      - procesy mogą migrować między rdzeniami jeśli trzeba równoważyć obciążenie

    Tryby działania:
      - procesy interaktywne: dostają większy priorytet (np. aplikacje GUI, terminal)
      - procesy tła: dostają mniejszy priorytet
      - scheduler preferuje procesy, które długo „spały” (np. oczekiwały na I/O)

    Wszystko dzieje się automatycznie.
    */

    /*
    ======================================================================================
    3. POLECENIE "ps" – WYŚWIETLANIE PROCESÓW
    ======================================================================================

    ps — pokazuje procesy bieżącej sesji.
    Derivaty:
        ps
        ps -ef
        ps aux
        ps -eH
        ps -ejH
        ps --forest

    Najważniejsze kolumny:
        PID   → identyfikator procesu
        PPID  → rodzic
        TTY   → terminal, z którego pochodzi proces
        TIME  → czas użycia CPU
        CMD   → komenda, która uruchomiła proces

    Przykład:
        ps -ef | more

    -ef → full listing
      e = wszystko
      f = pełny format, pokazuje PPID, UID, drzewa
    | more → stronicuje wynik

    ps aux → klasyczny BSD format

    - użytkownicy
    - procent CPU
    - procent RAM
    - stan procesu
    */

    /*
    ======================================================================================
    4. PROCESY RODZICE I DZIECI (PPID)
    ======================================================================================

    Wszystkie procesy w Linuxie mają rodzica.

      • PID=1 → systemd (dawniej init)
      • systemd jest przodkiem WSZYSTKICH procesów
      • jeśli proces rodzic umiera, proces staje się „sierotą”
            → systemd adoptuje go

    Drzewo procesów można zobaczyć:

        pstree
        pstree -p   → pokaż PID
        pstree -u   → pokaż użytkowników
        ps -eH      → hierarchia
        ps -ejH     → jeszcze więcej pól

    Przykład:
        pstree -p | more
    */

    /*
    ======================================================================================
    5. CO SIĘ STANIE, JEŚLI ZAMKNIESZ OKNO TERMINALA Z PROCESEM?
    ======================================================================================

    Terminal jest powiązany z procesami przez TTY.

    Jeśli terminal zamknie się:
      • wszystkie procesy zależne od tego terminala otrzymają sygnał SIGHUP
      • procesy GUI mogą przeżyć
      • procesy terminalowe (np. ping, top) zwykle umierają

    Ale:
        komenda zakończona znakiem "&" działa w tle i również dostaje SIGHUP,
        chyba że użyjesz:
            nohup proces &
    */

    /*
    ======================================================================================
    6. WYŚWIETLENIE PROCESÓW KONKRETNEGO UŻYTKOWNIKA
    ======================================================================================

        ps -u przemek
        ps -fU przemek
        ps -fu przemek

    Przykład:
        ps -u root
    */

    /*
    ======================================================================================
    7. top – MONITOROWANIE PROCESÓW W CZASIE RZECZYWISTYM
    ======================================================================================

    top:
      - pokazuje listę procesów dynamicznie
      - CPU %, RAM %, PID, priorytety, stany
      - sortowanie po CPU, po pamięci
      - możesz zabijać procesy z poziomu top:
            naciskasz k → wpisujesz PID

    Najważniejsze informacje:
      load average — obciążenie systemu
      wa — czas oczekiwania na dysk
      ni — priorytet nice

    W top możesz filtrować, sortować, zmieniać kolory.
    */

    /*
    ======================================================================================
    8. htop – ULEPSZONY MONITOR PROCESÓW
    ======================================================================================

    Instalacja:
        sudo apt install htop

    Co daje htop?
        - kolorowe słupki CPU/RAM/SWAP
        - drzewo procesów
        - kill procesów bez wpisywania PID
        - interaktywne sortowanie (F6)
        - podgląd PID, PPID, priorytetu, nice
        - przewijanie listy

    Uruchom:
        htop
    */

    /*
    ======================================================================================
    9. KILL – ZABIJANIE PROCESÓW
    ======================================================================================

    kill wysyła sygnał do procesu.

    Najczęstsze sygnały:
        kill -15 PID   → SIGTERM (grzeczne zakończenie)
        kill -9 PID    → SIGKILL (natychmiastowa śmierć, nie do zignorowania)
        kill -1 PID    → SIGHUP (restart / ponowne wczytanie configu)

    Przykłady:
        kill 1234
        kill -9 4444
        killall firefox
    */

    /*
    ======================================================================================
    10. nice i renice – PRIORYTETY PROCESÓW
    ======================================================================================

    W Linuxie „priorytet” oznacza „jak bardzo system NIE przeszkadza procesowi”.

    Skala nice:
        -20 … 0 … +19

        -20 = najwyższy priorytet (proces może zasysać CPU)
        +19 = najniższy priorytet (system mu przeszkadza)

    Uruchamianie procesu z priorytetem:
        nice -n 10 program
        nice -n -5 python3 skrypt.py

    Zmiana priorytetu działającego procesu:
        renice -n 5 -p 1234

    Możesz zobaczyć nice w top/htop.
    */

    /*
    ======================================================================================
    11. PROCESY W TLE, jobs, &, bg, fg
    ======================================================================================

    Każda komenda zakończona „&” działa w tle:

        ping google.com &

    Sprawdzanie listy zadań:
        jobs

    Przeniesienie zadania do tła:
        CTRL + Z   → zatrzymuje proces
        bg         → uruchamia w tle

    Przywrócenie do pierwszego planu:
        fg %1      → zadanie nr 1

    Uruchamianie z ignorowaniem zamknięcia terminala:
        nohup program &
    */

    /*
    ======================================================================================
    12. DEMONY (processes daemons)
    ======================================================================================

    Demon:
        - proces działający w tle,
        - nie ma terminala (TTY= ?),
        - zwykle startowany przez systemd,
        - np. sshd, cron, nginx, mysql.

    Charakteryzuje się:
        - brak powiązania z terminalem
        - działa cały czas
        - zarządzany przez systemd

    Przykład:
        systemctl status ssh
        ps -ef | grep sshd
    */

    /*
    ======================================================================================
    13. SYSTEMD – RODZIC WSZYSTKICH PROCESÓW
    ======================================================================================

    PID 1 → systemd

    Dlaczego nie można go zabić?
        - jest odpowiedzialny za:
            * start systemu
            * zarządzanie demonami
            * przyjmowanie procesów-sierot
            * zamykanie systemu
        - gdyby go zabić → kernel nie miałby co robić → system umiera

    Dawniej:
        init (SysV Init)
    Teraz:
        systemd (Ubuntu, Debian, Fedora, Arch)
    */

    /*
    ======================================================================================
    14. PODSUMOWANIE – CO MUSISZ ZAPAMIĘTAĆ
    ======================================================================================

    - Każdy proces ma: PID, PPID, UID, GID, TTY, czas CPU, deskryptory, nazwę.
    - Scheduler przydziela procesom małe kwanty czasu (~2–6 ms).
    - ps wyświetla procesy, ps -ef → pełne info.
    - pstree pokazuje drzewo procesów.
    - top/htop służą do monitorowania.
    - kill wysyła sygnały (TERM, KILL, HUP).
    - nice/renice zmieniają priorytet procesów.
    - jobs, &, fg, bg zarządzają procesami w tle.
    - systemd (PID 1) jest rodzicem całego systemu.
    - Demony (nginx, sshd, cron) działają w tle bez terminala.
    */
}
