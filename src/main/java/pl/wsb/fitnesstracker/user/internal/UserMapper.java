package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;

/**
 * Component responsible for mapping between {@link User} entity and its various DTO representations.
 */
@Component
class UserMapper {

    /**
     * Converts a {@link User} entity to a full {@link UserDto}.
     *
     * @param user the user entity
     * @return the corresponding DTO
     */
    UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail()
        );
    }

    /**
     * Converts a {@link UserDto} to a {@link User} entity (for creation).
     *
     * @param userDto the DTO
     * @return the corresponding entity
     */
    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email()
        );
    }

    /**
     * Converts a {@link User} entity to a {@link UserSimpleDto} (basic info only).
     *
     * @param user the user entity
     * @return the simple DTO
     */
    UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getId(), user.getFirstName(), user.getLastName());
    }

    /**
     * Converts a {@link User} entity to a {@link UserEmailDto} (ID + email).
     *
     * @param user the user entity
     * @return the email DTO
     */
    UserEmailDto toEmailDto(User user) {
        return new UserEmailDto(user.getId(), user.getEmail());
    }
}
