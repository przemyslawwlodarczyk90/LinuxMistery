package exercises;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ĆWICZENIE 5
 *
 * GET /api/sum?a=2&b=3
 *
 * - brak a lub b -> 400 + {"error":"a and b are required"}
 * - a lub b nie są liczbami -> 400 + {"error":"a and b must be numbers"}
 * - sukces -> 200 + {"result":5}
 *
 * Bez Gson/Jackson.
 */

    @WebServlet("/api/sum")
    public class SumServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");

            String a1 = req.getParameter("a");
            String b1 = req.getParameter("b");

            if (a1 == null || b1 == null || a1.trim().isEmpty() || b1.trim().isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("a and b are required");
                return;
            }

            try {
                int a = Integer.parseInt(a1);
                int b = Integer.parseInt(b1);

                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(String.valueOf(a + b));

            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("a and b must be numbers");
            }
        }
    }

    // TODO 1: nagłówki JSON + UTF-8

        // TODO 2: pobierz parametry a i b (String)

        // TODO 3: jeśli null lub pusty -> 400 + JSON error + return

        // TODO 4: spróbuj Integer.parseInt
        //   - catch NumberFormatException -> 400 + JSON error + return

        // TODO 5: policz sumę

        // TODO 6: 200 + {"result":X}


