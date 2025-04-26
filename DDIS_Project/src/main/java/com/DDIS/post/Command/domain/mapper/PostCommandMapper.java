//package com.DDIS.post.Command.domain.mapper;
//
//import com.DDIS.post.Command.domain.aggregate.dto.PostCreateDTO;
//import com.DDIS.post.Command.domain.aggregate.entity.Client;
//import com.DDIS.post.Command.domain.aggregate.entity.Post;
//import com.DDIS.post.Command.domain.aggregate.entity.PostCategory;
//import org.springframework.stereotype.Component;
//
//@Component
//public class PostCommandMapper {
//
//    public Post toEntity(PostCreateDTO dto, PostCategory category, Client client) {
//        return new Post(
//                dto.getPostTitle(),
//                dto.getPostContent(),
//                dto.getRecruitmentStartDate(),
//                dto.getRecruitmentEndDate(),
//                dto.getActivityTime(),
//                dto.getRecruitmentLimit(),
//                dto.getIsPublic(),
//                dto.getPostPassword(),
//                category,
//                client
//        );
//    }
//}
