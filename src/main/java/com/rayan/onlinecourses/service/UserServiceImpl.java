package com.rayan.onlinecourses.service;

import com.rayan.onlinecourses.dao.RoleDao;
import com.rayan.onlinecourses.dao.UserDao;
import com.rayan.onlinecourses.entity.Role;
import com.rayan.onlinecourses.entity.User;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public User loadUserBuEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User createUser(String email, String password) {
        return userDao.save(new User(email, password));
    }

    @Override
    public void assignRoleToUser(String email, String roleName) {
        User user = userDao.findByEmail(email);
        Role role = roleDao.findByName(roleName);

        user.assignRoleToUser(role);
    }

}
