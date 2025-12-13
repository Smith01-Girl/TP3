package web2.tp3.service;

import web2.tp3.events.UserDeleteEvent;
import web2.tp3.exceptions.BadRequestException;
import web2.tp3.exceptions.ResourceNotFoundException;
import web2.tp3.model.User;
import web2.tp3.model.UserDTO;
import web2.tp3.model.UserRole;
import web2.tp3.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Transactional
@Service
@AllArgsConstructor
public class UserService {

    private final ValidationUser validationUser;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public List<User> getAllUsers() {
        log.info("**************** Getting all users *****************");
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        log.info("**************** Getting user by id ****************");
        return userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'ID: " + id));
    }

    public User getUserByEmail(String email) {
        log.info("**************** Getting user by email ****************");
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec cet email: "+ email));
    }

    public List<User> getUsersByRole(UserRole role) {
        log.info("**************** Getting users by role ****************");
        return userRepository.findUserByRole(role);
    }


    public void createDefaultUsers() {
        log.info("Création des utilisateurs par défaut...");
        List<User> defaultUsers = List.of(
                createDefaultUser("Grace", "g@gmail.com", "1234", UserRole.ADMIN),
                createDefaultUser("Prynce", "p@gmail.com", "1234", UserRole.JOURNALISTE),
                createDefaultUser("William", "w@gmail.com", "1234", UserRole.JOURNALISTE),
                createDefaultUser("Orianne", "o@gmail.com", "1234", UserRole.INVITE),
                createDefaultUser("Sophie", "s@gmail.com", "1234", UserRole.INVITE)
        );
        userRepository.saveAll(defaultUsers);
        log.info("{} utilisateurs par défaut créés avec succès.", defaultUsers.size());
    }


    private User createDefaultUser(String name, String email, String password, UserRole role) {
        User user = new User();
        user.setNom(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setDateOfBirth(LocalDate.now());
        user.setInscriptionDate(LocalDate.now());

        if (role == UserRole.JOURNALISTE) {
            user.setArticleCount(5);
        }else {
            user.setArticleCount(0);
        }
        return user;
    }

    public User createOneUser(UserDTO userDTO) {

        String erreur = validationUser.valider(userDTO);

        if (!erreur.isEmpty()) {
            throw new BadRequestException(erreur);
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new BadRequestException("Cet email existe déja");
        }

        User user = new User();
        user.setNom(userDTO.getNom());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        user.setDateOfBirth(userDTO.getDateNaissance());
        user.setInscriptionDate(LocalDate.now());
        user.setArticleCount(0);

        return userRepository.save(user);
    }

    public User updateUser(Long id, UserDTO userDTO) {
        log.info("**************** Updating user ****************");

        User user = getUserById(id);

        String erreur = validationUser.valider(userDTO);
        if (!erreur.isEmpty()) {
            throw new BadRequestException(erreur);
        }

        if (!user.getEmail().equals(userDTO.getEmail()) && userRepository.existsByEmail(userDTO.getEmail())) {
            userRepository.existsByEmail(userDTO.getEmail());
            log.warn("Email already exists");
            throw new BadRequestException("Un utilisateur avec cet email existe déja");
        }

        user.setNom(userDTO.getNom());
        user.setEmail(userDTO.getEmail());
        user.setDateOfBirth(userDTO.getDateNaissance());
        user.setRole(userDTO.getRole());

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(userDTO.getPassword());
        }


        log.info("Utilisateur mis a jour avec succes {}", id);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        log.info("**************** Deleting user ****************");
        User user = getUserById(id);
        userRepository.delete(user);
        applicationEventPublisher.publishEvent(new UserDeleteEvent(this, id));
    }

    public void incrementArticleCount(Long userId) {
        log.info("Incrementing the article counter for the user: ", userId);
        User user = getUserById(userId);
        user.setArticleCount(user.getArticleCount() + 1);
        userRepository.save(user);
        log.info("Compteur d'articles mis à jour: {} articles pour l'utilisateur {}",
                user.getArticleCount(), userId);
    }

    public Long countByRole(UserRole role) {
        Long count = userRepository.countByRole(role);
        log.debug("Nombre d'utilisateurs avec le rôle {}: {}", role, count);
        return count;
    }

    public boolean canUserModifyNews(Long userId) {
        log.debug("Checking news editing permissions for: ", userId);
        User user = getUserById(userId);
        boolean canModify = user.getRole() == UserRole.ADMIN ||
                user.getRole() == UserRole.JOURNALISTE;
        log.debug("Utilisateur {} peut modifier nouvelles: {}", userId, canModify);
        return canModify;
    }

    public boolean canUserDeleteNews(Long userId) {
        log.debug("Checking news deletion permissions for: ", userId);
        User user = getUserById(userId);
        boolean canDelete = user.getRole() == UserRole.ADMIN;
        log.debug("Utilisateur {} peut supprimer nouvelles: {}", userId, canDelete);
        return canDelete;
    }

}


