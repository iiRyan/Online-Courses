package com.rayan.onlinecourses.service;

import com.rayan.onlinecourses.dao.RoleDao;
import com.rayan.onlinecourses.entity.Role;

public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role loadRoleByName(String roleName) {
        return roleDao.findByName(roleName);
    }

    @Override
    public Role createRole(String roleName) {
        return roleDao.save(new Role(roleName));
    }

}
