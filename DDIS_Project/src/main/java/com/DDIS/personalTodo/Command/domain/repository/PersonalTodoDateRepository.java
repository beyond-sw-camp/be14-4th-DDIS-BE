package com.DDIS.personalTodo.Command.domain.repository;


import com.DDIS.personalTodo.Command.domain.aggregate.PersonalTodoDate;
import com.DDIS.personalTodo.Command.domain.aggregate.PersonalTodoDateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalTodoDateRepository extends JpaRepository<PersonalTodoDate, PersonalTodoDateId> {
    void deleteAllById_TodoNum(Long todoNum);
}
