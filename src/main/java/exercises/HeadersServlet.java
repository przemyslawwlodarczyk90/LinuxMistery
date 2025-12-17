package exercises;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ĆWICZENIE 4
 *
 * GET /api/headers
 *
 * - Odczytaj nagłówek: X-User
 * - Jeśli brak lub pusty -> 400 + {"error":"X-User header is required"}
 * - Jeśli jest -> 200 + {"user":"..."}
 *
 * Bez Gson/Jackson.
 */
@WebServlet("/api/headers")
public class HeadersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String user = req.getHeader("X-User");

        if (user == null || user.trim().isEmpty()){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"user is required\"}");
            return;
        }else {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"status\":\"ok\",\"user\":\"" + user + "\"}");
        }

        // TODO 1: nagłówki odpowiedzi: JSON + UTF-8

        // TODO 2: String user = req.getHeader("X-User");

        // TODO 3: walidacja null/pusty -> 400 + JSON error + return

        // TODO 4: 200 + JSON {"user":"..."}
    }
}
