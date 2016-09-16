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
import org.springframework.web.bind.annotation.RequestParam;

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
		model.addAttribute("todoEntry", new Todo());
		model.addAttribute("todoFind", new Todo());
		model.addAttribute("todoList", todoList);
		return "todo";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String saveTodo(Model model, @ModelAttribute("todoEntry") @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:list";
		}
		todoService.save(todo);
		// 一覧画面を返す
		return "redirect:list";
	}

	@RequestMapping(value = "delete", method = RequestMethod.DELETE)
	public String deleteTodo(@RequestParam("id") String id) {
		Todo todo = new Todo();
		todo.setId(Integer.parseInt(id));
		todoService.delete(todo);
		return "redirect:list";
	}

	@RequestMapping(value = "find", method = RequestMethod.GET)
	public String findTodo(Model model, @ModelAttribute("todoFind") @Valid Todo findParam, BindingResult result) {
		List<Todo> searchResult = todoService.searchTodo(findParam);
		model.addAttribute("statusItems", STATUS_ITEMS);
		model.addAttribute("todoEntry", new Todo());
		model.addAttribute("todoFind", new Todo());
		model.addAttribute("todoList", searchResult);
		return "todo";
	}
}
