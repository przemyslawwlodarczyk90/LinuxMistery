package org.example.notes;

/**
 * LN24 – INSTALACJA MYSQL Z PAKIETU DEB + BACKUP + PHPMYADMIN
 *
 * - czym jest MySQL Community
 * - czym jest pakiet .deb
 * - instalacja MySQL z oficjalnej strony
 * - dpkg – jak działa
 * - MySQL Server vs Server Core
 * - struktura /var/lib/mysql
 * - backup baz danych
 * - instalacja phpMyAdmin
 */
public class LN24_MySQLInstallationAndPhpMyAdmin {

    /*
    ======================================================================================
    1. CZYM JEST MYSQL COMMUNITY?
    ======================================================================================

    MySQL występuje w kilku wersjach:

    - MySQL Community Edition:
        • darmowa
        • open source
        • najczęściej używana
        • IDEALNA do nauki i produkcji (małe/średnie systemy)

    - MySQL Enterprise:
        • płatna
        • wsparcie Oracle

    My instalujemy:
        → MySQL Community Server
    */

    /*
    ======================================================================================
    2. CZYM JEST PAKIET .DEB?
    ======================================================================================

    .deb:
        - format pakietów dla Debian / Ubuntu
        - zawiera:
            • pliki binarne
            • konfigurację
            • skrypty postinstalacyjne

    Narzędzia:
        dpkg  → niskopoziomowa instalacja
        apt   → wyższy poziom (repozytoria)

    My używamy:
        dpkg
    */

    /*
    ======================================================================================
    3. POBRANIE MYSQL Z OFICJALNEJ STRONY
    ======================================================================================

    Strona:
        https://dev.mysql.com/downloads/

    Wybierasz:
        - MySQL APT Repository (DEB)
        - albo MySQL Server .deb bundle

    Przykład pliku:
        mysql-server_8.x.x-1ubuntuXX_amd64.deb
    */

    /*
    ======================================================================================
    4. INSTALACJA MYSQL PRZEZ DPKG
    ======================================================================================

    Przejście do katalogu z plikiem:
        cd ~/Downloads

    Instalacja:
        sudo dpkg -i mysql-server_8.x.x-1ubuntuXX_amd64.deb

    Jeśli są błędy zależności:
        sudo apt --fix-broken install
    */

    /*
    ======================================================================================
    5. MYSQL SERVER VS MYSQL SERVER CORE
    ======================================================================================

    MySQL Server:
        - pełny serwer
        - silniki storage (InnoDB)
        - narzędzia administracyjne
        - rekomendowane

    MySQL Server Core:
        - minimalny zestaw
        - brak narzędzi dodatkowych
        - mniejszy footprint

    Do nauki i normalnej pracy:
        → MySQL Server (pełny)
    */

    /*
    ======================================================================================
    6. START I STATUS MYSQL
    ======================================================================================

    Sprawdzenie:
        systemctl status mysql

    Start:
        sudo systemctl start mysql

    Autostart:
        sudo systemctl enable mysql
    */

    /*
    ======================================================================================
    7. PODSTAWOWA KONFIGURACJA MYSQL
    ======================================================================================

    Po instalacji:
        sudo mysql_secure_installation

    Ustawisz:
        - hasło root
        - usunięcie anonimowych użytkowników
        - wyłączenie zdalnego roota
        - usunięcie test DB
    */

    /*
    ======================================================================================
    8. STRUKTURA DANYCH MYSQL
    ======================================================================================

    GŁÓWNY KATALOG:
        /var/lib/mysql

    Co tam jest:
        - katalogi baz danych
        - pliki tabel
        - logi binarne

    Przykład:
        /var/lib/mysql/db_name/

    UWAGA:
        - MySQL MUSI BYĆ ZATRZYMANY
          przed ręcznym kopiowaniem
    */

    /*
    ======================================================================================
    9. BACKUP BAZ DANYCH – DWA PODEJŚCIA
    ======================================================================================

    METODA 1 (LOGICZNA – REKOMENDOWANA):
        mysqldump

        mysqldump -u root -p db_name > backup.sql

    Zalety:
        - bezpieczna
        - przenośna
        - działa online

    METODA 2 (FIZYCZNA – KOPIOWANIE PLIKÓW):

        sudo systemctl stop mysql
        cp -r /var/lib/mysql /backup/mysql
        sudo systemctl start mysql

    Ryzyko:
        - wersje
        - locki
    */

    /*
    ======================================================================================
    10. INSTALACJA PHPMYADMIN
    ======================================================================================

    phpMyAdmin:
        - webowy panel do MySQL
        - napisany w PHP
        - NIE jest częścią MySQL

    Instalacja:
        sudo apt install phpmyadmin

    Podczas instalacji:
        - wybierz apache2
        - pozwól skonfigurować DB
    */

    /*
    ======================================================================================
    11. DOSTĘP DO PHPMYADMIN
    ======================================================================================

    W przeglądarce:
        http://localhost/phpmyadmin

    Logowanie:
        - użytkownik MySQL
        - hasło MySQL
    */

    /*
    ======================================================================================
    12. ARCHITEKTURA CAŁOŚCI
    ======================================================================================

    Przeglądarka
        ↓
    Apache
        ↓
    PHP
        ↓
    phpMyAdmin
        ↓
    MySQL

    Apache + PHP = warstwa web
    MySQL = silnik danych
    */

    /*
    ======================================================================================
    13. NAJCZĘSTSZE BŁĘDY
    ======================================================================================

    - brak php-mysql
    - mysql nie działa
    - złe hasło roota
    - brak restartu apache

    Rozwiązanie:
        sudo apt install php-mysql
        sudo systemctl restart apache2
    */

    /*
    ======================================================================================
    14. PODSUMOWANIE
    ======================================================================================

    - .deb = pakiet systemowy
    - dpkg = instalacja ręczna
    - MySQL Community = standard
    - /var/lib/mysql = dane
    - mysqldump = najlepszy backup
    - phpMyAdmin = GUI dla MySQL
    */
}
