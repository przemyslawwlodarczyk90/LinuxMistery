package org.example.notes;

/**
 * LN03 – HIERARCHICZNA STRUKTURA KATALOGÓW W LINUXIE (UBUNTU)
 *
 * Notatki o tym, jak zorganizowany jest system plików w Linuxie.
 * Klasa służy tylko jako ściąga – nie zawiera logiki.
 */
public class LN03_FileHierarchy {

    /*
    ======================================================================================
    1. OGÓLNA KONCEPCJA – WSZYSTKO ZACZYNA SIĘ OD "/"
    ======================================================================================

    - Linux ma jeden wspólny system plików.
    - Korzeń drzewa to katalog:
            /
      (root directory)

    - Wszystkie pliki i katalogi są poniżej "/":
            /
            ├── bin
            ├── dev
            ├── etc
            ├── home
            ├── tmp
            ├── usr
            └── var
      Nie ma C:, D:, E:. Dyski i partycje montuje się jako katalogi.

    - Przykład ścieżek:
        /home/przemek/Documents/plik.txt      -> ścieżka absolutna
        Documents/plik.txt                    -> ścieżka względna z katalogu domowego

    - Specjalne symbole:
        .   -> bieżący katalog
        ..  -> katalog nadrzędny
        ~   -> katalog domowy aktualnego użytkownika

    --------------------------------------------------------------------------------------
    NAWIGACJA – KOMENDY: ls, cd, pwd
    --------------------------------------------------------------------------------------

    ls      – wyświetla zawartość katalogu
              ls              (aktualny katalog)
              ls /etc         (zawartość /etc)
              ls -l           (lista w formie tabeli: prawa, właściciel, rozmiar, data)
              ls -a           (również pliki ukryte, zaczynające się od kropki)

    cd      – zmiana katalogu
              cd /etc
              cd /home
              cd ~            (do katalogu domowego)
              cd ..           (jeden poziom w górę)
              cd /            (do /)

    pwd     – pokaż pełną ścieżkę bieżącego katalogu

    Te trzy komendy są absolutnym minimum do poruszania się po strukturze.
    */

    /*
    ======================================================================================
    2. /bin – PODSTAWOWE PROGRAMY UŻYTKOWNIKÓW
    ======================================================================================

    - Katalog /bin zawiera podstawowe programy potrzebne każdemu użytkownikowi
      oraz do startu systemu w trybach awaryjnych.
    - Binarne pliki wykonywalne (stąd "bin").
    - Dostępne bez podawania pełnej ścieżki, bo /bin jest w zmiennej PATH.

    Typowe programy w /bin:
        - ls      – listowanie plików
        - cat     – wyświetlanie zawartości plików
        - cp      – kopiowanie
        - mv      – przenoszenie/zmiana nazwy
        - rm      – usuwanie
        - mkdir   – tworzenie katalogów
        - rmdir   – usuwanie pustych katalogów
        - bash/sh – powłoka

    Jeśli system wstanie w trybie ratunkowym, /bin nadal jest dostępny,
    więc można wykonywać podstawowe operacje.
    */

    /*
    ======================================================================================
    3. /sbin – NARZĘDZIA ADMINISTRACYJNE DLA ROOT
    ======================================================================================

    - /sbin zawiera programy do zarządzania systemem.
    - Te pliki też są wykonywalne, ale większość z nich ma sens tylko dla użytkownika root.
    - Zwykły użytkownik może je uruchomić tylko z sudo.

    Typowe narzędzia:
        - fdisk, parted      – partycjonowanie dysków
        - mkfs.*             – tworzenie systemów plików (mkfs.ext4 itp.)
        - ifconfig, ip       – konfiguracja sieci
        - shutdown, reboot   – wyłączanie i restart
        - mount, umount      – montowanie i odmontowywanie systemów plików
        - systemd-udevd itp. – komponenty systemowe

    /bin -> narzędzia „dla wszystkich”
    /sbin -> narzędzia „dla admina”.
    */

    /*
    ======================================================================================
    4. /boot – START SYSTEMU
    ======================================================================================

    - Zawiera wszystko, co potrzebne do uruchomienia systemu operacyjnego.
    - Przykładowe elementy:
        - jądro systemu (plik vmlinuz-...)
        - initramfs / initrd (dodatkowy system startowy z modułami)
        - katalog /boot/grub z konfiguracją bootloadera GRUB

    - Przy uruchamianiu komputera firmware/UEFI wskazuje na bootloader
      (np. GRUB) zapisany właśnie w /boot lub na specjalnej partycji EFI.

    - Zmiana plików w /boot bez wiedzy może unieruchomić system.
    */

    /*
    ======================================================================================
    5. /dev – URZĄDZENIA JAKO PLIKI
    ======================================================================================

    - /dev to katalog z „plikami urządzeń”.
    - Linux traktuje urządzenia (dyski, porty, terminale) jak pliki.
      Dzięki temu można używać tych samych narzędzi do obsługi danych i urządzeń.

    Przykłady:
        - /dev/sda      – pierwszy dysk
        - /dev/sda1     – pierwsza partycja na tym dysku
        - /dev/sdb      – drugi dysk lub pendrive
        - /dev/null     – wszystko, co tu zapiszesz, znika
        - /dev/zero     – generuje zera (używane np. do testów)
        - /dev/random   – dane losowe
        - /dev/tty      – aktualny terminal

    - Współczesne systemy używają udev/systemd do dynamicznego tworzenia tych plików,
      gdy podpinasz i odłączasz urządzenia.
    */

    /*
    ======================================================================================
    6. /etc – KONFIGURACJA SYSTEMU
    ======================================================================================

    - /etc zawiera pliki konfiguracyjne systemu oraz wielu usług.
    - Dotyczy to ustawień ogólnosystemowych, wspólnych dla wszystkich użytkowników.

    Typowe pliki:
        - /etc/passwd       – lista kont użytkowników
        - /etc/shadow       – zahashowane hasła użytkowników
        - /etc/group        – grupy
        - /etc/hosts        – lokalne mapowanie nazw hostów na IP
        - /etc/hostname     – nazwa hosta
        - /etc/fstab        – lista systemów plików montowanych przy starcie
        - /etc/resolv.conf  – serwery DNS (w nowoczesnych systemach często
                              nadpisywane przez NetworkManager/systemd-resolved)

    Typowe katalogi konfiguracyjne:
        - /etc/network/               – konfiguracja sieci w starszych systemach
        - /etc/systemd/               – jednostki systemd
        - /etc/apt/                   – konfiguracja APT w Debian/Ubuntu
        - /etc/ssh/                   – konfiguracja SSH

    Zmiany w zachowaniu systemu lub usług prawie zawsze odbywają się
    poprzez edycję plików w /etc (ręcznie albo przez narzędzia).
    */

    /*
    ======================================================================================
    7. /home – KATALOGI UŻYTKOWNIKÓW
    ======================================================================================

    - /home zawiera katalogi domowe zwykłych użytkowników.
      Przykład:
        /home/bill
        /home/patrik

    - W katalogu domowym użytkownik trzyma swoje pliki:
        /home/przemek/Documents
        /home/przemek/Downloads
        /home/przemek/Pictures

    - Pliki konfiguracyjne dla programów użytkownika często są ukryte
      i zaczynają się od kropki:
        /home/przemek/.bashrc
        /home/przemek/.ssh/
        /home/przemek/.config/

    - Jeśli w systemie jest jeden użytkownik, będzie jeden katalog w /home.
      Jeśli jest ich więcej – każdy dostaje swój podkatalog.
    */

    /*
    ======================================================================================
    8. /lib i /lib64 – BIBLIOTEKI SYSTEMOWE
    ======================================================================================

    - /lib – biblioteki współdzielone dla programów 32-bitowych
      (w systemach 64-bitowych czasem jako kompatybilność).
    - /lib64 – biblioteki dla programów 64-bitowych.

    - Biblioteki to odpowiednik .dll z Windowsa.
      Programy w /bin i /sbin korzystają z funkcji dostarczonych przez biblioteki
      znajdujące się w /lib i /lib64.

    Przykłady:
        - /lib/x86_64-linux-gnu/libc.so.6   – podstawowa biblioteka C
        - /lib/modules/<wersja-kernela>/    – moduły jądra

    Bez tych katalogów programy systemowe nie mogłyby się uruchomić.
    */

    /*
    ======================================================================================
    9. /mnt – RĘCZNE MONTOWANIE DYSKÓW
    ======================================================================================

    - /mnt historycznie służył do tymczasowego montowania dodatkowych systemów plików.
    - Administrator mógł ręcznie zamontować dysk:
          mount /dev/sdb1 /mnt

    - Współczesne systemy desktopowe często używają:
        /media/<użytkownik>/<nazwa_nośnika>
      ale /mnt nadal jest typowym miejscem do ręcznych testów i prac serwisowych.
    */

    /*
    ======================================================================================
    10. /opt – OPROGRAMOWANIE FIRM TRZECICH
    ======================================================================================

    - /opt przeznaczony jest dla dodatkowych aplikacji spoza podstawowej dystrybucji.
    - Programy instalowane ręcznie (np. z archiwów .tar.gz, pakietów vendorskich) mogą
      tworzyć własne katalogi:
        /opt/google/
        /opt/teamviewer/
        /opt/myApp/

    - Struktura w /opt jest zwykle niezależna od reszty systemu.
    - Dzięki temu można łatwiej usunąć aplikację – wystarczy usunąć jej katalog.
    */

    /*
    ======================================================================================
    11. /proc – INFORMACJE O SYSTEMIE I PROCESACH
    ======================================================================================

    - /proc to pseudosytem plików tworzony w RAM, a nie na dysku.
    - Zawiera informacje o:
        - procesach,
        - konfiguracji jądra,
        - sprzęcie.

    Przykłady:
        - /proc/cpuinfo    – informacje o procesorze
        - /proc/meminfo    – pamięć RAM
        - /proc/uptime     – czas działania systemu
        - /proc/<PID>/     – katalog z informacjami o konkretnym procesie

    - Dane w /proc są aktualne w czasie rzeczywistym.
    - Programy systemowe (top, ps, free) czytają te pliki, żeby wyświetlić statystyki.
    */

    /*
    ======================================================================================
    12. /root – KATALOG DOMOWY ADMINISTRATORA
    ======================================================================================

    - /root to katalog domowy użytkownika root (administrator systemu).
    - Nie mylić z "/" (katalog główny).
      "/"   -> root directory (korzeń)
      "/root" -> katalog domowy użytkownika root.

    - Pliki specyficzne dla pracy admina mogą być trzymane tutaj
      zamiast w /home/<ktoś>.
    */

    /*
    ======================================================================================
    13. /var – DANE, KTÓRE SIĘ ZMIENIAJĄ
    ======================================================================================

    - Nazwa pochodzi od "variable".
    - To miejsce na dane, których zawartość często się zmienia.

    Typowe podkatalogi:
        - /var/log/          – logi systemowe i aplikacji
              /var/log/syslog
              /var/log/auth.log
              /var/log/apache2/
        - /var/spool/        – kolejki zadań (np. drukarki, poczta)
        - /var/lib/          – dane robocze usług (np. bazy danych)
        - /var/www/          – domyślne miejsce na strony WWW dla Apache (w wielu distro)
        - /var/cache/        – cache pakietów i aplikacji

    - Jeśli któraś usługa jest intensywnie używana, /var może szybko rosnąć.
    - Przy administrowaniu systemem logi w /var/log są kluczowe do diagnozowania problemów.
    */

    /*
    ======================================================================================
    14. /tmp – PLIKI TYMCZASOWE
    ======================================================================================

    - /tmp przechowuje pliki tymczasowe tworzone przez programy i użytkowników.
    - Typowe zastosowania:
        - pliki robocze edytorów,
        - pliki tymczasowe przeglądarek,
        - buforowanie działań instalatorów.

    - W wielu systemach zawartość /tmp jest czyszczona przy restarcie
      lub po określonym czasie.
    - Nie jest to miejsce na dane, które mają być przechowywane długotrwale.
    */

    /*
    ======================================================================================
    15. /usr – PROGRAMY I DANE UŻYTKOWNIKÓW
    ======================================================================================

    - /usr = "user system resources" (nie „user home”).
    - To ogromna część systemu – tu są zainstalowane programy, biblioteki,
      dokumentacja i wiele narzędzi użytkownika.

    Ważne podkatalogi:
        - /usr/bin/      – ogromna większość programów użytkownika,
                           np. git, python, gcc, vim, firefox, itd.
        - /usr/sbin/     – programy administracyjne, mniej krytyczne niż w /sbin
        - /usr/lib/      – biblioteki dla programów z /usr/bin i /usr/sbin
        - /usr/local/    – miejsce na programy instalowane lokalnie,
                           niezależne od systemowego menedżera pakietów
        - /usr/share/    – pliki współdzielone: ikony, szablony, lokalizacje, dokumentacja
        - /usr/include/  – nagłówki dla kompilatorów (C/C++)

    Ogólna zasada:
        /bin, /sbin, /lib    -> minimalny zestaw potrzebny do startu systemu
        /usr/...             -> reszta oprogramowania i bibliotek
    */

    /*
    ======================================================================================
    16. INNE WAŻNE KATALOGI (DO ZNAJOMOŚCI OGÓLNEJ)
    ======================================================================================

    /media
        - automatyczne montowanie nośników wymiennych (pendrive, płyty)
        - przykłady:
              /media/przemek/PENDRIVE
              /media/przemek/SSD_PORTABLE

    /run
        - dane działających usług (PID-y, sockety, pliki lock)
        - katalog tworzony w RAM przy starcie systemu

    /sys
        - podobny do /proc, ale zawiera informacje o urządzeniach i jądrze.
        - używany przez udev, narzędzia sprzętowe, itp.
    */

    /*
    ======================================================================================
    17. SKRÓTOWE PODSUMOWANIE
    ======================================================================================

    /           – korzeń wszystkiego
    /bin        – podstawowe programy dla wszystkich użytkowników
    /sbin       – programy administracyjne
    /boot       – start systemu (kernel, bootloader)
    /dev        – urządzenia jako pliki
    /etc        – konfiguracja systemu
    /home       – katalogi domowe użytkowników
    /lib, /lib64 – biblioteki systemowe
    /mnt        – ręczne montowanie dysków
    /opt        – dodatkowe aplikacje firm trzecich
    /proc       – informacje o systemie i procesach (pseudo system plików)
    /root       – katalog domowy użytkownika root
    /var        – dane zmienne (logi, bazy, cache)
    /tmp        – pliki tymczasowe
    /usr        – programy i biblioteki dla użytkowników, dokumentacja

    Znajomość tych katalogów jest podstawą do:
        - poruszania się po systemie,
        - edycji konfiguracji,
        - analizy problemów,
        - administrowania serwerami.
    */

}
