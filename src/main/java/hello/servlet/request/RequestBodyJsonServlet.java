package hello.servlet.request;


import hello.servlet.Entity.HelloData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "jsonServlet", urlPatterns = "/jsonServlet")
public class RequestBodyJsonServlet extends HttpServlet {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp);


        ServletInputStream inputStream = req.getInputStream();
        String msgBodyString = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        HelloData helloData = objectMapper.readValue(msgBodyString, HelloData.class);
        // 스프링 mvc에서는 잭슨 라이브러리에서 제공하는 objectMapper를 통해 messageBody의 내용을 바탕으로
        // 객체 바인딩을 진행한다. 해당 객체 바인딩 떄 setter를 바탕으로 멤버변수에 접근하여 바인딩한다.
        // ex) 객체 바인딩 타입, messageBodyString

        System.out.println("username : " + helloData.getUsername());
        System.out.println("age : " + helloData.getAge());

        resp.getWriter().write("ok");


    }
}
