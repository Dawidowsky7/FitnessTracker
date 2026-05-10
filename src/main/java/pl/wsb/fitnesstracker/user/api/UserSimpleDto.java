package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * DTO containing only basic user information: ID and full name.
 * Used for listing all users without exposing sensitive details.
 *
 * @param id        user identifier
 * @param firstName user's first name
 * @param lastName  user's last name
 */
public record UserSimpleDto(@Nullable Long id, String firstName, String lastName) {
}
