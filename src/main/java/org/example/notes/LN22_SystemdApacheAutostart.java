package org.example.notes;

/**
 * LN22 – SYSTEMD: AUTOSTART APACHE PO STARCIE SYSTEMU
 *
 * - czym jest systemd
 * - jak działa domyślny skrypt Apache
 * - czym są unit files (.service)
 * - jak dodać własny skrypt do /etc/systemd/system
 * - systemctl enable / start / daemon-reload
 * - po co to wszystko i jak to działa pod maską
 */
public class LN22_SystemdApacheAutostart {

    /*
    ======================================================================================
    1. CZYM JEST SYSTEMD?
    ======================================================================================

    systemd:
        - menedżer systemu i usług (init system)
        - PID 1 (pierwszy proces po starcie kernela)
        - zarządza:
            • usługami (services)
            • socketami
            • timerami
            • montowaniem
            • zależnościami

    systemd ZASTĄPIŁ:
        - sysvinit
        - init.d

    Wszystkie usługi w systemd są opisane jako UNIT FILES.
    */

    /*
    ======================================================================================
    2. CZYM JEST DEMON?
    ======================================================================================

    Demon:
        - proces działający w tle
        - bez interakcji z użytkownikiem
        - startuje przy bootowaniu lub na żądanie

    Przykłady demonów:
        apache2
        sshd
        cron
        docker

    Apache = demon HTTP
    */

    /*
    ======================================================================================
    3. DOMYŚLNY SKRYPT STARTOWY APACHE
    ======================================================================================

    Po instalacji Apache przez apt:
        sudo apt install apache2

    systemd automatycznie dostaje plik:
        /lib/systemd/system/apache2.service

    To jest DOMYŚLNY unit Apache.

    Sprawdzenie:
        systemctl cat apache2
    */

    /*
    ======================================================================================
    4. STRUKTURA PLIKU .service
    ======================================================================================

    Plik unit składa się z sekcji:

        [Unit]
        [Service]
        [Install]

    Przykład uproszczony:
    */

    /*
        [Unit]
        Description=Apache HTTP Server
        After=network.target

        [Service]
        Type=forking
        ExecStart=/usr/sbin/apachectl start
        ExecReload=/usr/sbin/apachectl graceful
        ExecStop=/usr/sbin/apachectl stop
        PIDFile=/run/apache2/apache2.pid

        [Install]
        WantedBy=multi-user.target
    */

    /*
    ======================================================================================
    5. CO OZNACZAJĄ TE SEKCJE?
    ======================================================================================

    [Unit]:
        - opis usługi
        - zależności
        - kolejność startu

    After=network.target:
        → Apache wystartuje PO sieci

    [Service]:
        - jak uruchomić program
        - jak go zatrzymać
        - jaki typ procesu

    Type=forking:
        → demon odłącza się od terminala

    ExecStart:
        → polecenie startu

    ExecStop:
        → polecenie zatrzymania

    [Install]:
        - mówi systemd, kiedy usługa ma startować

    WantedBy=multi-user.target:
        → start przy normalnym trybie systemu (runlevel 3/5)
    */

    /*
    ======================================================================================
    6. DLACZEGO NIE EDYTUJE SIĘ PLIKU W /lib/systemd/system?
    ======================================================================================

    /lib/systemd/system:
        - pliki dostarczane przez pakiety
        - mogą zostać NADPISANE przy aktualizacji

    WŁASNE lub ZMIENIONE unity:
        → /etc/systemd/system/

    /etc/systemd/system MA PIERWSZEŃSTWO.
    */

    /*
    ======================================================================================
    7. TWORZENIE WŁASNEGO SKRYPTU STARTOWEGO (OVERRIDE)
    ======================================================================================

    Tworzymy własny unit:

        sudo vim /etc/systemd/system/apache-custom.service
    */

    /*
        [Unit]
        Description=Custom Apache HTTP Server
        After=network.target

        [Service]
        Type=forking
        ExecStart=/usr/sbin/apachectl start
        ExecStop=/usr/sbin/apachectl stop
        ExecReload=/usr/sbin/apachectl graceful
        Restart=always

        [Install]
        WantedBy=multi-user.target
    */

    /*
    Restart=always:
        → jeśli Apache padnie, systemd go uruchomi ponownie
    */

    /*
    ======================================================================================
    8. PRZEŁADOWANIE KONFIGURACJI SYSTEMD
    ======================================================================================

    Po dodaniu lub zmianie pliku .service:

        sudo systemctl daemon-reload

    Co to robi?
        - systemd czyta ponownie pliki unit
        - aktualizuje wewnętrzną konfigurację

    BEZ TEGO:
        - systemd NIE widzi nowych usług
    */

    /*
    ======================================================================================
    9. WŁĄCZENIE AUTOSTARTU – systemctl enable
    ======================================================================================

        sudo systemctl enable apache-custom.service

    Co to robi?
        - tworzy symlink w:
            /etc/systemd/system/multi-user.target.wants/

    Czyli:
        systemd WIE, że ma uruchomić usługę przy starcie.
    */

    /*
    ======================================================================================
    10. START / STOP / STATUS
    ======================================================================================

    Start:
        sudo systemctl start apache-custom

    Stop:
        sudo systemctl stop apache-custom

    Restart:
        sudo systemctl restart apache-custom

    Status:
        systemctl status apache-custom
    */

    /*
    ======================================================================================
    11. JAK SPRAWDZIĆ CZY APACHE STARTUJE PO BOOT?
    ======================================================================================

    1) Sprawdź enable:
        systemctl is-enabled apache-custom

    2) Zrestartuj system:
        sudo reboot

    3) Po starcie:
        systemctl status apache-custom
        ss -tulpn | grep 80
    */

    /*
    ======================================================================================
    12. PO CO TO WSZYSTKO?
    ======================================================================================

    systemd daje:
        - automatyczny start usług
        - restart po crashu
        - kontrolę zależności
        - spójny sposób zarządzania demonami

    W produkcji:
        - ręczne uruchamianie = błąd
        - wszystko MUSI startować samo
    */

    /*
    ======================================================================================
    13. PODSUMOWANIE
    ======================================================================================

    - Apache to demon
    - systemd nim zarządza
    - unit files opisują usługi
    - /etc/systemd/system = twoje unity
    - daemon-reload = obowiązkowe
    - enable = autostart
    - bez tego serwer NIE jest produkcyjny
    */
}
