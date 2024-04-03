package tn.Backend.dto;

import lombok.Getter;
import lombok.Setter;
import tn.Backend.auth.RegisterRequest;
import tn.Backend.entites.Administrateur;
import tn.Backend.entites.Agent;
import tn.Backend.entites.Role;

@Getter
@Setter
public class AgentDto extends RegisterRequest {

    public static Agent toEntity(AgentDto request) {
        return Agent.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .password(request.getPassword())
                .email(request.getEmail())
                .role(request.getRole())
                .phone(request.getPhone())
                .build();
    }
    public static AgentDto fromEntity(Agent request) {
        if (request == null) {
            return null;
        }

        return (AgentDto) AgentDto.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .role(request.getRole())
                .phone(request.getPhone())
                .build();
    }

    /* private Long id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String phone;
    private Role role;

   public static AgentDto fromEntity(Agent request) {
        if (request == null) {
            return null;
        }

        return (AgentDto) AgentDto.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .role(request.getRole())
                .phone(request.getPhone())
                .build();
    }

    public static Agent toEntity(AgentDto dto) {
        if (dto == null) {
            return null;
        }

        Agent agent = new Agent();
        agent.setId(dto.getId());
        agent.setFirstname(dto.getFirstname());

        agent.setLastname(dto.getLastname());
        agent.setEmail(dto.getEmail());
        agent.setRole(dto.getRole());
        agent.setPhone(dto.getPhone());

        return agent;
    }*/
}
