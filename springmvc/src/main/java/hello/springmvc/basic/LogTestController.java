package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    private String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        // 로그 레벨 순
        log.trace(" trace log = {}", name);
        log.debug(" debug log = {}", name);// 개발 서버
        log.info("  info log = {}", name);// 운영 서버
        log.warn(" warn log = {}", name);
        log.error(" error log = {}", name);

        return "ok";
    }
}
