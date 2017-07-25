/**
 * Created by Lukasz on 25.07.2017.
 */
package zadanie.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // ustawienie MIME i kodowania
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        // response in html
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("<title>Echo Servlet</title></head>");
            out.println("<body><h2>You have enter</h2>");

            // Pobranie paramentru username
            String username = request.getParameter("username");
            // sprawdzenie czy nazwa jest nullem lub pusta
            if (username == null
                    || (username = htmlFilter(username.trim())).length() == 0) {
                out.println("<p>Name: MISSING</p>");
            } else {
                out.println("<p>Name: " + username + "</p>");
            }

            // Pobranie paramentru username
            String password = request.getParameter("password");
            if (password == null
                    || (password = htmlFilter(password.trim())).length() == 0) {
                out.println("<p>Password: MISSING</p>");
            } else {
                out.println("<p>Password: " + password + "</p>");
            }

            // Pobranie paramentru plec
            String gender = request.getParameter("gender");
            // sprawdzenie czy jest null
            if (gender == null) {
                out.println("<p>Gender: MISSING</p>");
            } else if (gender.equals("m")) {
                out.println("<p>Gender: male</p>");
            } else {
                out.println("<p>Gender: female</p>");
            }

            // Pobranie wartosci age
            String age = request.getParameter("age");
            if (age == null) {
                out.println("<p>Age: MISSING</p>");
            } else if (age.equals("1")) {
                out.println("<p>Age: &lt; 1 year old</p>");
            } else if (age.equals("99")) {
                out.println("<p>Age: 1 to 99 years old</p>");
            } else {
                out.println("<p>Age: &gt; 99 years old</p>");
            }

            // Pobranie wartosci language
            // Mozliwe wiele wartosci.
            //  getParameterValues()
            String[] languages = request.getParameterValues("language");
            // Get null if the parameter is missing from query string.
            if (languages == null || languages.length == 0) {
                out.println("<p>Languages: NONE</p>");
            } else {
                out.println("<p>Languages: ");
                for (String language : languages) {
                    if (language.equals("c")) {
                        out.println("C/C++ ");
                    } else if (language.equals("cs")) {
                        out.println("C# ");
                    } else if (language.equals("java")) {
                        out.println("Java ");
                    }
                }
                out.println("</p>");
            }

            // Pobranie wartosci instruction
            String instruction = request.getParameter("instruction");
            // sprawdzenie wartosci
            if (instruction == null
                    || (instruction = htmlFilter(instruction.trim())).length() == 0
                    || instruction.equals("Enter your instruction here...")) {
                out.println("<p>Instruction: NONE</p>");
            } else {
                out.println("<p>Instruction: " + instruction + "</p>");
            }

            // Pobranie wartosci z pola secret
            String secret = request.getParameter("secret");
            out.println("<p>Secret: " + secret + "</p>");

            // Get all the names of request parameters
            Enumeration names = request.getParameterNames();
            out.println("<p>Request Parameter Names are: ");
            if (names.hasMoreElements()) {
                out.print(htmlFilter(names.nextElement().toString()));
            }
            do {
                out.print(", " + htmlFilter(names.nextElement().toString()));
            } while (names.hasMoreElements());
            out.println(".</p>");

            // Hyperlink "BACK"
            out.println("<a href='form.html'>BACK</a>");

            out.println("</body></html>");
        } finally {
            out.close();  // Zawsze zamykamy out
        }
    }

    // Przekierowanie POST request to GET request.
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }

    // Filter na znaki specjalne
    private static String htmlFilter(String message) {
        if (message == null) return null;
        int len = message.length();
        StringBuffer result = new StringBuffer(len + 20);
        char aChar;

        for (int i = 0; i < len; ++i) {
            aChar = message.charAt(i);
            switch (aChar) {
                case '<': result.append("&lt;"); break;
                case '>': result.append("&gt;"); break;
                case '&': result.append("&amp;"); break;
                case '"': result.append("&quot;"); break;
                default: result.append(aChar);
            }
        }
        return (result.toString());
    }
}

