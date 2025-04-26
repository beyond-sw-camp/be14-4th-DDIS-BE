package com.DDIS.postCategory.Command.domain.repository;

import com.DDIS.postCategory.Command.domain.aggregate.entity.PostCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCategoryRepository extends JpaRepository<PostCategoryEntity, Long> {


}
