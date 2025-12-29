package org.example.notes;

/**
 * LN25 – PHPMYADMIN + KONFIGURACJA APACHE (DirectoryIndex index.php)
 *
 * - czym jest phpMyAdmin i po co istnieje
 * - instalacja phpMyAdmin
 * - integracja z Apache
 * - zmiana pliku startowego katalogu (index.php zamiast index.html)
 * - DirectoryIndex – jak działa
 * - test poprawności konfiguracji
 */
public class LN25_PhpMyAdminAndApacheIndex {

    /*
    ======================================================================================
    1. CZYM JEST PHPMYADMIN?
    ======================================================================================

    phpMyAdmin:
        - aplikacja WEBOWA napisana w PHP
        - służy do zarządzania bazami MySQL / MariaDB
        - NIE jest częścią MySQL
        - działa przez przeglądarkę

    Umożliwia:
        - tworzenie baz danych
        - zarządzanie tabelami
        - wykonywanie zapytań SQL
        - zarządzanie użytkownikami
        - import / export danych

    Architektura:
        Przeglądarka
            ↓
        Apache
            ↓
        PHP
            ↓
        phpMyAdmin
            ↓
        MySQL
    */

    /*
    ======================================================================================
    2. INSTALACJA PHPMYADMIN
    ======================================================================================

    Instalacja przez apt:
        sudo apt update
        sudo apt install phpmyadmin

    Podczas instalacji:
        - wybierz apache2
        - pozwól skonfigurować bazę danych
        - podaj hasło do MySQL

    Jeśli instalator NIE zapytał o Apache:
        → trzeba skonfigurować ręcznie
    */

    /*
    ======================================================================================
    3. WŁĄCZENIE PHPMYADMIN W APACHE
    ======================================================================================

    phpMyAdmin instaluje konfigurację Apache:
        /etc/apache2/conf-available/phpmyadmin.conf

    Aktywacja:
        sudo a2enconf phpmyadmin

    Restart Apache:
        sudo systemctl restart apache2
    */

    /*
    ======================================================================================
    4. DOSTĘP DO PHPMYADMIN
    ======================================================================================

    W przeglądarce:
        http://localhost/phpmyadmin

    Logowanie:
        - użytkownik MySQL
        - hasło MySQL
    */

    /*
    ======================================================================================
    5. PROBLEM: APACHE ŁADUJE index.html ZAMIAST index.php
    ======================================================================================

    Apache ma listę plików startowych katalogu:
        DirectoryIndex

    Domyślnie:
        index.html

    Jeśli:
        - istnieje index.html
        - i index.php

    Apache ZAWSZE wybierze PIERWSZY z listy.
    */

    /*
    ======================================================================================
    6. PLIK KONFIGURACYJNY APACHE
    ======================================================================================

    W zależności od instalacji Apache:

    Debian / Ubuntu:
        /etc/apache2/apache2.conf

    Apache ze źródeł:
        /usr/local/apache2/conf/httpd.conf
    */

    /*
    ======================================================================================
    7. EDYCJA httpd.conf / apache2.conf
    ======================================================================================

    Otwórz plik:
        sudo vim /usr/local/apache2/conf/httpd.conf
        LUB
        sudo vim /etc/apache2/apache2.conf
    */

    /*
    ======================================================================================
    8. DYREKTYWA DirectoryIndex
    ======================================================================================

    Szukaj:
        DirectoryIndex

    Domyślnie:
        DirectoryIndex index.html

    Zmień na:
        DirectoryIndex index.php index.html

    Kolejność MA ZNACZENIE:
        Apache sprawdza od LEWEJ do PRAWEJ
    */

    /*
    ======================================================================================
    9. PRZYKŁAD KONFIGURACJI
    ======================================================================================

        <IfModule dir_module>
            DirectoryIndex index.php index.html
        </IfModule>
    */

    /*
    ======================================================================================
    10. DLACZEGO TO JEST WAŻNE?
    ======================================================================================

    Bez index.php:
        - phpMyAdmin może się NIE ładować
        - aplikacje PHP nie startują automatycznie
        - Apache serwuje stare HTML

    Z index.php:
        - PHP przejmuje kontrolę
        - aplikacje webowe działają poprawnie
    */

    /*
    ======================================================================================
    11. RESTART APACHE (OBOWIĄZKOWY)
    ======================================================================================

        sudo systemctl restart apache2

    Bez restartu:
        - zmiany NIE zadziałają
    */

    /*
    ======================================================================================
    12. TEST KONFIGURACJI
    ======================================================================================

    Test 1:
        http://localhost/phpmyadmin

    Test 2:
        /var/www/html/index.php

        <?php
        echo "PHP DZIAŁA";
        ?>

    Jeśli widzisz tekst:
        → Apache + PHP OK
    */

    /*
    ======================================================================================
    13. NAJCZĘSTSZE BŁĘDY
    ======================================================================================

    - index.html blokuje index.php
    - brak restartu Apache
    - brak php module
    - phpMyAdmin nieaktywny w Apache

    Naprawa:
        sudo a2enconf phpmyadmin
        sudo systemctl restart apache2
    */

    /*
    ======================================================================================
    14. PODSUMOWANIE
    ======================================================================================

    - phpMyAdmin = GUI do MySQL
    - działa przez Apache + PHP
    - DirectoryIndex decyduje co się ładuje
    - index.php MUSI być przed index.html
    - restart Apache jest obowiązkowy
    */
}
