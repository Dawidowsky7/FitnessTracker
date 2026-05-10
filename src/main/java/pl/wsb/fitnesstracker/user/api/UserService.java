package pl.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user The user to be created
     * @return The created user
     */
    User createUser(User user);

    /**
     * Deletes a user by their ID.
     *
     * @param userId id of the user to delete
     * @throws UserNotFoundException if no user with the given ID exists
     */
    void deleteUser(Long userId);

    /**
     * Updates an existing user.
     *
     * @param userId  id of the user to update
     * @param updated DTO with updated fields
     * @return the updated user
     * @throws UserNotFoundException if no user with the given ID exists
     */
    User updateUser(Long userId, UserDto updated);
}
