package hello.servlet.frontController.v5;

import hello.servlet.frontController.MyView;
import hello.servlet.frontController.v3.ModelView;
import hello.servlet.frontController.v3.controller.MemberFormControllerV3;
import hello.servlet.frontController.v3.controller.MemberListControllerV3;
import hello.servlet.frontController.v3.controller.MemberSaveControllerV3;
import hello.servlet.frontController.v5.handlerAdopterList.ControllerV3HandlerAdopter;
import hello.servlet.frontController.v5.handlerAdopterList.ControllerV4HandlerAdopter;
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
public class  FrontControllerV5 extends HttpServlet {


    // 1. 요청된 url이 어떤 controller(구현체)인지를 판별한다.
    // 2. 요청된 uri의 구현체를 식별하기 위해서 HashMap으로 구현체들별 url과 구현체를 key value로 담는다.
    // 3,

    private Map<String, Object> handlerMappingMap = new HashMap<>(); // 요청 url을 handler에 매핑하기 위해서 해당 interface 구현체 정보(v1~5)를 담는다.
    private List<HandlerAdopter> handlerAdopterList = new ArrayList<>(); // 요청된 url에 따른 interface HandlerAdopter 들을 담는다.


    /**
     * 요청 url에 따른 구현체 controller 들을 매핑하기 위해서 담는 HashMap
     */
    @PostConstruct
    public void initHandlerMappingMap() {
        // v3
        handlerMappingMap.put("/frontController/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/frontController/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/frontController/v5/v3/members", new MemberListControllerV3());

        // v4
        handlerMappingMap.put("/frontController/v5/v4/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/frontController/v5/v4/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/frontController/v5/v4/members", new MemberListControllerV3());

    }

    /**
     * 요청된 Url를 처리해줄 adopter 를 frontController 에 담는 list
     * - 요청 url 을 해당 frontController 에서 처리할 adopter들만 담는다.
     */
    @PostConstruct
    public void initHandlerAdoptersList() {
        handlerAdopterList.add(new ControllerV3HandlerAdopter());
        handlerAdopterList.add(new ControllerV4HandlerAdopter());
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /**
         * getHandler()
         * 핸들러 매핑 정보 조회
         * - 요청 url에 따른 구현체(controller)를 식별 -> 핸들러 매핑 정보 조회
         * @return Object object (요청 url을 처리할 구현체 handler)
         */
        log.info("=== 요청된 url에 매핑할 구현체를 조회 start ========");
        Object handler = getHandler(req.getRequestURI());
        if (ObjectUtils.isEmpty(handler)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("not found");
        }
        log.info("======= 요청된 url에 매핑할 구현체를 조회하는 것은 end =============");


        /**
         * support()
         * 핸들러 어댑터 목록 조회
         * - 요청 url을 처리할 수 있는 구현체의 adopter 목록을 조회
         * @return HandlerAdopter handlerAdopter (구현체 adopter의 interface, 다형성)
         */
        log.info("======= 구현체의 상위 인터페이스 일치여부 조회 start ==========");
        Optional<HandlerAdopter> handlerAdopter = getHandlerAdopter(handler);
        if (handlerAdopter.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("not found");
        }
        log.info("===== 구현체의 상위 인터페이스 일치여부 조회 완료 =============");

        /**
         *  handler()
         *  핸들러 어댑터를 통해 구현체를 핸들링
         *   - controller를 대신해서 adopter 에서 해당 controller를 호출 및 실행
         * @return ModelView mv (viwPath 및 model을 내장)
         */
        if (handlerAdopter.isPresent()) {

            log.info("===== handler adopter 를 통해 구현체(controller or handler) 실행 start =============");
            ModelView mv = handlerAdopter.get().handle(req, resp, handler);
            log.info("===== handler adopter 를 통해 구현체 실행 end =============");

            MyView myView = viewResolver(mv);
            myView.render(mv.getModel(), req, resp);

        }

    }

    /**
     *
     * @param handler
     * @return handler의 구현체
     */
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
