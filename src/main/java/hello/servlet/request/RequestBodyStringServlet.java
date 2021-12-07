package hello.servlet.request;


import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/requestBodyStringServlet")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp);
        // 전송된 text는 bytecode로 전송되기에 해당 byte code를 꺼낸다.
        ServletInputStream inputStream = req.getInputStream();
        String s = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// Stream을 가져오고, 해당 Stream의 byte 문자를 uft로 인코딩
        System.out.println("messageBody : " + s);
        resp.getWriter().write("ok"); //return response
    }
}
