package co.istad.suiii.fswd.sbapp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/public")
public class TestController {
    @GetMapping
    public Map<String, String> test(){
       return Map.of("message","this is end point");
    }
}
