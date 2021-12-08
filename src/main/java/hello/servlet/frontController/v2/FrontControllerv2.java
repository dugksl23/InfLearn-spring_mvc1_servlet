package hello.servlet.frontController.v2;

import hello.servlet.frontController.MyView;
import hello.servlet.frontController.v2.Controllerv2;
import hello.servlet.frontController.v2.controller.MemberFormControllerV2;
import hello.servlet.frontController.v2.controller.MemberListControllerv2;
import hello.servlet.frontController.v2.controller.MemberSaveControllerv2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerv2", urlPatterns = "/frontController/v2/*")
public class FrontControllerv2 extends HttpServlet {

    private Map<String, Controllerv2> controllerv1Map = new HashMap<>();

    public FrontControllerv2() {
        controllerv1Map.put("/frontController/v2/members/save", new MemberSaveControllerv2());
        controllerv1Map.put("/frontController/v2/members/new-form", new MemberFormControllerV2());
        controllerv1Map.put("/frontController/v2/members", new MemberListControllerv2());
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        boolean b = controllerv1Map.containsKey(requestURI);
        if (b == false) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("not found");
        }

        Controllerv2 controllerV2 = controllerv1Map.get(requestURI);
        MyView process = controllerV2.process(req, resp);
        process.render(req, resp);

    }
}
