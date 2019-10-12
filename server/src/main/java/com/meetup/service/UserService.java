package com.meetup.service;

import com.meetup.entities.User;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**.
 * UserService interface. Working with abstract User
 */
public interface UserService {
    /**.
     *
     * @param user User
     * @return   ResponseEntity<String>
     */
    ResponseEntity<String> registerAsListener(User user);

    /**.
     *
     * @param user User
     * @return   ResponseEntity<String>
     */
    ResponseEntity<String> registerAsSpeaker(User user);

    /**.
     *
     * @param user User
     * @return User
     */
    User updateProfile(User user);

    /**.
     *
     * @param user User
     * @return User
     */
    User changePassword(User user);

    /**.
     *
     * @param login String
     * @return User
     */
    User getProfile(String login);

    /**.
     *
     * @return List<User> of speakers
     * */
    List<User> getAllSpeakers();

}

