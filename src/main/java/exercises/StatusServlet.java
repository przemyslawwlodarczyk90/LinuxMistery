package exercises;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ĆWICZENIE 3
 *
 * GET /api/status?name=Przemek
 *
 * - brak name -> 400 + {"error":"name is required"}
 * - jest name -> 200 + {"status":"ok","name":"..."}
 *
 * Bez Gson, bez Jackson.
 * Ręczny JSON.
 */
@WebServlet("/api/status")
public class StatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // TODO 1: nagłówki (JSON + UTF-8)

        // TODO 2: pobierz parametr "name"

        // TODO 3: if (name == null || pusty)
        //   - status 400
        //   - JSON error

        // TODO 4: else
        //   - status 200
        //   - JSON {"status":"ok","name":"..."}
    }
}
