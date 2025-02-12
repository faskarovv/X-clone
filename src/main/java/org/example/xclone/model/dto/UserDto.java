package org.example.xclone.model.dto;

import lombok.*;
import org.example.xclone.model.entity.Role;

@Data
@Builder
@NoArgsConstructor
public class UserDto {


    @Data
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class response{
       private Long id;
       private String username;
       private String email;

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class createRequest{
        private String username;
        private String password;
        private String email;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class updateRequest{
        private String username;
        private String password;
        private String email;

    }


}
