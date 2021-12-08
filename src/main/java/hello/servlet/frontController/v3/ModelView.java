package hello.servlet.frontController.v3;

import hello.servlet.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.HashMap;


@Getter
@Setter
@Builder
// builder 패턴일 경우에는 builder 하는 데에서 값을 채워줘서 new를 하기에 반드시 해당 값을 사용되는 곳이 있다면, 꼭 넣어줘야 한다.
public class ModelView {

    private String viewName; // viewResolver 반환용
    private Map<String, Object> model = new HashMap<String, Object>();
    // model(service와 db 작업이 완료된 data의 집합체)를 Controller 에서 set 후
    // frontController로 반환.


}
