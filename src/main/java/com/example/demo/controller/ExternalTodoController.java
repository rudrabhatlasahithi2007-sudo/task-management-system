package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ExternalTodo;
import com.example.demo.service.ExternalTodoService;

@RestController
@RequestMapping("/api/external")
public class ExternalTodoController {

    private final ExternalTodoService service;

    public ExternalTodoController(ExternalTodoService service) {
        this.service = service;
    }

    @GetMapping("/todos/{id}")
    public ExternalTodo getTodo(@PathVariable int id) {

        return service.getTodo(id);
    }
}