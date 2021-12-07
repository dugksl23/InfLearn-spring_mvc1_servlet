package hello.servlet.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Member {

    private Long id;
    private String username;
    private Long age;

    public Member(String username, Long age) {
        this.username = username;
        this.age = age;
    }
}

