package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Spring Data JPA repository for {@link User} entities.
 * Provides both derived queries and stream-based default methods.
 */
interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by exact email match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    /**
     * Finds users whose email contains the given fragment, case-insensitive.
     *
     * @param emailFragment part of the email to search
     * @return list of matching users
     */
    default List<User> findByEmailContainingIgnoreCase(String emailFragment) {
        return findAll().stream()
                .filter(user -> user.getEmail() != null &&
                        user.getEmail().toLowerCase().contains(emailFragment.toLowerCase()))
                .toList();
    }

    /**
     * Finds users born before the given date (i.e. older than that date implies).
     *
     * @param date reference date; users born before this date are returned
     * @return list of users with birthdate before the given date
     */
    default List<User> findByBirthdateBefore(LocalDate date) {
        return findAll().stream()
                .filter(user -> user.getBirthdate() != null && user.getBirthdate().isBefore(date))
                .toList();
    }
}
