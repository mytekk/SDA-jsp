package zadanie.java;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Lukasz on 25.07.2017.
 */

public class ParamServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.print("<html>");
        out.print("<body>");
        out.write("<h1 style=\"font-family: 'Quicksand', sans-serif;\">Hello "+request.getParameter("name")+"</h1>");
        out.print("</body></html>");
    }
}
