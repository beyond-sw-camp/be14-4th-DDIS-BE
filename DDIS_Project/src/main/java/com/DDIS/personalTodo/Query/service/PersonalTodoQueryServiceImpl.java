package com.DDIS.personalTodo.Query.service;

import com.DDIS.personalTodo.Query.dto.PersonalTodoQueryDTO;
import com.DDIS.personalTodo.Query.mapper.PersonalTodoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalTodoQueryServiceImpl implements PersonalTodoQueryService{

    private final PersonalTodoMapper personalTodoMapper;

    @Override
    public List<PersonalTodoQueryDTO> getAllTodos(Long clientNum) {
        return personalTodoMapper.findAllByClientNum(clientNum);
    }

    @Override
    public List<PersonalTodoQueryDTO> getTodosByDate(Long clientNum, String todoDate) {
        return personalTodoMapper.findByClientNumAndDate(clientNum, todoDate);
    }

    @Override
    public List<PersonalTodoQueryDTO> getTodosByCategory(Long clientNum, Long categoryNum) {
        return personalTodoMapper.findTodosByClientAndCategory(clientNum, categoryNum);
    }

    @Override
    public List<PersonalTodoQueryDTO> getPinnedTodos(Long clientNum) {
        return personalTodoMapper.findPinnedTodosByClientNum(clientNum);
    }

//    @Override
//    public List<PersonalTodoQueryDTO> getPublicTodos(Long clientNum) {
//        return personalTodoMapper.findPublicTodosByClientNum(clientNum);
//    }
//
//    @Override
//    public List<PersonalTodoQueryDTO> getPrivateTodos(Long clientNum) {
//        return personalTodoMapper.findPrivateTodosByClientNum(clientNum);
//    }

    @Override
    public List<PersonalTodoQueryDTO> getTodosByVisibility(Long clientNum, Boolean isPublic) {
        return personalTodoMapper.findTodosByVisibility(clientNum, isPublic);
    }

}
