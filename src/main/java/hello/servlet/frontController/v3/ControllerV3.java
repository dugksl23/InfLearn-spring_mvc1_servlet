package hello.servlet.frontController.v3;


import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, Object> paramMap);


}
