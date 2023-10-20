package kr.co.yanadoo.image.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/lazy")
public class LazyLoadController {

    // http://localhost:8080/lazy/load
    @GetMapping(value = "/load")
    public String load() {
        log.info("load");
        return "lazyLoad";
    }
}
