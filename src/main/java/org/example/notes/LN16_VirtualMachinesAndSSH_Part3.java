package org.example.notes;

/**
 * LN16 – SSH (CZĘŚĆ 3)
 *
 * - aliasy SSH (plik ~/.ssh/config)
 * - zmiana portu SSH na serwerze
 * - restart usługi ssh
 * - zakresy portów i dlaczego nie używać niskich
 * - użycie klucza SSH do GitHuba
 * - instalacja gita
 * - inicjalizacja repozytorium
 * - dodanie plików i pierwsze commity
 */
public class LN16_VirtualMachinesAndSSH_Part3 {

    /*
    ======================================================================================
    1. ALIASY SSH – PO CO?
    ======================================================================================

    Jeśli często łączysz się:
        ssh przemek@192.168.1.50 -p 2222

    to jest upierdliwe.

    Rozwiązanie:
        aliasy SSH w pliku konfiguracyjnym klienta.

    Dzięki temu piszesz:
        ssh serwer-test
    */

    /*
    ======================================================================================
    2. PLIK ~/.ssh/config – GDZIE I JAK?
    ======================================================================================

    Lokalizacja:
        ~/.ssh/config

    Jeśli katalog nie istnieje:
        mkdir -p ~/.ssh
        chmod 700 ~/.ssh

    Jeśli plik nie istnieje:
        touch ~/.ssh/config
        chmod 600 ~/.ssh/config

    Edycja:
        vim ~/.ssh/config
    */

    /*
    ======================================================================================
    3. STRUKTURA ALIASU SSH
    ======================================================================================

    Przykład wpisu w ~/.ssh/config:

        Host serwer-test
            HostName 192.168.1.50
            User przemek
            Port 2222
            IdentityFile ~/.ssh/id_rsa

    Co to oznacza:
        Host        → nazwa aliasu
        HostName    → IP lub domena
        User        → użytkownik na serwerze
        Port        → port SSH
        IdentityFile → klucz prywatny

    Od teraz:
        ssh serwer-test
    */

    /*
    ======================================================================================
    4. ZMIANA PORTU SSH NA SERWERZE
    ======================================================================================

    Domyślny port SSH:
        22

    Zmiana portu:
        - utrudnia skanowanie botom
        - NIE zastępuje bezpieczeństwa, ale pomaga

    Porty:
        0–1023   → zarezerwowane dla usług systemowych
        1024+    → porty użytkownika (zalecane)

    Nie używaj niskich portów:
        - mogą być zajęte
        - wymagają roota
        - konflikt z usługami

    Wybierz np:
        2222
        22022
        2022
    */

    /*
    ======================================================================================
    5. EDYCJA KONFIGURACJI SERWERA SSH
    ======================================================================================

    Plik konfiguracyjny serwera:
        /etc/ssh/sshd_config

    Edycja:
        sudo vim /etc/ssh/sshd_config

    Znajdź linię:
        #Port 22

    Odkomentuj i zmień:
        Port 2222

    Uwaga:
        - linie zaczynające się od # są komentarzami
    */

    /*
    ======================================================================================
    6. RESTART USŁUGI SSH
    ======================================================================================

    Każda zmiana konfiguracji serwera WYMAGA restartu usługi.

    Restart:
        sudo systemctl restart ssh.service

    Sprawdzenie:
        sudo systemctl status ssh.service

    Jeśli usługa nie wstaje:
        - masz błąd w konfiguracji
        - port zajęty
        - literówka
    */

    /*
    ======================================================================================
    7. TEST POŁĄCZENIA PO ZMIANIE PORTU
    ======================================================================================

    Bez aliasu:
        ssh przemek@192.168.1.50 -p 2222

    Z aliasem:
        ssh serwer-test

    Jeśli NIE możesz się zalogować:
        - firewall blokuje port
        - sshd nie działa
        - zły port
    */

    /*
    ======================================================================================
    8. KLUCZ SSH A GIT – PO CO?
    ======================================================================================

    GitHub / GitLab używają SSH do:
        - autoryzacji
        - push/pull bez hasła
        - bezpiecznej komunikacji

    Klucz SSH:
        - identyfikuje Ciebie jako użytkownika
        - NIE jest hasłem
    */

    /*
    ======================================================================================
    9. INSTALACJA GITA W LINUXIE
    ======================================================================================

        sudo apt update
        sudo apt install git

    Sprawdzenie:
        git --version
    */

    /*
    ======================================================================================
    10. GENEROWANIE KLUCZA SSH DLA GITA
    ======================================================================================

    Jeśli już masz klucz:
        ~/.ssh/id_rsa
        ~/.ssh/id_rsa.pub

    Jeśli nie:
        ssh-keygen -t rsa -b 4096 -C "email@github"

    Klucz publiczny:
        ~/.ssh/id_rsa.pub

    Wyświetlenie:
        cat ~/.ssh/id_rsa.pub
    */

    /*
    ======================================================================================
    11. DODANIE KLUCZA SSH DO GITHUBA
    ======================================================================================

    1) Zaloguj się na GitHub
    2) Settings → SSH and GPG keys
    3) New SSH key
    4) Wklej zawartość id_rsa.pub
    5) Zapisz

    Test:
        ssh -T git@github.com

    Jeśli działa:
        Hi username! You've successfully authenticated.
    */

    /*
    ======================================================================================
    12. INICJALIZACJA LOKALNEGO REPOZYTORIUM
    ======================================================================================

    Utwórz katalog projektu:
        mkdir projekt
        cd projekt

    Skopiuj NIEPUSTY plik:
        cp ~/jakis_plik.txt .

    Inicjalizacja repo:
        git init

    Sprawdzenie:
        git status
    */

    /*
    ======================================================================================
    13. DODANIE PLIKÓW I COMMIT
    ======================================================================================

        git add .
        git commit -m "Initial commit"

    Repozytorium lokalne gotowe.
    */

    /*
    ======================================================================================
    14. PODPIĘCIE REPOZYTORIUM ZDALNEGO (GITHUB)
    ======================================================================================

    Na GitHubie:
        - New repository
        - NIE dodawaj README (bo już masz lokalne)

    Skopiuj adres SSH:
        git@github.com:user/repo.git

    W terminalu:
        git remote add origin git@github.com:user/repo.git

    Push:
        git branch -M main
        git push -u origin main
    */

    /*
    ======================================================================================
    15. PODSUMOWANIE
    ======================================================================================

    - ~/.ssh/config → aliasy SSH
    - zmiana portu SSH zwiększa bezpieczeństwo
    - po zmianach → restart ssh.service
    - porty <1024 są systemowe
    - Git używa SSH do autoryzacji
    - klucz publiczny trafia do GitHuba
    - git init → git add → git commit → git push
    */
}
