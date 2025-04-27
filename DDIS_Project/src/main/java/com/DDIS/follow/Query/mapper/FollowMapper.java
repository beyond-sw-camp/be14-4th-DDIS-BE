package com.DDIS.follow.Query.mapper;

import com.DDIS.follow.Query.dto.FollowQueryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface FollowMapper {

    List<FollowQueryDTO> findAllFollowersByFollowerNum(@Param("clientNum") Long clientNum);

    List<FollowQueryDTO> findAllFollowingsByFollowingNum(@Param("clientNum") Long clientNum);
}
