package pl.przemek.linux.notes;

/**
 * LN21 – PRZYGOTOWANIE ŚRODOWISKA DO PRACY Z JAVĄ + APACHE
 *
 * - instalacja JDK i JRE
 * - zmienne środowiskowe (bashrc vs /etc/environment)
 * - sumy kontrolne (SHA256)
 * - Maven
 * - nohup
 * - Apache HTTP Server
 * - demony
 * - konfiguracja i struktura Apache
 */
public class LN21_JavaEnvironmentAndApache {

    /*
    ======================================================================================
    1. JDK VS JRE – CO TO JEST?
    ======================================================================================

    JRE (Java Runtime Environment):
        - służy do URUCHAMIANIA aplikacji Java
        - zawiera JVM + biblioteki
        - NIE zawiera kompilatora

    JDK (Java Development Kit):
        - zawiera JRE
        - zawiera kompilator javac
        - narzędzia: javac, jar, javadoc, jdb

    Do pracy developerskiej:
        -> ZAWSZE JDK
    */

    /*
    ======================================================================================
    2. INSTALACJA JDK I JRE PRZEZ APT
    ======================================================================================

    Aktualizacja repozytoriów:
        sudo apt update

    Instalacja OpenJDK:
        sudo apt install openjdk-17-jdk

    Sprawdzenie:
        java -version
        javac -version

    Lokalizacja JDK:
        /usr/lib/jvm/

    Przykład:
        /usr/lib/jvm/java-17-openjdk-amd64
    */

    /*
    ======================================================================================
    3. ZMIENNE ŚRODOWISKOWE – JAVA_HOME I PATH
    ======================================================================================

    JAVA_HOME:
        wskazuje katalog instalacji JDK

    PATH:
        musi zawierać $JAVA_HOME/bin
    */

    /*
    --------------------------------------------------------------------------------------
    3A. DODANIE ZMIENNYCH DO ~/.bashrc (PER USER)
    --------------------------------------------------------------------------------------

    Edycja:
        vim ~/.bashrc

    Dodaj:
        export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
        export PATH=$JAVA_HOME/bin:$PATH

    Załaduj:
        source ~/.bashrc
    */

    /*
    --------------------------------------------------------------------------------------
    3B. DODANIE ZMIENNYCH DO /etc/environment (GLOBALNIE)
    --------------------------------------------------------------------------------------

    Plik:
        /etc/environment

    UWAGA:
        - tylko wartości
        - BEZ export
        - BEZ zmiennych typu $PATH

    Przykład:
        JAVA_HOME="/usr/lib/jvm/java-17-openjdk-amd64"

    PATH trzeba wpisać w całości:
        PATH="/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/lib/jvm/java-17-openjdk-amd64/bin"

    Zmiany po reboocie lub relogowaniu.
    */

    /*
    ======================================================================================
    4. SUMY KONTROLNE – WERYFIKACJA PLIKÓW
    ======================================================================================

    Po co?
        - sprawdzenie integralności
        - ochrona przed podmianą pliku

    IntelliJ / Maven / JDK:
        - podają SHA256 lub SHA1

    Sprawdzenie:
        sha256sum plik.tar.gz

    Porównujesz:
        hash z terminala == hash ze strony

    Jeśli NIE pasuje:
        - plik uszkodzony
        - albo podmieniony
    */

    /*
    ======================================================================================
    5. MAVEN – DO CZEGO I JAK ZAINSTALOWAĆ
    ======================================================================================

    Maven:
        - zarządzanie zależnościami
        - build
        - lifecycle projektu Java

    Instalacja:
        sudo apt install maven

    Sprawdzenie:
        mvn -version

    Lokalizacja repo:
        ~/.m2/repository
    */

    /*
    ======================================================================================
    6. NOHUP – URUCHAMIANIE PROGRAMU PO ZAMKNIĘCIU TERMINALA
    ======================================================================================

    nohup = no hang up

    Użycie:
        nohup java -jar app.jar &

    Co robi:
        - proces działa po zamknięciu sesji
        - stdout zapisany do nohup.out
        - uruchomiony w tle (&)

    Sprawdzenie:
        ps -ef | grep java
    */

    /*
    ======================================================================================
    7. CZYM JEST APACHE HTTP SERVER
    ======================================================================================

    Apache:
        - serwer HTTP
        - obsługuje protokół HTTP/HTTPS
        - NIE jest serwerem Javy

    Apache:
        - serwuje statyczne pliki
        - może działać jako reverse proxy

    Java:
        - działa na Tomcat / Jetty / Spring Boot

    Apache + Java:
        - Apache jako frontend
        - Java jako backend
    */

    /*
    ======================================================================================
    8. INSTALACJA APACHE NA LINUX
    ======================================================================================

    Instalacja:
        sudo apt install apache2

    Sprawdzenie:
        systemctl status apache2

    Test:
        http://localhost
    */

    /*
    ======================================================================================
    9. MAKEFILE.IN I KONFIGURACJA PAKIETÓW
    ======================================================================================

    makefile.in:
        - szablon makefile
        - używany przez ./configure

    Proces kompilacji ze źródeł:
        ./configure
        make
        make install

    ./configure:
        - sprawdza zależności
        - ustawia opcje
        - generuje Makefile
    */

    /*
    ======================================================================================
    10. DEMONY – CO TO JEST?
    ======================================================================================

    Demon:
        - proces działający w tle
        - bez interakcji z użytkownikiem
        - uruchamiany przy starcie systemu

    Przykłady:
        apache2
        sshd
        systemd

    Zarządzanie:
        systemctl start apache2
        systemctl stop apache2
        systemctl restart apache2
    */

    /*
    ======================================================================================
    11. STRUKTURA APACHE
    ======================================================================================

    /etc/apache2/
        apache2.conf      -> główna konfiguracja
        ports.conf        -> porty
        sites-available/  -> definicje vhostów
        sites-enabled/    -> aktywne vhosty
        mods-available/   -> moduły
        mods-enabled/

    /var/www/html/
        -> katalog stron (DocumentRoot)

    /var/log/apache2/
        access.log
        error.log
    */

    /*
    ======================================================================================
    12. PLIKI KONFIGURACYJNE
    ======================================================================================

    httpd.conf / apache2.conf:
        - główna konfiguracja

    DocumentRoot:
        katalog serwowanych plików

    Zmiana konfiguracji:
        sudo vim /etc/apache2/apache2.conf

    Po zmianach:
        sudo systemctl restart apache2
    */

    /*
    ======================================================================================
    13. JAK SPRAWDZIĆ CZY APACHE DZIAŁA POPRAWNIE
    ======================================================================================

    Status:
        systemctl status apache2

    Port:
        ss -tulpn | grep 80

    Logi:
        tail -f /var/log/apache2/error.log
    */

    /*
    ======================================================================================
    14. PODSUMOWANIE
    ======================================================================================

    - JDK = development
    - JAVA_HOME musi być ustawione
    - Maven = standard w Javie
    - Apache = serwer HTTP
    - Apache nie jest serwerem Javy
    - demony działają w tle
    - konfiguracja = /etc
    */
}
