package com.DDIS.personalTodo.Command.domain.repository;

import com.DDIS.personalTodo.Command.domain.aggregate.PersonalTodos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalTodoRepository extends JpaRepository<PersonalTodos, Long> {
    List<PersonalTodos> findAllByPersonalCategoryNum(Long personalCategoryNum);
}
