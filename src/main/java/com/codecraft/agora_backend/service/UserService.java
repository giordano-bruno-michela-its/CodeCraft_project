package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.dto.UserDTO;
import com.codecraft.agora_backend.model.Role;
import com.codecraft.agora_backend.model.User;
import com.codecraft.agora_backend.repository.RoleRepository;
import com.codecraft.agora_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class for User entity.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    /**
     * Constructor for UserService.
     *
     * @param userRepository UserRepository
     * @param roleRepository RoleRepository
     */
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Get all users that are not soft deleted.
     *
     * @return the list of all users
     */
    public List<UserDTO> getAllUsers() {
        return userRepository.findByDeletedFalse().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    /**
     * Get all users including soft deleted ones.
     *
     * @return the list of all users including deleted ones
     */
    public List<UserDTO> getAllUsersIncludingDeleted() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    /**
     * Get a user by ID.
     *
     * @param id the user ID
     * @return the user with the given ID
     */
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToDTO);
    }

    /**
     * Update an existing user.
     *
     * @param id the user ID
     * @param userDTO the user data to update
     * @return the updated user
     */
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (userDTO.getName() != null) {
                user.setName(userDTO.getName());
            }
            if (userDTO.getUsername() != null) {
                user.setUsername(userDTO.getUsername());
            }
            if (userDTO.getEmail() != null) {
                user.setEmail(userDTO.getEmail());
            }
            if (userDTO.getRoles() != null) {
                Set<Role> roles = userDTO.getRoles().stream()
                        .map(roleName -> roleRepository.findByName(roleName)
                                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                        .collect(Collectors.toSet());
                user.setRoles(roles);
            }
            return convertToDTO(userRepository.save(user));
        }
        return null;
    }

    /**
     * Soft delete a user by setting the deleted flag to true.
     *
     * @param id the user ID
     */
    public void deleteUser(Long id) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser.ifPresent(user -> {
            if (!user.getUsername().equals(currentUsername)) {
                user.setDeleted(true);
                userRepository.save(user);
            } else {
                throw new RuntimeException("You cannot delete yourself.");
            }
        });
    }

    /**
     * Hard delete a user by removing the user from the database.
     *
     * @param id the user ID
     */
    public void hardDeleteUser(Long id) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser.ifPresent(user -> {
            if (!user.getUsername().equals(currentUsername)) {
                userRepository.deleteById(id);
            } else {
                throw new RuntimeException("You cannot delete yourself.");
            }
        });
    }

    /**
     * Reactivate a soft deleted user by setting the deleted flag to false.
     *
     * @param id the user ID
     */
    public void reactivateUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser.ifPresent(user -> {
            user.setDeleted(false);
            userRepository.save(user);
        });
    }

    /**
     * Convert a User entity to a UserDTO.
     *
     * @param user the user entity
     * @return the user data transfer object
     */
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setDeleted(user.isDeleted());
        userDTO.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
        return userDTO;
    }

    /**
     * Convert a UserDTO to a User entity.
     *
     * @param userDTO the user data transfer object
     * @return the user entity
     */
    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setDeleted(userDTO.isDeleted());
        return user;
    }
}