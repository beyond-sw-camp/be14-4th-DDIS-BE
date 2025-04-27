package com.DDIS.personalCategory.Command.domain.repository;

import com.DDIS.personalCategory.Command.domain.aggregate.PersonalCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalCategoryRepository extends JpaRepository<PersonalCategories, Long> {

    boolean existsByClientNumAndPersonalCategoryName(Long clientNum, String personalCategoryName);
}
