package hello.servlet.frontController;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Getter
public class MyView {

    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setStatus(HttpServletResponse.SC_OK);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(this.getViewPath());
        requestDispatcher.forward(req, resp);

    }

}
