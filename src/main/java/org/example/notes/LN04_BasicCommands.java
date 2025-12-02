package org.example.notes;

/**
 * LN04 – PODSTAWOWE POLECENIA LINUX + DOKŁADNE WYJAŚNIENIA
 *
 * Ściąga z poleceń: ls, cd, pwd, touch, cat, pipe "|", mv, mkdir, cp, rm,
 * + kilka dodatkowych: grep, sort, wc, head, tail – wszystkie z opisami.
 */
public class LN04_BasicCommands {

    /*
    ======================================================================================
    1. ls – LISTOWANIE PLIKÓW I KATALOGÓW
    ======================================================================================

    Ogólna idea:
    - ls pokazuje, co jest w katalogu: pliki, katalogi, uprawnienia, rozmiary itd.

    Przykłady:

    ls
    -> wypisuje nazwy plików i katalogów w bieżącym katalogu, bez szczegółów.

    ls /etc
    -> wypisuje zawartość katalogu /etc (nie tego, w którym stoisz).

    ls -l
    -> szczegółowa lista:
       - typ pliku (d, -, l),
       - uprawnienia,
       - liczba linków,
       - właściciel,
       - grupa,
       - rozmiar,
       - data ostatniej modyfikacji,
       - nazwa pliku.

    ls -a
    -> pokazuje też pliki ukryte (zaczynające się od kropki, np. .bashrc).

    ls -la
    -> połączenie -l i -a: wszystkie pliki + szczegóły.

    ls -lh
    -> jak -l, ale rozmiary w formacie czytelnym: 10K, 2M, 3G.

    ls plik.txt
    -> pokazuje informacje o konkretnym pliku; jeśli plik nie istnieje, dostaniesz błąd.

    ls *.txt
    -> pokazuje wszystkie pliki, których nazwa kończy się na ".txt".

    ls a*
    -> pokazuje pliki i katalogi zaczynające się na literę "a".

    ls -R
    -> listuje bieżący katalog i wszystkie podkatalogi (rekurencyjnie).
    */

    /*
    ======================================================================================
    2. cd – ZMIANA KATALOGU
    ======================================================================================

    Ogólna idea:
    - cd zmienia katalog roboczy procesu powłoki (shella).

    cd /etc
    -> przechodzisz do katalogu /etc (bez względu na to, gdzie byłeś wcześniej).

    cd ~
    -> przechodzisz do katalogu domowego aktualnego użytkownika (np. /home/przemek).

    cd
    -> to samo co cd ~, czyli katalog domowy.

    cd ..
    -> przejście o jeden poziom wyżej (do katalogu nadrzędnego).

    cd ../..
    -> przejście o dwa poziomy wyżej.

    cd /                       -> przejście do katalogu głównego systemu.

    cd Documents
    -> wejście do podkatalogu "Documents" w bieżącym katalogu (jeśli istnieje).

    cd -
    -> przełączenie na poprzedni katalog (taki toggle między dwoma ostatnimi).

    cd "Moje dokumenty"
    -> wejście do katalogu ze spacjami w nazwie (muszą być cudzysłowy).
    */

    /*
    ======================================================================================
    3. pwd – WYŚWIETLENIE BIEŻĄCEGO KATALOGU
    ======================================================================================

    pwd
    -> wypisuje pełną ścieżkę katalogu, w którym aktualnie jesteś.
       Przykład: /home/przemek/projekt/java

    To pomaga ogarnąć, gdzie stoisz w drzewie katalogów.
    */

    /*
    ======================================================================================
    4. touch – TWORZENIE I MODYFIKACJA PLIKÓW
    ======================================================================================

    Ogólna idea:
    - Jeśli plik nie istnieje → touch go tworzy jako pusty.
    - Jeśli plik istnieje → touch aktualizuje czas ostatniej modyfikacji.

    touch plik.txt
    -> jeśli plik.txt nie istnieje, tworzy pusty plik.
       jeśli istnieje, zmienia mu timestamp (czas modyfikacji).

    touch a b c
    -> tworzy trzy pliki: a, b i c (lub aktualizuje im timestamp).

    touch "notatki dzien 1.txt"
    -> tworzy plik z nazwą zawierającą spacje.

    touch log{1..3}.txt
    -> rozwinięcie przez bash:
       tworzy pliki: log1.txt, log2.txt, log3.txt.

    touch raport_$(date +%F).txt
    -> tworzy plik z datą w nazwie, np. raport_2025-12-02.txt.
    */

    /*
    ======================================================================================
    5. cat – WYŚWIETLANIE I ŁĄCZENIE PLIKÓW
    ======================================================================================

    Ogólna idea:
    - cat wypisuje zawartość plików na standardowe wyjście (stdout).
    - nazwa pochodzi od "concatenate" (łączenie).

    cat plik.txt
    -> wypisuje zawartość pliku linia po linii na ekran.

    cat plik1.txt plik2.txt
    -> wypisuje zawartość plik1.txt, a zaraz potem plik2.txt.

    cat -n plik.txt
    -> wyświetla plik z numerami linii.

    cat > nowy_plik.txt
    -> przekierowanie: wszystko, co wpiszesz z klawiatury,
       trafia do pliku nowy_plik.txt; zakończ wpisywanie CTRL + D.
       Uwaga: nadpisuje plik.

    cat >> istniejący_plik.txt
    -> to samo, ale dopisuje na końcu istniejącego pliku zamiast nadpisywać.

    cat duzy_plik.txt | more
    -> cat wypisuje plik, a more pokazuje go stronami:
       - spacja -> następna strona,
       - q      -> wyjście.
    */

    /*
    ======================================================================================
    6. PIPE "|" – POTOKI MIĘDZY PROCESAMI
    ======================================================================================

    Ogólna idea:
    - "|" łączy dwa programy: wyjście pierwszego (stdout) staje się wejściem drugiego (stdin).

    program1 | program2
    -> dane wychodzące z program1 trafiają bezpośrednio do program2, bez pliku pośredniego.

    Przykłady:

    cat plik.txt | more
    -> cat wyświetla plik,
       more bierze ten strumień i pokazuje go po kawałku stronami.

    ls -l | grep ".txt"
    -> ls -l wypisuje szczegółową listę,
       grep filtruje i pokazuje tylko linie zawierające ".txt".

    dmesg | grep -i error
    -> dmesg wypisuje logi kernela,
       grep wyszukuje linie zawierające "error" (bez patrzenia na wielkość liter).

    who | wc -l
    -> who wypisuje listę zalogowanych użytkowników,
       wc -l liczy, ile jest linii, czyli ilu użytkowników jest zalogowanych.

    du -sh * | sort -h
    -> du -sh * pokazuje rozmiary wszystkich elementów w katalogu,
       sort -h sortuje po rozmiarze (czyta K, M, G jako liczby).
    */

    /*
    ======================================================================================
    7. mv – ZMIANA NAZWY I PRZENOSZENIE
    ======================================================================================

    Ogólna idea:
    - mv może zmienić nazwę pliku lub go przenieść do innego katalogu.

    mv stary.txt nowy.txt
    -> zmienia nazwę pliku z stary.txt na nowy.txt w tym samym katalogu.

    mv plik.txt /home/przemek/
    -> przenosi plik.txt do katalogu /home/przemek/.

    mv plik.txt /home/przemek/nowa_nazwa.txt
    -> przenosi plik i jednocześnie zmienia mu nazwę.

    mv katalog1 katalog2
    -> jeśli katalog2 nie istnieje:
         zmienia nazwę katalog1 na katalog2;
       jeśli katalog2 istnieje:
         przenosi katalog1 do katalog2 (jako podkatalog).

    mv *.log logs/
    -> przenosi wszystkie pliki z rozszerzeniem .log do katalogu logs/.

    Uwaga:
    - jeśli docelowy plik istnieje, mv go nadpisze bez pytania (chyba że użyjesz opcji -i).
    */

    /*
    ======================================================================================
    8. mkdir – TWORZENIE KATALOGÓW
    ======================================================================================

    mkdir katalog
    -> tworzy katalog o nazwie "katalog" w bieżącym miejscu.

    mkdir katalog1 katalog2 katalog3
    -> tworzy trzy katalogi na raz.

    mkdir -p a/b/c
    -> tworzy katalog a, w nim b, w nim c.
       -p oznacza: twórz brakujące katalogi po drodze, nie zgłaszaj błędu jeśli istnieją.

    mkdir "Nowy folder"
    -> tworzy katalog ze spacją w nazwie (konieczne cudzysłowy).

    Przykład scenariusza:
        mkdir projekty
        cd projekty
        mkdir java python bash
    */

    /*
    ======================================================================================
    9. cp – KOPIOWANIE PLIKÓW I KATALOGÓW
    ======================================================================================

    cp plik1.txt plik2.txt
    -> robi kopię plik1.txt pod nazwą plik2.txt.

    cp plik.txt /home/przemek/
    -> kopiuje plik.txt do katalogu /home/przemek/.

    cp *.txt /tmp/
    -> kopiuje wszystkie pliki .txt do /tmp/.

    cp -r katalog1 katalog2
    -> kopiuje katalog1 rekurencyjnie (z zawartością) do katalog2.
       jeśli katalog2 nie istnieje, powstaje nowy katalog2 jako kopia katalog1.

    cp -p oryginal kopia
    -> kopiuje plik ORAZ zachowuje uprawnienia, właściciela i timestampy.

    Uwaga:
    - cp nadpisze plik docelowy bez pytania (chyba że użyjesz -i).
    */

    /*
    ======================================================================================
    10. rm – USUWANIE PLIKÓW I KATALOGÓW
    ======================================================================================

    Ogólna idea:
    - rm usuwa pliki/katalogi. Nie ma kosza, więc jak usuniesz → znika.

    rm plik.txt
    -> usuwa pojedynczy plik.

    rm plik1 plik2 plik3
    -> usuwa kilka plików.

    rm -f plik.txt
    -> usuwa bez pytania, nawet jeśli nie masz normalnych uprawnień do zapytania.

    rm -r katalog
    -> usuwa katalog i całą jego zawartość (rekurencyjnie),
       ale może pytać o potwierdzenia.

    rm -rf katalog
    -> usuwa katalog i wszystko w środku:
       -r  rekurencyjnie,
       -f  bez pytań.
       Bardzo niebezpieczne, trzeba uważać na ścieżkę.

    Przykład:
        rm *.log           -> usuwa wszystkie pliki .log w katalogu.
        rm -rf /tmp/test   -> usuwa cały katalog /tmp/test.
    */

    /*
    ======================================================================================
    11. grep – WYSZUKIWANIE TEKSTU W LINACH
    ======================================================================================

    Ogólna idea:
    - grep przegląda linia po linii i wybiera tylko te, które pasują do wzorca.

    grep "tekst" plik.txt
    -> szuka lin zawierających dokładny ciąg znaków "tekst" w pliku.txt
       i wypisuje tylko te linie.

    grep -i "text" plik.txt
    -> to samo, ale ignoruje wielkość liter (Text, TEXT, teXT – wszystko pasuje).

    grep -r "szukaj" /etc
    -> rekurencyjnie przeszukuje wszystkie pliki w /etc,
       pokazuje linie zawierające "szukaj" oraz ścieżkę do pliku.

    grep -v "error" plik.txt
    -> odwrotność:
       -v: pokaż linie, które NIE zawierają słowa "error".
       Użyteczne do filtrowania śmieci.

    Przykład z pipe:
        dmesg | grep -i usb
        -> dmesg wypisuje log kernela,
           grep wybiera linie związane z USB.

    */

    /*
    ======================================================================================
    12. sort – SORTOWANIE LINII
    ======================================================================================

    sort plik.txt
    -> sortuje linie w plik.txt alfabetycznie i wypisuje wynik na stdout.

    sort -n plik.txt
    -> sortuje numerycznie:
       zakłada, że linie zaczynają się od liczb, porównuje je jako liczby.

    sort -h plik.txt
    -> sortuje traktując sufiksy K, M, G jako liczby (np. 10K, 2M).
       Używane często wraz z du -h.

    du -sh * | sort -h
    -> du -sh *:
           pokazuje rozmiary wszystkich plików/katalogów w formie np. 4.0K, 2.1M
       sort -h:
           sortuje po rozmiarze, od najmniejszego do największego.

    sort -r plik.txt
    -> sortuje odwrotnie (od końca).

    sort -u plik.txt
    -> sortuje i usuwa duplikaty (unique).
    */

    /*
    ======================================================================================
    13. wc, head, tail – LICZENIE I PODGLĄD POCZĄTKU/KOŃCA
    ======================================================================================

    wc plik.txt
    -> pokazuje:
        liczba_linii liczba_słów liczba_znaków nazwa_pliku

    wc -l plik.txt
    -> pokazuje tylko liczbę linii w pliku.

    wc -w plik.txt
    -> liczba słów.

    head plik.txt
    -> pokazuje pierwsze 10 linii pliku.

    head -20 plik.txt
    -> pierwsze 20 linii.

    tail plik.txt
    -> ostatnie 10 linii.

    tail -50 plik.txt
    -> ostatnie 50 linii.

    tail -f plik.log
    -> pokazuje ostatnie linie loga na żywo,
       nowe wpisy dopisywane do pliku będą od razu wyświetlane.
       (przydatne do obserwowania logów działających usług).
    */

    /*
    ======================================================================================
    14. MINI PODSUMOWANIE
    ======================================================================================

    ls      -> pokazuje, co jest w katalogu.
    cd      -> zmienia katalog roboczy.
    pwd     -> pokazuje, gdzie aktualnie jesteś.
    touch   -> tworzy lub aktualizuje plik.
    cat     -> pokazuje zawartość plików.
    "|"     -> łączy programy (wyjście jednego, wejście drugiego).
    mv      -> przenosi lub zmienia nazwę pliku/katalogu.
    mkdir   -> tworzy katalog.
    cp      -> kopiuje pliki i katalogi.
    rm      -> usuwa pliki i katalogi.
    grep    -> filtruje linie pasujące do wzorca.
    sort    -> sortuje linie.
    wc      -> liczy linie, słowa, znaki.
    head    -> początek pliku.
    tail    -> koniec pliku.

    To jest fundament, na którym budujesz całą resztę nauki Linuksa.
    */
}
