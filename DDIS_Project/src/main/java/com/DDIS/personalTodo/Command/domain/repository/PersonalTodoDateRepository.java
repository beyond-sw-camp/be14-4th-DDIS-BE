package com.DDIS.personalTodo.Command.domain.repository;


import com.DDIS.personalTodo.Command.domain.aggregate.PersonalTodoDate;
import com.DDIS.personalTodo.Command.domain.aggregate.PersonalTodoDateId;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonalTodoDateRepository extends JpaRepository<PersonalTodoDate, PersonalTodoDateId> {
    @Query("SELECT d FROM PersonalTodoDate d WHERE d.id.todoNum = :todoNum")
    Optional<PersonalTodoDate> findFirstByTodoNum(@Param("todoNum") Long todoNum);

    @Query("SELECT MAX(d.pinOrder) FROM PersonalTodoDate d JOIN PersonalTodos t ON d.id.todoNum = t.todoNum WHERE t.clientNum = :clientNum")
    Integer findMaxPinOrderByClientNum(@Param("clientNum") Long clientNum);

    @Modifying
    @Transactional
    @Query("DELETE FROM PersonalTodoDate d WHERE d.id.todoNum = :todoNum")
    void deleteAllByTodoNum(@Param("todoNum") Long todoNum);
}
