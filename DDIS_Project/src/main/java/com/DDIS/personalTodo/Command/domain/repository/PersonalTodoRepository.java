package com.DDIS.personalTodo.Command.domain.repository;

import com.DDIS.personalTodo.Command.domain.aggregate.PersonalTodos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalTodoRepository extends JpaRepository<PersonalTodos, Long> {

    List<PersonalTodos> findAllByPersonalCategoryNum(Long personalCategoryNum);
}
