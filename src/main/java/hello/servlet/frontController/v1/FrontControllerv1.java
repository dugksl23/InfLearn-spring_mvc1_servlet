package hello.servlet.frontController.v1;

import hello.servlet.frontController.v1.controller.MemberFormControllerV1;
import hello.servlet.frontController.v1.controller.MemberListControllerv1;
import hello.servlet.frontController.v1.controller.MemberSaveControllerv1;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerv1", urlPatterns = "/frontController/v1/*")
// /controller/vi 하위 요청은 모두 v1에서 받겠다는 의미이다.
@Slf4j
public class FrontControllerv1 extends HttpServlet {

    private Map<String, ControllerV1> controllerv1Map = new HashMap<>();

    public FrontControllerv1() {
        controllerv1Map.put("/frontController/v1/members/save", new MemberSaveControllerv1());
        controllerv1Map.put("/frontController/v1/members/new-form", new MemberFormControllerV1());
        controllerv1Map.put("/frontController/v1/members", new MemberListControllerv1());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String contextPath = req.getRequestURI(); // 스키마, host(url), port/ uri
        System.out.println("contextPath : " + contextPath);

        boolean b = controllerv1Map.containsKey(contextPath);
        if (b != true) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("not found");
        }

        ControllerV1 controllerV1 = controllerv1Map.get(contextPath);
        log.info("controllerV1, {}", controllerV1.getClass());
        controllerV1.process(req, resp);//다형성에 의해서 overriding 된 함수가 호출된다.client는 구현체가 몰라도 된다.


    }
}
