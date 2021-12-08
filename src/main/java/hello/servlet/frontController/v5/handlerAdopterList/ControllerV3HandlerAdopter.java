package hello.servlet.frontController.v5.handlerAdopterList;

import hello.servlet.frontController.v3.ControllerV3;
import hello.servlet.frontController.v3.ModelView;
import hello.servlet.frontController.v5.HandlerAdopter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdopter implements HandlerAdopter {
    // v3의 요청 url의 구현체를 연결시켜주기 위한 Adopter(사용자)
    // 구현체는 다형성에 의해서 ControllerV3 하나면 하위 구현체들까지 모두 알 수 있기에..
    // 부모 interface에 담기 구현체의 주소값으로 process 실행;

    @Override
    public boolean support(Object handler) {
        return handler instanceof ControllerV3; // interfaceV3 instance가 맞는지 확인 -> true or false
    }

    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws ServletException, IOException {

        ControllerV3 controllerV3 = (ControllerV3) handler; //support에 의해 true 가 된 handler를 해당 version의 interface로 형변환

        Map<String, Object> requestParam = new HashMap<>();
        extractRequestParam(req, requestParam);
        ModelView mv = controllerV3.process(requestParam);// v3는 requestParam을 HashMap으로 넘겼고, controller 내부에서 mv를 반환했다.

        return mv;
    }

    private void extractRequestParam(HttpServletRequest req, Map<String, Object> requestParam) {
        req.getParameterNames().asIterator().forEachRemaining(key -> requestParam.put(key, req.getParameter(key)));
    }
}
