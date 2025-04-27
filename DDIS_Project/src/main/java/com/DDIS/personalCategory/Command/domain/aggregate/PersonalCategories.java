package com.DDIS.personalCategory.Command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "personal_categories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personalCategoryNum;

    @Column(nullable = false)
    private String personalCategoryName;

    @Column(nullable = false)
    private Long clientNum;

    @Column(nullable = false)
    private String personalCategoryColorRgb;

    public PersonalCategories(String personalCategoryName, Long clientNum, String personalCategoryColorRgb) {
        this.personalCategoryName = personalCategoryName;
        this.clientNum = clientNum;
        this.personalCategoryColorRgb = personalCategoryColorRgb;
    }

    // 수정 메소드
    public void updateCategory(String name, String colorRgb) {
        this.personalCategoryName = name;
        this.personalCategoryColorRgb = colorRgb;
    }
}
