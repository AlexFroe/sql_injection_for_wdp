package at.froehlich.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping(path = "/")
    public String getIndex(){
        return "index";
    }

}
