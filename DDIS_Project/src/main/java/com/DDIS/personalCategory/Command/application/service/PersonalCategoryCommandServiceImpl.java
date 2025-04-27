package com.DDIS.personalCategory.Command.application.service;

import com.DDIS.personalCategory.Command.application.dto.PersonalCategoryCreateDTO;
import com.DDIS.personalCategory.Command.application.dto.PersonalCategoryUpdateDTO;
import com.DDIS.personalCategory.Command.domain.aggregate.PersonalCategories;
import com.DDIS.personalCategory.Command.domain.aggregate.PersonalCategoryColor;
import com.DDIS.personalCategory.Command.domain.repository.PersonalCategoryRepository;
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
public class PersonalCategoryCommandServiceImpl implements PersonalCategoryCommandService {

    private final PersonalCategoryRepository personalCategoryRepository;
    private final PersonalTodoRepository personalTodoRepository;
    private final PersonalTodoDateRepository personalTodoDateRepository;

    private static final String DEFAULT_COLOR = "rgba(80, 212, 198, 1)";

    // 1. 카테고리 생성
    @Override
    public Long createCategory(Long clientNum, PersonalCategoryCreateDTO requestDTO) {
        String trimmedName = requestDTO.getTrimmedName();
        validateDuplicateName(clientNum, trimmedName);

        String colorRgb = requestDTO.getColor() != null
                ? requestDTO.getColor().getRgba() // 바로 enum에서 꺼낸다
                : DEFAULT_COLOR;

        PersonalCategories category = new PersonalCategories(trimmedName, clientNum, colorRgb);
        return personalCategoryRepository.save(category).getPersonalCategoryNum();
    }

    // 2. 카테고리 수정
    @Override
    public void updateCategory(Long clientNum, Long categoryId, PersonalCategoryUpdateDTO requestDTO) {
        PersonalCategories category = personalCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));

        String trimmedName = requestDTO.getTrimmedName();

        if (!category.getPersonalCategoryName().equals(trimmedName)) {
            validateDuplicateName(clientNum, trimmedName);
        }

        String colorRgb = requestDTO.getColor() != null
                ? requestDTO.getColor().getRgba()
                : category.getPersonalCategoryColorRgb();

        category.updateCategory(trimmedName, colorRgb);
    }

    // 3. 카테고리 삭제
    @Override
    public void deleteCategory(Long categoryId, String action) {
        PersonalCategories category = personalCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));

        List<PersonalTodos> relatedTodos = personalTodoRepository.findAllByPersonalCategoryNum(categoryId);
        System.out.println("relatedTodos.size() = " + relatedTodos.size());

        // 관련 todo 삭제
        if ("delete".equalsIgnoreCase(action)) {
            // delete일 때만 personal_todo_date 삭제
            for (PersonalTodos todo : relatedTodos) {
                personalTodoDateRepository.deleteAllById_TodoNum(todo.getTodoNum());
            }
            personalTodoRepository.deleteAll(relatedTodos);
            personalTodoRepository.flush();

        } else if ("detach".equalsIgnoreCase(action)) {
            for (PersonalTodos todo : relatedTodos) {
                todo.detachCategory();
                personalTodoRepository.save(todo);
            }
            personalTodoRepository.flush();
        } else {
            throw new IllegalArgumentException("잘못된 action 값입니다. (delete 또는 detach만 허용)");
        }

        personalCategoryRepository.delete(category);
    }

    // --- 내부 유틸 메소드 ---

    private void validateDuplicateName(Long clientNum, String name) {
        if (personalCategoryRepository.existsByClientNumAndPersonalCategoryName(clientNum, name)) {
            throw new IllegalArgumentException("이미 존재하는 카테고리 이름입니다.");
        }
    }

    private String extractColorRgb(String color) {
        if (color != null) {
            try {
                return PersonalCategoryColor.valueOf(color).getRgba();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("존재하지 않는 색상입니다.");
            }
        }
        return DEFAULT_COLOR;
    }
}
