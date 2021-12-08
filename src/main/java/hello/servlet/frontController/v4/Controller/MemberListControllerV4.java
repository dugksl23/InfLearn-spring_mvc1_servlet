package hello.servlet.frontController.v4.Controller;

import hello.servlet.domain.Member;
import hello.servlet.domain.MemberRepository;
import hello.servlet.frontController.v3.ControllerV3;
import hello.servlet.frontController.v3.ModelView;
import hello.servlet.frontController.v4.ControllerV4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, Object> paramMap, Map<String, Object> model) {

        List<Member> all = memberRepository.findAll();
        model.put("members", all);

        return "listView";

    }


}
