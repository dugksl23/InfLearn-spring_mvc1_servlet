package hello.servlet.frontController.v4;


import hello.servlet.frontController.MyView;
import hello.servlet.frontController.v4.Controller.MemberFormControllerV4;
import hello.servlet.frontController.v4.Controller.MemberListControllerV4;
import hello.servlet.frontController.v4.Controller.MemberSaveControllerV4;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "frontControllerV4", urlPatterns = "/frontController/v4/*")
public class FrontControllerV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    @PostConstruct
    public void init() {
        controllerMap.put("/frontController/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/frontController/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/frontController/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean b = controllerMap.containsKey(req.getRequestURI());
        if (b == false) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("not found");
        }

        ControllerV4 controllerV4 = controllerMap.get(req.getRequestURI());


        Map<String, Object> requestParam = extractRequestParam(req);
        Map<String, Object> model = new HashMap<>();
        String viewName = controllerV4.process(requestParam, model);
        MyView myView = viewResolver(viewName);
        myView.render(model, req, resp);

    }

    private Map<String, Object> extractRequestParam(HttpServletRequest req) {
        Map<String, Object> requestParam = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(key -> {
            requestParam.put(key, req.getParameter(key));
        });

        return requestParam;
    }

    private MyView viewResolver(String path) {

        final String prefixPath = "/WEB-INF/views/";
        final String surfixPath = ".jsp";

        return new MyView(prefixPath + path + surfixPath);
    }


}
