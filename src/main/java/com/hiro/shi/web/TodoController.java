package com.hiro.shi.web;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hiro.shi.domain.model.Todo;
import com.hiro.shi.domain.service.TodoService;

@Controller
@RequestMapping("/todo")
public class TodoController {

	@Autowired
	TodoService todoService;

	private static Logger logger = LoggerFactory.getLogger(TodoController.class);

	final static Map<String, String> STATUS_ITEMS = Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
		{
			put("未計画", "0");
			put("計画中", "1");
			put("達成済", "2");
		}
	});

	/**
	 * すべてのTodoを指定します。
	 * 
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String getAllTodo(Model model) {
		List<Todo> todoList = todoService.findAllTodo();
		model.addAttribute("statusItems", STATUS_ITEMS);
		model.addAttribute("todoForm", new Todo());
		model.addAttribute("todoList", todoList);
		return "todo";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String saveTodo(Model model, @ModelAttribute("todoForm") @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:list";
		}
		todoService.save(todo);
		// 一覧画面を返す
		return "redirect:list";
	}
	
	@RequestMapping(value = "find", method = RequestMethod.GET)
	public String findTodo(Model model, @ModelAttribute("") Todo findParam, BindingResult result) {
		
		
		return "redirect:list";
	}

	/**
	 * ステータスが未着手(0)のTodoを指定します
	 * 
	 * @return
	 */
	public String getNotStartTodo() {

		return "list";
	}

	/**
	 * ステータスが計画中(1)のTodoを指定します
	 * 
	 * @return
	 */
	public String getPlanningTodo() {

		return "list";
	}

	/**
	 * ステータスが計画中(1)のTodoを指定します
	 * 
	 * @return
	 */
	public String getFinishedTodo() {

		return "list";
	}

	/**
	 * IDで指定してTodoを取得します
	 * 
	 * @return
	 */
	public String getTodo() {

		return "todo";
	}

	/**
	 * 作成者で限定してTodoを絞り込んで取得します
	 * 
	 * @return
	 */
	public String getTodoLimitedCreater() {

		return "todo";
	}
}
