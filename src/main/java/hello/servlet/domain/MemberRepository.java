package hello.servlet.domain;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberRepository {

    private static final MemberRepository store = new MemberRepository();
    private static Long memberSequence = 0L;
    private static final ConcurrentHashMap<Long, Member> repository = new ConcurrentHashMap<Long, Member>();

    public static final MemberRepository getInstance() {
        return store;
    }




    public Member save(Member member) {

        member.setId(++memberSequence);
        repository.put(memberSequence, member);
        return repository.get(member.getId());

    }

    public Member findId(Long id) {

        Member member1 = repository.get(id);
        return member1;
    }

    public List<Member> findAll() {
        List<Member> list = new ArrayList<>();

        Collection<Member> values = repository.values();
        list.addAll(values);
        return list;
    }


    public void cleanRepository(){
        repository.clear();
    }
}
