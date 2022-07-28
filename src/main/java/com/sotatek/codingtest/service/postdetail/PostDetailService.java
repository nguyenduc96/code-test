package com.sotatek.codingtest.service.postdetail;

import com.sotatek.codingtest.domain.dto.PostDetailDTO;
import com.sotatek.codingtest.domain.entity.PostDetail;
import com.sotatek.codingtest.repository.PostDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostDetailService implements IPostDetailService {
    @Autowired
    private PostDetailRepository postDetailRepository;


    @Override
    public List<PostDetailDTO> findAll() throws Exception {
        List<PostDetailDTO> postDetailDTOList = new ArrayList<>();
        postDetailRepository.getAll().stream().forEach(postDetail -> {
            postDetailDTOList.add(new PostDetailDTO(postDetail));
        });
        return postDetailDTOList;
    }

    @Override
    public PostDetailDTO save(PostDetailDTO postDetailDTO) throws Exception {
        PostDetail postDetail = new PostDetail(postDetailDTO);
        postDetailDTO = new PostDetailDTO(postDetailRepository.save(postDetail));
        return postDetailDTO;
    }

    @Override
    public List<PostDetailDTO> findAllByPostcode(List<String> postcodes) throws Exception{
        if (postcodes.size() > 0) {
            List<PostDetail> postDetailList = postDetailRepository.findByPostcodesInOrderBySuburbNames(postcodes);
            List<PostDetailDTO> postDetailDTOList = new ArrayList<>();
            postDetailList.stream().forEach(postDetail -> {
                postDetailDTOList.add(new PostDetailDTO(postDetail));
            });
            postDetailDTOList
                    .stream().sorted(PostDetailDTO::compareTo).collect(Collectors.toList());
            return postDetailDTOList;
        } else {
            return this.findAll();
        }
    }
}
