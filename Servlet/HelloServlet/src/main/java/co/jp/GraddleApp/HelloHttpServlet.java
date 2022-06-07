package co.jp.GraddleApp;

// tomcat10以降だと javaxではなくjakartaの方のパッケージを使わないといけない。URLにアクセスしても404 Not Foundとして扱われてしまう。
// https://stackoverflow.com/questions/70262257/404-after-using-servlet-annotation-webservlet
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.Principal;

@WebServlet(value="/", name="Hello Servlet")
public class HelloHttpServlet extends HttpServlet {

    private String greeting;

    @Override
    public void init() throws ServletException {
        super.init();
        greeting = System.getenv("GREETING");
        if (greeting == null) {
            greeting = "Hello";
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Principal principal = req.getUserPrincipal();
        String username = (principal == null ? "unauthenticated user" : principal.getName());
        res.getWriter().println(greeting + " " + username);
    }
}