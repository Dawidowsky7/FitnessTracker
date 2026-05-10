package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface defining read operations for {@link User} entities.
 */
public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their exact email address.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return list of all users
     */
    List<User> findAllUsers();

    /**
     * Searches users by email fragment, case-insensitive.
     *
     * @param emailFragment part of the email address to search for
     * @return list of users whose email contains the given fragment
     */
    List<User> findUsersByEmailContaining(String emailFragment);

    /**
     * Searches users older than the given date (birthdate before the given date).
     *
     * @param date the reference date; users born before this date are returned
     * @return list of users whose birthdate is before the given date
     */
    List<User> findUsersOlderThan(LocalDate date);
}
