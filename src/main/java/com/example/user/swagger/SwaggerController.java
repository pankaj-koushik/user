package com.example.user.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {
    @RequestMapping(value = "/")
    public String index() {
        return "redirect:swagger-ui.html";
    }
}
