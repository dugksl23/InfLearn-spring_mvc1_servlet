package hello.servlet.response;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/responseServlet")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("=== Response packet setting start ====");

        // [status-line]
        resp.setStatus(HttpServletResponse.SC_OK); //200, OK

        // [response header]
        resp.setHeader("Content-type", "text/plain;charset=utf-8");
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // cache 관련 설정
        resp.setHeader("Pragma", "no-cache");

        resp.setHeader("myHeader", "what?");

        // [response 편의 메소드]
        content(resp);
        cookie(resp);
        redirect(resp);


        // [response message body]
        resp.getWriter().write("ok");
        System.out.println("===== response packet end");

    }

    private void redirect(HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_FOUND);
        resp.setHeader("Location", "/basic/hello-form.html");

        //resp.sendRedirect("/basic/hello-form.html");
    }

    private void cookie(HttpServletResponse resp) {
        Cookie cookie = new Cookie("myCookie", "id");
        cookie.setMaxAge(300);
        resp.addCookie(cookie);
    }

    private void content(HttpServletResponse resp) {
        resp.setContentType("text/pain");
        resp.setCharacterEncoding("utf-8");
    }


}
