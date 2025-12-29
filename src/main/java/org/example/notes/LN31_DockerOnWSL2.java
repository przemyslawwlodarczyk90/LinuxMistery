package org.example.notes;

/**
 * LN31 – DOCKER NA WSL2 (ARCHITEKTURA I PRAKTYKA)
 *
 * - czym jest Docker i co rozwiązuje
 * - Docker vs VM (różnice architektoniczne)
 * - jak Docker działa na WSL2
 * - Docker Desktop vs Docker Engine w WSL
 * - instalacja Docker Desktop + WSL2
 * - komunikacja kontenery ↔ Windows
 * - wolumeny i system plików (NAJWAŻNIEJSZE!)
 * - porty, sieć, localhost
 * - typowe problemy i dobre praktyki
 */
public class LN31_DockerOnWSL2 {

    /*
    ======================================================================================
    1. CZYM JEST DOCKER?
    ======================================================================================

    Docker:
        - platforma do uruchamiania aplikacji w kontenerach
        - izolacja na poziomie:
            • procesów
            • systemu plików
            • sieci
        - WSPÓLNY kernel Linux

    Kontener:
        - NIE ma własnego kernela
        - jest procesem w kernelu hosta
    */

    /*
    ======================================================================================
    2. DOCKER VS MASZYNA WIRTUALNA
    ======================================================================================

    VM:
        - własny kernel
        - pełny system operacyjny
        - duży narzut

    Docker:
        - jeden kernel
        - izolacja przez namespaces + cgroups
        - minimalny narzut

    Docker = szybkość + powtarzalność
    */

    /*
    ======================================================================================
    3. DLACZEGO DOCKER POTRZEBUJE LINUXA?
    ======================================================================================

    Docker bazuje na:
        - namespaces
        - cgroups
        - union filesystem (overlayfs)

    To są MECHANIZMY KERNELA LINUX.

    Windows:
        - NIE ma natywnie tych mechanizmów
    */

    /*
    ======================================================================================
    4. JAK DOCKER DZIAŁA NA WINDOWS + WSL2
    ======================================================================================

    Docker na Windows:
        - NIE działa natywnie
        - używa WSL2

    Architektura:
        Windows
          ↓
        WSL2 (Linux kernel)
          ↓
        Docker Engine
          ↓
        Kontenery

    WSL2 = fundament Dockera na Windows
    */

    /*
    ======================================================================================
    5. DOCKER DESKTOP – CO TO JEST?
    ======================================================================================

    Docker Desktop:
        - GUI
        - zarządza:
            • Docker Engine
            • siecią
            • integracją z WSL
        - instaluje Dockera do WSL2

    REKOMENDACJA:
        Docker Desktop + WSL2
    */

    /*
    ======================================================================================
    6. INSTALACJA DOCKER DESKTOP + WSL2
    ======================================================================================

    KROKI:
        1. Włącz WSL2
        2. Zainstaluj Docker Desktop (Windows)
        3. Włącz integrację z dystrybucją WSL

    Sprawdzenie:
        docker version
        docker info
    */

    /*
    ======================================================================================
    7. GDZIE DZIAŁA DOCKER ENGINE?
    ======================================================================================

    Docker Engine:
        - działa WEWNĄTRZ WSL2
        - NIE w Windows

    Kontenery:
        - to procesy Linuxa
        - widoczne w:
            ps aux
    */

    /*
    ======================================================================================
    8. SIEĆ – JAK DZIAŁAJĄ PORTY?
    ======================================================================================

    Kontener:
        - ma własną sieć (bridge)

    Mapowanie portów:
        docker run -p 8080:80 nginx

    Efekt:
        localhost:8080 (Windows)
        → kontener (WSL)

    Docker Desktop robi NAT automatycznie
    */

    /*
    ======================================================================================
    9. WOLUMENY I SYSTEM PLIKÓW (MEGA WAŻNE)
    ======================================================================================

    NAJWIĘKSZA PUŁAPKA:

    ❌ Trzymanie projektu w /mnt/c
    ✔ Trzymanie projektu w /home

    Powód:
        - NTFS jest WOLNY w WSL
        - Linux FS (ext4) jest szybki

    ZŁO:
        /mnt/c/Users/...

    DOBRO:
        /home/przemek/project
    */

    /*
    ======================================================================================
    10. WOLUMENY – PRZYKŁADY
    ======================================================================================

    Volume:
        docker run -v /home/app:/app image

    Bind mount:
        - szybki
        - idealny do dev

    Named volume:
        docker volume create data
    */

    /*
    ======================================================================================
    11. DOCKER COMPOSE W WSL2
    ======================================================================================

    docker-compose:
        - definiuje wiele kontenerów
        - sieć + wolumeny

    Przykład:
        docker-compose up -d

    Działa IDEALNIE w WSL2
    */

    /*
    ======================================================================================
    12. KOMUNIKACJA KONTENER ↔ KONTENER
    ======================================================================================

    Docker network:
        - własny DNS
        - kontenery widzą się po nazwach

    Przykład:
        db
        backend
        frontend

    backend → db
    */

    /*
    ======================================================================================
    13. KOMUNIKACJA WINDOWS ↔ KONTENER
    ======================================================================================

    Windows:
        localhost:PORT

    Kontener:
        dostępny przez mapowanie

    NIE potrzebujesz IP WSL
    */

    /*
    ======================================================================================
    14. DEBUG I MONITORING
    ======================================================================================

    Podstawowe polecenia:
        docker ps
        docker logs
        docker exec -it container bash
        docker stats

    W WSL:
        top
        htop
    */

    /*
    ======================================================================================
    15. CO NIE DZIAŁA / OGRANICZENIA
    ======================================================================================

    ❌ systemd w kontenerach (chyba że specjalnie)
    ❌ Docker-in-Docker (bez konfiguracji)
    ❌ produkcja

    Docker na WSL:
        DEV ONLY
    */

    /*
    ======================================================================================
    16. DOCKER A SYSTEMD W WSL
    ======================================================================================

    Docker Engine:
        - zarządzany przez Docker Desktop
        - NIE przez systemctl

    Nie robisz:
        systemctl start docker
    */

    /*
    ======================================================================================
    17. PERFORMANCE – CO JEST SZYBKIE, CO NIE
    ======================================================================================

    SZYBKIE:
        - CPU
        - RAM
        - sieć

    WOLNE:
        - NTFS (/mnt/c)
        - bind mounts na Windows FS
    */

    /*
    ======================================================================================
    18. KIEDY DOCKER + WSL MA SENS
    ======================================================================================

    ✔ backend dev
    ✔ microservices
    ✔ Java + Spring
    ✔ Node
    ✔ Python

    ❌ produkcja
    ❌ heavy IO na NTFS
    */

    /*
    ======================================================================================
    19. PORÓWNANIE: DOCKER NA WSL VS NA LINUX
    ======================================================================================

    Linux:
        - najlepsza wydajność
        - pełna kontrola

    WSL2:
        - 90% doświadczenia
        - wygoda Windows
    */

    /*
    ======================================================================================
    20. PODSUMOWANIE
    ======================================================================================

    - Docker na Windows = Docker na WSL2
    - WSL2 dostarcza kernel Linux
    - Docker Desktop zarządza integracją
    - projekty trzymaj w /home
    - idealne środowisko developerskie
    */
}
