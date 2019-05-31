package edu.neu.medical.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("test")
public class TestController {
    @RequestMapping("/t1")
    public String m1() {
        return "ttt";
    }
}
