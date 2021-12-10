package hello.servlet.springmvc.v2;


import hello.servlet.domain.Member;
import hello.servlet.domain.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMvcControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();


    @RequestMapping("/new-form")
    public ModelAndView process(){
        System.out.println("controller annotation come");
        return new ModelAndView("formView");
    };

    @RequestMapping
    public ModelAndView memberList(HttpServletRequest req, HttpServletResponse resp, ModelAndView model){


        List<Member> all = memberRepository.findAll();
        model.addObject("members", all);
        model.setViewName("listView");
        return model;

    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest req, HttpServletResponse resp, ModelAndView model){

        String username = req.getParameter("username");
        long age = Long.parseLong(req.getParameter("age"));


        Member save = memberRepository.save(new Member(username, age));
        model.addObject("member", save);
        model.setViewName("saveView");

        return model;

    }
}
