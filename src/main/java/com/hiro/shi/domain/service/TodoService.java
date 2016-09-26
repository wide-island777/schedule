package com.hiro.shi.domain.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiro.shi.domain.model.Todo;
import com.hiro.shi.domain.repository.TodoRepository;

@Service
@Transactional
public class TodoService {

	private static Logger logger = LoggerFactory.getLogger(TodoService.class);

	@Autowired
	TodoRepository todoRepo;

	public Todo save(Todo todo) {
		logger.info("名前: " + todo.getName() + "場所: " + todo.getPlace());
		return todoRepo.save(todo);
	}

	public List<Todo> findAllTodo() {
		return todoRepo.findAllTodo();
	}

	public void delete(Todo todo) {
		todoRepo.delete(todo);
	}

	public List<Todo> searchTodo(Todo todo) {
		return todoRepo.searchTodo(todo);
	}
}
