package org.example.notes;

/**
 * LN14 – UŻYTKOWNICY W LINUXIE: /etc/passwd, /etc/shadow, SHELL, PIPE, POWŁOKI
 *
 * - struktura pliku passwd
 * - czym jest użytkownik systemowy
 * - rola powłoki (bash, sh, nologin)
 * - plik shadow – przechowywanie haseł
 * - pipe | – przekierowanie wejścia/wyjścia
 * - jak działa logowanie i zmiana powłoki
 * - gdzie trafiają nowi użytkownicy
 */
public class LN14_PasswdAndShadow {

    /*
    ======================================================================================
    1. PIPE – PRZYPOMNIENIE JAK DZIAŁA "|"
    ======================================================================================

    Pipe "|" przekierowuje STANDARDOWE WYJŚCIE jednego programu → na STANDARDOWE WEJŚCIE drugiego programu.

        komenda1 | komenda2

    Przykład klasyczny:
        cat /etc/passwd | more

    cat -> wyświetla cały plik naraz
    more -> stronicuje wyświetlanie (pokazuje po kawałku)

    Idea:
        - cat generuje dane (stdout)
        - pipe przechwytuje je
        - more odbiera je jako stdin

    To jest „rurka”, która łączy procesy.
    */

    /*
    ======================================================================================
    2. PLIK /etc/passwd – KIM SĄ UŻYTKOWNICY SYSTEMU?
    ======================================================================================

    W Linuxie każdy użytkownik (normalny lub systemowy) ma swój wpis w pliku:

        /etc/passwd

    Mimo nazwy – tutaj **nie ma haseł!**
    Dawniej były tutaj, teraz są w /etc/shadow (z powodów bezpieczeństwa).

    Każda linia opisuje jednego użytkownika.
    Format (7 pól):
        NAZWA_UŻYTKOWNIKA : X : UID : GID : KOMENTARZ : KATALOG_DOMOWY : POWŁOKA

    Przykład linii:
        przemek:x:1000:1000:Przemek,,,:/home/przemek:/bin/bash

    Rozbicie:

    1) NAZWA_UŻYTKOWNIKA
        przemek
        Logujesz się tą nazwą.

    2) X
        kiedyś tu było hasło
        teraz "x" oznacza: hasło siedzi w /etc/shadow

    3) UID – User ID
        root → UID=0
        użytkownicy systemowi → 1–999
        normalni użytkownicy → 1000+

        UID=1000 to zwykle pierwszy stworzone konto.

    4) GID – Group ID
        ID głównej grupy użytkownika
        np. przemek: GID=1000 → grupa o ID 1000

    5) KOMENTARZ (GECOS)
        Informacje opisowe:
            imię, telefony, dział
        Najczęściej puste lub nazwa osoby.

    6) KATALOG DOMOWY
        /home/przemek

    7) POWŁOKA (shell)
        np. /bin/bash
             /bin/sh
             /usr/sbin/nologin
             /bin/false

        → To określa **co użytkownik uruchamia po zalogowaniu**.

    */

    /*
    ======================================================================================
    3. POWŁOKA (SHELL) – DLACZEGO JEST TAK WAŻNA?
    ======================================================================================

    Shell to interpreter poleceń.
    To, co widzisz po zalogowaniu – to właśnie powłoka.

    Popularne:
        /bin/bash   → pełna powłoka
        /bin/sh     → prostsza powłoka Bourne
        /bin/zsh    → bardziej zaawansowana z oh-my-zsh
        /bin/fish   → nowoczesna powłoka
        /usr/sbin/nologin → użytkownik NIE MOŻE się zalogować
        /bin/false → blokuje logowanie

    Jeśli użytkownik ma:
        /bin/false
        /usr/sbin/nologin
    → NIE wejdzie na terminal ani SSH.

    To używa się dla:
        - kont systemowych,
        - użytkowników serwisowych (np. nginx, mysql),
        - programów, którym nie wolno się logować.
    */

    /*
    ======================================================================================
    4. PRZELOGOWANIE POMIĘDZY POWŁOKAMI
    ======================================================================================

    Możesz zmienić powłokę użytkownika poleceniem:

        chsh -s /bin/bash przemek
        chsh -s /bin/sh przemek

    Lub edytując bezpośrednio:
        sudo vipw
        (bezpieczna edycja /etc/passwd)

    Zmiana działa przy kolejnym logowaniu.

    Jak sprawdzić, jakiej powłoki używasz?
        echo $SHELL
    */

    /*
    ======================================================================================
    5. TWORZENIE UŻYTKOWNIKA → GDZIE TRAFIA WPIS?
    ======================================================================================

        sudo adduser kuba

    Dzieje się:

    1) dopisanie linii do /etc/passwd
    2) dopisanie zaszyfrowanego hasła do /etc/shadow
    3) utworzenie katalogu domowego /home/kuba
    4) utworzenie grupy o tej samej nazwie (kuba)
    5) ustawienie powłoki domyślnej (często /bin/bash)

    Każdy nowy użytkownik pojawia się jako nowa linia w:

        /etc/passwd

    oraz odpowiedni wpis w:

        /etc/shadow
    */

    /*
    ======================================================================================
    6. PLIK /etc/shadow – GDZIE SĄ HASŁA?
    ======================================================================================

    /etc/shadow zawiera ściśle chronione dane:
        - zaszyfrowane hasła użytkowników
        - daty ważności haseł
        - zasady blokowania kont

    TYLKO ROOT ma do niego dostęp.

    Format (skrótowo):
        NAZWA:HASH:OSTATNIA_ZMIANA:MIN:MAX:OSTRZEŻENIE:WYGAŚNIĘCIE

    Przykład:
        przemek:$6$JFl.../$tqG...:20000:0:99999:7:::

    Wyjaśnienie:
        $6$ → SHA-512
        ciąg po $6$ → sól
        ostatni człon → hash hasła

    Jeśli hasło jest zablokowane:
        !   przed hashem
        !!  brak hasła ustawionego

    */

    /*
    ======================================================================================
    7. JAK DZIAŁA LOGOWANIE W LINUXIE?
    ======================================================================================

    1) System sprawdza w /etc/passwd, czy użytkownik istnieje.
    2) Pobiera jego powłokę.
    3) Pobiera hash hasła z /etc/shadow.
    4) Porównuje hash z tym, co wpiszesz.
    5) Po poprawnym logowaniu:
        → startuje powłoka użytkownika (bash/sh/zsh/etc.)
        → ładuje pliki konfiguracyjne (.bashrc, .profile itd.)
    */

    /*
    ======================================================================================
    8. DLACZEGO PROGRAMOWE KONT SYSTEMOWE MAJĄ /usr/sbin/nologin LUB /bin/false?
    ======================================================================================

    Przykład:
        mysql:x:112:117:MySQL Server,,,:/nonexistent:/usr/sbin/nologin

    Dlaczego?
        - te konta istnieją tylko po to, aby proces działał z ograniczonymi uprawnieniami
        - nie powinny być używane do logowania
        - system minimalizuje ryzyko ataków

    Jeśli program tworzy użytkownika:
        powłoka = /usr/sbin/nologin lub /bin/false

    Wtedy:
        - nie można zalogować się przez SSH
        - nie można otworzyć terminala jako ten użytkownik
    */

    /*
    ======================================================================================
    9. ODCZYT STRUKTURY /etc/passwd Z PIPE
    ======================================================================================

    Praktyczne przykłady:

        cat /etc/passwd | more
        cut -d: -f1 /etc/passwd | sort
        awk -F: '{ print $1, $3 }' /etc/passwd | more
        grep "/bin/bash" /etc/passwd
        grep -v "/bin/false" /etc/passwd

    To pokazuje:
        - nazwę użytkownika
        - UID
        - powłokę
        - kto ma prawdziwy shell
    */

    /*
    ======================================================================================
    10. PODSUMOWANIE
    ======================================================================================

    /etc/passwd:
        - lista użytkowników
        - UID, GID, katalog domowy, powłoka
        - brak haseł (jest tylko "x")

    /etc/shadow:
        - zaszyfrowane hasła
        - tylko root może czytać

    Shell:
        - określa, co użytkownik może uruchomić po zalogowaniu
        - nologin/false blokują logowanie

    Pipe:
        - łączy stdout jednego procesu ze stdin drugiego
        - cat /etc/passwd | more

    Nowy użytkownik:
        - dopisywany do passwd + shadow

    Zmiana powłoki:
        chsh -s /bin/bash nazwa
        lub edycja passwd przez vipw

    */
}
