package hello.core.web;

import hello.core.common.MyLogger;
import hello.core.common.MyLoggerProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogProxyDemoController {

    private final LogProxyDemoService longDemoService;
    private final MyLoggerProxy myLogger; // 싱글톤처럼 보이지만 Proxy
    //private final MyLogger myLogger;

    @RequestMapping("log-demo-proxy")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURI = request.getRequestURI().toString();

        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURI);

        myLogger.log("controller test");
        longDemoService.logic("testId");
        return "OK";
    }
}
