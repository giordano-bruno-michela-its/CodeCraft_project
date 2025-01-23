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

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findByDeletedFalse().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersIncludingDeleted() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToDTO);
    }

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

    public void reactivateUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser.ifPresent(user -> {
            user.setDeleted(false);
            userRepository.save(user);
        });
    }

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

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setDeleted(userDTO.isDeleted());
        return user;
    }
}