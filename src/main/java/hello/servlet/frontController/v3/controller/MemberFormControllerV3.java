package hello.servlet.frontController.v3.controller;

import hello.servlet.domain.MemberRepository;
import hello.servlet.frontController.v3.ControllerV3;
import hello.servlet.frontController.v3.ModelView;

import java.util.*;

public class MemberFormControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, Object> paramMap) {
        return ModelView.builder().viewName("formView").model(new HashMap<>()).build();
    }
}
