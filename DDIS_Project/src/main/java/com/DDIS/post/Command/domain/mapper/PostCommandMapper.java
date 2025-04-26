package com.DDIS.post.Command.domain.mapper;

import com.DDIS.client.Command.domain.aggregate.UserEntity;
import com.DDIS.post.Command.domain.aggregate.dto.PostCreateRequestDTO;
import com.DDIS.post.Command.domain.aggregate.entity.Post;
import com.DDIS.postCategory.Command.domain.aggregate.entity.PostCategoryEntity;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class PostCommandMapper {

    public Post toEntity(PostCreateRequestDTO dto, PostCategoryEntity category, UserEntity client) {
        String now = LocalDate.now().toString();
        return new Post(
                dto.getPostTitle(),
                dto.getPostContent(),
                now,
                dto.getRecruitmentEndDate(),
                dto.getActivityTime(),
                dto.getRecruitmentLimit(),
                dto.getIsPublic(),
                dto.getPostPassword(),
                category,
                client
        );
    }
}

