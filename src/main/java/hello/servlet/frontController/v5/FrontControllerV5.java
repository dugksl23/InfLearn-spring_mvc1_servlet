package hello.servlet.frontController.v5;

import hello.servlet.frontController.MyView;
import hello.servlet.frontController.v3.ModelView;
import hello.servlet.frontController.v3.controller.MemberFormControllerV3;
import hello.servlet.frontController.v3.controller.MemberListControllerV3;
import hello.servlet.frontController.v3.controller.MemberSaveControllerV3;
import hello.servlet.frontController.v5.handlerAdopterList.ControllerV3HandlerAdopter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "frontControllerV5", urlPatterns = "/frontController/v5/*")
@Slf4j
public class FrontControllerV5 extends HttpServlet {


    // 1. 요청된 url이 어떤 controller(구현체)인지를 판별한다.
    // 2. 요청된 uri의 구현체를 식별하기 위해서 HashMap으로 구현체들별 url과 구현체를 key value로 담는다.
    // 3,

    private Map<String, Object> handlerMappingMap = new HashMap<>(); // 요청 url을 handler에 매핑하기 위해서 해당 interface 구현체 정보(v1~5)를 담는다.
    private List<HandlerAdopter> handlerAdopterList = new ArrayList<>(); // 요청된 url에 따른 interface HandlerAdopter 들을 담는다.

    @PostConstruct
    public void initHandlerMappingMap() { // controller interface의 HandlerAdopter를 HashMap 담는다.
        handlerMappingMap.put("/frontController/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/frontController/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/frontController/v5/v3/members", new MemberListControllerV3());
    }

    @PostConstruct
    public void initHandlerAdoptersList() { //요청된 Url를 처리해줄 frontController들 list에 담는다.
        handlerAdopterList.add(new ControllerV3HandlerAdopter());
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("=== 요청된 url에 매핑할 구현체를 조회 start ========"); //핸들러 매핑 정보 조회
        Object handler = getHandler(req.getRequestURI());// 요청 url에 따른 구현체(controller)를 식별 -> 핸들러 매핑 정보 조회
        if (ObjectUtils.isEmpty(handler)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("not found");
        }
        log.info("======= 요청된 url에 매핑할 구현체를 조회하는 것은 end =============");

        log.info("======= 구현체의 상위 인터페이스 일치여부 조회 start =========="); // 핸들러 어댑터 목록 조회, 핸들러(구현체)를 처리할 수 있는 어답터(상위인터페이스) 목록을 조회
        Optional<HandlerAdopter> handlerAdopter = getHandlerAdopter(handler);
        if (handlerAdopter.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("not found");

        } else if (handlerAdopter.isPresent()) {
            log.info("===== 구현체의 상위 인터페이스 일치여부 조회 완료 =============");

            log.info("===== handler adopter 를 통해 구현체(controller or handler) 실행 start ============="); //핸들러 어댑터를 통해 구현체(핸들러) 호출 및 실행
            ModelView mv = handlerAdopter.get().handle(req, resp, handler);
            log.info("===== handler adopter 를 통해 구현체 실행 end =============");

            MyView myView = viewResolver(mv);
            myView.render(mv.getModel(), req, resp);

        }

    }

    private Optional<HandlerAdopter> getHandlerAdopter(Object handler) {
        return handlerAdopterList.stream().filter(handlerAdopter -> handlerAdopter.support(handler)).findFirst();
    }

    private Object getHandler(String path) {
        return handlerMappingMap.get(path);
    }

    private MyView viewResolver(ModelView mv) {

        final String prefixPath = "/WEB-INF/views/";
        final String surfixPath = ".jsp";
        return new MyView(prefixPath + mv.getViewName() + surfixPath);
    }
}
