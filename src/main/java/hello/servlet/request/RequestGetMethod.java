package hello.servlet.request;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "getMethod", urlPatterns = "/getMethod")
public class RequestGetMethod extends HttpServlet { //http api server interface를 HttpServlet으로 추상화 class 이며,
    // 구현체는 HttpServlet 를 상속받은 구현체 class 가 된다.

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {                    // Rest method 는 protected void service()로 통일
        // Rest method 는 protected void service()로 통일

        //super.service(req, resp);
        Map<String, String[]> parameterMap = req.getParameterMap();
        Enumeration<String> parameterNames = req.getParameterNames();


//        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
//        entries.iterator().forEachRemaining(parameter -> {
//            System.out.println("dhkdy?");
//            System.out.println(parameter.getKey() + ":" + parameterMap.get(parameter.getKey()));
//        });

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
