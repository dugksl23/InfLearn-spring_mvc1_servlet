package hello.servlet.frontController.v3.controller;

import hello.servlet.domain.Member;
import hello.servlet.domain.MemberRepository;
import hello.servlet.frontController.v3.ControllerV3;
import hello.servlet.frontController.v3.ModelView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, Object> paramMap) {

        List<Member> all = memberRepository.findAll();
        ModelView listView = ModelView.builder().viewName("listView").model(new HashMap<>()).build();
        listView.getModel().put("members", all);
        List<Member> members = (List<Member>) listView.getModel().get("members");
        members.stream().forEach(member -> System.out.println(member.getUsername()));

        return listView;
    }
}
