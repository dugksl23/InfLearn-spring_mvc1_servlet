package hello.servlet.springmvc.v3;

import hello.servlet.domain.Member;
import hello.servlet.domain.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMvcControllerV3 {


    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("/new-form")
    public String process(Model model) {
        System.out.println("controller annotation come");
        return "formView";
    }

    @GetMapping
    public String memberList(Model model) {


        List<Member> all = memberRepository.findAll();
        model.addAttribute("members", all);
        return "listView";
    }

    @PostMapping("/save")
    public String save(@RequestParam("username") String username, @RequestParam("age") Long age, Model model) {

        Member save = memberRepository.save(new Member(username, age));
        model.addAttribute("member", save);
        return "saveView";
    }
}


