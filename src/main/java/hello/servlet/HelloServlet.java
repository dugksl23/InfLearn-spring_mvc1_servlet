package hello.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/helloServlet")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp);
        System.out.println("helloServlet requested");
        System.out.println("request : " + req);
        System.out.println("response : " + resp);

        // req 의 정보를 출력
        String username = req.getParameter("username");
        System.out.println("username : " + username);


        // res의 header 셋팅 및 body 내용 설정
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain"); // 일반 텍스트 평문
        resp.getWriter().write("200/ok");// resp.setBody()





    }


}
