package com.week6challange.lostandfound;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findByRole(String role);
    Role findByUsers(User user);
    //List<Role> findByUsers(User user);
}
