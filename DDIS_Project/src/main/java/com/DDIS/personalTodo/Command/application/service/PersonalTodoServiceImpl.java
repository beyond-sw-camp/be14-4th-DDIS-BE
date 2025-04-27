package com.DDIS.personalTodo.Command.application.service;

import com.DDIS.personalCategory.Command.domain.repository.PersonalCategoryRepository;
import com.DDIS.personalTodo.Command.application.dto.request.CreateType;
import com.DDIS.personalTodo.Command.application.dto.request.PersonalTodoCreateRequestDTO;
import com.DDIS.personalTodo.Command.application.dto.request.PersonalTodoUpdateRequestDTO;
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

    @Override
    public void updatePersonalTodo(PersonalTodoUpdateRequestDTO requestDTO, Long clientNum) {
        // Todo 조회
        PersonalTodos personalTodo = personalTodoRepository.findById(requestDTO.getTodoNum())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Todo입니다."));

        // clientNum 검증
        if (!personalTodo.getClientNum().equals(clientNum)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        // 기존 personalTodoDate 조회 (todoNum + 기존 todoDate)
        PersonalTodoDate personalTodoDate = personalTodoDateRepository.findById(
                        new PersonalTodoDateId(requestDTO.getExistingTodoDate(), requestDTO.getTodoNum()))
                .orElseThrow(() -> new IllegalArgumentException("연결된 날짜 데이터가 없습니다."));

        // todoContent 수정
        if (requestDTO.getTodoContent() != null) {
            personalTodo.updateContent(requestDTO.getTodoContent());
        }

        // isPublic 수정
        if (requestDTO.getIsPublic() != null) {
            personalTodoDate.updateIsPublic(requestDTO.getIsPublic());
        }

        // isDone 수정
        if (requestDTO.getIsDone() != null) {
            personalTodoDate.updateIsDone(requestDTO.getIsDone());
        }

        // todoDate 수정 (날짜 바꾸는 경우)
        if (requestDTO.getNewTodoDate() != null && !requestDTO.getNewTodoDate().equals(requestDTO.getExistingTodoDate())) {
            // 기존 날짜 삭제
            personalTodoDateRepository.deleteById(new PersonalTodoDateId(requestDTO.getExistingTodoDate(), requestDTO.getTodoNum()));
            personalTodoDateRepository.flush();

            // 새 날짜로 저장
            saveTodoDate(
                    requestDTO.getNewTodoDate(),
                    personalTodo,
                    personalTodoDate.isDone(),
                    personalTodoDate.isPublic(),
                    personalTodoDate.getPinOrder() != null ? personalTodoDate.getPinOrder() : 0
            );
        }

        // 핀 고정/해제
        if (requestDTO.getPinOrderUpdate() != null) {
            if (requestDTO.getPinOrderUpdate()) {
                Integer maxPinOrder = personalTodoDateRepository.findMaxPinOrderByClientNum(clientNum);
                personalTodoDate.updatePinOrder(maxPinOrder != null ? maxPinOrder + 1 : 1);
            } else {
                personalTodoDate.updatePinOrder(0);
            }
        }

        personalTodoRepository.saveAndFlush(personalTodo);
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
