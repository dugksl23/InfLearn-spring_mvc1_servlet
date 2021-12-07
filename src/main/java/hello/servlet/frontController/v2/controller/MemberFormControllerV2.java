package hello.servlet.frontController.v2.controller;

import hello.servlet.domain.MemberRepository;
import hello.servlet.frontController.MyView;
import hello.servlet.frontController.v2.Controllerv2;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class MemberFormControllerV2 implements Controllerv2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return new MyView("/WEB-INF/views/formView.jsp");
    }
}
