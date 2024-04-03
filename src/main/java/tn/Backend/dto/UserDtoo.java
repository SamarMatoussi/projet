
package tn.Backend.dto;

import lombok.Builder;
import lombok.Data;
import tn.Backend.entites.Role;
import tn.Backend.entites.User;


@Data
@Builder
public class UserDtoo {
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private String phone;
    private Role role;

    public static UserDtoo fromEntity(User request) {

        return UserDtoo.builder()
                .id(request.getId())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .role(request.getRole())
                .phone(request.getPhone())
                .build();
    }

   /* public static User toEntity(UserDtoo dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setId(dto.getId());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setPhone(dto.getPhone());

        return user;
    }*/
}
