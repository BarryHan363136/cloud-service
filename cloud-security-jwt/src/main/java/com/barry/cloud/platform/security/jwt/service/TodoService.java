package com.barry.cloud.platform.security.jwt.service;

import java.util.List;

public interface TodoService {
    Todo addTodo(Todo todo);
    Todo deleteTodo(String id);
    List<Todo> findAll(String username);
    Todo findById(String id);
    Todo update(Todo todo);
}
