package com.DDIS.postCategory.Command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "post_categories")
public class PostCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_num")
    private Long categoryNum;

    @Column
    private String categoryName;

    public void updateCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
