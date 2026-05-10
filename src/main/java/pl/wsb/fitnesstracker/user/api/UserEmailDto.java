package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * DTO containing user ID and email address.
 * Returned when searching users by email fragment.
 *
 * @param id    user identifier
 * @param email user's email address
 */
public record UserEmailDto(@Nullable Long id, String email) {
}
