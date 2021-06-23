package beans.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomServletGET extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String customParameterValue = request.getParameter("customParameterName");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>Hello World! " + CustomServletGET.class.getCanonicalName() + " with parameter 'customParameterName' = " + customParameterValue + " custom servlet</body></html>");

    }
}
