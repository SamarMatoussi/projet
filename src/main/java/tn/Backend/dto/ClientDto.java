package tn.Backend.dto;

import lombok.Getter;
import lombok.Setter;
import tn.Backend.auth.RegisterRequest;
import tn.Backend.entites.Administrateur;
import tn.Backend.entites.Client;

@Getter
@Setter
public class ClientDto extends RegisterRequest {

    public static Client toEntity(ClientDto request) {
        return Client.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .password(request.getPassword())
                .email(request.getEmail())
                .role(request.getRole())
                .phone(request.getPhone())
                .build();
    }


}
