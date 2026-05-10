package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

/**
 * JPA entity representing a user of the FitnessTracker system.
 */
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Creates a new User.
     *
     * @param firstName user's first name
     * @param lastName  user's last name
     * @param birthdate user's date of birth
     * @param email     user's email address (must be unique)
     */
    public User(
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }

    /**
     * Updates the fields of this user with the provided values.
     * Only non-null values in the provided parameters are applied.
     *
     * @param firstName new first name (or null to keep current)
     * @param lastName  new last name (or null to keep current)
     * @param birthdate new birthdate (or null to keep current)
     * @param email     new email (or null to keep current)
     */
    public void update(String firstName, String lastName, LocalDate birthdate, String email) {
        if (firstName != null) this.firstName = firstName;
        if (lastName != null) this.lastName = lastName;
        if (birthdate != null) this.birthdate = birthdate;
        if (email != null) this.email = email;
    }
}
