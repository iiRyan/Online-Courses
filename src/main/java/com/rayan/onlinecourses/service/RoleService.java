package com.rayan.onlinecourses.service;

import com.rayan.onlinecourses.entity.Role;

public interface RoleService {

    Role loadRoleByName(String roleName);

    Role createRole(String roleName);
}
