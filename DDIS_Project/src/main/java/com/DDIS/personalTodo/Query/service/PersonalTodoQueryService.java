package com.DDIS.personalTodo.Query.service;

import com.DDIS.personalTodo.Query.dto.PersonalTodoQueryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PersonalTodoQueryService {
    // 1. 전체 조회
    List<PersonalTodoQueryDTO> getAllTodos(Long clientNum);

    // 2. 날짜별 조회
    List<PersonalTodoQueryDTO> getTodosByDate(Long clientNum, String todoDate);

    // 3. 카테고리별 조회
    List<PersonalTodoQueryDTO> getTodosByCategory(Long clientNum, Long categoryNum);

    // 4. 핀고정 조회
    List<PersonalTodoQueryDTO> getPinnedTodos(Long clientNum);

    // 5, 6번 공개/비공개 TODO 조회
    List<PersonalTodoQueryDTO> getTodosByVisibility(Long clientNum, Boolean isPublic);

    // 7, 8번 완료/미완료 된 todo 조회
    List<PersonalTodoQueryDTO> getTodosByDoneStatus(Long clientNum, Boolean isDone);
}
