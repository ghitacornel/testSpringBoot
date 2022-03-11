package beans.http.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomServletPOST extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // attribute must be set somewhere else
        String customAttributeValue = (String) request.getAttribute("customAttributeName");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>Hello World! " + CustomServletPOST.class.getCanonicalName() + " with attribute 'customAttributeName' = " + customAttributeValue + " custom servlet</body></html>");

    }
}
