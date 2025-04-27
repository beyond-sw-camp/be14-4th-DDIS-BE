package com.DDIS.inquiry.Query.dao;

import com.DDIS.inquiry.Query.dto.InquiryQueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryMapper {

    List<InquiryQueryDTO> selectAllInquiry();

    InquiryQueryDTO selectInquiryByNum(Long noticeNum);
}
