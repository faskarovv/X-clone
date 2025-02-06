package org.example.xclone.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class PostDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class response{
        private Long postId;
        private String name;
        private String content;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createRequest{
        private String name;
        private String content;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class updateRequest{
        private String name;
        private String content;
    }


}
