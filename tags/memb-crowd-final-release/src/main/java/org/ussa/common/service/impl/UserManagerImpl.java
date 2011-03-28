package org.ussa.common.service.impl;

import javax.persistence.EntityExistsException;
import java.util.List;

import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataIntegrityViolationException;
import org.ussa.common.dao.UserDao;
import org.ussa.common.model.User;
import org.ussa.common.service.UserExistsException;
import org.ussa.common.service.UserManager;
import org.ussa.common.service.UserService;


/**
 * Implementation of UserManager interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class UserManagerImpl extends UniversalManagerImpl implements UserManager, UserService
{
    private UserDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao the UserDao that communicates with the database
     */
    @Required
    public void setUserDao(UserDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    public User getUser(String userId) {
        return dao.get(new Long(userId));
    }

    /**
     * {@inheritDoc}
     */
    public List<User> getUsers(User user) {
        return dao.getUsers();
    }


    /**
     * {@inheritDoc}
     */
    public User saveUser(User user) throws UserExistsException
	{

        if (user.getId() == null) {
            // if new user, lowercase userId
            user.setUsername(user.getUsername().toLowerCase());
        }

        try {
            return dao.saveUser(user);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        } catch (EntityExistsException e) { // needed for JPA
            e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("User '" + user.getUsername() + "' already exists!");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeUser(String userId) {
        log.debug("removing user: " + userId);
        dao.remove(new Long(userId));
    }

    /**
     * {@inheritDoc}
     * @param username the login name of the human
     * @return User the populated user object
     * @throws UsernameNotFoundException thrown when username not found
     */
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        return (User) dao.loadUserByUsername(username);
    }
}
