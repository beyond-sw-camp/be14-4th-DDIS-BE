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

    // 5, 6번 공개/비공개 TODO 조회
    List<PersonalTodoQueryDTO> findTodosByVisibility(@Param("clientNum") Long clientNum,
                                                     @Param("isPublic") Boolean isPublic);

    // 7, 8번 완료/미완료 된 todo 조회
    List<PersonalTodoQueryDTO> findTodosByDoneStatus(@Param("clientNum") Long clientNum,
                                                     @Param("isDone") Boolean isDone);
}