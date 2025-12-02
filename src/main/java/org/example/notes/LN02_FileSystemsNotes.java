package org.example.notes;

/**
 * NOTATKI: SYSTEMY PLIKÓW (NTFS, FAT, EXT4) + SZYFROWANIE PARTYCJI
 *
 * Ten plik jest notatką, nie zawiera logiki wykonywalnej.
 */
public class LN02_FileSystemsNotes {

    /*
    ============================================
    1. Co to jest system plików?
    ============================================
    System plików = sposób organizacji danych na dysku.
    To on określa:
        - jak zapisywane są pliki,
        - jak organizowane są foldery,
        - jak działa wyszukiwanie,
        - jakie są maksymalne rozmiary plików/dysków,
        - jak wyglądają uprawnienia i metadane.

    Każdy dysk/pendrive/partycja MUSI mieć system plików.
    */

    /*
    =================================================
    2. Popularne systemy plików – Windows
    =================================================
    --- NTFS ---
    - Domyślny system plików Windows od XP do dziś.
    - Cechy:
        * obsługa dużych plików i dysków,
        * uprawnienia (ACL),
        * szyfrowanie plików EFS,
        * kompresja plików,
        * dziennik zdarzeń (journaling) — zmniejsza ryzyko uszkodzeń.

    - Wady:
        * Linux potrafi używać NTFS, ale wolniej.
        * Nie dla pendrive (za ciężki).

    --- FAT32 ---
    - Bardzo stary, działa wszędzie.
    - Cechy:
        * kompatybilność 100% (Windows, Linux, macOS, konsole, TV),
        * idealny dla pendrive.

    - Wady:
        * maksymalny rozmiar pliku: 4 GB (!)
        * maksymalna partycja: 2 TB
        * brak uprawnień i zabezpieczeń

    --- exFAT ---
    - Ulepszona wersja FAT bez limitu 4GB.
    - Najlepszy dla pendrive/kart SD > 4GB.
    - Działa na Windows, macOS, Linux (po instalacji sterowników).
    */

    /*
    =================================================
    3. Popularne systemy plików – Linux
    =================================================

    --- ext4 ---
    - Najpopularniejszy system plików w Linuxie.
    - Cechy:
        * journaling (zabezpiecza dane),
        * stabilny i szybki,
        * brak limitu 4 GB (pliki do 16 TB),
        * uprawnienia użytkowników/grup,
        * obsługa linków symbolicznych.

    - Idealny jako system dla Linuxa.

    --- ext3 ---
    - Starsza wersja ext4.
    - Wolniejsza, przestarzała.

    --- ext2 ---
    - Bardzo stara — brak journalingu.
    - Nie używana jako główny system, ale dobra na pendrive do Linuxa
      (bo nie męczy pamięci flash).

    --- Btrfs ---
    - Nowoczesny system plików z:
        * snapshotami,
        * subwoluminami,
        * wbudowaną kompresją.
    - Używany np. w openSUSE i Fedory Silverblue.

    --- XFS ---
    - System plików używany w serwerach (bardzo szybki dla dużych danych).
    */

    /*
    =================================================
    4. Różnice: NTFS vs ext4
    =================================================

    - NTFS:
        * Windows default,
        * ACL, kompresja, EFS,
        * duży narzut na metadane.

    - ext4:
        * Linux default,
        * prostszy, szybszy,
        * lepsza obsługa przez jądro Linuxa.

    ZASADA:
    → Windows na NTFS
    → Linux na ext4
    → Pendrive na exFAT
    */

    /*
    =================================================
    5. Szyfrowanie partycji — co to jest?
    =================================================
    Szyfrowanie = dane na dysku są zakodowane i bez hasła nie da się ich odczytać.

    Typy szyfrowania:

    1) Szyfrowanie całej partycji / dysku
    2) Szyfrowanie pojedynczych plików

    W praktyce używa się narzędzi sprzętowych lub systemowych.
    */

    /*
    =================================================
    6. Szyfrowanie Windows — BitLocker
    =================================================
    - Narzędzie systemowe Windows (Pro/Enterprise).
    - Szyfruje cały dysk:
        * systemowy,
        * dodatkowe partycje,
        * dyski USB.
    - Możliwe odblokowanie:
        * hasłem,
        * TPM (chip bezpieczeństwa),
        * pendrive z kluczem odzyskiwania.
    - Zabezpiecza przed:
        * kradzieżą danych po zgubieniu laptopa,
        * atakiem offline.

    */

    /*
    =================================================
    7. Szyfrowanie Linux — LUKS
    =================================================
    - Najpopularniejszy sposób szyfrowania w Linuxie.
    - LUKS = Linux Unified Key Setup.
    - Stosowany w:
        * Debian,
        * Ubuntu,
        * Mint,
        * Arch,
        * Kali,
        * większości dystrybucji.

    DZIAŁA TAK:
      - Podczas instalacji wybierasz: „Szyfruj dysk”.
      - Tworzony jest kontener LUKS.
      - System montuje go po podaniu hasła.
      - Bez hasła dane są nie do odczytania.

    Cechy:
      - Bardzo bezpieczny.
      - Obsługuje wiele kluczy (kilka haseł).
      - Można szyfrować pojedyncze partycje lub cały dysk.

    */

    /*
    =================================================
    8. MASTER PODSUMOWANIE
    =================================================
    - NTFS → Windows
    - FAT32 → pendrive (ale ma limit 4 GB)
    - exFAT → nowoczesne pendrive i karty SD
    - ext4 → Linux (system plików)
    - XFS/Btrfs → serwery, snapshoty, nowoczesne systemy

    - BitLocker → szyfrowanie Windows
    - LUKS → szyfrowanie Linux

    Co to daje?
    - ochrona danych,
    - odporność na kradzież laptopa,
    - uniemożliwia odczyt danych bez klucza.
    */

}
