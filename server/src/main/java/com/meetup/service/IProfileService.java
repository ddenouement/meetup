package com.meetup.service;

import java.util.Map;

/**.
 * Interface for getting a user Profile as Map
 */
public interface IProfileService {

      Map<Object, Object> getOtherUserProfile(int id);
}
