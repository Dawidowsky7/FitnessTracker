package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller exposing the User CRUD API under {@code /v1/users}.
 *
 * <ul>
 *   <li>GET /v1/users - list all users (basic info: id, firstName, lastName)</li>
 *   <li>GET /v1/users/{id} - get full user details by ID</li>
 *   <li>POST /v1/users - create a new user</li>
 *   <li>DELETE /v1/users/{id} - delete a user by ID</li>
 *   <li>PUT /v1/users/{id} - update a user by ID</li>
 *   <li>GET /v1/users/search/email?email={fragment} - search users by email fragment</li>
 *   <li>GET /v1/users/search/older-than?date={date} - find users older than a given date</li>
 * </ul>
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    /**
     * Lists all users with basic information (ID, first name, last name).
     *
     * @return list of {@link UserSimpleDto}
     */
    @GetMapping
    public List<UserSimpleDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    /**
     * Retrieves full details of a single user by ID.
     *
     * @param id user identifier
     * @return the full {@link UserDto}
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Creates a new user.
     *
     * @param userDto request body containing user data
     * @return the created {@link UserDto} with assigned ID
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) {
        User created = userService.createUser(userMapper.toEntity(userDto));
        return userMapper.toDto(created);
    }

    /**
     * Deletes a user by ID.
     *
     * @param id user identifier
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    /**
     * Updates an existing user by ID.
     *
     * @param id      user identifier
     * @param userDto request body with fields to update (null fields are ignored)
     * @return the updated {@link UserDto}
     */
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User updated = userService.updateUser(id, userDto);
        return userMapper.toDto(updated);
    }

    /**
     * Searches users by email fragment (case-insensitive, partial match).
     * Returns only ID and email of matching users.
     *
     * @param email email fragment to search for
     * @return list of {@link UserEmailDto}
     */
    @GetMapping("/search/email")
    public List<UserEmailDto> searchByEmail(@RequestParam String email) {
        return userService.findUsersByEmailContaining(email)
                .stream()
                .map(userMapper::toEmailDto)
                .toList();
    }

    /**
     * Returns users who are older than the specified age threshold,
     * i.e. born before the given date.
     *
     * @param date reference date in {@code yyyy-MM-dd} format
     * @return list of full {@link UserDto} for matching users
     */
    @GetMapping("/search/older-than")
    public List<UserDto> searchOlderThan(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return userService.findUsersOlderThan(date)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}
