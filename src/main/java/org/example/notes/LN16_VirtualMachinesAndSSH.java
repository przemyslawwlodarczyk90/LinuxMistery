package org.example.notes;

/**
 * LN16 – ŁĄCZENIE MASZYN WIRTUALNYCH, SSH, VDI, SIEĆ, PRZESYŁANIE PLIKÓW
 *
 * - czym jest dystrybucja Lubuntu
 * - czym jest plik VDI i VMDK
 * - OSBoxes – gotowe maszyny
 * - VirtualBox / VMware – wirtualizacja
 * - zmiana rozmiaru dysku VDI (VBoxManage)
 * - RAM i CPU dla VM
 * - GParted – rozszerzanie partycji
 * - sieć NAT vs Bridged
 * - klasy adresowe IP
 * - SSH: instalacja, klient–serwer, port
 * - przesyłanie plików SCP
 * - repozytoria i sources.list
 */
public class LN16_VirtualMachinesAndSSH {

    /*
    ======================================================================================
    1. CZYM JEST DYSTRYBUCJA LUBUNTU?
    ======================================================================================

    Lubuntu:
        - lekka dystrybucja Linuxa
        - oparta na Ubuntu
        - środowisko graficzne LXQt
        - małe zużycie RAM i CPU
        - idealna do maszyn wirtualnych i starszych komputerów

    Dziedziczy:
        - system pakietów APT
        - repozytoria Ubuntu
        - systemd
        - jądro Linux

    */

    /*
    ======================================================================================
    2. PLIKI DYSKÓW WIRTUALNYCH: VDI, VMDK
    ======================================================================================

    VDI (Virtual Disk Image):
        - format dysku VirtualBoxa
        - plik reprezentujący CAŁY dysk maszyny wirtualnej
        - zawiera partycje, system plików, dane

    VMDK:
        - format VMware
        - podobna rola, inny format

    To NIE JEST partycja systemu hosta.
    To „dysk w pliku”.

    */

    /*
    ======================================================================================
    3. OSBoxes – GOTOWE MASZYNY
    ======================================================================================

    https://www.osboxes.org

    Co tam jest?
        - gotowe obrazy Linuxa (VDI, VMDK)
        - nie instalujesz systemu od zera
        - loginy i hasła są podane

    Workflow:
        - pobierasz VDI
        - podłączasz do VirtualBoxa
        - odpalasz VM
    */

    /*
    ======================================================================================
    4. VIRTUALBOX I VMWARE – CO TO JEST?
    ======================================================================================

    VirtualBox:
        - darmowy
        - Oracle
        - idealny do nauki

    VMware:
        - komercyjny
        - bardzo stabilny
        - szeroko używany w firmach

    Oba:
        - emulują sprzęt
        - izolują system
        - umożliwiają sieci, snapshoty, dyski
    */

    /*
    ======================================================================================
    5. ZMIANA ROZMIARU DYSKU VDI (PO CO?)
    ======================================================================================

    Jeśli VM ma np. 10 GB:
        - system może się rozrosnąć tylko do tej granicy
        - nawet jeśli host ma wolne miejsce

    Musisz:
        1) powiększyć plik VDI
        2) rozszerzyć partycję w Linuxie

    */

    /*
    ======================================================================================
    6. VBoxManage – ZMIANA ROZMIARU VDI
    ======================================================================================

    VBoxManage.exe znajduje się w katalogu instalacyjnym VirtualBoxa:
        C:\Program Files\Oracle\VirtualBox\

    Najlepiej:
        - dodać ten katalog do zmiennej środowiskowej PATH

    PATH:
        - lista katalogów, w których system szuka poleceń
        - po dodaniu: VBoxManage działa z każdego miejsca w CMD

    Komenda:
        VBoxManage modifymedium disk "ścieżka_do_dysku.vdi" --resize 20000

    20000 = MB (20 GB)

    To TYLKO powiększa plik dysku, nie partycję.
    */

    /*
    ======================================================================================
    7. GParted – ROZSZERZANIE PARTYCJI
    ======================================================================================

    GParted:
        - narzędzie do zarządzania partycjami
        - najlepiej uruchomić z ISO jako Live CD

    Kroki:
        1) podłącz ISO GParted do VM
        2) uruchom VM z ISO
        3) zobaczysz nieprzydzielone miejsce
        4) rozszerz partycję /
        5) zatwierdź zmiany

    Bez tego Linux NIE ZOBACZY nowego miejsca.
    */

    /*
    ======================================================================================
    8. PRZYDZIAŁ RAM I CPU DO VM
    ======================================================================================

    VirtualBox → Settings → System:

    RAM:
        - Lubuntu: minimum 1 GB
        - komfortowo: 2 GB+

    CPU:
        - 1 rdzeń: minimum
        - 2 rdzenie: wygodniej

    Więcej = szybciej, ale host musi to udźwignąć.
    */

    /*
    ======================================================================================
    9. SIEĆ: NAT VS BRIDGED
    ======================================================================================

    NAT:
        - VM ma internet
        - host NIE widzi VM bez port forwarding
        - IP prywatne

    Bridged:
        - VM jest w tej samej sieci co host
        - dostaje IP z routera
        - IDEALNE do SSH i serwowania plików

    Do łączenia maszyn:
        → BRIDGED
    */

    /*
    ======================================================================================
    10. KLASY ADRESOWE IP – CO TO ZNACZY?
    ======================================================================================

    Przykład:
        192.168.1.10
        192.168.1.20

    Te adresy są w tej samej sieci (klasa C):
        - ta sama podsieć
        - mogą się widzieć bez routera

    Jeśli:
        192.168.1.x
        10.0.0.x

    → różne sieci, brak komunikacji bez routingu
    */

    /*
    ======================================================================================
    11. REPOZYTORIA I sources.list
    ======================================================================================

    Jeśli APT nie działa:
        /etc/apt/sources.list

    To lista repozytoriów.

    Generator:
        - online generator sources.list dla Ubuntu/Lubuntu
        - wybierasz wersję systemu
        - podmieniasz plik
        - sudo apt update

    Bez repozytoriów:
        - nie zainstalujesz pakietów
    */

    /*
    ======================================================================================
    12. INSTALACJA NARZĘDZI SIECIOWYCH
    ======================================================================================

        sudo apt install net-tools

    Dostajesz:
        ifconfig
        netstat
    */

    /*
    ======================================================================================
    13. SSH – ARCHITEKTURA KLIENT–SERWER
    ======================================================================================

    SSH:
        - Secure Shell
        - szyfrowane połączenie zdalne

    Architektura:
        klient (ssh) → serwer (sshd)

    Domyślny port:
        22

    */

    /*
    ======================================================================================
    14. INSTALACJA SSH NA LUBUNTU
    ======================================================================================

        sudo apt update
        sudo apt install openssh-server

    Sprawdzenie:
        systemctl status ssh

    */

    /*
    ======================================================================================
    15. ŁĄCZENIE SIĘ Z DRUGĄ MASZYNĄ
    ======================================================================================

    Sprawdź IP Lubuntu:
        ifconfig

    Z Ubuntu:
        ssh user@IP

    Przykład:
        ssh przemek@192.168.1.50
    */

    /*
    ======================================================================================
    16. PRZESYŁANIE PLIKÓW SCP
    ======================================================================================

    SCP:
        Secure Copy (przez SSH)

    Kopiowanie pliku:
        scp plik.txt user@IP:/home/user/

    Kopiowanie katalogu:
        scp -r folder user@IP:/home/user/

    */

    /*
    ======================================================================================
    17. UPRAWNIENIA I SUDO
    ======================================================================================

    sudo:
        - wykonanie polecenia jako root
        - wymaga hasła użytkownika

    PID:
        - identyfikator procesu
        - np. sshd ma swój PID

    */

    /*
    ======================================================================================
    18. RESTART SYSTEMU
    ======================================================================================

        reboot
        sudo reboot

    */

    /*
    ======================================================================================
    19. PODSUMOWANIE
    ======================================================================================

    - VDI = dysk w pliku
    - VBoxManage powiększa dysk
    - GParted rozszerza partycję
    - Bridged = widoczność w sieci
    - SSH działa na porcie 22
    - SCP = kopiowanie plików
    - sources.list = repozytoria
    - Lubuntu = lekki Linux do VM
    */
}
