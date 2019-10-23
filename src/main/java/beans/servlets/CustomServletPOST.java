package beans.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomServletPOST extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // attribute must be set somewhere else
        String customAttributeName = (String) request.getAttribute("customAttributeName");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<p>Hello World! " + CustomServletPOST.class.getCanonicalName() + " with attribute 'customAttributeName'= " + customAttributeName + "</p>");

    }
}
