package com.sotatek.codingtest.domain.dto;

import com.sotatek.codingtest.domain.entity.PostDetail;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDetailDTO implements Comparable<PostDetailDTO> {
    private String suburbNames;

    private String postcodes;

    public PostDetailDTO(PostDetail postDetail) {
        this.suburbNames = postDetail.getSuburbNames();
        this.postcodes = postDetail.getPostcodes();
    }



    @Override
    public int compareTo(PostDetailDTO o) {
        return this.suburbNames.compareTo(o.getSuburbNames());
    }
}
