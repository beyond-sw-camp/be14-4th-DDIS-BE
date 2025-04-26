package com.DDIS.shareTodo.Command.domain.aggregate.entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "share_todos")
@Getter
@NoArgsConstructor
public class ShareTodo {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long shareTodoNum;

        @Column(nullable = false, length = 255)
        private String shareTodoName; // 할 일 이름

        @Column(nullable = false)
        private Long postNum; // 모집 게시글 번호 (post 테이블의 post_num FK)

        @Column(nullable = false)
        private Integer pinOrder = 0; // 고정 순서 (기본 0)

        @Builder
        public ShareTodo(String shareTodoName, Long postNum, Integer pinOrder) {
            this.shareTodoName = shareTodoName;
            this.postNum = postNum;
            this.pinOrder = pinOrder == null ? 0 : pinOrder; // null이면 기본 0
        }

}
