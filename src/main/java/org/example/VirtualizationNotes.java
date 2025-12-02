package org.example;

/**
 * NOTATKI: WIRTUALIZACJA, VMWARE, VIRTUALBOX
 *
 * Ten plik służy TYLKO jako notatki w kodzie.
 * Nie ma tu logiki – to „ściąga” w formie klasy.
 */
public class VirtualizationNotes {

    /*
    ============================================
    1. Co to jest wirtualizacja?
    ============================================
    - Wirtualizacja = uruchamianie dodatkowych systemów operacyjnych
      jako MASZYN WIRTUALNYCH (VM) na jednym fizycznym komputerze (host).
    - Maszyna wirtualna dostaje:
        * wirtualny procesor (CPU),
        * wirtualną pamięć RAM,
        * wirtualny dysk,
        * wirtualną kartę sieciową itp.
    - Pomiędzy sprzętem a VM jest HYPERVISOR.

    PO CO TO?
    - testowanie różnych systemów (np. Linux na Windowsie),
    - izolacja środowisk (dev / test / produkcja),
    - możliwość robienia snapshotów (przywracanie stanu),
    - bezpieczne eksperymenty (psucie systemu bez ryzyka dla hosta).
    */

    /*
    ============================================
    2. Typy hypervisorów
    ============================================

    Hypervisor = warstwa zarządzająca maszynami wirtualnymi.

    TYP 1 (bare-metal):
    - działa BEZPOŚREDNIO na sprzęcie, zamiast klasycznego systemu.
    - przykłady:
        * VMware ESXi
        * Microsoft Hyper-V Server
        * Proxmox (KVM)
        * Xen
    - używany głównie w serwerowniach, data center.
    - zaleta: duża wydajność.

    TYP 2 (hosted):
    - działa jako APLIKACJA w normalnym systemie (Windows, Linux, macOS).
    - przykłady:
        * VMware Workstation / VMware Player
        * Oracle VirtualBox
        * QEMU + virt-manager (też może tak działać)
    - idealny na PC/laptopa do nauki, testów, dev.
    */

    /*
    ============================================
    3. VMware (Workstation / Player)
    ============================================

    - Typ 2 hypervisora (hosted).
    - Instalowany na istniejącym systemie (Windows / Linux).
    - Funkcje:
        * snapshoty,
        * klonowanie maszyn,
        * integracja z systemem hosta (udostępnianie folderów, kopiuj/wklej),
        * dobre wsparcie sprzętowe (akceleracja, sterowniki).
    - Często bardziej dopracowany i stabilny niż VirtualBox,
      zwłaszcza w zastosowaniach komercyjnych.
    */

    /*
    ============================================
    4. VirtualBox
    ============================================

    - Też hypervisor typu 2.
    - Darmowy, open source (Oracle).
    - Działa na Windows / Linux / macOS.
    - Typowe zastosowania:
        * nauka Linuxa,
        * laby do sieci / serwerów,
        * testy oprogramowania.
    - Ma snapshoty, klonowanie, udostępnianie folderów, tryb „seamless”
      (okna VM mieszają się z oknami hosta).
    */

    /*
    ============================================
    5. Wirtualizacja a ARCHITEKTURA CPU
    ============================================

    WAŻNE:
    - Klasyczna wirtualizacja (VMware, VirtualBox) zakłada TĘ SAMĄ
      architekturę procesora:
        * host: x86_64 (np. Intel/AMD),
        * VM: też x86_64 (Windows, Linux 64-bit).
    - DLATEGO:
        * Na normalnym PC z Intelem/AMD uruchomisz:
            -> Windows 10/11 64-bit,
            -> dowolnego Linuxa 64-bit,
            -> stare systemy 32-bit x86.
        * ALE nie uruchomisz bezpośrednio np. systemu ARM (typowa architektura
          telefonów i wielu SBC jak Raspberry Pi), bo to INNA architektura CPU.

    EMULACJA vs WIRTUALIZACJA:
    - Wirtualizacja:
        * VM korzysta z instrukcji CPU hosta,
        * bardzo szybka,
        * wymaga tej samej architektury (x86 -> x86).
    - Emulacja:
        * symuluje zupełnie inną architekturę w software,
        * przykłady narzędzi:
            -> QEMU w trybie pełnej emulacji,
        * pozwala odpalać systemy/soft ARM na x86,
        * ale jest dużo wolniejsza.

    PODSUMOWANIE TEGO FRAGMENTU:
    - „Na wirtualizacji można odpalać software pod inną architekturę”
      -> nie do końca prawda.
    - VMware / VirtualBox = raczej TA SAMA architektura (x86 na x86).
    - INNA architektura = trzeba EMULACJI (np. QEMU), a nie czystej wirtualizacji.
    */

    /*
    ============================================
    6. Typowy scenariusz użycia dla Ciebie
    ============================================

    - Host: Windows / Linux na Twoim laptopie.
    - Instalujesz:
        * VirtualBox albo VMware Workstation Player.
    - Tworzysz VM z:
        * Linux Mint / Ubuntu / Debian.
    - W VM:
        * uczysz się konsoli,
        * instalujesz narzędzia dev,
        * testujesz konfiguracje, których nie chcesz ruszać na hoście.

    - Jak coś zepsujesz:
        * przywracasz snapshot i jedziesz dalej.
    */

    // Klasa nie musi mieć żadnego kodu wykonywalnego.
    // Chodzi tylko o to, aby IDE widziało ją jako część projektu.
}
