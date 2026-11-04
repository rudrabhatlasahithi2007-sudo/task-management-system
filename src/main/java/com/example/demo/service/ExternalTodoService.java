package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.ExternalTodo;

@Service
public class ExternalTodoService {

    private final RestTemplate restTemplate = new RestTemplate();

    public ExternalTodo getTodo(int id) {

        String url = "https://dummyjson.com/todos/" + id;

        return restTemplate.getForObject(url, ExternalTodo.class);
    }
}