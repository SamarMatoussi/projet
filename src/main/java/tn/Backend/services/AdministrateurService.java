package tn.Backend.services;



import tn.Backend.dto.AgentDto;
import tn.Backend.dto.Response;
import tn.Backend.dto.UserDtoo;
import tn.Backend.entites.User;

import java.util.List;
import java.util.Optional;

public interface AdministrateurService {
    Response addAgent(AgentDto agentDto);
    List<User> getAllUsers();
    Response revokeAccount(String cin, boolean activate);
    List<UserDtoo> getAllUsersExceptAdmin();


    Response updateUser(User updatedUser);

    Optional<User> findUserById(Long id);
}