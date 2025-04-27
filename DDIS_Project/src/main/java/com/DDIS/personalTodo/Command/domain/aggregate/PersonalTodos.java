package com.DDIS.personalTodo.Command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "personal_todos")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalTodos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_num", nullable = false)
    private Long todoNum;

    @Column(name = "todo_content", nullable = true)
    private String todoContent;

    @Column(name = "personal_category_num", nullable = true)
    private Long personalCategoryNum;

    @Column(name = "client_num", nullable = false)
    private Long clientNum;

    // 카테고리 연결 끊기
    public void detachCategory() {
        this.personalCategoryNum = null;
    }
}
