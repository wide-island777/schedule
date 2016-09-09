package com.hiro.shi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hiro.shi.domain.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

	@Query("select t from Todo t")
	public List<Todo> findAllTodo();
}
