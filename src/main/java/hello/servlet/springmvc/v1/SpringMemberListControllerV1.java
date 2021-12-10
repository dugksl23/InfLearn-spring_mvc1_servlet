package hello.servlet.springmvc.v1;

import hello.servlet.domain.Member;
import hello.servlet.domain.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
public class SpringMemberListControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members")
    public ModelAndView memberList(HttpServletRequest req, HttpServletResponse resp, ModelAndView model){


        List<Member> all = memberRepository.findAll();
        model.addObject("members", all);
        model.setViewName("listView");
        return model;

    }

}
