package tn.Backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.Backend.dto.AgentDto;
import tn.Backend.dto.Response;
import tn.Backend.dto.UserDtoo;
import tn.Backend.entites.User;
import tn.Backend.services.AdministrateurService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdministrateurController {
    private final AdministrateurService administrateurService;



    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addagent")
    public Response addAgent(
            @RequestBody @Valid AgentDto userRequest

    )  {
        return administrateurService.addAgent(userRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/revokeaccount")
    public ResponseEntity<Response> revokeAccount(@RequestParam String email, @RequestParam boolean activate) {
        Response response = administrateurService.revokeAccount(email, activate);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/usersexceptadmin")
    public ResponseEntity<List<UserDtoo>> getAllUsersExceptAdmin() {
        List<UserDtoo> users = administrateurService.getAllUsersExceptAdmin();
        return ResponseEntity.ok(users);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/updateuser")
    public ResponseEntity<Response> updateUser(@RequestBody User user) {
        Response response = administrateurService.updateUser(user);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/{Id}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        Optional<User> userOptional = administrateurService.findUserById(userId);
        return userOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

/*

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addagent")
    public ResponseEntity<Response> addAgent(@RequestBody @Valid AgentDto agentDto) {
        Response response = administrateurService.addAgent(agentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/revokeaccount")
    public ResponseEntity<Response> revokeAccount(@RequestParam String email, @RequestParam boolean activate) {
        Response response = administrateurService.revokeAccount(email, activate);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAuthority('ADMIN')")

    @GetMapping("/usersexceptadmin")
    public ResponseEntity<List<User>> getAllUsersExceptAdmin() {
        List<User> users = administrateurService.getAllUsersExceptAdmin();
        return ResponseEntity.ok(users);
    }
    @PreAuthorize("hasAuthority('ADMIN')")

    @PutMapping("/updateuser")
    public ResponseEntity<Response> updateUser(@RequestBody UserDto userDto) {
        Response response = administrateurService.updateUser(userDto);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAuthority('ADMIN')")

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        Optional<User> userOptional = administrateurService.findUserById(userId);
        return userOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
*/
