package hello.servlet.frontController.v5.handlerAdopterList;

import hello.servlet.frontController.v3.ModelView;
import hello.servlet.frontController.v4.ControllerV4;
import hello.servlet.frontController.v5.HandlerAdopter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdopter implements HandlerAdopter {

    /**
     * @param handler
     * @return ControllerV4 interface
     * <p>
     * 다형성에 의해 구현체들의 주소값을 가진 부모 interface 가 return 이 된다.
     */
    @Override
    public boolean support(Object handler) {
        return handler instanceof ControllerV4;
    }

    /**
     * @param req
     * @param resp
     * @param handler
     * @return ModelAndView mv
     * @throws ServletException
     * @throws IOException      핵심로직을 처리한 후에 model과 viewPath 반환
     */
    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws ServletException, IOException {

        var controller = (ControllerV4) handler;

        Map<String, Object> requestParameter = new HashMap<>();
        Map<String, Object> model = new HashMap<>();
        extreactRequestParam(req, requestParameter);

        String process = controller.process(requestParameter, model);
        ModelView mv = ModelView.builder().model(model).viewName(process).build();

        return mv;
    }

    private void extreactRequestParam(HttpServletRequest req, Map<String, Object> requestParameter) {
        req.getParameterNames().asIterator().forEachRemaining(key -> requestParameter.put(key, req.getParameter(key)));
    }
}
