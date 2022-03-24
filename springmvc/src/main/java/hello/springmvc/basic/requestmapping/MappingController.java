package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class MappingController {

    @RequestMapping(value = {"/hello-basic", "/hello-go"}, method = RequestMethod.GET)
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    // 스프링에서는 /spring과 /spring/ 둘 다 허용한다.

    @RequestMapping(value = {"/mapping-get-v1"}, method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    @GetMapping("/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "ok";
    }


    // PathVariable
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable("userId") String data,
                              @PathVariable("orderId") Long orderId) {
        log.info("mappingPath userId={}, orderId={}", data, orderId);
        return "ok";
    }

    // 쿼리 파라미터
    @GetMapping("/mapping")
    public String queryParam(@RequestParam("data") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    // 파라미터 추가 매핑 -> /mapping-param?mode=debug 일때만 ok
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    // 헤더의 값이 mode=debug가 있어야한다.
    @GetMapping(value = "/mapping-header", headers = "mode-debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    // Content-Type - 서버 입장에서 소비(요청)
//    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }


    // Content-Type - 서버 입장에서 생산(응답)
//    @PostMapping(value = "/mapping-produce", produces = "text/html")
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
