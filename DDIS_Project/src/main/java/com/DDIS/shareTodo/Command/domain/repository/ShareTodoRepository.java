package com.DDIS.shareTodo.Command.domain.repository;

import com.DDIS.shareTodo.Command.domain.aggregate.entity.ShareTodo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShareTodoRepository extends JpaRepository<ShareTodo, Long> {
    // 특정 모집 게시글(post_num)과 연결된 ToDo 리스트 조회
    List<ShareTodo> findByPostNum(Long postNum);

}
