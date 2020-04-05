package org.meeting.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/video")
    public String video() {
        return "video";
    }
}





