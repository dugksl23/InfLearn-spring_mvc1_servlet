package hello.servlet.frontController.v1.controller;

import hello.servlet.domain.MemberRepository;
import hello.servlet.frontController.v1.ControllerV1;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MemberListControllerv1 implements ControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        log.info("list ");
        String viewPath = "/WEB-INF/views/listView.jsp";
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewPath);
        requestDispatcher.forward(req, res);
    }
}
