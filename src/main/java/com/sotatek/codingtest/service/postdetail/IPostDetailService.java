package com.sotatek.codingtest.service.postdetail;

import com.sotatek.codingtest.domain.dto.PostDetailDTO;
import com.sotatek.codingtest.service.IBaseService;

import java.util.List;

public interface IPostDetailService extends IBaseService<PostDetailDTO> {
    List<PostDetailDTO> findAllByPostcode(List<String> postcodes) throws Exception;
}
