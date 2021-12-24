package com.kursach.kursach.controller;
import com.kursach.kursach.service.HashService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/hash")
public class HashController {
   private final  HashService hashService;

    public HashController(HashService hashService) {
        this.hashService = hashService;
    }


    @PostMapping
    public String post(@RequestBody String req) {
        return  hashService.calcHash(req);
    }
    //TODO подключить логирование
}