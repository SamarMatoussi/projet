package tn.Backend.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.Backend.dto.AdministrateurDto;
import tn.Backend.dto.AgentDto;
import tn.Backend.dto.ClientDto;
import tn.Backend.dto.Response;
import tn.Backend.entites.User;
import tn.Backend.exception.ResourceNotFound;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/registerClient")
  public Response register(
          @RequestBody @Valid ClientDto userRequest,
          HttpServletRequest request
  )  {
    return service.register(userRequest,request);
  }



  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
          @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }



  @PostMapping("/refresh-token")
  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }


}
