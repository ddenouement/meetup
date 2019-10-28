package com.meetup.utils;

import com.meetup.entities.User;
import com.meetup.entities.dto.SimpleUserDTO;
import com.meetup.entities.dto.UserDTO;

/**.
 * Class to convert a User exemplar to UserDTO exemplar
 */
public class UserDTOConverter {

    /**
     * . Convert a User exemplar to UserDTO exemplar (e.g. without password)
     *
     * @param us User
     * @return UserDTO
     */
    public UserDTO convertToUserDTO(final User us) {
        UserDTO newUser = new UserDTO();
        newUser.setAbout(us.getAbout());
        newUser.setActive(us.isActive());
        newUser.setEmail(us.getEmail());
        newUser.setId(us.getId());
        newUser.setFirstName(us.getFirstName());
        newUser.setLastName(us.getLastName());
        newUser.setRoles(us.getRoles());
        newUser.setLogin(us.getLogin());
        newUser.setRate(us.getRate());
        newUser.setNumRates(us.getNumRates());
        return newUser;
    }
    /**
     * . Convert a User exemplar to SimpleUserDTO exemplar     *
     * @param us User
     * @return UserDTO
     */
    public SimpleUserDTO convertToSimpleUserDTO(final User us) {
        SimpleUserDTO newUser = new SimpleUserDTO();
        newUser.setId(us.getId());
        newUser.setLastName(us.getLastName());
        newUser.setFirstName(us.getFirstName());
        newUser.setLogin(us.getLogin());
        newUser.setRate(us.getRate());
        newUser.setNumRates(us.getNumRates());
        return newUser;
    }

}
