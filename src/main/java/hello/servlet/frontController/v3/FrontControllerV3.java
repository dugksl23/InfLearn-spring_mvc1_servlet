package hello.servlet.frontController.v3;

import hello.servlet.frontController.MyView;
import hello.servlet.frontController.v3.controller.MemberFormControllerV3;
import hello.servlet.frontController.v3.controller.MemberListControllerV3;
import hello.servlet.frontController.v3.controller.MemberSaveControllerV3;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "FrontControllerV3", urlPatterns = "/frontController/v3/*")
public class FrontControllerV3 extends HttpServlet {

    private final Map<String, ControllerV3> controllerMap = new HashMap<>();

    @PostConstruct
    public void init() {
        controllerMap.put("/frontController/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/frontController/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/frontController/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String prefixPath = "/WEB-INF/views/";
        final String surfixPath = ".jsp";


        String requestURI = req.getRequestURI();
        boolean b = controllerMap.containsKey(requestURI);
        if (b == false) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("not found");
        }

        Map<String, Object> requestParam = createRequestParam(req);
        ControllerV3 controllerV3 = controllerMap.get(requestURI);
        ModelView mv = controllerV3.process(requestParam);
        MyView myView = viewResolver(prefixPath, surfixPath, mv);
        myView.render(mv.getModel(), req, resp);
        // view가 rendering이 되려면 model 객체가 필요하기에 함께 넘긴다.
        // 단일 요청도 modelAndView에서 member로 저장
        // / findall() 관련해서도 member로 해서 list로 저장하며, 이 구조가 HashMap 구조이다.

    }

    private MyView viewResolver(String prefixPath, String surfixPath, ModelView mv) {

        return new MyView(prefixPath + mv.getViewName() + surfixPath);
    }

    private Map<String, Object> createRequestParam(HttpServletRequest req) {
        HashMap<String, Object> paramHashMap = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(param -> paramHashMap.put(param, req.getParameter(param)));

        return paramHashMap;
    }


//    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        HttpServletRequest processedRequest = request;
//        HandlerExecutionChain mappedHandler = null;
//        ModelAndView mv = null;
//        // 1. 핸들러 조회
//        mappedHandler = getHandler(processedRequest);
//        if (mappedHandler == null) {
//
//            noHandlerFound(processedRequest, response);
//            return;
//        }
//        //2.핸들러 어댑터 조회-핸들러를 처리할 수 있는 어댑터
//        HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());

//        // 3. 핸들러 어댑터 실행 -> 4. 핸들러 어댑터를 통해 핸들러 실행 -> 5. ModelAndView 반환 mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
//        processDispatchResult(processedRequest, response, mappedHandler, mv,
//                dispatchException);
//    }
//
//    private void processDispatchResult(HttpServletRequest request,
//                                       HttpServletResponse response, HandlerExecutionChain mappedHandler, ModelAndView
//                                               mv, Exception exception) throws Exception {
//        // 뷰 렌더링 호출
//        render(mv, request, response);
//    }
//
//    protected void render(ModelAndView mv, HttpServletRequest request,
//                          HttpServletResponse response) throws Exception {
//        View view;
//        String viewName = mv.getViewName(); //6. 뷰 리졸버를 통해서 뷰 찾기,7.View 반환
//        view = resolveViewName(viewName, mv.getModelInternal(), locale, request);
//        // 8. 뷰 렌더링
//        view.render(mv.getModelInternal(), request, response);
//    }
}
