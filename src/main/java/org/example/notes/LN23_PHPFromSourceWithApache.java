package org.example.notes;

/**
 * LN23 – INSTALACJA PHP ZE ŹRÓDEŁ + INTEGRACJA Z APACHE
 *
 * - dlaczego PHP ze źródeł
 * - czym jest interpreter PHP
 * - jak Apache komunikuje się z PHP
 * - biblioteki dynamiczne (.so)
 * - apxs2 – co to jest i po co
 * - kompilacja PHP jako modułu Apache
 * - konfiguracja Apache (FileMatch, AddHandler)
 * - test PHP na serwerze
 */
public class LN23_PHPFromSourceWithApache {

    /*
    ======================================================================================
    1. DLACZEGO PHP ZE ŹRÓDEŁ, A NIE Z APT?
    ======================================================================================

    Instalacja z apt:
        - szybka
        - prosta
        - MAŁO kontrolowana

    Instalacja ze źródeł:
        - pełna kontrola opcji
        - świadome włączanie modułów
        - zrozumienie architektury

    Tu uczymy się:
        JAK APACHE ROZMAWIA Z PHP
    */

    /*
    ======================================================================================
    2. ARCHITEKTURA: APACHE ↔ PHP
    ======================================================================================

    Apache:
        - serwer HTTP
        - obsługuje żądania

    PHP:
        - interpreter języka PHP
        - NIE jest serwerem

    Apache MUSI:
        - wiedzieć, że plik .php ≠ zwykły plik
        - przekazać go do interpretera PHP
        - odebrać wynik HTML
        - wysłać go do klienta

    To połączenie realizuje:
        MODUŁ APACHE (biblioteka dynamiczna .so)
    */

    /*
    ======================================================================================
    3. BIBLIOTEKI DYNAMICZNE (.so)
    ======================================================================================

    .so (shared object):
        - dynamicznie ładowana biblioteka
        - ładowana w runtime
        - NIE statycznie wkompilowana

    Apache:
        - ładuje moduły .so
        - np: mod_php.so

    PHP:
        - podczas kompilacji może wygenerować:
            libphp.so
    */

    /*
    ======================================================================================
    4. CZY APACHE OBSŁUGUJE MODUŁY DYNAMICZNE?
    ======================================================================================

    Apache MUSI być skompilowany z:
        --enable-so

    Sprawdzenie:
        apachectl -l

    Jeśli nie ma mod_so:
        → Apache nie obsłuży dynamicznych modułów
    */

    /*
    ======================================================================================
    5. APXS2 – CO TO JEST I PO CO?
    ======================================================================================

    apxs2 = APache eXtenSion tool

    apxs2:
        - narzędzie Apache
        - kompiluje i instaluje moduły .so
        - wie gdzie:
            • są nagłówki Apache
            • gdzie są moduły
            • jak je zarejestrować

    PHP używa apxs2,
    aby poprawnie zbudować moduł Apache.

    Lokalizacja:
        /usr/bin/apxs2

    Instalacja:
        sudo apt install apache2-dev
    */

    /*
    ======================================================================================
    6. POBRANIE PHP ZE ŹRÓDEŁ
    ======================================================================================

    Oficjalna strona:
        https://www.php.net/downloads

    Przykład:
        php-8.x.x.tar.gz

    Rozpakowanie:
        tar xvf php-8.x.x.tar.gz
        cd php-8.x.x
    */

    /*
    ======================================================================================
    7. KONFIGURACJA PHP POD APACHE
    ======================================================================================

    ./configure:
        - sprawdza zależności
        - ustawia opcje
        - generuje Makefile

    Kluczowe opcje:
        --with-apxs2=/usr/bin/apxs2

    Przykład:
        ./configure \
          --with-apxs2=/usr/bin/apxs2 \
          --enable-mbstring \
          --with-zlib

    Efekt:
        - PHP zostanie skompilowany jako MODUŁ Apache
    */

    /*
    ======================================================================================
    8. KOMPILACJA I INSTALACJA PHP
    ======================================================================================

        make
        sudo make install

    Co się stanie:
        - powstanie libphp.so
        - zostanie skopiowany do Apache
        - Apache dostanie wpis LoadModule
    */

    /*
    ======================================================================================
    9. REJESTRACJA PHP W APACHE
    ======================================================================================

    W konfiguracji Apache (np. apache2.conf):

        LoadModule php_module modules/libphp.so

    Lub automatycznie dodane przez apxs2.
    */

    /*
    ======================================================================================
    10. MAPOWANIE ROZSZERZENIA .php NA INTERPRETER
    ======================================================================================

    Apache MUSI wiedzieć:
        .php → PHP interpreter

    Używa się:
        <FilesMatch>

    Przykład:

        <FilesMatch \.php$>
            SetHandler application/x-httpd-php
        </FilesMatch>

    To mówi Apache:
        - każdy plik .php przekaż do PHP
    */

    /*
    ======================================================================================
    11. GDZIE DODAĆ KONFIGURACJĘ?
    ======================================================================================

    Możliwości:
        - apache2.conf
        - mods-available/php.conf
        - sites-available/*.conf

    Po zmianach:
        sudo systemctl restart apache2
    */

    /*
    ======================================================================================
    12. TEST PHP NA SERWERZE
    ======================================================================================

    Plik testowy:
        /var/www/html/info.php

    Zawartość:
        <?php
        phpinfo();
        ?>

    W przeglądarce:
        http://localhost/info.php

    Jeśli widzisz stronę phpinfo:
        → PHP działa poprawnie
    */

    /*
    ======================================================================================
    13. CO SIĘ DZIEJE POD MASKĄ?
    ======================================================================================

    Klient:
        → HTTP request

    Apache:
        → rozpoznaje .php
        → przekazuje do PHP

    PHP:
        → interpretuje kod
        → generuje HTML

    Apache:
        → wysyła odpowiedź
    */

    /*
    ======================================================================================
    14. PODSUMOWANIE
    ======================================================================================

    - PHP to interpreter, nie serwer
    - Apache komunikuje się z PHP przez moduł .so
    - apxs2 umożliwia poprawną kompilację modułu
    - --with-apxs2 = kluczowa flaga
    - FilesMatch mapuje .php na PHP
    - phpinfo() = test poprawności
    */
}
