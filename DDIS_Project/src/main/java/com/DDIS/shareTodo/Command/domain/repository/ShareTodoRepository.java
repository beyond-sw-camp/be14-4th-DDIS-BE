package com.DDIS.shareTodo.Command.domain.repository;

import com.DDIS.shareTodo.Command.domain.aggregate.Entity.ShareTodo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShareTodoRepository extends JpaRepository<ShareTodo, Long> {

    List<ShareTodo> findByRooms_RoomNum(Long roomNum);
}
