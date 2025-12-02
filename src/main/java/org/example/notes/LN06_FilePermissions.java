package org.example.notes;

/**
 * LN06 – PRAWA DOSTĘPU W PRAKTYCE (chmod, ls -la, właściciel, grupa)
 *
 * Najważniejsza i najbardziej praktyczna wiedza:
 * - jak czytać prawa dostępu
 * - jak je zmieniać
 * - jak działają typy dostępu
 * - przykłady chmod liczbowego i symbolicznego
 * - prawa plików vs prawa katalogów
 */
public class LN06_FilePermissions {

    /*
    ======================================================================================
    1. ODCZYT PRAW – „ls -la plik”
    ======================================================================================

    Polecenie:
        ls -la nazwa_pliku

    Wynik wygląda np. tak:

        -rwxr-xr--  1 przemek grupa  1200  Jan  1 12:00  skrypt.sh
         ^^^ ^^^ ^^^
         │   │   └─── inni (others)
         │   └────── grupa (group)
         └────────── właściciel (user)

    Pierwszy znak:
        -   -> plik zwykły
        d   -> katalog
        l   -> dowiązanie symboliczne (link)
        c   -> character device
        b   -> block device

    Trzy trójki liter (rwx):
        r = read    (odczyt)
        w = write   (zapis)
        x = execute (wykonywanie)

    Ułożenie:
        rwx | r-x | r--
        user group others

    Przykład:
        -rwxr-x---
        właściciel -> rwx  (czyli może czytać, pisać, wykonywać)
        grupa      -> r-x  (czytać i wykonywać)
        inni       -> ---  (nie mogą nic)

    */

    /*
    ======================================================================================
    2. WARTOŚCI LICZBOWE (chmod numeryczny)
    ======================================================================================

    Każda litera ma swoją wartość:

        r = 4
        w = 2
        x = 1

    Sumujemy:

        rwx = 4+2+1 = 7
        rw- = 4+2   = 6
        r-x = 4+1   = 5
        r-- = 4     = 4
        --- = 0

    Dlaczego to ważne?
    Bo chmod może ustawiać prawa w formie liczb:

    chmod 755 plik
    -> użytkownik: 7  (rwx)
       grupa:      5  (r-x)
       inni:       5  (r-x)

    chmod 644 plik
    -> typowe prawa dla plików konfiguracyjnych:
       user: rw-
       group: r--
       others: r--

    chmod 600 haslo.txt
    -> tylko właściciel ma dostęp (r i w),
       grupa i inni nie mają nic

    chmod 700 sekretny_folder/
    -> tylko właściciel ma pełny dostęp
    */

    /*
    ======================================================================================
    3. CHMOD SYMBOLICZNY (czytelniejsza forma)
    ======================================================================================

    Formuła:
        chmod [kogo] [+/−/=] [jakie_prawa] plik

    Kogo?
        u = user      (właściciel)
        g = group     (grupa)
        o = others    (inni)
        a = all       (wszyscy)

    Jakie prawa?
        r, w, x

    Operacje:
        + = dodaj
        - = zabierz
        = = ustaw dokładnie

    Przykłady:

        chmod u+x skrypt.sh
        -> dodaj prawo wykonywania właścicielowi

        chmod g-w dokument.txt
        -> odbierz prawo zapisu grupie

        chmod a+r plik.txt
        -> każdy może odczytać

        chmod u=rw, g=r, o= plik
        -> ustawienia dokładne:
           user: rw
           group: r
           others: brak praw

        chmod o+x folder
        -> pozwala innym wchodzić do folderu
    */

    /*
    ======================================================================================
    4. PRAWA DO PLIKÓW VS PRAWA DO KATALOGÓW
    ======================================================================================

    ***PLIK:***
        r – możliwość odczytu treści pliku
        w – możliwość modyfikacji pliku
        x – możliwość uruchomienia (skrypt, program, binarka)

    ***KATALOG:***
        r – możliwość listowania plików (ls)
        w – możliwość tworzenia/usuwania plików w katalogu
        x – możliwość wejścia (cd) do katalogu

    SUPER WAŻNE:
        Żeby wejść do katalogu → MUSISZ mieć x
        Żeby zobaczyć zawartość → MUSISZ mieć r i x
        Żeby tworzyć/usuwać → MUSISZ mieć w i x

    Przykład:
        chmod 000 folder
        -> nikt nie może nic zrobić (nawet wejść)

        chmod 100 folder
        -> x tylko dla właściciela – może wejść, ale nie listować

        chmod 755 folder
        -> standardowe prawa dla katalogów
    */

    /*
    ======================================================================================
    5. TWORZENIE PLIKÓW I KATALOGÓW – DOMYŚLNE PRAWA (umask)
    ======================================================================================

    Domyślne prawa:
        pliki:  666 (rw-rw-rw-) minus umask
        katalogi: 777 (rwxrwxrwx) minus umask

    Typowe umask = 022
        pliki -> 644 (rw-r--r--)
        katalogi -> 755 (rwxr-xr-x)

    Przykład:
        umask
        -> pokazuje aktualną maskę

        umask 077
        -> nowe pliki będą prywatne (600), a katalogi 700
    */

    /*
    ======================================================================================
    6. PRAKTYKA CHMOD – PRZYKŁADY
    ======================================================================================

    chmod 755 skrypt.sh
    -> użytkownik ma rwx, reszta tylko r-x

    chmod 744 plik.txt
    -> user: rwx
       group: r--
       others: r--

    chmod 600 hasla.txt
    -> tylko właściciel może czytać i pisać

    chmod 700 .ssh
    -> folder SSH musi mieć pełne prawa tylko dla właściciela

    chmod u+x instalator.sh
    -> dodaje prawo wykonywania tylko właścicielowi

    chmod go-rw plik
    -> zabiera grupie i innym prawa r i w

    chmod a+rx folder
    -> każdy może wejść i czytać zawartość

    chmod o+x folder
    -> inni mogą wejść do katalogu, ale nie wiedzą co w nim jest
    */

    /*
    ======================================================================================
    7. chown – ZMIANA WŁAŚCICIELA I GRUPY
    ======================================================================================

    Format:
        chown nowy_właściciel plik
        chown użytkownik:grupa plik

    Przykłady:

        chown przemek plik.txt
        -> właściciel = przemek

        chown przemek:devs plik.txt
        -> właściciel = przemek, grupa = devs

        chown root:root /folder
        -> folder należy do root

        chown -R przemek projekty/
        -> rekurencyjna zmiana właściciela
    */

    /*
    ======================================================================================
    8. chgrp – ZMIANA GRUPY (skrót)
    ======================================================================================

    chgrp devs plik.txt
    -> zmienia grupę pliku na devs

    chgrp -R devs katalog/
    -> rekurencyjnie ustawia grupę devs dla katalogu i zawartości
    */

    /*
    ======================================================================================
    9. PRAKTYCZNE PRZYKŁADY – NAJWAŻNIEJSZA CZĘŚĆ
    ======================================================================================

    ***1) Nadaj plikowi uprawnienia wykonywania:***
        chmod +x skrypt.sh
        chmod u+x skrypt.sh

    ***2) Odbierz prawo wykonywania wszystkim:***
        chmod a-x skrypt.sh

    ***3) Zablokuj dostęp do pliku:***
        chmod 000 plik
        -> nikt nie może nic zrobić

    ***4) Nadaj pełne prawa tylko właścicielowi:***
        chmod 700 folder

    ***5) Upublicznij katalog do odczytu:***
        chmod 755 katalog
        -> typowe prawa w /usr, /bin, /etc

    ***6) Zablokuj listowanie katalogu, ale pozwól wejść:***
        chmod 711 katalog
        -> można wejść, ale nie widać plików

    ***7) Nadaj prawa dla wszystkich użytkowników:***
        chmod 777 katalog
        -> pełny dostęp, ale bardzo niebezpieczne

    ***8) Usuń możliwość modyfikacji katalogu:***
        chmod a-w katalog
        -> nie można tworzyć, usuwać ani zapisywać plików w katalogu

    ***9) Zmień właściciela pliku:***
        chown root plik.txt

    ***10) Zmień właściciela i grupę:***
        chown przemek:dev plik.txt

    */

    /*
    ======================================================================================
    10. PODSUMOWANIE: CO MUSISZ ZAPAMIĘTAĆ
    ======================================================================================

    - ls -la → pokazuje typ pliku i jego prawa.
    - r w x → odczyt, zapis, wykonanie.
    - dla pliku x = możliwość uruchomienia.
    - dla katalogu x = możliwość wejścia do katalogu.

    chmod w liczbach:
        7 = rwx
        6 = rw-
        5 = r-x
        4 = r--
        0 = ---

    chmod symbolicznie:
        u/g/o + r/w/x

    Najważniejsze tryby:
        600 → plik prywatny
        644 → normalny plik do odczytu dla wszystkich
        700 → katalog prywatny
        755 → katalog wykonywalny i czytelny dla wszystkich

    Mechanika:
    - prawa działają zawsze w kolejności:
        właściciel → grupa → inni
    */
}
