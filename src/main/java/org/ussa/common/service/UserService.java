package org.ussa.common.service;

import javax.jws.WebService;
import java.util.List;

import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.ussa.common.model.User;

/**
 * Web Service interface so hierarchy of Universal and Generic Managers isn't carried through.
 */
@WebService
public interface UserService {
    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    User getUser(String userId);

    /**
     * Finds a user by their username.
     * @param username the user's username used to login
     * @return User a populated user object
     * @throws org.acegisecurity.userdetails.UsernameNotFoundException
     *         exception thrown when user not found
     */
    User getUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Retrieves a list of users, filtering with parameters on a user object
     * @param user parameters to filter on
     * @return List
     */
    List<User> getUsers(User user);

    /**
     * Saves a user's information
     *
     * @param user the user's information
     * @throws org.ussa.common.service.UserExistsException thrown when user already exists
     * @return updated user
     */
    User saveUser(User user) throws UserExistsException;

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeUser(String userId);
}
