package hello.servlet.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Component("/springmvc/old-controller")
public class OldController implements Controller {

    /**
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     *
     * Object handler = getHandler(request.url);
     * HandlerAdopter handler = handlerAdopter.handler(req, resp, handler) 와 동일
     *
     */
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("formView");
    }
}
