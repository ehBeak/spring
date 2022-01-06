package hello.core.web;

import hello.core.common.MyLogger;
import hello.core.common.MyLoggerProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LogProxyDemoService {

    private final MyLoggerProxy myLogger;

    public void logic(String testId) {
        myLogger.log("service id = " + testId);
    }
}
