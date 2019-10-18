package com.meetup.service;

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
