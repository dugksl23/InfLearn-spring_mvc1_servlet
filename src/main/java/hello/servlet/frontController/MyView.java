package hello.servlet.frontController;


import hello.servlet.domain.Member;
import hello.servlet.frontController.v3.ModelView;
import lombok.Getter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Getter
public class MyView {

    private final String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setStatus(HttpServletResponse.SC_OK);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(this.getViewPath());
        requestDispatcher.forward(req, resp);

    }

//    public void render(ModelView mv, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        modelToRequestAttribute(mv, req);// model을 다시 req에 setAttrbute에 셋팅
//        resp.setStatus(HttpServletResponse.SC_OK);
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher(this.getViewPath());
//        requestDispatcher.forward(req, resp);
//    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest req ) {


        if (model.isEmpty()) {
            return;
        }
        model.forEach((key, value) -> req.setAttribute(key, value));
    }

    public void render(Map<String, Object> model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        modelToRequestAttribute(model, req);// model을 다시 req에 setAttrbute에 셋팅
        // jsp는 request.setAttribute()에서만 사용가능 하기에 함꼐 넘겨준다.
        resp.setStatus(HttpServletResponse.SC_OK);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(this.getViewPath());
        requestDispatcher.forward(req, resp);

    }
}
