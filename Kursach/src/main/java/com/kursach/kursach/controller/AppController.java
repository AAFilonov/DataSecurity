package com.kursach.kursach.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.TreeMap;

@Controller
public class AppController {

    @RequestMapping(value = "/")
    public String index(Model model,
                        @Value("${server.port}") String port,
                        @Value("${server.host}") String host
    ) throws JsonProcessingException {
        Map<String, String> properties = new TreeMap<>();
        properties.put("port", port);
        properties.put("host", host);
        model.addAttribute("appProperties", properties);
        return "index";
    }
}
