package com.hiro.shi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hiro.shi.domain.model.Todo;
import com.hiro.shi.domain.service.TodoService;

@Controller
@RequestMapping("/todo")
public class TodoController {
	
	@Autowired
	TodoService todoService;
	
	/**
	 * すべてのTodoを指定します。
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String getAllTodo(Model model) {
		List<Todo> todoList = todoService.findAllTodo();
		model.addAttribute("todoList", todoList);
		return "todo";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String saveTodo(Model model) {
		Todo todo = new Todo();
		todoService.save(todo);
		// 一覧画面を返す
		return getAllTodo(model);
	}
	
	/**
	 * ステータスが未着手(0)のTodoを指定します
	 * @return
	 */
	public String getNotStartTodo() {
		
		return "list";
	}

	/**
	 * ステータスが計画中(1)のTodoを指定します
	 * @return
	 */
	public String getPlanningTodo() {
		
		return "list";
	}

	/**
	 * ステータスが計画中(1)のTodoを指定します
	 * @return
	 */
	public String getFinishedTodo() {
		
		return "list";
	}

	/**
	 * IDで指定してTodoを取得します
	 * @return
	 */
	public String getTodo() {
		
		return "todo";
	}
	
	/**
	 * 作成者で限定してTodoを絞り込んで取得します
	 * @return
	 */
	public String getTodoLimitedCreater() {
		
		return "todo";
	}
}