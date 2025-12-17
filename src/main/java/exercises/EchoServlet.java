package exercises;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;



    @WebServlet("/api/echo")
    public class EchoServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            String message = req.getParameter("msg");

            // brak parametru lub pusty
            if (message == null || message.trim().isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"msg is required\"}");
                return;
            }

            // OK
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"echo\":\"" + message + "\"}");
        }
    }

