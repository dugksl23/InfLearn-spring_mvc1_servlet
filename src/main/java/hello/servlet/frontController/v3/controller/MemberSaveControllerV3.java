package hello.servlet.frontController.v3.controller;

import hello.servlet.domain.Member;
import hello.servlet.domain.MemberRepository;
import hello.servlet.frontController.v3.ControllerV3;
import hello.servlet.frontController.v3.ModelView;

import java.util.HashMap;
import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    public ModelView process(Map<String, Object> paramMap) {

        Member m = new Member((String) paramMap.get("username"), Long.parseLong((String) paramMap.get("age")));
        Member save = memberRepository.save(m);
        ModelView save1 = ModelView.builder().viewName("saveView").model(new HashMap<>()).build();
        save1.getModel().put("member", save);

        return save1;
    }
}
