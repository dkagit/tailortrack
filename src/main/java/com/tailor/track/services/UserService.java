package com.tailor.track.services;

import com.tailor.track.dao.UserDAO;
import com.tailor.track.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDAO userDb;

    UserService(UserDAO userDAO) {
        this.userDb = userDAO;
    }
    public User saveUser(User user) {
        return userDb.save(user);
    }

    public List<User> getAllUsers() {
        return userDb.getAllUsers();
    }
    public void removeUser(String userId)
    {
        userDb.deleteUser(userId);
    }
}
