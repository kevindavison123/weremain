package com.weremain.fund.wrw.services;

import com.google.common.base.Optional;
import com.weremain.fund.wrw.support.User;

import java.util.List;

/**
 * Created by kevin on 11/15/16.
 */
public interface UserDataManager
{
    /**
     * Find all users in the database
     */
    List<User> findUsers();
    /**
     * Find a single user in the database by email
     */
    Optional<User> findUser(String email);
    /**
     * Save a user to the database
     */
    boolean saveUser(User user);
    /**
     * Delete a user in the database
     */
    boolean deleteUser(String email);

}
