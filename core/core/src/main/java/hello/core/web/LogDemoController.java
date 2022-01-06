package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService longDemoService;
    private final Provider<MyLogger> myLoggerProvider;
    //private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        MyLogger myLogger = myLoggerProvider.get();
        myLogger.setRequestURL(requestURI);

        myLogger.log("controller test");
        longDemoService.logic("testId");
        return "OK";
    }
}
