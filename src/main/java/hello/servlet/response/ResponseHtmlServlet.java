package hello.servlet.response;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHtmlServlet", urlPatterns = "/responseHtmlServlet")
public class ResponseHtmlServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // [start-line]
        resp.setStatus(HttpServletResponse.SC_OK);

        // [html 에서 렌더링할 수 있도록 타입을 html로 지정]
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        // [view 작성]
        PrintWriter writer = resp.getWriter();
        writer.write("<html>");

        writer.write("<head>");

        writer.write("<body>");
        writer.write("<div>");
        writer.write("html 구조 완성");
        writer.write("</div>");
        writer.write("</body>");

        writer.write("</head>");
        writer.write("</html>");

    }
}
