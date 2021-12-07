package hello.servlet;

import domain.Member;
import domain.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemberRepositoryTest {


    private MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void clear(){
        memberRepository.cleanRepository();
    }

    @Test
    void saveMember(){

        // given...
        Member member = new Member("dd", 30L);

        // when...
        Member save = memberRepository.save(member);

        // then...
        Assertions.assertThat(member).isEqualTo(memberRepository.findId(save.getId()));
    }

    @Test
    void findAll(){

        // given...
        Member member1 = new Member("dd", 30L);
        Member member2 = new Member("dd", 40L);
        memberRepository.save(member1);
        memberRepository.save(member2);

        // when...
        List<Member> all = memberRepository.findAll();

        // then...
        Assertions.assertThat(all).contains(member1, member2);
    }


}
