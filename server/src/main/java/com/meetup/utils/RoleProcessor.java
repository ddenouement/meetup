package com.meetup.utils;

import com.meetup.entities.Role;
import com.meetup.entities.User;
import com.meetup.entities.dto.UserDTO;

import java.util.List;

/**.
 * Service, used to manage user roles.
 */
public final class RoleProcessor {

    /**.
     * Private constructor. (Utility class)
     */
    private RoleProcessor() {

    }

    /**
     * Get a string representing user's role.
     * @param user user to get role for
     * @return a string for role
     */
    public static String getRoleString(final User user) {
        List<Role> roles = user.getRoles();
        if (roles.contains(Role.ADMIN)) {
            return Role.ADMIN.name();
        } else if (roles.contains(Role.SPEAKER)) {
            return Role.SPEAKER.name();
        } else if (roles.contains(Role.LISTENER)) {
            return Role.LISTENER.name();
        } else {
            return "UNKNOWN";
        }
    }

    /**.
     * Returns true, if user is speaker.
     *
     * @param user User, used to check if it is speaker
     * @return boolean
     */
    public static boolean isSpeaker(final User user) {
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role == Role.SPEAKER) {
                return true;
            }
        }
        return false;
    }

    /**.
     *
     * @param user UserDTO
     * @return boolean
     */
    public static boolean isSpeaker(final UserDTO user) {
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role == Role.SPEAKER) {
                return true;
            }
        }
        return false;
    }
}
