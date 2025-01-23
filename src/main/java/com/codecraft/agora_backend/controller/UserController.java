package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.dto.UserDTO;
import com.codecraft.agora_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing users.
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    /**
     * Constructor for UserController.
     *
     * @param userService User service to be injected into the controller.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all users.
     *
     * @return the list of all users. Soft deleted users are not included.
     */
    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Get all users including soft deleted ones.
     *
     * @return the list of all users including soft deleted ones
     */
    @GetMapping("/all-including-deleted")
    public List<UserDTO> getAllUsersIncludingDeleted() {
        return userService.getAllUsersIncludingDeleted();
    }

    /**
     * Get a user by ID.
     *
     * @param id the user ID
     * @return the user with the given ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<UserDTO> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Update a user.
     *
     * @param id the user ID
     * @param userDTO the user data to update
     * @return the updated user
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Soft delete a user. The "deleted" field of the user is set to true.
     * The user is not removed from the database, and can be reactivated from /reactivate/{id}.
     *
     * @param id the user ID
     * @return no content response
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Hard delete a user. All user data will not be recoverable.
     *
     * @param id the user ID
     * @return no content response
     */
    @DeleteMapping("/hard-delete/{id}")
    public ResponseEntity<Void> hardDeleteUser(@PathVariable Long id) {
        userService.hardDeleteUser(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Reactivate a soft deleted user.
     *
     * @param id the user ID
     * @return no content response
     */
    @PutMapping("/reactivate/{id}")
    public ResponseEntity<Void> reactivateUser(@PathVariable Long id) {
        userService.reactivateUser(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Handle runtime exceptions.
     *
     * @param ex the runtime exception
     * @return the error message
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}