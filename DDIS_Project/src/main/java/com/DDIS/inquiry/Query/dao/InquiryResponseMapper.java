package com.DDIS.inquiry.Query.dao;

import com.DDIS.inquiry.Query.dto.InquiryQueryDTO;
import com.DDIS.inquiry.Query.dto.InquiryResponseQueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryResponseMapper {
    // 답변 전체 조회
    List<InquiryResponseQueryDTO> selectAllResponses();

    // 특정 답변 조회 (by response_num)
    InquiryResponseQueryDTO selectResponse(Long inquiryNum);
}
