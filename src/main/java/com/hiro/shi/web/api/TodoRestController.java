package com.hiro.shi.web.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hiro.shi.domain.model.Todo;
import com.hiro.shi.domain.service.TodoService;

@RestController
@RequestMapping(value = "/api/todo")
public class TodoRestController {

	@Autowired
	TodoService todoService;

	private static Logger logger = LoggerFactory.getLogger(TodoRestController.class);

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTodo(@PathVariable("id") String id) {
		Todo todo = new Todo();
		todo.setId(Integer.parseInt(id));
		todoService.delete(todo);
		logger.info("delete: " + id);
	}
}
