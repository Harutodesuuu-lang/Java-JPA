package co.istad.suiii.fswd.sbapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewHtmlController {
    @GetMapping("/view.html")
    public String getViewHtml(){
        return "index";
    }
}
