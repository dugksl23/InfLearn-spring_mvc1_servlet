package hello.servlet.request;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "form-request", urlPatterns = "/formRequest")
public class RequestParamMethod extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp);
        Enumeration<String> parameterNames = req.getParameterNames();
        parameterNames.asIterator().forEachRemaining(parameterName -> {
            System.out.println("parameter : " + req.getParameter(parameterName));
        });


        System.out.println("=== 단일 parameter 조회 ===");
        System.out.println("[ 단일 parameter 조회 ]");

        req.getParameterNames().asIterator().forEachRemaining(parameter -> System.out.println(req.getParameter(parameter)));

        // 중복 key에 따른 value 값, 중복 parameter
        System.out.println("=== 중복 parameter 조회 ===");
        System.out.println("[ 중복 parameter 조회 ]");
        String[] usernames = req.getParameterValues("username");
        for (String username : usernames) {
            System.out.println(username);
        }


    }
}
