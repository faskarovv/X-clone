package org.example.xclone.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.xclone.model.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
public class UserDto {


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class response{
       private Long id;
       private String username;
       private String email;

        public response(long userId, String username, String email, Role role) {

        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class createRequest{
        private String username;
        private String password;
        private String email;
        private Role role;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class updateRequest{
        private String username;
        private String password;
        private String email;
        private Role role;
    }


}
