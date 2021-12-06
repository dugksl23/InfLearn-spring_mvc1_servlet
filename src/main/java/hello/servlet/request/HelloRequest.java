package hello.servlet.request;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "helloRequest", urlPatterns = "/helloRequest")
public class HelloRequest extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp); // protected 제한자로 생성된 service를 사용해야 한다. public은 안된다.

        // === req server info ===
        printStartLine(req); // cnt + t > extractMethod > method
        printHeaders(req);
        printHeaderUtils(req);

        // === Http Api server info ===
        remoteUtils(req);


    }

    // Start line
    private void printStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + request.getMethod()); //GET
        System.out.println("request.getProtocal() = " + request.getProtocol()); //HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme()); //http
        // http://localhost:8080/request-header
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        // /request-test
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        //username=hi
        System.out.println("request.getQueryString() = " + request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure()); //https 사용 유무
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }

    //Header 모든 정보
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");

//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String s =  headerNames.nextElement();
//            System.out.println("header name : "+ request.getHeader(s));
//        }

        request.getHeaderNames().asIterator().forEachRemaining(System.out::println);
//        request.getHeaderNames().asIterator().forEachRemaining(headerName -> System.out.println(headerName));

        System.out.println("--- Headers - end ---");
        System.out.println();
    }


    private void printHeaderUtils(HttpServletRequest req) {

        System.out.println("==== header 편의 조회 Start ====");
        System.out.println("[ Req Server Host 조회 ]");
        System.out.println("server host name : " + req.getServerName()); // req server Host

        System.out.println("[ Req Server port 조회 ]");
        System.out.println("server port name : " + req.getServerPort()); // req server port

        System.out.println("[ Req Server Accept Language 조회 ]");
        req.getLocales().asIterator()
                .forEachRemaining(locale -> {
                    System.out.println("server Accept Language : " + locale); // server Accept Lanauge
                });

        System.out.println("[ Req Cookie 조회 ]");
        Arrays.stream(req.getCookies()).spliterator().forEachRemaining(cookie -> {
            System.out.println("cookie name : " + cookie);
        });

        System.out.println("[ Req Content 조회 ]");
        System.out.println("content type : " + req.getContentType());

        System.out.println("[ Req Content Length 조회 ]");
        System.out.println("content Length : " + req.getContentLength()); // unknown 이면 -1 return or length of content type

        System.out.println("[ Req Content Encoding 조회 ]");
        System.out.println("Req content Encoding : " + req.getCharacterEncoding()); // unknown 이면 -1 return or length of content type

        System.out.println("==== header 편의 조회 End ====");

    }

    private void remoteUtils(HttpServletRequest req) {

        System.out.println("==== remote header 편의 조회 Start ====");
        System.out.println("[ remote Server Host 조회 ]");
        System.out.println("remote Server Host : " + req.getRemoteHost());

        System.out.println("[ remote Server port 조회 ]");
        System.out.println("remote Server port : " + req.getRemotePort());

        System.out.println("[ remote Server Address 조회 ]");
        System.out.println("remote Server Address : " + req.getRemoteAddr());

        System.out.println("[ remote Server User 조회 ]");
        System.out.println("remote Server User : " + req.getRemoteUser());


        System.out.println("==== Req Local 편의 조회 Start ====");
        System.out.println("[ local Address 조회 ]");
        System.out.println("local Address : " + req.getLocalAddr());

        System.out.println("[ local IP 조회 ]");
        System.out.println("local IP : " + req.getLocalName());

        System.out.println("[ local port 조회 ]");
        System.out.println("local port : " + req.getLocalPort());

    }

}
