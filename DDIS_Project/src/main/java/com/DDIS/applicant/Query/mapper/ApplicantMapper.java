package com.DDIS.applicant.Query.mapper;

import com.DDIS.applicant.Query.dto.ApplicantResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApplicantMapper {

    List<ApplicantResponseDTO> findApplicantsByPostNum(@Param("postNum") Long postNum);

    boolean existsPost(@Param("postNum") Long postNum);
}
