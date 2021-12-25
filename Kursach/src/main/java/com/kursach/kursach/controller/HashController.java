package com.kursach.kursach.controller;

import com.kursach.kursach.dto.HashRequestDto;
import com.kursach.kursach.service.HashService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(HashController.ROOT)
public class HashController {
    final static String ROOT = "/api/hash";
    private final HashService hashService;

    public HashController(HashService hashService) {
        this.hashService = hashService;
    }

    @PostMapping
    public String post(@RequestBody HashRequestDto req) {
        return hashService.calcHash(req.getData());
    }
    //TODO подключить логирование
}
