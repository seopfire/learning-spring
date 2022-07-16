package com.example.learnunittest.controller;

import com.example.learnunittest.api.SomeApi;
import com.example.learnunittest.dto.Request;
import com.example.learnunittest.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
    private final SomeApi api;

    @GetMapping("/get")
    public String get() {
        return "hello!";
    }

    @PostMapping("/convert-dollars") // dollar -> won
    public Response convertDollars(@RequestBody Request dollar) {
        return new Response(dollar.getName(), dollar.getDollars() * api.getRate());
    }
}
