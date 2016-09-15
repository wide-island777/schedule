package com.hiro.shi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hiro.shi.domain.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

	@Query("select t from Todo t")
	public List<Todo> findAllTodo();

	@Query("select t from Todo t where t.name like %:#{#todo.name}% and t.place like %:#{#todo.place}% and t.status = :#{#todo.status}")
	public List<Todo> searchTodo(@Param("todo") Todo todo);

}
