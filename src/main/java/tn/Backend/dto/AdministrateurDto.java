package tn.Backend.dto;


import lombok.Getter;
import lombok.Setter;
import tn.Backend.auth.RegisterRequest;
import tn.Backend.entites.Administrateur;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AdministrateurDto extends RegisterRequest {

    public static Administrateur toEntity(AdministrateurDto request) {
        return Administrateur.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .password(request.getPassword())
                .email(request.getEmail())
                .role(request.getRole())
                .phone(request.getPhone())
                .build();
    }


}
