package com.DDIS.personalTodo.Command.domain.repository;


import com.DDIS.personalTodo.Command.domain.aggregate.PersonalTodoDate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PersonalTodoDateRepository extends JpaRepository<PersonalTodoDate, Long> {

    void deleteAllByTodoNum(Long todoNum);
}
