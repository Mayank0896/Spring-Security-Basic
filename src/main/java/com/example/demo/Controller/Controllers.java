package com.example.demo.Controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Util.TestRequestBody;

@RestController
public class Controllers {
    private static final Logger logger = LoggerFactory.getLogger(Controllers.class);

    @GetMapping("/")
    public String firstController() {
        logger.info("This is test Controller 1 method !!");
        return "This is test Controller 1";
    }

    @GetMapping("/unAuthGet")
    public String secondController() {
        logger.info("This is test Controller 2 method !!");
        return "This is test Controller 2";
    }

    @GetMapping("/authGet")
    public String thirdController() {
        logger.info("This is test Controller 3 method !!");
        return "This is test Controller 3";
    }
    
    @PostMapping("/authPost")
    public String fourthController(@RequestBody TestRequestBody requestBody) {
        logger.info(requestBody.toString());
        return "This is test Controller 4";
    }
}
