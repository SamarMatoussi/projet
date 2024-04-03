package tn.Backend.services;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.Backend.dto.*;
import tn.Backend.entites.*;
import tn.Backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AdministrateurServiceImpl implements AdministrateurService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher publisher;
    private final EmailService emailService;

    @Override
    public Response addAgent(AgentDto agentDto) {

        boolean userExists = repository.findAll()
                .stream()
                .anyMatch(user -> agentDto.getEmail().equalsIgnoreCase(user.getEmail()));

        if (userExists) {
            return (Response.builder()
                    .responseMessage("User with provided email  already exists!")
                    .build());
        }

        User user;
        User savedUser = null;

        user = new Agent();
        user = AgentDto.toEntity((AgentDto) agentDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.AGENT);
        var saveUsers = repository.save(user);

        // Envoyer un email à l'agent
        sendAgentRegistrationEmail(agentDto, null); // Vous pouvez passer HttpServletRequest si nécessaire

        return Response.builder()
                .responseMessage("register")
                .email(user.getEmail())
                .build();
    }

    private void sendAgentRegistrationEmail(AgentDto agentDto, HttpServletRequest request) {
        // Construire le contenu de l'email pour l'agent
        String subject = "Welcome to our platform";
        String content = "Dear " + agentDto.getFirstname() + ",\n\n"
                + "Thank you for registering as an agent.\n"
                + "Your account has been created successfully.\n"
                + "Below is your login information:\n"
                + "Email: " + agentDto.getEmail() + "\n"
                + "<b>Password: " + agentDto.getPassword() + "</b>\n\n"
                + "Please note that this is your temporary password. "
                + "We strongly recommend that you change your password immediately after logging in for the first time.\n\n"
                + "Please keep this email for your records.\n\n"
                + "Best regards,\nYour Platform Team";

        // Envoyer l'email à l'agent
        EmailDetails emailDetails = EmailDetails.builder()
                .to(agentDto.getEmail())
                .subject(subject)
                .messageBody(content)
                .build();

        emailService.sendMail(emailDetails);
    }


    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public Response revokeAccount(String email, boolean activate) {
        // Recherchez l'utilisateur par email dans votre système
        Optional<User> userOptional = repository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return Response.builder()
                    .responseMessage("Utilisateur non trouvé pour l'email spécifié")
                    .build();
        }

        // Extraire l'utilisateur de l'Optional
        User user = userOptional.get();

        // Révoquez ou activez le compte de l'utilisateur en fonction du boolean "activate"
        user.setEnabled(activate);
        repository.save(user);

        if (activate) {
            return Response.builder()
                    .responseMessage("Le compte de l'utilisateur a été activé avec succès")
                    .email(user.getEmail()) // Inclure l'adresse e-mail de l'utilisateur dans la réponse
                    .build();
        } else {
            return Response.builder()
                    .responseMessage("Le compte de l'utilisateur a été révoqué avec succès")
                    .email(user.getEmail()) // Inclure l'adresse e-mail de l'utilisateur dans la réponse
                    .build();
        }
    }

    @Override
    public List<UserDtoo> getAllUsersExceptAdmin() {
        List<User> allUsers = repository.findAll();
        // Exclure les utilisateurs ayant le rôle ADMINISTRATEUR
        return allUsers.stream()
                .filter(user -> user.getRole() != Role.ADMIN)
                .map(UserDtoo::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Response updateUser(User updatedUser) {
        // Recherchez l'utilisateur par son identifiant
        Optional<User> optionalUser = repository.findById(updatedUser.getId());

        if (optionalUser.isEmpty()) {
            return Response.builder()
                    .responseMessage("Utilisateur non trouvé pour l'identifiant spécifié")
                    .build();
        }

        User existingUser = optionalUser.get();

        // Mettre à jour les informations de l'utilisateur avec les données fournies dans updatedUser
        existingUser.setFirstname(updatedUser.getFirstname());
        existingUser.setLastname(updatedUser.getLastname());
        existingUser.setPhone(updatedUser.getPhone());

        // Vérifier si le mot de passe a été fourni, puis le mettre à jour si nécessaire
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        // Enregistrer les modifications dans la base de données
        repository.save(existingUser);

        return Response.builder()
                .responseMessage("Les informations de l'utilisateur ont été mises à jour avec succès")
                .build();
    }
    @Override
    public Optional<User> findUserById(Long id) {
        return repository.findById(id);
    }

}