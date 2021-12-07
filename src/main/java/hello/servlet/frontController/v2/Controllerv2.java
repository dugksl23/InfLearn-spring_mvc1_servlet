package hello.servlet.frontController.v2;

import hello.servlet.frontController.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controllerv2 {
    
    MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
