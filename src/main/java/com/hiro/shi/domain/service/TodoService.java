package com.hiro.shi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiro.shi.domain.model.Todo;
import com.hiro.shi.domain.repository.TodoRepository;

@Service
@Transactional
public class TodoService {

	@Autowired
	TodoRepository todoRepo;

	public Todo save(Todo todo) {
		return todoRepo.save(todo);
	}
	
	public List<Todo> findAllTodo() {
		return todoRepo.findAllTodo();
	}
}
