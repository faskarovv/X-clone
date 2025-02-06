package org.example.xclone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class CommentDto {


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class response{
        private Long commentId;
        private String content;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createRequest{
        private String content;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class updateRequest{
        private String content;
    }


}
