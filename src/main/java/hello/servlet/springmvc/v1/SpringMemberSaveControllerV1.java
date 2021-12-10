package hello.servlet.springmvc.v1;


import hello.servlet.domain.Member;
import hello.servlet.domain.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SpringMemberSaveControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView save(HttpServletRequest req, HttpServletResponse resp, ModelAndView model){

        String username = req.getParameter("username");
        long age = Long.parseLong(req.getParameter("age"));


        Member save = memberRepository.save(new Member(username, age));
        model.addObject("member", save);
        model.setViewName("saveView");

        return model;

    }



}
