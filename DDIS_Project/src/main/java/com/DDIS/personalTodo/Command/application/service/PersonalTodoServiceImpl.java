package com.DDIS.personalTodo.Command.application.service;

import com.DDIS.personalCategory.Command.domain.repository.PersonalCategoryRepository;
import com.DDIS.personalTodo.Command.application.dto.request.CreateType;
import com.DDIS.personalTodo.Command.application.dto.request.PersonalTodoCreateRequestDTO;
import com.DDIS.personalTodo.Command.application.dto.request.RepeatInfo;
import com.DDIS.personalTodo.Command.domain.aggregate.PersonalTodoDate;
import com.DDIS.personalTodo.Command.domain.aggregate.PersonalTodoDateId;
import com.DDIS.personalTodo.Command.domain.aggregate.PersonalTodos;
import com.DDIS.personalTodo.Command.domain.repository.PersonalTodoDateRepository;
import com.DDIS.personalTodo.Command.domain.repository.PersonalTodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonalTodoServiceImpl implements PersonalTodoService {

    private final PersonalTodoRepository personalTodoRepository;
    private final PersonalTodoDateRepository personalTodoDateRepository;
    private final PersonalCategoryRepository personalCategoryRepository;

    @Override
    public void createPersonalTodo(PersonalTodoCreateRequestDTO requestDTO, Long clientNum) {
        CreateType createType = requestDTO.getCreateType();

        // personalCategoryNum 소유자 검증 추가
        if (requestDTO.getPersonalCategoryNum() != null) {
            boolean isValidCategory = personalCategoryRepository.existsByPersonalCategoryNumAndClientNum(
                    requestDTO.getPersonalCategoryNum(), clientNum
            );
            if (!isValidCategory) {
                throw new IllegalArgumentException("해당 카테고리는 존재하지 않거나 사용자에게 속하지 않습니다.");
            }
        }

        // todo 저장 (공통)
        PersonalTodos personalTodos = PersonalTodos.builder()
                .clientNum(clientNum)
                .todoContent(requestDTO.getTodoContent())
                .personalCategoryNum(requestDTO.getPersonalCategoryNum())
                .build();

        personalTodoRepository.save(personalTodos);

        // 생성 타입별로 날짜 저장
        switch (createType) {
            case SINGLE -> createSingleTodo(requestDTO, personalTodos);
            case MULTI -> createMultiTodo(requestDTO, personalTodos);
            case REPEAT -> createRepeatTodo(requestDTO, personalTodos);
            default -> throw new IllegalArgumentException("알 수 없는 생성 타입입니다.");
        }
    }

    // 생성 타입별로 메서드 분리
    private void createSingleTodo(PersonalTodoCreateRequestDTO requestDTO, PersonalTodos personalTodos) {
        saveTodoDate(requestDTO.getTodoDate(), personalTodos, false, requestDTO.getIsPublic(), requestDTO.getPinOrder());
    }

    private void createMultiTodo(PersonalTodoCreateRequestDTO requestDTO, PersonalTodos personalTodos) {
        for (String date : requestDTO.getTodoDates()) {
            saveTodoDate(date, personalTodos, false, requestDTO.getIsPublic(), requestDTO.getPinOrder());
        }
    }

    private void createRepeatTodo(PersonalTodoCreateRequestDTO requestDTO, PersonalTodos personalTodos) {
        RepeatInfo repeatInfo = requestDTO.getRepeatInfo();
        List<String> repeatDates = RepeatDateCalculator.calculate(repeatInfo);
        for (String date : repeatDates) {
            saveTodoDate(date, personalTodos, false, requestDTO.getIsPublic(), requestDTO.getPinOrder());
        }
    }

    // repository에 저장 메서드
    private void saveTodoDate(String date, PersonalTodos personalTodos, boolean isDone, boolean isPublic, int pinOrder) {
        PersonalTodoDate todoDate = PersonalTodoDate.builder()
                .todoDate(date)
                .todoNum(personalTodos.getTodoNum())
                .isDone(isDone)
                .isPublic(isPublic)
                .pinOrder(pinOrder)
                .build();

        personalTodoDateRepository.save(todoDate);
    }
}
