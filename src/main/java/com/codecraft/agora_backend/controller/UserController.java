package com.codecraft.agora_backend.controller;

import com.codecraft.agora_backend.dto.UserDTO;
import com.codecraft.agora_backend.model.View;
import com.codecraft.agora_backend.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing users.
 */
@Tag(name = "UserController", description = "Controller for managing users")
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
    @Operation(summary = "Get all users", description = "Retrieve a list of all users excluding soft deleted ones")
    @GetMapping("/all")
    @JsonView(View.GetView.class)
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Get all users including soft deleted ones.
     *
     * @return the list of all users including soft deleted ones
     */
    @Operation(summary = "Get all users including soft deleted ones", description = "Retrieve a list of all users including soft deleted ones")
    @GetMapping("/all-including-deleted")
    @JsonView(View.GetView.class)
    public List<UserDTO> getAllUsersIncludingDeleted() {
        return userService.getAllUsersIncludingDeleted();
    }

    /**
     * Get a user by ID.
     *
     * @param id the user ID
     * @return the user with the given ID
     */
    @Operation(summary = "Get user by ID", description = "Retrieve a user by its ID")
    @GetMapping("/{id}")
    @JsonView(View.GetView.class)
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<UserDTO> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Update a user.
     *
     * @param id      the user ID
     * @param userDTO the user data to update
     * @return the updated user
     */
    @Operation(summary = "Update user by ID", description = "Update a user by its ID")
    @PutMapping("/update/{id}")
    @JsonView(View.PostView.class)
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
    @Operation(summary = "Soft delete user by ID", description = "Soft delete a user. The \"deleted\" field of the user is set to true. The user is not removed from the database, and can be reactivated from /reactivate/{id}.")
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
    @Operation(summary = "Hard delete user by ID", description = "Hard delete a user. All user data will not be recoverable.")
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
    @Operation(summary = "Reactivate user by ID", description = "Reactivate a soft deleted user by its ID")
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