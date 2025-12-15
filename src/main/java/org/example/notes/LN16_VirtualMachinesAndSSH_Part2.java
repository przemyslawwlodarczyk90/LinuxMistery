package org.example.notes;

/**
 * LN16 (CZĘŚĆ 2) – TRANSFER PLIKÓW, SSH BEZ HASŁA, KLUCZE KRYPTOGRAFICZNE
 *
 * - jak działa transfer plików między maszynami
 * - SSH vs SCP – logowanie i kopiowanie
 * - tworzenie identycznych użytkowników na dwóch maszynach
 * - dodawanie użytkownika do grupy sudo/root
 * - uwierzytelnianie kluczem publicznym
 * - klucz prywatny vs publiczny
 * - ssh-keygen i algorytmy RSA / DSA
 */
public class LN16_VirtualMachinesAndSSH_Part2 {

    /*
    ======================================================================================
    1. TRANSFER PLIKÓW MIĘDZY MASZYNAMI – JAK TO DZIAŁA?
    ======================================================================================

    Transfer plików między maszynami w Linuxie najczęściej odbywa się:
        - przez SSH (szyfrowane połączenie)
        - przy użyciu protokołów opartych o SSH

    Najważniejsze narzędzia:
        - ssh  → zdalne logowanie
        - scp  → kopiowanie plików
        - sftp → interaktywny transfer plików

    Wspólna cecha:
        → wszystko idzie przez TEN SAM KANAŁ SZYFROWANY SSH
        → nie ma przesyłania haseł w jawnej postaci
    */

    /*
    ======================================================================================
    2. LOGOWANIE PRZEZ SSH
    ======================================================================================

    Składnia:
        ssh użytkownik@IP

    Przykład:
        ssh przemek@192.168.1.50

    Co się dzieje po kolei:
        1) klient ssh łączy się z serwerem sshd na porcie 22
        2) serwer wysyła swój klucz hosta
        3) klient weryfikuje, czy ufa temu serwerowi
        4) następuje uwierzytelnienie użytkownika (hasło lub klucz)
        5) po sukcesie uruchamiana jest powłoka użytkownika

    */

    /*
    ======================================================================================
    3. SCP – KOPIOWANIE PLIKÓW PRZEZ SSH
    ======================================================================================

    SCP = Secure Copy

    Przykłady:

        scp plik.txt user@192.168.1.50:/home/user/

    Kopiowanie katalogu:
        scp -r katalog user@192.168.1.50:/home/user/

    Kopiowanie Z maszyny zdalnej:
        scp user@192.168.1.50:/home/user/plik.txt .

    SCP:
        - używa SSH
        - jest szyfrowane
        - nie wymaga dodatkowych serwerów
    */

    /*
    ======================================================================================
    4. SSH BEZ HASŁA – DLACZEGO I PO CO?
    ======================================================================================

    Logowanie bez hasła:
        - szybsze
        - bezpieczniejsze
        - standard w administracji systemami i DevOps

    Zamiast hasła:
        → uwierzytelnianie kluczem kryptograficznym

    Warunek:
        - klucz prywatny zostaje TYLKO na kliencie
        - klucz publiczny trafia na serwer
    */

    /*
    ======================================================================================
    5. UŻYTKOWNICY MUSZĄ ISTNIEĆ NA OBU MASZYNAMI
    ======================================================================================

    Dobra praktyka:
        - ten sam login na obu maszynach
        - ten sam katalog domowy (/home/nazwa)

    Tworzenie użytkownika:
        sudo adduser przemek

    Dodanie do grupy sudo (root):
        sudo usermod -aG sudo przemek

    Sprawdzenie:
        groups przemek

    Dzięki temu:
        - użytkownik może używać sudo
        - ma pełne prawa administracyjne
    */

    /*
    ======================================================================================
    6. KRYPTOGRAFIA KLUCZA PUBLICZNEGO – PODSTAWY
    ======================================================================================

    W SSH używana jest kryptografia asymetryczna (klucz podwójny).

    Składa się z:
        - klucza prywatnego (SECRET)
        - klucza publicznego (PUBLIC)

    Klucz prywatny:
        - zostaje NA TWOJEJ MASZYNIE
        - NIGDY nie jest wysyłany
        - służy do podpisywania danych

    Klucz publiczny:
        - możesz go rozdawać
        - trafia na serwer
        - serwer używa go do weryfikacji klienta

    Zasada:
        co zaszyfrowano kluczem publicznym
        → można odszyfrować tylko kluczem prywatnym
    */

    /*
    ======================================================================================
    7. GENEROWANIE KLUCZY – ssh-keygen
    ======================================================================================

    Polecenie:
        ssh-keygen

    Domyślnie:
        - algorytm RSA
        - długość klucza 3072 lub 4096 bitów
        - pliki:
            ~/.ssh/id_rsa        (klucz prywatny)
            ~/.ssh/id_rsa.pub    (klucz publiczny)

    Z parametrami:
        ssh-keygen -t rsa -b 4096

    Opcje:
        -t → typ algorytmu
        -b → długość klucza w bitach

    */

    /*
    ======================================================================================
    8. ALGORYTMY: RSA I DSA – NA CZYM TO BAZUJE?
    ======================================================================================

    RSA:
        - oparty o faktoryzację dużych liczb pierwszych
        - bardzo bezpieczny
        - powszechnie stosowany
        - wolniejszy niż nowoczesne algorytmy
        - REKOMENDOWANY

    DSA:
        - oparty o logarytmy dyskretne
        - starszy
        - ograniczona długość klucza
        - obecnie NIEZALECANY

    Dlatego:
        używaj RSA (lub ed25519 – nowoczesny, ale to później)

    */

    /*
    ======================================================================================
    9. KOPIOWANIE KLUCZA PUBLICZNEGO NA SERWER
    ======================================================================================

    Najprościej:
        ssh-copy-id user@IP

    Przykład:
        ssh-copy-id przemek@192.168.1.50

    Co to robi:
        - loguje się ostatni raz hasłem
        - dopisuje klucz publiczny do:
            ~/.ssh/authorized_keys
        - od teraz logowanie bez hasła

    Ręcznie:
        cat ~/.ssh/id_rsa.pub | ssh user@IP "mkdir -p ~/.ssh && cat >> ~/.ssh/authorized_keys"
    */

    /*
    ======================================================================================
    10. JAK DZIAŁA LOGOWANIE KLUCZEM KROK PO KROKU
    ======================================================================================

    1) klient łączy się z serwerem
    2) serwer wysyła losowe wyzwanie
    3) klient podpisuje je kluczem prywatnym
    4) serwer sprawdza podpis kluczem publicznym
    5) jeśli pasuje → logowanie OK
    6) hasło NIE JEST używane

    Klucz prywatny nigdy nie opuszcza klienta.
    */

    /*
    ======================================================================================
    11. LOGOWANIE I SCP BEZ HASŁA
    ======================================================================================

        ssh przemek@192.168.1.50
        scp plik.txt przemek@192.168.1.50:/home/przemek/

    Działa automatycznie, bez pytania o hasło.
    */

    /*
    ======================================================================================
    12. BEZPIECZEŃSTWO – O CZYM PAMIĘTAĆ
    ======================================================================================

    - chroń klucz prywatny (chmod 600 ~/.ssh/id_rsa)
    - nie wysyłaj go nikomu
    - możesz ustawić hasło na klucz prywatny
    - authorized_keys decyduje kto ma dostęp

    Jeśli ktoś ma klucz prywatny → ma dostęp do serwera.
    */

    /*
    ======================================================================================
    13. PODSUMOWANIE
    ======================================================================================

    - SSH = bezpieczne logowanie klient–serwer
    - SCP = bezpieczny transfer plików
    - klucz prywatny → tylko u Ciebie
    - klucz publiczny → na serwerze
    - RSA → bezpieczny i standardowy
    - użytkownik musi istnieć na obu maszynach
    - dodanie do sudo → prawa administratora
    - logowanie bez hasła = standard adminsko-devopsowy
    */
}
