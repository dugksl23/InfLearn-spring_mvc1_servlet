package hello.servlet.frontController.v4.Controller;

import hello.servlet.domain.Member;
import hello.servlet.domain.MemberRepository;
import hello.servlet.frontController.v3.ControllerV3;
import hello.servlet.frontController.v3.ModelView;
import hello.servlet.frontController.v4.ControllerV4;

import java.util.HashMap;
import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, Object> paramMap, Map<String, Object> model) {

        Member member = memberRepository.save(new Member((String) paramMap.get("username"), Long.parseLong((String) paramMap.get("age"))));
        model.put("member", member);

        return "saveView";
    }


}
