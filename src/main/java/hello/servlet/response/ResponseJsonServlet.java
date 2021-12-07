package hello.servlet.response;

import Entity.HelloData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "responseJsonServlet", urlPatterns = "/responseJsonServlet")
@RequiredArgsConstructor
public class ResponseJsonServlet extends HttpServlet {

    private final ObjectMapper objectMapper;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // [status-line]
        resp.setStatus(HttpServletResponse.SC_OK);

        // [편의 데이터]
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        // [request messagebody 파싱]
        ServletInputStream inputStream = req.getInputStream();
        HelloData helloData1 = objectMapper.readValue(inputStream, HelloData.class);


        // [response message body]
        String s = objectMapper.writeValueAsString(helloData1);// java의 객체를 json으로 형변환
        resp.getWriter().write(s);

    }
}
