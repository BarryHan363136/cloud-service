package com.barry.cloud.platform.security.jwt.service.impl;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/22 22:46
 */
@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repository;

    @Autowired
    MongoTodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Todo addTodo(Todo todo) {
        return repository.insert(todo);
    }

    @Override
    public Todo deleteTodo(String id) {
        Todo deletedTodo = repository.findOne(id);
        repository.delete(id);
        return deletedTodo;
    }

    @Override
    public List<Todo> findAll(String username) {
        return repository.findByUserUsername(username);
    }

    @Override
    public Todo findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public Todo update(Todo todo) {
        repository.save(todo);
        return todo;
    }

}
