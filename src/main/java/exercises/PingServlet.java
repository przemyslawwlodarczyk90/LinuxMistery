package exercises;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ĆWICZENIE 1 (najprostsze):
 *
 * Zrób endpoint GET /api/ping, który zwraca JSON:
 *   {"status":"ok"}
 *
 * WYMAGANIA:
 * 1) Ustaw nagłówki:
 *    - Content-Type: application/json
 *    - UTF-8
 * 2) Status ma być 200
 * 3) Bez Gson/Jackson — ręcznie wypisz JSON Stringiem
 * 4) Nie rób żadnych ifów, żadnego pathInfo — jedno proste doGet.
 *
 * BONUS (opcjonalnie, jak chcesz):
 * - dodaj drugi klucz "app":"servlet"
 *   {"status":"ok","app":"servlet"}
 */


    @WebServlet("/api/ping")
    public class PingServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

            // encoding
            resp.setCharacterEncoding("UTF-8");

            // content type
            resp.setContentType("application/json");

            // status
            resp.setStatus(HttpServletResponse.SC_OK);

            // body (JSON)
            resp.getWriter().write("{\"status\":\"ok\"}");
        }


        // TODO 1: ustaw Content-Type i encoding

        // TODO 2: ustaw status 200 (albo zostaw domyślny, ale jawnie też możesz)

        // TODO 3: wypisz JSON do resp.getWriter()
        // Pamiętaj: ma to być poprawny JSON (cudzysłowy itd.)
    }

