package beans.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomServletGET extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String nume = request.getParameter("nume");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<p>Hello World! " + CustomServletGET.class.getCanonicalName() + " with parameter 'nume' = " + nume + "</p>");

    }
}
