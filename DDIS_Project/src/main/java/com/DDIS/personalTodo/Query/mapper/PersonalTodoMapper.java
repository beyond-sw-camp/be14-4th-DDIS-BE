package com.DDIS.personalTodo.Query.mapper;

import com.DDIS.personalTodo.Query.dto.PersonalTodoQueryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PersonalTodoMapper {

    // 1. 회원별 전체 todo 조회 (카테고리, 일자 포함)
    List<PersonalTodoQueryDTO> findAllByClientNum(@Param("clientNum") Long clientNum);

    // 2. 특정 일자별 특정 회원의 todo 조회
    List<PersonalTodoQueryDTO> findByClientNumAndDate(@Param("clientNum") Long clientNum,
                                              @Param("todoDate") String todoDate);

    // 3. 카테고리 별 특정 회원의 todo 조회
    List<PersonalTodoQueryDTO> findTodosByClientAndCategory(@Param("clientNum") Long clientNum,
                                                    @Param("categoryNum") Long categoryNum);

    // 4. 핀고정되어 있는 특정 회원의 todo 조회
    List<PersonalTodoQueryDTO> findPinnedTodosByClientNum(@Param("clientNum") Long clientNum);

    // 5, 6번 공개/비공개 TODO 조회를 하나로 합치기
    List<PersonalTodoQueryDTO> findTodosByVisibility(@Param("clientNum") Long clientNum, @Param("isPublic") Boolean isPublic);

//    // 5. 공개(true)인 특정 회원의 todo 조회
//    List<PersonalTodoQueryDTO> findPublicTodosByClientNum(@Param("clientNum") Long clientNum);
//
//    // 6. 비공개(false)인 특정 회원의 todo 조회
//    List<PersonalTodoQueryDTO> findPrivateTodosByClientNum(@Param("clientNum") Long clientNum);
}