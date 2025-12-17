package org.example.notes;

/**
 * LN17 – RSYNC: PROFESJONALNA SYNCHRONIZACJA DANYCH
 *
 * - czym jest rsync i dlaczego jest lepszy niż scp/cp
 * - algorytm rsync (delta-transfer)
 * - składnia i NAJWAŻNIEJSZE flagi
 * - synchronizacja lokalna
 * - synchronizacja po SSH (host ↔ VM)
 * - backupy, mirror, --delete
 * - --dry-run (bezpieczne testy)
 * - --exclude / --include
 * - uprawnienia, właściciele, ACL
 * - wznawianie transferu
 * - ograniczanie pasma
 */
public class LN17_Rsync {

    /*
    ======================================================================================
    1. CZYM JEST RSYNC?
    ======================================================================================

    rsync to narzędzie do:
        - synchronizacji plików i katalogów
        - backupów
        - mirrorów danych
        - transferów lokalnych i zdalnych

    Dlaczego rsync jest PRO?
        - NIE kopiuje wszystkiego od nowa
        - przesyła tylko RÓŻNICE (delta)
        - zachowuje prawa, właścicieli, timestampy
        - działa przez SSH (bezpiecznie)
        - potrafi wznawiać przerwane transfery

    W porównaniu:
        cp    → kopiuje wszystko
        scp   → kopiuje wszystko przez sieć
        rsync → kopiuje tylko zmiany
    */

    /*
    ======================================================================================
    2. ALGORYTM RSYNC – JAK TO DZIAŁA POD SPODEM?
    ======================================================================================

    rsync dzieli pliki na bloki.
    Dla każdego bloku:
        - liczy sumy kontrolne
        - porównuje blok lokalny z blokiem zdalnym

    Jeśli blok się nie zmienił:
        → NIE jest przesyłany

    Jeśli blok się zmienił:
        → przesyłany jest TYLKO TEN BLOK

    Efekt:
        - ogromna oszczędność czasu i transferu
        - idealne do częstych synchronizacji
    */

    /*
    ======================================================================================
    3. PODSTAWOWA SKŁADNIA
    ======================================================================================

        rsync [OPCJE] ŹRÓDŁO CEL

    Przykład lokalny:
        rsync plik.txt /backup/

    Przykład katalogów:
        rsync katalog/ /backup/katalog/

    UWAGA NA SLASH:
        katalog/   → zawartość katalogu
        katalog    → katalog jako folder
    */

    /*
    ======================================================================================
    4. NAJWAŻNIEJSZA FLAGA: -a (ARCHIVE)
    ======================================================================================

    -a = archive
    W praktyce:
        -r  → rekursywnie
        -l  → linki symboliczne
        -p  → prawa
        -t  → timestampy
        -g  → grupa
        -o  → właściciel
        -D  → device files

    ZAWSZE używaj:
        rsync -a

    To robi „kopię jak należy”.
    */

    /*
    ======================================================================================
    5. FLAGI PRAKTYCZNE (MUST-HAVE)
    ======================================================================================

    -v   → verbose (co się dzieje)
    -h   → human-readable
    --progress → pasek postępu
    --stats    → statystyki po zakończeniu

    Typowy zestaw:
        rsync -avh --progress źródło cel
    */

    /*
    ======================================================================================
    6. SYNCHRONIZACJA LOKALNA – PRZYKŁADY
    ======================================================================================

    Kopia katalogu:
        rsync -avh ~/projekt/ ~/backup/projekt/

    Aktualizacja backupu (tylko zmiany):
        rsync -avh ~/projekt/ ~/backup/projekt/

    Zobacz co by się stało (BEZ KOPIOWANIA):
        rsync -avh --dry-run ~/projekt/ ~/backup/projekt/

    --dry-run:
        - absolutnie kluczowe przy produkcji
        - pokazuje operacje, NIC nie zmienia
    */

    /*
    ======================================================================================
    7. RSYNC PRZEZ SSH (HOST ↔ VM)
    ======================================================================================

    rsync automatycznie używa SSH, jeśli celem jest host zdalny.

    Składnia:
        rsync -avh źródło user@IP:/ścieżka/

    Przykład:
        rsync -avh ~/projekt/ przemek@192.168.1.50:/home/przemek/projekt/

    Z niestandardowym portem SSH:
        rsync -avh -e "ssh -p 2222" ~/projekt/ przemek@192.168.1.50:/home/przemek/projekt/

    Z aliasem SSH (z ~/.ssh/config):
        rsync -avh ~/projekt/ serwer-test:/home/przemek/projekt/
    */

    /*
    ======================================================================================
    8. MIRROR KATALOGU – --delete (UWAGA!)
    ======================================================================================

    --delete:
        - usuwa pliki w CELU, których nie ma w ŹRÓDLE
        - tworzy lustrzaną kopię (mirror)

    Przykład:
        rsync -avh --delete ~/projekt/ ~/backup/projekt/

    OSTRZEŻENIE:
        - błędny katalog = KASACJA danych
        - ZAWSZE testuj z --dry-run

    Test:
        rsync -avh --delete --dry-run ~/projekt/ ~/backup/projekt/
    */

    /*
    ======================================================================================
    9. WYKLUCZENIA – --exclude / --include
    ======================================================================================

    Wykluczenie plików:
        --exclude="*.log"
        --exclude="node_modules/"
        --exclude=".git/"

    Przykład:
        rsync -avh --exclude=".git/" --exclude="*.log" projekt/ backup/

    Z pliku:
        --exclude-from=exclude.txt

    exclude.txt:
        *.log
        node_modules/
        .git/
    */

    /*
    ======================================================================================
    10. UPRAWNIENIA, WŁAŚCICIELE, ROOT
    ======================================================================================

    Jeśli synchronizujesz systemowe pliki:
        - potrzebujesz sudo
        - zachowanie właścicieli wymaga roota

    Przykład:
        sudo rsync -a /etc/ /backup/etc/

    Bez sudo:
        - właściciel stanie się użytkownikiem kopiującym
    */

    /*
    ======================================================================================
    11. WZNAWIANIE PRZERWANEGO TRANSFERU
    ======================================================================================

    Flagi:
        --partial
        --append-verify

    Przykład:
        rsync -avh --partial --progress duzy_plik.iso serwer:/data/

    rsync:
        - nie zaczyna od zera
        - kontynuuje transfer
    */

    /*
    ======================================================================================
    12. OGRANICZENIE PASMA (BANDWIDTH LIMIT)
    ======================================================================================

    --bwlimit=KBPS

    Przykład:
        rsync -avh --bwlimit=500 projekt/ serwer:/data/

    → max 500 KB/s
    */

    /*
    ======================================================================================
    13. RSYNC JAKO BACKUP – DOBRA PRAKTYKA
    ======================================================================================

    Codzienny backup:
        rsync -avh --delete ~/dane/ /mnt/backup/dane/

    Z cronem:
        0 2 * * * rsync -avh --delete ~/dane/ /mnt/backup/dane/

    rsync NIE JEST snapshotem:
        - nadpisuje
        - usuwa
        - do snapshotów: rsnapshot / btrfs / zfs
    */

    /*
    ======================================================================================
    14. NAJCZĘSTSZE BŁĘDY
    ======================================================================================

    1) Zły slash:
        projekt  vs  projekt/

    2) Brak --dry-run przed --delete

    3) Brak -a → utrata praw

    4) Brak -e ssh przy niestandardowym porcie

    5) Kopiowanie jako zwykły user zamiast root
    */

    /*
    ======================================================================================
    15. PODSUMOWANIE
    ======================================================================================

    - rsync = synchronizacja różnicowa
    - -a to podstawa
    - --dry-run to bezpieczeństwo
    - --delete = mirror (ostrożnie!)
    - działa lokalnie i po SSH
    - idealny do backupów i VM ↔ VM
    - standard w administracji Linuxa
    */
}
