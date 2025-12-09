package org.example.notes;

/**
 * LN13 – SERWOWANIE PLIKÓW PO HTTP W LINUXIE + KOMUNIKACJA HOST–GOŚĆ
 *
 * - Kiedy serwowanie plików ma sens
 * - Warunek: urządzenia muszą się widzieć w sieci (bridge / NAT z port forwarding)
 * - Sprawdzanie adresów IP (Linux + Windows)
 * - Instalacja net-tools (ifconfig)
 * - Jak uruchomić prosty serwer HTTP w Pythonie
 * - Jak wejść do serwowanych plików z przeglądarki
 * - Testowanie połączenia (ping, curl)
 */
public class LN13_HTTPFileServing {

    /*
    ======================================================================================
    1. PO CO TO ROBIĆ?
    ======================================================================================

    Możesz szybko udostępnić katalog przez HTTP bez:
        - wysyłania plików przez komunikatory,
        - konfiguracji Samby czy FTP,
        - kombinujęnia z dyskami współdzielonymi.

    W szczególności przydatne gdy:
        - chcesz przerzucić pliki między maszyną hosta i maszyną wirtualną,
        - udostępniasz pliki w swojej sieci lokalnej,
        - budujesz środowisko do nauki.

    Wymóg:
        → Maszyny MUSZĄ się widzieć w sieci (mieć routowalne adresy).
    */

    /*
    ======================================================================================
    2. JAK MUSI BYĆ SKONFIGUROWANA KARTA SIECIOWA WIRTUALKI?
    ======================================================================================

    Opcje:

    1) **Bridged adapter (mostkowana karta sieciowa)**
        - VirtualBox/VMware daje wirtualce dostęp do sieci fizycznej jak normalne urządzenie
        - Host i gość dostają **adresy IP z tej samej sieci lokalnej LAN**
        - Maszyna Windows i Ubuntu widzą się jak dwa komputery w domu

    2) **NAT + port forwarding**
        - działa, ale mniej wygodne (trzeba ręcznie przekazać porty)
        - bridged jest lepszy do nauki HTTP

    Do serwowania plików polecam:
        → Bridged Network

    */

    /*
    ======================================================================================
    3. SPRAWDZANIE, CZY HOST I GOŚĆ SIĘ WIDZĄ
    ======================================================================================

    LINUX (gość):
        sudo apt install net-tools
        ifconfig

    Widzisz:
        - lo       -> loopback (127.0.0.1)
        - eth0 / ens33 / wlo1 -> karta sieciowa (ma mieć adres IP np. 192.168.1.50)

    WINDOWS (host):
        ipconfig

    Widzisz:
        - Wireless LAN/Wi-Fi: 192.168.1.xxx
        - Ethernet:           192.168.1.xxx

    Obie maszyny muszą być w tej samej podsieci:
        przykład:
            host: 192.168.1.20
            VM:   192.168.1.55

    Test połączenia:
        ping 192.168.1.20   (z Ubuntu do Windows)
        ping 192.168.1.55   (z Windows do Ubuntu)

    Jeśli ping działa → można serwować pliki.
    Jeśli nie:
        - firewall blokuje ICMP lub porty
        - zła konfiguracja kart wirtualnych
    */

    /*
    ======================================================================================
    4. PRZYGOTOWANIE KATALOGU DO SERWOWANIA
    ======================================================================================

    Wybierz katalog, który chcesz udostępniać:
        cd ~/Pulpit
        mkdir share
        cd share
        echo "TEST PLIKU" > test.txt

    zawartość katalogu będzie dostępna w przeglądarce przez HTTP.
    */

    /*
    ======================================================================================
    5. URUCHAMIANIE SERWERA HTTP W PYTHONIE
    ======================================================================================

    PYTHON 3 (standard w Ubuntu):

        python3 -m http.server 8000

    Co się dzieje?
        - Python odpala mini-serwer HTTP
        - katalog, z którego uruchomiłeś komendę → staje się rootem HTTP
        - serwer działa na porcie 8000
        - nasłuchuje na adresie:
            http://<IP_maszyny>:8000/

    Przykład:
        jeśli Ubuntu ma IP 192.168.1.55:
            http://192.168.1.55:8000/

    WAŻNE:
        Serwer musi być otwarty, dopóki chcesz udostępniać pliki.
        Zatrzymanie:
            CTRL + C
    */

    /*
    ======================================================================================
    6. JAK OGLĄDAĆ PLIKI W PRZEGLĄDARCE
    ======================================================================================

    W Windows:
        uruchom Chrome i wpisz:

        http://192.168.1.55:8000/

    Zobaczysz listę plików katalogu, np.:

        test.txt
        image.png
        instalka.iso

    Klikasz → pobiera się lub otwiera.

    */

    /*
    ======================================================================================
    7. SERWER NA INNYM PORCIE
    ======================================================================================

        python3 -m http.server 1234

    adres:
        http://192.168.1.55:1234/

    Możesz użyć portu standardowego 80, ale wtedy trzeba sudo:
        sudo python3 -m http.server 80
    */

    /*
    ======================================================================================
    8. BLOKOWANIE PRZEZ FIREWALL
    ======================================================================================

    Jeśli nie widzisz strony → przyczyny:
        - firewall w Windows blokuje port
        - firewall w Ubuntu blokuje port
        - VM nie ma bridged karty sieciowej

    W Ubuntu wyłącz zaporę (do nauki):
        sudo ufw disable

    W Windows:
        ustaw "Zezwalaj" dla Pythona lub portu 8000.
    */

    /*
    ======================================================================================
    9. TESTOWANIE SERWERA Z TERMINALA
    ======================================================================================

    Z hosta Windows (PowerShell):

        curl http://192.168.1.55:8000

    Z Ubuntu (np. test lokalny):

        curl localhost:8000
    */

    /*
    ======================================================================================
    10. PODSUMOWANIE – CO TRZEBA ZAPAMIĘTAĆ
    ======================================================================================

    1) Maszyny muszą być w tej samej sieci (bridged).
    2) Sprawdzasz IP:
            ifconfig (Linux)
            ipconfig (Windows)
    3) Testujesz pingiem.
    4) W katalogu, który chcesz udostępnić:
            python3 -m http.server 8000
    5) Otwierasz w przeglądarce:
            http://IP_UBUNTU:8000/
    6) Firewall potrafi blokować.
    7) Zatrzymujesz serwer: CTRL + C
    */
}
